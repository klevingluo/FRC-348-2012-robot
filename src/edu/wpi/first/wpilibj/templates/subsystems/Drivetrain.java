/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.LeftDrive;

/**
 *
 * @author team348
 */
public class Drivetrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    CANJaguar[] leftDrive = new CANJaguar[3];
    CANJaguar[] rightDrive = new CANJaguar[3];
    
    public void initDefaultCommand() {
        
        try {
            leftDrive[0] = new CANJaguar(RobotMap.leftDriveCIM1);
            leftDrive[1] = new CANJaguar(RobotMap.leftDriveCIM2);
            leftDrive[2] = new CANJaguar(RobotMap.leftDriveFP);
            rightDrive[0] = new CANJaguar(RobotMap.rightDriveCIM1);
            rightDrive[1] = new CANJaguar(RobotMap.rightDriveCIM2);
            rightDrive[2] = new CANJaguar(RobotMap.rightDriveFP);
        } catch (Exception ex) {    
            ex.printStackTrace();  
        }
        
        setDefaultCommand(new LeftDrive());
    }
    
    public void moveLeft(double x) {
        
        try {  
            for(int i = 0; i < leftDrive.length; i++)          
                leftDrive[i].setX(x);
        } catch (Exception ex) {       
            ex.printStackTrace();   
        }
        
    }
    
        public void moveRight(double x) {
        
        try {
            
            for(int i = 0; i < leftDrive.length; i++)
                rightDrive[i].setX(x);
        
        } catch (Exception ex) {
            
            ex.printStackTrace();
            
        }
        
    }
    
}
