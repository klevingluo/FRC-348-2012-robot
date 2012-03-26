/*
 * contains all code for interacting with the drivetrain
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
    
        public static final double 
            FP_ACTIVATION_THRESHOLD = 0.7,
            ACCELERATION_THRESHOLD  = 0.03,
            EQUALIZATION_THRESHOLD  = 0.1,
            SLOW_DRIVE_FACTOR       = 0.4;
    
        private static Jaguar rightDriveCIMs    = new Jaguar(RobotMap.rightDriveCIMs);
        private static Jaguar rightDriveFP      = new Jaguar(RobotMap.rightDriveFP);  
        private static Jaguar leftDriveCIMs     = new Jaguar(RobotMap.leftDriveCIMs);
        private static Jaguar leftDriveFP       = new Jaguar(RobotMap.leftDriveFP);
        private static Solenoid puck            = new Solenoid(RobotMap.puck);
        private static DigitalOutput leftCIMBrakes = new DigitalOutput(RobotMap.leftCIMBrakes);
        private static DigitalOutput rightCIMBrakes = new DigitalOutput(RobotMap.rightCIMBrakes);
        /*
         * the values currently being sent to the jaguars
         */
        public static double leftOutput = 0;
        public static double rightOutput = 0;
        
        private static boolean isInSlowMode = false;
        
        /*
         * a safer method that handles driving
         */
        public static void drive(double leftInput, double rightInput) {

            double leftPower = leftInput;
            double rightPower = rightInput;

            if (isInSlowMode){
                leftPower *= 0.4;
                rightPower *= 0.4;
            }

            /*
            * slows down acceleration if joystick input changes too quickly near position 0
            * is active only if joystick positions were previously less than 0 and both motors
            * are moving in the same direction
            */       
            if(leftPower > 0 && leftOutput <=0 && leftPower > 0 == rightPower > 0) {
                    if(Math.abs(leftPower - leftOutput) > ACCELERATION_THRESHOLD) {
                        if(leftPower < leftOutput) {
                            leftPower = leftOutput - ACCELERATION_THRESHOLD;
                        } else {
                            leftPower = leftOutput + ACCELERATION_THRESHOLD;
                        }
                    }
                    if(Math.abs(rightPower - leftOutput) > ACCELERATION_THRESHOLD){
                        if (rightPower < leftOutput) {
                            rightPower = leftOutput - ACCELERATION_THRESHOLD;
                        } else {
                            rightPower = leftOutput + ACCELERATION_THRESHOLD;
                        }
                    }
            }

            /*
            * if the difference of the 2 power values are within the threshold, sets both to their average
            */
            if (Math.abs(leftPower - rightPower) < EQUALIZATION_THRESHOLD) {
                double averagePower = (leftPower + rightPower)/2;
                leftPower = averagePower;
                rightPower = averagePower;
            }

            leftOutput = leftPower;
            leftOutput = rightPower;

            // runs the FP motors if robot is not turning and puck is not deployed
            if (Math.abs(leftPower) > FP_ACTIVATION_THRESHOLD && (leftPower > 0 == rightPower > 0) && !Drivetrain.isPucklowered()) {
                Drivetrain.driveLeftFP(leftPower);
            } else {
                Drivetrain.driveLeftFP(0);
            }
            if (Math.abs(rightPower) > FP_ACTIVATION_THRESHOLD && (leftPower > 0 == rightPower > 0) && !Drivetrain.isPucklowered()) {
                Drivetrain.driveRightFP(rightPower);
            } else {
                Drivetrain.driveRightFP(0);
            }
            // runs the Cims
            Drivetrain.driveCIMs(leftPower, rightPower);

        }
        
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
        
        /*
         * sets fine control mode on or off
         */
        public static void setSlow(boolean on) {
            isInSlowMode = on;
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