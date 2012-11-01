#include "Camera.h"
#include "../Robotmap.h"
#include "../Defines.h"
#include "WPILib.h"

Camera::Camera()
	: Subsystem("Camera"),
	  axis(AxisCamera::GetInstance("10.43.43.11")),
	  relay(CAMERA_LIGHT_RELAY)
	{
	printf("Initializing camera.\n");
	axis.WriteResolution(AxisCamera::kResolution_320x240);
	axis.WriteCompression(CAMERA_COMPRESSION);
	axis.WriteBrightness(CAMERA_BRIGHTNESS);
	axis.WriteColorLevel(CAMERA_COLOUR_LEVEL);
	
	lightState = true;
}
    
void Camera::InitDefaultCommand() {
}

vector<double> Camera::getCenterOfMass() {
	vector<double> centers;

	ParticleFilterCriteria2 criteria[] = {
										{IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false, false},
										{IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false, false}
	};

	axis.WriteCompression(0);
	ColorImage *image = axis.GetImage();
	axis.WriteCompression(CAMERA_COMPRESSION);
	
	//Reflective tape HSL threshold.
	Threshold threshold(HMIN, HMAX, SMIN, SMAX, LMIN, LMAX);
	//BinaryImage *thresholdImage = image->ThresholdHSL(threshold);
	BinaryImage *thresholdImage = image->ThresholdHSV(threshold);
    thresholdImage->Write("Image.bmp");
	
	BinaryImage *bigObjectsImage = thresholdImage->RemoveSmallObjects(false, 2);  // remove small objects (noise)
	BinaryImage *convexHullImage = bigObjectsImage->ConvexHull(false);  // fill in partial and full rectangles
	BinaryImage *filteredImage = convexHullImage->ParticleFilter(criteria, 2);  // find the rectangles
	filteredImage->Write("Image2.bmp");
	
	vector<ParticleAnalysisReport> *reports = filteredImage->GetOrderedParticleAnalysisReports();  // get the results
	
	ParticleAnalysisReport largestParticle;
	double highestY = 0;
	
	for(UINT32 i = 0; i < reports->size(); i++)
	{
		ParticleAnalysisReport& par = (reports->at(i));
		
		if (par.center_mass_y_normalized > highestY) {
			largestParticle = par;
			highestY = par.center_mass_y_normalized;
		}
	}
	
	if (highestY != 0) {
	 	centers.push_back(largestParticle.center_mass_x_normalized);
		printf("Center mass X: %i\n", largestParticle.center_mass_x);
		printf("Center mass X normalized: %f\n", largestParticle.center_mass_x_normalized);
	}
	
	printf("\n");
	
	// be sure to delete images after using them
	delete reports;
	delete filteredImage;
	delete convexHullImage;
	delete bigObjectsImage;
	delete thresholdImage;
	delete image;
	
	return centers;
}

void Camera::toggleLight() {
	if (lightState) {
		printf("Setting light off.");
		relay.Set(Relay::kOff);
	}
	else {
		printf("Setting light on.");
		relay.Set(Relay::kOn);
		relay.Set(Relay::kForward);
	}
	
	lightState = !lightState;
}
