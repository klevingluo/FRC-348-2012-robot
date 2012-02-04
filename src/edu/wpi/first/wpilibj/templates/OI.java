package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.templates.commands.LeftDrive;
import edu.wpi.first.wpilibj.templates.commands.RightDrive;

public class OI {
    // Process operator interface input here.
    protected Joystick driver = new Joystick(1);
    protected Joystick operator = new Joystick(2);
    
    protected LeftDrive leftDrive = new LeftDrive(driver.getY(GenericHID.Hand.kLeft));
    protected RightDrive rightDrive = new RightDrive(driver.getY(GenericHID.Hand.kRight));
    
    public void test() {
    
        //while(true) {
        //    leftDrive.setX()
        //}
    
    }
    
}

