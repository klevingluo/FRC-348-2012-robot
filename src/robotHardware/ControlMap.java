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
public class ControlMap {
    
    public static final int
      // jaguars
        rightDriveCIMs    = 5,
        rightDriveFP      = 6,  
        leftDriveCIMs     = 7,
        leftDriveFP       = 8,
        lowerBallConveyor = 2,
        upperBallConveyor = 3,
        shooter           = 4,
      // relays
        compressor        = 1,
        fan1              = 2,
        fan2              = 3,
      // solenoids
        puck              = 1,
      //digital inputs
        pressureSwitch    = 2;
    public static final String
        cameraIP          = "10.3.48.7";
    
}
