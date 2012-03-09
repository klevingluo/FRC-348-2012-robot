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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import robotHardware.Drivetrain;
import robotHardware.RampArm;
import robotHardware.RobotMap;
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
    
    public void robotInit() {
        driverLeft = new Joystick(2);
        driverRight = new Joystick(1);
        operator = new Joystick(3);
        timer = new Timer();
    }
    
    public void autonomousInit() {
        timer.start();
        compressor.start();
        while (timer.get() < 1.3) {
            Drivetrain.driveCIMs(-.4,- .4);
        }
        while (timer.get() < 2.7) {
            Drivetrain.driveCIMs(-0.2, -0.2);
        }
        
        while (timer.get() < 14) {
            Drivetrain.driveCIMs(-0.2, -0.2);
            Shooter.advanceBall(1, 1);
            Shooter.runShooter(0.7);
        }

        Drivetrain.driveCIMs(0,0);
        Shooter.stopAdvance();
        Shooter.runShooter(0);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    public void teleopInit() {
        timer.start();
        compressor.start();
    }
    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        // this is new
        //if (timer.get() > 0.05) {
        //    timer.reset();
        //    timer.start();
            drive(driverLeft.getY(), driverRight.getY());
        //}
        
        if (operator.getRawButton(6) || operator.getRawButton(8)) {
            Shooter.runShooter(.7);         
        } else {
            Shooter.stopShooter();
        }
        
        if(operator.getRawButton(7)) {
            Shooter.startFeeder(1);
        } else if (operator.getRawButton(5)) {
            Shooter.startFeeder(-1);
        } else {
            Shooter.startFeeder(0);
        }
        if (operator.getRawAxis(3) != 0) {
            Shooter.startIntake(operator.getRawAxis(3));
        } else Shooter.startIntake(operator.getRawAxis(6));
                
        // lowers arm if operator signals
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
                    } else if (leftPower > lastLeftInput) {
                        leftPower = lastLeftInput + ACCELERATION_THRESHOLD;
                    }
                }

                if(Math.abs(rightPower - lastRightInput) > ACCELERATION_THRESHOLD){
                    if (rightPower < lastRightInput) {
                        rightPower = lastRightInput - ACCELERATION_THRESHOLD;
                    } else if (rightPower > lastRightInput) {
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
        
        /*
         * if both drives are moting in the same direction, sets them closer to each other.
         */
        /*if(leftJoystick < 0 == rightJoystick < 0) {
            leftPower -= (leftJoystick - rightJoystick)*EQUALIZATION_FACTOR;
            rightPower -= (rightJoystick - leftJoystick)*EQUALIZATION_FACTOR;
        }*/
        
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
        
        // lowers puck if drivetrains are in different directions or button is pressed
        if (driverLeft.getButton(Joystick.ButtonType.kTrigger)) { // || Math.abs(leftPower - rightPower) > 0.3) {
            Drivetrain.lowerPuck();
        } else {
            Drivetrain.raisePuck();
        }

    }

}
