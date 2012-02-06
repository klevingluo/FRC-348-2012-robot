/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author team348
 */
public class Compressor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    protected Relay compressor;
    protected DigitalInput pressureSwitch;
    private boolean on;
    
    public void initDefaultCommand() {
        compressor = new Relay(RobotMap.compressor, Relay.Direction.kForward);
        pressureSwitch = new DigitalInput(RobotMap.pressureSwitch);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void start() {
        on = true;
    }
    
    public void stop() {
        on = false;
    }
    
    
    public boolean isFull() {
        return !pressureSwitch.get();
    }
    
    public void check() {
        if (!isFull() && on == true)
            compressor.set(Relay.Value.kOn);
        else
            compressor.set(Relay.Value.kOff);
    }
}
