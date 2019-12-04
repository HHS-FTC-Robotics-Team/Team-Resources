package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode. TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

public class SciLift extends LinearOpMode {

  private int position = 0;
  private int max = 2000;
  private int min = 0;
  private DcMotor motor = null;
  private DigitalChannel touch;
  public String state = "rest";


  public SciLift(DcMotor mtr) {
    motor = mtr;
    motor.setDirection(DcMotor.Direction.FORWARD);

    // touch = t;
    // touch.setMode(DigitalChannel.Mode.INPUT);


    position = motor.getCurrentPosition();
  }

  public void update() {
    if(state == "rest") {
      telemetry.addData("Lift Status:", state);
    } else if(state == "down") {
      this.down();
    }
  }

  public void home() {

  }

  public void down(float power) {
    position = motor.getCurrentPosition();

    motor.setPower(0.6 * power);

    // kill the motor if the touch sensor gets pressed
  }

  public void down() {
    position = motor.getCurrentPosition();

    motor.setPower(0.3);

    // kill the motor if the touch sensor gets pressed
  }

  public void up(float power) {
    position = motor.getCurrentPosition();

    motor.setPower(-1 * power);

    // kill the motor if the touch sensor gets pressed
  }

  public void up() {
    position = motor.getCurrentPosition();

    motor.setPower(-0.6);

    // kill the motor if the touch sensor gets pressed
  }

  public void rest() {
    motor.setPower(0);
  }

  public void setState(String state) {
    this.state = state;
  }

  public double getPower() {
    double p = motor.getPower();
    return p;
  }

  public void runOpMode() {
  }
}
