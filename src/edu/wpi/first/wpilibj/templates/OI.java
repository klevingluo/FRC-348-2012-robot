package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.templates.commands.LeftDrive;
import edu.wpi.first.wpilibj.templates.commands.RightDrive;

public class OI {
    // Process operator interface input here.
    protected Ps2Controller driver = new Ps2Controller(1);
    protected Ps2Controller operator = new Ps2Controller(2);
    
    protected LeftDrive leftDrive = new LeftDrive(driver.getLeftAnalogY());
    protected RightDrive rightDrive = new RightDrive(driver.getRightAnalogY());
}

