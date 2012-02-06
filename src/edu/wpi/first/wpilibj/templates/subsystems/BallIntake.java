/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author team348
 */
public class BallIntake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private CANJaguar intakeRoller;
    private DigitalInput ballSwitch;
    private boolean ballTaken = false;
    
    public void initDefaultCommand() {
        try {
            intakeRoller = new CANJaguar(RobotMap.intakeRollerBB);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        ballSwitch = new DigitalInput(RobotMap.ballSwitch);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    public boolean ballTaken() {
        return ballTaken;
    }
}
