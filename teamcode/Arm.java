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


  public Arm(DcMotor mtr) {
    motor = mtr;
    motor.setDirection(DcMotor.Direction.FORWARD);
  }


  public void retract(double power) {
    motor.setPower(1 * power);
  }

  public void retract() {
    motor.setPower(1);
  }

  public void extend(double power) {
    motor.setPower(-1 * power);
  }

  public void extend() {
    motor.setPower(-1);
  }

  public void rest() {
    motor.setPower(0);
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
