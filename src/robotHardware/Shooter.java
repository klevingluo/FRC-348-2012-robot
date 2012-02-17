/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotHardware;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author team348
 */
public class Shooter {
    
    private static Jaguar lowerConveyor = new Jaguar(RobotMap.lowerBallConveyor);
    private static Jaguar upperConveyor = new Jaguar(RobotMap.upperBallConveyor);
    private static Jaguar shooter = new Jaguar(RobotMap.shooter);
    
    public static void startShooter(double power) {
        shooter.set(power);
    } 
    
    public static void stopShooter() {
        shooter.set(0);
    }
    
    public static void advanceBall(double lowerConveyorPower, double upperConveyorPower) {
        lowerConveyor.set(lowerConveyorPower);
        upperConveyor.set(upperConveyorPower);
    }
    
    public static void stopAdvance() {
        lowerConveyor.set(0);
        upperConveyor.set(0);        
    }
    
    public static void startIntake(double power) {
        lowerConveyor.set(power);
    }
    
    public static void startFeeder(double power) {
        upperConveyor.set(power);
    }
            
}
