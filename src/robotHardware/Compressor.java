/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotHardware;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author team348
 */
public class Compressor {
    
    private static Relay compressor = new Relay(ControlMap.compressor);
    private static DigitalInput pressureSwitch = new DigitalInput(ControlMap.pressureSwitch);
    
    public static void start() {
        
    }
    
    public static void stop () {
        
    }
}
