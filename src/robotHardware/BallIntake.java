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
public class BallIntake {
    
    
    private static Jaguar intake = Shooter.lowerConveyor;
    
    public static void run(double power) {
        intake.set(power);
    }
    
    public static void stop() {
        intake.set(0);
    }
}
