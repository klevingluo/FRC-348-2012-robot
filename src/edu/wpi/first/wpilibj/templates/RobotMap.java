package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

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
            leftDriveCIM1   = 3,
            leftDriveCIM2   = 4,
            leftDriveFP     = 5,
            rightDriveCIM1  = 6,
            rightDriveCIM2  = 7,
            rightDriveFP    = 8,
            shooterBB       = 9,
            intakeRollerBB  = 10,
        // relays
            compressor      = 1,
            steeringPuck    = 2,
            shooterGate     = 3,
            brakes          = 4,
        // digital inputs
            pressureSwitch  = 1,
            ballSwitch      = 2,
    // control elements
            leftDrive       = 2,
            rightDrive      = 3,
            shooter         = 1;
}
