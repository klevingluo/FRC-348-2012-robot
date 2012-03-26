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
    
    public static final double 
            SHOOTER_POWER = 0.7,
            FEEDER_POWER  = 1,
            INTAKE_POWER  = 1;
    
    protected static Jaguar lowerConveyor = new Jaguar(RobotMap.lowerBallConveyor);
    private static Jaguar upperConveyor = new Jaguar(RobotMap.upperBallConveyor);
    private static Jaguar shooter = new Jaguar(RobotMap.shooter);
    
    public static void runShooter() {
        shooter.set(-SHOOTER_POWER);    // shooter is backwards
    } 
    
    public static void stopShooter() {
        shooter.set(0);
    }
    
    public static void advanceBall() {
        lowerConveyor.set(-INTAKE_POWER);     // lower conveyor is backwards
        upperConveyor.set(FEEDER_POWER);
    }
    
    /*
     * stops both feeder and intake
     */
    public static void stopBall() {
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
