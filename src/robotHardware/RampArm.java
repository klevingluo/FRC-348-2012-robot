/*
 * contains code that controls the arm
 */
package robotHardware;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author team348
 */
public class RampArm {
    
    private static Solenoid arm = new Solenoid(RobotMap.arm);
    
    public static void raise() {   
        arm.set(false);
    }
    
    public static void lower() {
        arm.set(true); 
    }
    
}
