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
public class RobotMap {
    
    public static final int
      // jaguars
        rightDriveCIMs    = 5,
        rightDriveFP      = 6,  
        leftDriveCIMs     = 7,
        leftDriveFP       = 8,
        lowerBallConveyor = 4,
        upperBallConveyor = 3,
        shooter           = 2,
      // digital outputs
        leftCIMBrakes     = 1,
        rightCIMBrakes    = 2,
      // relays
        compressor        = 8,
        fan1              = 2,
        fan2              = 3,
      // solenoids
        puck              = 1,
        arm               = 2,
      //digital inputs
        pressureSwitch    = 2;
    public static final String
        cameraIP          = "10.3.48.7";
    
}
