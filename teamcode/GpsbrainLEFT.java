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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.lang.reflect.Array;
import org.firstinspires.ftc.teamcode.Chomp;

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


public class GpsbrainLEFT extends LinearOpMode {

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

  public String[] states = new String[]{"forward","strafeLeft", "rest"};
  private double[] args = new double[]{300,12000,0};
  public int count = 0;
  private boolean[] isArgs = new boolean[]{true,true, false};


  private BNO055IMU imu = null;
  private Orientation lastAngles = new Orientation();
  private double globalAngle, power = 0.30, correction;


  Chomp collect = null;
  Find f = null;

  public GpsbrainLEFT(Drive drive, BNO055IMU acc, Chomp c, Find find) {
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


   public void pop() {
    count = count + 1;
  }

  public void pop(double argument) {
    // arg = argument;
    count = count + 1;
  }

  public void update() {
    if(states[count] == "rest") {
      // nothing
      d.setPower(0, 0, 0, 0);
    }
    if(states[count] == "turn") {
      if (isArgs[count]) {
        this.turn(args[count]);
        isArgs[count] = false;
      }
      this.turn();
    }
    if(states[count] == "forward"){
      if (isArgs[count]) {
        this.forward(args[count]);
        isArgs[count] = false;
      }
      this.forward();
    }
    if(states[count] == "strafeLeft"){
      if (isArgs[count]) {
        this.strafeLeft(args[count]);
        isArgs[count] = false;
      }
      // d.setPower(0, -1, 0, 0.3);
      strafeLeft();
    }
    if(states[count] == "strafeRight"){
      if (isArgs[count]) {
        this.strafeRight(args[count]);
        isArgs[count] = false;
      }
      // d.setPower(0, -1, 0, 0.3);
      strafeRight();
    }
    if(states[count] == "seek") {
      double angle = f.findSkystoneAngle();
      //d.setPower(0, 1*angle/15, 0, 0.2);
      if(angle < 1 && angle > -1) {
        pop();
      } else {
        d.setPower(0, 1*angle/15, 0, 0.3);
      }
    }
    if(states[count] == "collect") {
      if (isArgs[count]) {
        collect.in(args[count]); // no way to pop
        isArgs[count] = false;
      }
    }
    if(states[count] == "out") {
      if (isArgs[count]) {
        collect.out(args[count]); // no way to pop
        isArgs[count] = false;
      }
    }
  }

  public void turn() {
      theta = getAngle();
      //telemetry.addData("Angle: ", theta);
      //telemetry.addData("Look", "Here");
      d.setPower(0, 0, (dtheta - theta) / (Math.abs(dtheta - theta)) , 0.6);
      if(Math.abs(theta - dtheta) < 2) {
        pop();
      }
  }
  public void turn(double degrees){
      dtheta = theta + degrees;
  }

  public void forward(){
    double current = d.getClickslf();
    if(current > goalclicks - 25 && current < goalclicks + 25) {
      pop();
    } else if(current < goalclicks) {
      d.setPower(1, 0, 0, 0.3);
    } else if(current > goalclicks) {
      d.setPower(-1, 0, 0, 0.3);
    }
    // if(current < goalclicks) {
    //   d.setPower(1,0,0,0.3);
    // } else {
    //   pop();
    // }
  }
  public void backward(){
    double current = d.getClickslf();
    if(current < goalclicks) {
      d.setPower(1,0,0,0.3);
    } else {
      pop();
    }
  }

  public void forward(double clicks){
      startclicks = d.getClickslf(); // where the encoder starts
      goalclicks = startclicks + clicks; // how far to go
  }
  public void strafeLeft(double clicks){
   startclicks = d.getClickslf(); // where the encoder starts
   goalclicks = startclicks + clicks; // how far to go
  }
  public void strafeRight(double clicks){
   startclicks = d.getClickslf(); // where the encoder starts
   goalclicks = startclicks - clicks; // how far to go
   state = "strafeRight";
  }


  public void strafeLeft(){
    double current = d.getClickslf();
    if(current < goalclicks) {
     d.setPower(0,-1,0,1);
    } else {
     d.setPower(0,0,0,0);
     pop();
    }
  }
  public void strafeRight() {
    double current = d.getClickslf();
    if(current > goalclicks){
      d.setPower(0,1,0,1);
    } else {
      d.setPower(0,0,0,0);
      pop();
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

  public double find() {
      double angle = f.findSkystoneAngle();
      return angle;
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
