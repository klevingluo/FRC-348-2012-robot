/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import robotHardware.Compressor;
import robotHardware.Drivetrain;
import robotHardware.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    
    /*
     * the value used to equalize the drives if they are moving in the same direction
     * must be between 0 and 1
     * lower values will make the robot veer more easily
     */
    public static double EQUALIZATION_FACTOR = 0.2;
    
    /*
     * if the difference b/w the motor values after the equalization divisor is applied, they are set to be equal.
     * must be between 0 and 2
     * higher values will make the robot drive straighter
     */
    public static double EQUALIZATION_THRESHOLD = 0.1;
    
    /*
     * slows down acceleration if input changes faster than this
     * must be between 0 and 2
     * higher values will make the robot limit acceleration more
     */
    public static double ACCELERATION_THRESHOLD = 0.03;
    
    /*
     * slows down acceleration by this factor if input acceleration exceeds the limit above
     * must be between 0 and 1
     * lower values will make the robot acclerate slower when limiting is in effect.
     */
    public static double ACCELERATION_FACTOR = 0.7;
    
    public static double FP_ACTIVATION_THRESHOLD = 0.7;
    
    private Jaguar rightDrive;
    private Jaguar leftDrive;
    private Jaguar rightDriveFP;
    private Jaguar leftDriveFP;
    private Joystick driverLeft;
    private Joystick driverRight;
    private Joystick operator;
    
    /*
     * the values curently being sent to the jaguars
     */
    private double lastLeftInput = 0;
    private double lastRightInput = 0;
    
    /*
     * the values being sent to the jaguars.
     */
    private double leftOutput = 0;
    private double rightOutput = 0;
    
    public void robotInit() {
        driverLeft = new Joystick(2);
        driverRight = new Joystick(1);
        operator = new Joystick(3);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drive(driverLeft.getY(), driverRight.getY());
        Compressor.run();
    }
    
    public void disabledInit() {
        Compressor.stop();
    }
    
    private void drive(double leftJoystick, double rightJoystick) {
        
        double leftPower = leftJoystick;
        double rightPower = rightJoystick;
        
        /*
         * slows down acceleration if joystick input changes too quickly
         */
        if (leftPower > 0 == rightPower > 0) {
            if(Math.abs(leftPower - lastLeftInput) > ACCELERATION_THRESHOLD)
                if(leftPower < -0.05)
                    leftPower = lastLeftInput - ACCELERATION_THRESHOLD;
                else if (leftPower > 0.05)
                    leftPower = lastLeftInput + ACCELERATION_THRESHOLD;
                else
                    leftPower = 0;
            if(Math.abs(rightPower - lastRightInput) > ACCELERATION_THRESHOLD)
                if (rightPower < -0.05)
                    rightPower = lastRightInput - ACCELERATION_THRESHOLD;
                else if (rightPower > 0.05)
                    rightPower = lastRightInput + ACCELERATION_THRESHOLD;
                else
                    rightPower = 0;
        }
        
         /*
         * if the difference of the 2 power values are within the threshold, sets both to their average
         */
        if (Math.abs(leftPower - rightPower) < EQUALIZATION_THRESHOLD) {
            double averagePower = (leftPower + rightPower)/2;
            leftPower = averagePower;
            rightPower = averagePower;
        }
        
        /*
         * if both drives are moting in the same direction, sets them closer to each other.
         */
        /*if(leftJoystick < 0 == rightJoystick < 0) {
            leftPower -= (leftJoystick - rightJoystick)*EQUALIZATION_FACTOR;
            rightPower -= (rightJoystick - leftJoystick)*EQUALIZATION_FACTOR;
        }*/
        
        lastLeftInput = leftPower;
        lastRightInput = rightPower;
        
        if (Math.abs(leftPower) > FP_ACTIVATION_THRESHOLD) {
            Drivetrain.driveLeftFP(leftPower);
        } else {
            Drivetrain.driveLeftFP(0);
        }
        if (Math.abs(rightPower) > FP_ACTIVATION_THRESHOLD) {
            Drivetrain.driveRightFP(rightPower);
        } else {
            Drivetrain.driveRightFP(0);
        }
        
        Drivetrain.driveCIMs(leftPower, rightPower);
        
        if (operator.getRawButton(7)) {
            Shooter.startShooter(1);
        } else {
            Shooter.stopShooter();
        }

        Shooter.advanceBall(operator.getRawAxis(2), operator.getRawAxis(3));
        
    }
    
}
