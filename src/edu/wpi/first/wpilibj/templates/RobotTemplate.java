/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import robotHardware.Drivetrain;
import robotHardware.RampArm;
import robotHardware.RobotMap;
import robotHardware.Shooter;

/**
 *
 */
public class RobotTemplate extends IterativeRobot {
    
    /*
     * if the difference between the motor values are less than this they are set to be equal.
     * must be between 0 and 2
     */
    public static double EQUALIZATION_THRESHOLD = 0.1;
    
    /*
     * slows down acceleration if input changes faster than this in 1 iteration of the loop
     * must be between 0 and 2
     */
    public static double ACCELERATION_THRESHOLD = 0.03;
    
    /*
     * activates the fisher price motors if power is above this level
     */
    public static double FP_ACTIVATION_THRESHOLD = 0.7;
    
    private Joystick driverLeft;
    private Joystick driverRight;
    private Joystick operator;
    private Timer timer;
    private Compressor compressor = new Compressor(RobotMap.pressureSwitch, RobotMap.compressor);
    
    /*
     * the values curently being sent to the jaguars
     */
    private double lastLeftInput = 0;
    private double lastRightInput = 0;
    
    /*
     * creates all objects
     */
    public void robotInit() {
        driverLeft = new Joystick(2);
        driverRight = new Joystick(1);
        operator = new Joystick(3);
        timer = new Timer();
    }
    
    public void autonomousInit() {
        timer.start();
        compressor.start();
        while (timer.get() < 1.4) {
            Drivetrain.driveCIMs(-.4,- .4);  // CIM outputs and joystick inputs are both backwards
        }
        while (timer.get() < 3.1) {
            Drivetrain.driveCIMs(-0.2, -0.2);
        }
        
        while (timer.get() < 14) {
            Shooter.advanceBall(1, 1);
            Shooter.runShooter(0.7);
        }

        Drivetrain.driveCIMs(0,0);
        Shooter.stopAdvance();
        Shooter.runShooter(0);
    }

    public void teleopInit() {
        timer.start();
        compressor.start();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        /*  uninplemented feature that uses real time in controlling acceleration
        if (timer.get() > 0.05) {
            timer.reset();
            timer.start();
            drive(driverLeft.getY(), driverRight.getY());
        } */
        
        drive(driverLeft.getY(), driverRight.getY());
        
        /*
         * runs brakes if top buttons are depressed
         */
        if (driverRight.getRawButton(3) || driverLeft.getRawButton(3)) {
            Drivetrain.brakeLeft();
            Drivetrain.brakeRight();
        } else {
            Drivetrain.coastLeft();
            Drivetrain.coastRight();
        }
        
        /*
         * runs shooter if operator right shoulder buttons are pressed
         */
        if (operator.getRawButton(6) || operator.getRawButton(8)) {
            Shooter.runShooter(.7);         
        } else {
            Shooter.stopShooter();
        }
        
        /*
         * runs feeder according to operator left shoulder buttons
         */
        if(operator.getRawButton(7)) {
            Shooter.setFeeder(1);
        } else if (operator.getRawButton(5)) {
            Shooter.setFeeder(-1);
        } else {
            Shooter.setFeeder(0);
        }
        
        /*
         * runs intake according to left analog or d-pad input
         */
        if (operator.getRawAxis(3) != 0) {
            Shooter.setIntake(operator.getRawAxis(3));
        } else Shooter.setIntake(operator.getRawAxis(6));
                
        // lowers arm if 3 is depressed
        if (operator.getRawButton(3)) {
            RampArm.lower();
        } else RampArm.raise();
        
    }
    
    public void disabledInit() {
        compressor.stop();
        timer.stop();
        timer.reset();
    }
    
    private void drive(double leftJoystick, double rightJoystick) {
        
        double leftPower = leftJoystick;
        double rightPower = rightJoystick;
        
                
        if (driverRight.getButton(Joystick.ButtonType.kTrigger)){
            leftPower *= 0.4;
            rightPower *= 0.4;
        }
        
        /*
         * slows down acceleration if joystick input changes too quickly
         */       
        if(leftPower > 0 && lastLeftInput <=0) {
        
            if (leftPower > 0 == rightPower > 0) {
                if(Math.abs(leftPower - lastLeftInput) > ACCELERATION_THRESHOLD) {
                    if(leftPower < lastLeftInput) {
                        leftPower = lastLeftInput - ACCELERATION_THRESHOLD;
                    } else {
                        leftPower = lastLeftInput + ACCELERATION_THRESHOLD;
                    }
                }

                if(Math.abs(rightPower - lastRightInput) > ACCELERATION_THRESHOLD){
                    if (rightPower < lastRightInput) {
                        rightPower = lastRightInput - ACCELERATION_THRESHOLD;
                    } else {
                        rightPower = lastRightInput + ACCELERATION_THRESHOLD;
                    }
                }
            }
        }
        
         /*
         * if the difference of the 2 power values are within the threshold, sets both to their average
         */
        if (Math.abs(leftPower - rightPower) < EQUALIZATION_THRESHOLD) {
            double averagePower = (leftPower + rightPower)/2;
            leftPower = averagePower;
            rightPower = averagePower;
        }
        
        lastLeftInput = leftPower;
        lastRightInput = rightPower;
        
        // runs the FP motors
        if (Math.abs(leftPower) > FP_ACTIVATION_THRESHOLD && (leftPower > 0 == rightPower > 0) && !Drivetrain.isPucklowered()) {
            Drivetrain.driveLeftFP(leftPower);
        } else {
            Drivetrain.driveLeftFP(0);
        }
        
        if (Math.abs(rightPower) > FP_ACTIVATION_THRESHOLD && (leftPower > 0 == rightPower > 0) && !Drivetrain.isPucklowered()) {
            Drivetrain.driveRightFP(rightPower);
        } else {
            Drivetrain.driveRightFP(0);
        }
        // runs the Cims
        Drivetrain.driveCIMs(leftPower, rightPower);
        
        // lowers puck if trigger is depressed
        if (driverLeft.getButton(Joystick.ButtonType.kTrigger)) {
            Drivetrain.lowerPuck();
        } else {
            Drivetrain.raisePuck();
        }

    }

}
