# Wind River Workbench generated Makefile.
# Do not edit!!!
#
# The file ".wrmakefile" is the template used by the Wind River Workbench to
# generate the makefiles of this project. Add user-specific build targets and
# make rules only(!) in this project's ".wrmakefile" file. These will then be
# automatically dumped into the makefiles.

WIND_HOME := $(subst \,/,$(WIND_HOME))
WIND_BASE := $(subst \,/,$(WIND_BASE))
WIND_USR := $(subst \,/,$(WIND_USR))

all : pre_build main_all post_build

_clean ::
	@echo "make: removing targets and objects of `pwd`"

TRACE=0
TRACEON=$(TRACE:0=@)
TRACE_FLAG=$(TRACEON:1=)

MAKEFILE := Makefile

FLEXIBLE_BUILD := 1

BUILD_SPEC = PPC603gnu
DEBUG_MODE = 1
ifeq ($(DEBUG_MODE),1)
MODE_DIR := Debug
else
MODE_DIR := NonDebug
endif
OBJ_DIR := .
WS_ROOT_DIR := C:/Users/Maxim/Documents/Code
PRJ_ROOT_DIR := $(WS_ROOT_DIR)/max2013



#Global Build Macros
PROJECT_TYPE = DKM
DEFINES = 
EXPAND_DBG = 0


#BuildSpec specific Build Macros
VX_CPU_FAMILY = ppc
CPU = PPC603
TOOL_FAMILY = gnu
TOOL = gnu
TOOL_PATH = 
CC_ARCH_SPEC = -mcpu=603 -mstrict-align -mno-implicit-fp -mlongcall
LIBPATH = 
LIBS = 

IDE_INCLUDES = -I$(WIND_BASE)/target/h -I$(WIND_BASE)/target/h/WPILib -I$(WIND_BASE)/target/h/wrn/coreip 

IDE_LIBRARIES = $(WIND_BASE)/target/lib/WPILib.a 

IDE_DEFINES = 



#BuildTool flags
ifeq ($(DEBUG_MODE),1)
DEBUGFLAGS_C-Compiler = -g
DEBUGFLAGS_C++-Compiler = -g
DEBUGFLAGS_Linker = -g
DEBUGFLAGS_Partial-Image-Linker = 
DEBUGFLAGS_Librarian = 
DEBUGFLAGS_Assembler = -g
else
DEBUGFLAGS_C-Compiler = -O2 -fstrength-reduce -fno-builtin
DEBUGFLAGS_C++-Compiler = -O2 -fstrength-reduce -fno-builtin
DEBUGFLAGS_Linker = -O2 -fstrength-reduce -fno-builtin
DEBUGFLAGS_Partial-Image-Linker = 
DEBUGFLAGS_Librarian = 
DEBUGFLAGS_Assembler = -O2 -fstrength-reduce -fno-builtin
endif


#Project Targets
PROJECT_TARGETS = max2013/$(MODE_DIR)/max2013.out \
	 max2013_partialImage/$(MODE_DIR)/max2013_partialImage.o


#Rules

# max2013
ifeq ($(DEBUG_MODE),1)
max2013/$(MODE_DIR)/% : DEBUGFLAGS_C-Compiler = -g
max2013/$(MODE_DIR)/% : DEBUGFLAGS_C++-Compiler = -g
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Linker = -g
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Partial-Image-Linker = 
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Librarian = 
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Assembler = -g
else
max2013/$(MODE_DIR)/% : DEBUGFLAGS_C-Compiler = -O2 -fstrength-reduce -fno-builtin
max2013/$(MODE_DIR)/% : DEBUGFLAGS_C++-Compiler = -O2 -fstrength-reduce -fno-builtin
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Linker = -O2 -fstrength-reduce -fno-builtin
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Partial-Image-Linker = 
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Librarian = 
max2013/$(MODE_DIR)/% : DEBUGFLAGS_Assembler = -O2 -fstrength-reduce -fno-builtin
endif
max2013/$(MODE_DIR)/% : IDE_INCLUDES = -I$(WIND_BASE)/target/h -I$(WIND_BASE)/target/h/WPILib -I$(WIND_BASE)/target/h/wrn/coreip 
max2013/$(MODE_DIR)/% : IDE_LIBRARIES = $(WIND_BASE)/target/lib/WPILib.a 
max2013/$(MODE_DIR)/% : IDE_DEFINES = 
max2013/$(MODE_DIR)/% : PROJECT_TYPE = DKM
max2013/$(MODE_DIR)/% : DEFINES = 
max2013/$(MODE_DIR)/% : EXPAND_DBG = 0
max2013/$(MODE_DIR)/% : VX_CPU_FAMILY = ppc
max2013/$(MODE_DIR)/% : CPU = PPC603
max2013/$(MODE_DIR)/% : TOOL_FAMILY = gnu
max2013/$(MODE_DIR)/% : TOOL = gnu
max2013/$(MODE_DIR)/% : TOOL_PATH = 
max2013/$(MODE_DIR)/% : CC_ARCH_SPEC = -mcpu=603 -mstrict-align -mno-implicit-fp -mlongcall
max2013/$(MODE_DIR)/% : LIBPATH = 
max2013/$(MODE_DIR)/% : LIBS = 
max2013/$(MODE_DIR)/% : OBJ_DIR := max2013/$(MODE_DIR)

OBJECTS_max2013 = max2013_partialImage/$(MODE_DIR)/max2013_partialImage.o

max2013/$(MODE_DIR)/max2013.out : $(OBJECTS_max2013)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@";rm -f "$@";nmppc $(OBJECTS_max2013) $(IDE_LIBRARIES) | tclsh $(WIND_BASE)/host/resource/hutils/tcl/munch.tcl -c ppc > $(OBJ_DIR)/ctdt.c; $(TOOL_PATH)ccppc $(DEBUGFLAGS_Linker) $(CC_ARCH_SPEC) -fdollars-in-identifiers -Wall $(ADDED_CFLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES)  -o $(OBJ_DIR)/ctdt.o -c $(OBJ_DIR)/ctdt.c; $(TOOL_PATH)ccppc -r -nostdlib -Wl,-X -T $(WIND_BASE)/target/h/tool/gnu/ldscripts/link.OUT -o "$@" $(OBJ_DIR)/ctdt.o $(OBJECTS_max2013) $(IDE_LIBRARIES) $(LIBPATH) $(LIBS)  $(ADDED_LIBPATH) $(ADDED_LIBS) && if [ "$(EXPAND_DBG)" = "1" ]; then plink "$@";fi

max2013/$(MODE_DIR)/max2013_compile_file : $(FILE) ;

_clean :: max2013/$(MODE_DIR)/max2013_clean

max2013/$(MODE_DIR)/max2013_clean : 
	$(TRACE_FLAG)if [ -d "max2013" ]; then cd "max2013"; rm -rf $(MODE_DIR); fi


# max2013_partialImage
ifeq ($(DEBUG_MODE),1)
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_C-Compiler = -g
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_C++-Compiler = -g
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Linker = -g
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Partial-Image-Linker = 
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Librarian = 
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Assembler = -g
else
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_C-Compiler = -O2 -fstrength-reduce -fno-builtin
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_C++-Compiler = -O2 -fstrength-reduce -fno-builtin
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Linker = -O2 -fstrength-reduce -fno-builtin
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Partial-Image-Linker = 
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Librarian = 
max2013_partialImage/$(MODE_DIR)/% : DEBUGFLAGS_Assembler = -O2 -fstrength-reduce -fno-builtin
endif
max2013_partialImage/$(MODE_DIR)/% : IDE_INCLUDES = -I$(WIND_BASE)/target/h -I$(WIND_BASE)/target/h/WPILib -I$(WIND_BASE)/target/h/wrn/coreip 
max2013_partialImage/$(MODE_DIR)/% : IDE_LIBRARIES = $(WIND_BASE)/target/lib/WPILib.a 
max2013_partialImage/$(MODE_DIR)/% : IDE_DEFINES = 
max2013_partialImage/$(MODE_DIR)/% : PROJECT_TYPE = DKM
max2013_partialImage/$(MODE_DIR)/% : DEFINES = 
max2013_partialImage/$(MODE_DIR)/% : EXPAND_DBG = 0
max2013_partialImage/$(MODE_DIR)/% : VX_CPU_FAMILY = ppc
max2013_partialImage/$(MODE_DIR)/% : CPU = PPC603
max2013_partialImage/$(MODE_DIR)/% : TOOL_FAMILY = gnu
max2013_partialImage/$(MODE_DIR)/% : TOOL = gnu
max2013_partialImage/$(MODE_DIR)/% : TOOL_PATH = 
max2013_partialImage/$(MODE_DIR)/% : CC_ARCH_SPEC = -mcpu=603 -mstrict-align -mno-implicit-fp -mlongcall
max2013_partialImage/$(MODE_DIR)/% : LIBPATH = 
max2013_partialImage/$(MODE_DIR)/% : LIBS = 
max2013_partialImage/$(MODE_DIR)/% : OBJ_DIR := max2013_partialImage/$(MODE_DIR)

max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBase.o : $(PRJ_ROOT_DIR)/src/Team4343/CommandBase.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBasedRobot.o : $(PRJ_ROOT_DIR)/src/Team4343/CommandBasedRobot.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BallPickupDoNothing.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/BallPickupDoNothing.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeDoNothing.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/BridgeDoNothing.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeRotate.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/BridgeRotate.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultDoNothing.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/CatapultDoNothing.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRelease.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/CatapultRelease.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRotate.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/CatapultRotate.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultWait.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/CatapultWait.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveInASquare.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/DriveInASquare.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveStraight.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/DriveStraight.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithCamera.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/DriveWithCamera.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithGyro.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/DriveWithGyro.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithJoystick.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/DriveWithJoystick.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Fire.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/Fire.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TargetWithCamera.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/TargetWithCamera.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleLight.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/ToggleLight.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TogglePickup.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/TogglePickup.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleSolenoid.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/ToggleSolenoid.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Turn.o : $(PRJ_ROOT_DIR)/src/Team4343/Commands/Turn.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/OI.o : $(PRJ_ROOT_DIR)/src/Team4343/OI.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BallPickup.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/BallPickup.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BridgeArm.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/BridgeArm.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Camera.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Camera.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Catapult.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Catapult.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Chassis.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Chassis.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Gyroscope.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Gyroscope.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/MSKinect.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/MSKinect.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Rangefinder.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Rangefinder.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Solenoids.o : $(PRJ_ROOT_DIR)/src/Team4343/Subsystems/Solenoids.cpp $(FORCE_FILE_BUILD)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc $(DEBUGFLAGS_C++-Compiler) $(CC_ARCH_SPEC) -ansi -Wall  -MD -MP -mlongcall $(ADDED_C++FLAGS) $(IDE_INCLUDES) $(ADDED_INCLUDES) -DCPU=$(CPU) -DTOOL_FAMILY=$(TOOL_FAMILY) -DTOOL=$(TOOL) -D_WRS_KERNEL   $(DEFINES) -o "$@" -c "$<"


OBJECTS_max2013_partialImage = max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBase.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBasedRobot.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BallPickupDoNothing.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeDoNothing.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeRotate.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultDoNothing.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRelease.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRotate.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultWait.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveInASquare.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveStraight.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithCamera.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithGyro.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithJoystick.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Fire.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TargetWithCamera.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleLight.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TogglePickup.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleSolenoid.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Turn.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/OI.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BallPickup.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BridgeArm.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Camera.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Catapult.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Chassis.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Gyroscope.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/MSKinect.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Rangefinder.o \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Solenoids.o

max2013_partialImage/$(MODE_DIR)/max2013_partialImage.o : $(OBJECTS_max2013_partialImage)
	$(TRACE_FLAG)if [ ! -d "`dirname "$@"`" ]; then mkdir -p "`dirname "$@"`"; fi;echo "building $@"; $(TOOL_PATH)ccppc -r -nostdlib -Wl,-X  -o "$@" $(OBJECTS_max2013_partialImage) $(LIBPATH) $(LIBS) $(ADDED_LIBPATH) $(ADDED_LIBS)  && if [ "$(EXPAND_DBG)" = "1" ]; then plink "$@";fi

max2013_partialImage/$(MODE_DIR)/max2013_partialImage_compile_file : $(FILE) ;

_clean :: max2013_partialImage/$(MODE_DIR)/max2013_partialImage_clean

max2013_partialImage/$(MODE_DIR)/max2013_partialImage_clean : 
	$(TRACE_FLAG)if [ -d "max2013_partialImage" ]; then cd "max2013_partialImage"; rm -rf $(MODE_DIR); fi

force : 

DEP_FILES := max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBase.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/CommandBasedRobot.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BallPickupDoNothing.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeDoNothing.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/BridgeRotate.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultDoNothing.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRelease.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultRotate.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/CatapultWait.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveInASquare.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveStraight.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithCamera.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithGyro.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/DriveWithJoystick.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Fire.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TargetWithCamera.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleLight.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/TogglePickup.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/ToggleSolenoid.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Commands/Turn.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/OI.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BallPickup.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/BridgeArm.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Camera.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Catapult.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Chassis.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Gyroscope.d \
	 max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/MSKinect.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Rangefinder.d max2013_partialImage/$(MODE_DIR)/Objects/max2013/src/Team4343/Subsystems/Solenoids.d
-include $(DEP_FILES)



-include $(PRJ_ROOT_DIR)/*.makefile

-include *.makefile

main_all : external_build $(PROJECT_TARGETS)
	@echo "make: built targets of `pwd`"

# entry point for extending the build
external_build ::
	@echo ""

# main entry point for pre processing prior to the build
pre_build :: $(PRE_BUILD_STEP) generate_sources
	@echo ""

# entry point for generating sources prior to the build
generate_sources ::
	@echo ""

# main entry point for post processing after the build
post_build :: $(POST_BUILD_STEP) deploy_output
	@echo ""

# entry point for deploying output after the build
deploy_output ::
	@echo ""

clean :: external_clean $(CLEAN_STEP) _clean

# entry point for extending the build clean
external_clean ::
	@echo ""
