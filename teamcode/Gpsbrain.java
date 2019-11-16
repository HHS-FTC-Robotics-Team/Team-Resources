/*
Copyright 2019 FIRST Tech Challenge Team 6383

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import java.util.stream.Collector;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Find;
import org.firstinspires.ftc.teamcode.Collect;

class Gpsbrain extends LinearOpMode {
  
  String state = "rest";
  
  Drive d = null;
  double x = 0;
  double y = 0;
  double dx = 0;
  double dy = 0;
  double travelled = 0;
  
  private BNO055IMU imu = null;
  private Orientation lastAngles = new Orientation();
  private double globalAngle, power = 0.30, correction;
  Collect collect = null;
  
  Find f = null;
  
  public Gpsbrain(Drive drive, BNO055IMU acc, Collect c) {
    //add find object to constructor parameters
  
      d = drive;
      imu = acc;
      collect = c;
      
      
  }
 
  
  public void turn(double degrees){
      if (getAngle() > 86) {
          d.setPower(0,0,0);
      } else {
          //set the power for the turn
      }
  
  }
  
  public void drive(){
    double h =   Math.sqrt((x-dx)*(x-dx)- (y-dy)*(y-dy));
  
    if (travelled < h){
    d.setPower(1,1,1);
    
    } else {
    
    d.setPower(0,0,0);
    state = "rest";
    
    }
    
  }
  
  public void seek(){
    
    // if(f.countSkystones() > 0) {
    //   double angle = f.getSkystoneAngle();
    //   if(angle == 0) {
    //     turn(angle);
    //   }
    // }
    
    // if(f.getDistance() < 200) {
    //   collect.in();
    // } else {
    //   collect.out();
    // }
    
    // if(f.getBlock()) {
    //   state = "rest";
    // }
    
    
    
    
    
  }
  private double getAngle() {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }
    
    public void runOpMode() {
      
    }
}
