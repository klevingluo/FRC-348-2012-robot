/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.command.Subsystem;
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
        
            for(int i = 1; i <= leftDrive.length; i++) {
                
                leftDrive[i] = new CANJaguar(i,CANJaguar.ControlMode.kPercentVbus);
                rightDrive[i] = new CANJaguar(i+3,CANJaguar.ControlMode.kPercentVbus);
                
            }
        
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
