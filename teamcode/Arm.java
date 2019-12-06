package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class Arm extends LinearOpMode {

  private int position;
  private int max = 2000;
  private int min = 0;
  private DcMotor motor = null;
  private DigitalChannel touch;
  public String state = "rest";


  public Arm(DcMotor mtr, DigitalChannel t) {
    motor = mtr;
    motor.setDirection(DcMotor.Direction.FORWARD);

    touch = t;
    touch.setMode(DigitalChannel.Mode.INPUT);

    position = motor.getCurrentPosition();
  }

//  public void update() {
//    if(state == "rest") {
//      telemetry.addData("Lift Status:", state)
//    } else if(state == "down") {
//      this.down();
//    }
//  }


  public void retract(double power) {
    position = motor.getCurrentPosition();

    motor.setPower(1 * power);

    // kill the motor if the touch sensor gets pressed
  }

  public void retract() {
    position = motor.getCurrentPosition();

    motor.setPower(1);

    // kill the motor if the touch sensor gets pressed
  }

  public void extend(double power) {
    position = motor.getCurrentPosition();

    motor.setPower(-1 * power);

    // kill the motor if the touch sensor gets pressed
  }

  public void extend() {
    position = motor.getCurrentPosition();
    motor.setPower(-1);
  }

  public void rest() {
    position = motor.getCurrentPosition();

    motor.setPower(0);

    // kill the motor if the touch sensor gets pressed
  }

  public double getPower() {
    double p = motor.getPower();
    return p;
  }

  // public void setState(int state) {
  //   return position;
  // }

  public void runOpMode() {
  }
}
