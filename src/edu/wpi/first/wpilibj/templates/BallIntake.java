/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author team348
 */
public class BallIntake extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private DigitalInput ballSwitch = new DigitalInput(RobotMap.ballSwitch);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void start() {
        
    }
    
    public void stop() {
        
    }
    
    public boolean ballTaken() {
        if (ballSwitch.get()) return true;
        else return false;
    }
}
