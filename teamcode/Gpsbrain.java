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
import org.firstinspires.ftc.teamcode.Collector;

import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Find;


public class Gpsbrain extends LinearOpMode {

  public String state = "rest";

  Drive d = null;
  double x = 0;
  double y = 0;
  double dx = 0;
  double dy = 0;
  double theta = 0;
  double dtheta = 0;
  double travelled = 0;
  double goalclicks = 0;
  double startclicks = 0;

  private BNO055IMU imu = null;
  private Orientation lastAngles = new Orientation();
  private double globalAngle, power = 0.30, correction;


  Collector collect = null;
  Find f = null;

  public Gpsbrain(Drive drive, BNO055IMU acc, Collect c, Find find) {
      d = drive;
      BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
      imu = acc;
      imu.initialize(parameters);
      collect = c;
      f = find;
  }

  public void update() {
    if(state == "rest") {
      // nothing
      d.setPower(0, 0, 0, 0.3);
    }
    if(state == "turn") {
      this.turn();
    }
    if(state == "forward"){
      this.forward();
    }
    if(state == "strafeLeft"){
      this.strafeLeft();
    }
    if(state == "strafeRight"){
      this.strafeRight();
    }
    if(state == "strafeRight"){
      this.seek();
    }
    if(state == "seek") {
      double angle = f.findSkystoneAngle();
      if(angle > 0) {
        this.strafeRight(300);
      } else if(angle < 0) {
        this.strafeLeft(300);
      }
    }
  }

  public void turn() {
      theta = getAngle();
      telemetry.addData("Angle: ", theta);
      telemetry.addData("Look", "Here");
      d.setPower(0, 0, (dtheta - theta) / (Math.abs(dtheta - theta)) , 0.3);
      if(Math.abs(theta - dtheta) < 2) {
        state = "rest";
      }
  }
  public void turn(double degrees){
      dtheta = theta + degrees;
      state = "turn";
  }

  public void forward(){
    double current = d.getClickslf();
    if(current < goalclicks) {
      d.setPower(1,0,0,0.3);
    } else {
      state = "rest";
    }
  }

  public void forward(double clicks){
   startclicks = d.getClickslf(); // where the encoder starts
   goalclicks = startclicks + clicks; // how far to go
   state = "forward";
  }
  public void strafeLeft(double clicks){
   startclicks = d.getClickslf(); // where the encoder starts
   goalclicks = startclicks - clicks; // how far to go
   state = "strafeLeft";
  }
  public void strafeRight(double clicks){
   startclicks = d.getClickslf(); // where the encoder starts
   goalclicks = startclicks + clicks; // how far to go
   state = "strafeRight";
  }


  public void strafeLeft(){
    double current = d.getClickslf();
    if(current > goalclicks) {
     d.setPower(0,-1,0,0.3);
    } else {
     d.setPower(0,0,0,0.3);
     state = "rest";
    }
  }
  public void strafeRight() {
    double current = d.getClickslf();
    if(current < goalclicks){
      d.setPower(0,1,0,0.3);
    } else {
      d.setPower(0,0,0,0.3);
      state = "rest";
    }
  }

  public void drive(){
    double h =   Math.sqrt((x-dx)*(x-dx)- (y-dy)*(y-dy));
    if (travelled < h){
      d.setPower(1,1,1,0.3);
      d.motorlf.getCurrentPosition();
    } else {
      d.setPower(0,0,0,0.3);
      state = "rest";
    }
  }


  public void seek(){

    if(f.countSkystones() > 0) {
      double angle = f.findSkystoneAngle();
      if(angle == 0) {
        this.turn(angle);
      }
    }

    // if(f.getDistance() < 200) {
    //   collect.in();
    // } else {
    //   collect.out();
    // }

    // if(f.getBlock()) {
    //   state = "rest";
    // }

  }


  public double getAngle() {
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
