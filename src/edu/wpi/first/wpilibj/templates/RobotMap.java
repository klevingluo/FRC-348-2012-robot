package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    public static final int
        // jaguars
            leftDriveCIM1   = 1,
            leftDriveCIM2   = 3,
            leftDriveBB     = 4,
            rightDriveCIM1  = 5,
            rightDriveCIM2  = 6,
            rightDriveBB    = 7,
            shooterBB       = 8,
        // relays
            compressor      = 1,
            steeringPuck    = 2,
            shooterGate     = 3,
            brakes          = 4,
        // digital inputs
            pressureSwitch  = 1;
    
}
