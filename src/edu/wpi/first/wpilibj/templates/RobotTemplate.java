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
    
    private Joystick driverLeft;
    private Joystick driverRight;
    private Joystick operator;
    private Timer timer;
    private Compressor compressor = new Compressor(RobotMap.pressureSwitch, RobotMap.compressor);
    
    /*
     * creates all controll objects
     */
    public void robotInit() {
        driverLeft = new Joystick(2);
        driverRight = new Joystick(1);
        operator = new Joystick(3);
        timer = new Timer();
    }
    
    /*
     * autonomous code
     */
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
            Shooter.advanceBall();
            Shooter.runShooter();
        }
        Drivetrain.driveCIMs(0,0);
        Shooter.stopBall();
        Shooter.runShooter();
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
        
        /*
         * drives the robot
         */
        Drivetrain.drive(driverLeft.getY(), driverRight.getY());
        
        /*
         * sets the drivetrain to slowmode if right trigger is deressed
         */
        Drivetrain.setSlow(driverRight.getButton(Joystick.ButtonType.kTrigger));
        
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
            Shooter.runShooter();         
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
        
        // lowers puck if trigger is depressed
        if (driverLeft.getButton(Joystick.ButtonType.kTrigger)) {
            Drivetrain.lowerPuck();
        } else {
            Drivetrain.raisePuck();
        }
        
    }
    
    public void disabledInit() {
        compressor.stop();
        timer.stop();
        timer.reset();
    }

}
