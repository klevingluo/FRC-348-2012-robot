/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package robotHardware;

import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 *
 * @author team348
 */
public class Camera {
    
    private static AxisCamera camera = AxisCamera.getInstance(ControlMap.cameraIP);
    
    public static void getImage() {
        
    }
}
