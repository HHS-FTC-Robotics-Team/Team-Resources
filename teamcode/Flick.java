package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public class Flick extends LinearOpMode {

  private Servo servo = null;
  private double max = .1; // Maximum rotational position
  private double min = .8; // Minimum rotational position

  public Flick (Servo s) {
    servo = s;
  }

  public void up() {
    servo.setPosition(max);
  }
  public void down() {
    servo.setPosition(min);
  }


  public void runOpMode() {

  }
}
