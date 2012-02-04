/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.EllipseMatch;
import edu.wpi.first.wpilibj.image.MonoImage;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author team348
 */
public class Camera extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    AxisCamera camera;
    EllipseMatch ball;
    ColorImage pic;
    
    // some variable that corresponds to the acceptable offset for a ball to be considered "in front"
    private static final int OFFSET = 9;
    
    public void initDefaultCommand() {
        camera = AxisCamera.getInstance("10.3.48.11");
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void findBall() {
        try {
            ColorImage scan = camera.getImage();
            MonoImage plane = scan.getLuminancePlane();
            EllipseMatch[] balls = plane.detectEllipses(null);
            ball = getLargest(balls);
        } 
        catch (NIVisionException ex) {System.out.println("ball not found");}
        catch (AxisCameraException ex) {System.out.println("camera unresponsive");}
    }
    
    public void trackBall() {
        
    }
    /**
     * 
     * @return a positive # if ball is to the right of the camera; negative if it it to the left, and zero if in front
     */
    public int getBallDirection() {
        try {
            if (Math.abs(pic.getWidth()/2 - ball.m_xPos) < OFFSET)
                return 0;
            return (int)(pic.getWidth()/2 - ball.m_xPos);
        } catch (NIVisionException ex) {System.out.println("ball not found");}
        return 0;
    }
    
    private EllipseMatch getLargest(EllipseMatch[] list) {
        int max = 0;
        for (int i=1; i < list.length; i++)
            if (list[i].m_majorRadius > list[max].m_majorRadius)
                max = i;
        return list[max];
    }
}
