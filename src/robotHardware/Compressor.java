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
    
    private static Relay compressor = new Relay(RobotMap.compressor, Relay.Direction.kForward);
    private static DigitalInput pressureSwitch = new DigitalInput(RobotMap.pressureSwitch);
    
    public static void run() {
        //if (Compressor.isFull()) {
        //    compressor.set(Relay.Value.kOff);    
        //} else {
        //    compressor.set(Relay.Value.kOn);
        //}
    }
    
    public static void stop () {
        compressor.set(Relay.Value.kOff);
    }
    
    public static boolean isFull() {
        return pressureSwitch.get();
    }
    
}
