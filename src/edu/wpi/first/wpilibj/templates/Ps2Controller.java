/*
 * simplifies PS2 controller input.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author team348
 */
public class Ps2Controller extends Joystick {
    
    public Ps2Controller(int index) {
        super (index);
    }
    
    public boolean getButton1() {
        return getRawButton(1);
    }
        
    public boolean getButton2() {
        return getRawButton(2);
    }
        
    public boolean getButton3() {
        return getRawButton(3);
    }
        
    public boolean getButton4() {
        return getRawButton(4);
    }
    
    public boolean getTopRightTrigger() {
        return getRawButton(7);
    }
    
    public boolean getBottomRightTrigger() {
        return getRawButton(5);
    }
    
    public boolean getTopLeftTrigger() {
        return getRawButton(6);
    }
     
    public boolean getBottomLeftTrigger() {
        return getRawButton(8);
    }
    
    public boolean getStartButton() {
        return getRawButton(9);
    }
    
    public boolean getSelectButton() {
        return getRawButton(10);
    }
    
    public double getRightAnalogX() {
        return getRawAxis(1);
    }
    
    public double getRightAnalogY() {
        return getRawAxis(3);
    }
    
    public double getLeftAnalogX() {
        return getRawAxis(2);
    }
    
    public double getLeftAnalogY() {
        return getRawAxis(4);
    }
    
    public double getDPadX () {
        return getRawAxis(5);
    }
    
    public double getDPadY () {
        return getRawAxis(6);
    }
}
