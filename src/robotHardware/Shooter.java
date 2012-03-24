/*
 * cotrols shooter and ball intake
 */
package robotHardware;

import edu.wpi.first.wpilibj.Jaguar;

/**
 *
 * @author team348
 */
public class Shooter {
    
    protected static Jaguar lowerConveyor = new Jaguar(RobotMap.lowerBallConveyor);
    private static Jaguar upperConveyor = new Jaguar(RobotMap.upperBallConveyor);
    private static Jaguar shooter = new Jaguar(RobotMap.shooter);
    
    public static void runShooter(double power) {
        shooter.set(-power);    // shooter is backwards
    } 
    
    public static void stopShooter() {
        shooter.set(0);
    }
    
    public static void advanceBall(double lowerConveyorPower, double upperConveyorPower) {
        lowerConveyor.set(-lowerConveyorPower);     // lower conveyor is bakcwards
        upperConveyor.set(upperConveyorPower);
    }
    
    /*
     * stops both feeder and intake
     */
    public static void stopAdvance() {
        lowerConveyor.set(0);
        upperConveyor.set(0);        
    }
    
    public static void setIntake(double power) {
        lowerConveyor.set(power);
    }
    
    public static void setFeeder(double power) {
        upperConveyor.set(power);
    }
            
}
