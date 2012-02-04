/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

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
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void start() {
        compressor.set(Relay.Value.kOn);
    }
    
    public void stop() {
        compressor.set(Relay.Value.kOff);
    }
    
    public boolean isFull() {
        return !pressureSwitch.get();
    }
}
