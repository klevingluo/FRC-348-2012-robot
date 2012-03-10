/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotHardware;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author team348
 */
public class Drivetrain {
    
        private static Jaguar rightDriveCIMs    = new Jaguar(RobotMap.rightDriveCIMs);
        private static Jaguar rightDriveFP      = new Jaguar(RobotMap.rightDriveFP);  
        private static Jaguar leftDriveCIMs     = new Jaguar(RobotMap.leftDriveCIMs);
        private static Jaguar leftDriveFP       = new Jaguar(RobotMap.leftDriveFP);
        private static Solenoid puck            = new Solenoid(RobotMap.puck);
        private static DigitalOutput leftCIMBrakes = new DigitalOutput(RobotMap.leftCIMBrakes);
        private static DigitalOutput rightCIMBrakes = new DigitalOutput(RobotMap.rightCIMBrakes);
        
        public static void driveCIMs(double leftSpeed, double rightSpeed) {
            leftDriveCIMs.set(leftSpeed);
            rightDriveCIMs.set(rightSpeed);
        }
        
        public static void driveFPs(double leftSpeed, double rightSpeed) {
            leftDriveFP.set(leftSpeed);
            rightDriveFP.set(rightSpeed);
        }
        
        public static void driveLeftCIMs(double speed) {
            leftDriveCIMs.set(speed);
        }
        
        public static void driveRightCIMs(double speed) {
            rightDriveCIMs.set(speed);
        }
        
        public static void driveRightFP(double speed) {
            rightDriveFP.set(speed);
        }
        
        public static void driveLeftFP(double speed) {
            leftDriveFP.set(speed);
        }
        
        public static void brakeLeft() {
            leftCIMBrakes.set(false);
        }
        
        public static void coastLeft() {
            leftCIMBrakes.set(true);
        }
        
        public static void brakeRight() {
            rightCIMBrakes.set(false);
        }
        
        public static void coastRight() {
            rightCIMBrakes.set(true);
        }
        
        public static void lowerPuck(){
            puck.set(true);
        }
        
        public static void raisePuck(){
            puck.set(false);
        }
        
        public static boolean isPucklowered() {
            return puck.get();
        }
}