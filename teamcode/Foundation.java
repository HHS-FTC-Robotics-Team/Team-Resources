package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public class Foundation extends LinearOpMode {

  private Servo servo = null;
  private double max = .5; // Maximum rotational position
  private double min = .5; // Minimum rotational position

  public Foundation (Servo s) {
    servo = s;
  }

  public void grab() {
    servo.setPosition(min);
  }
  public void release() {
    servo.setPosition(max);
  }

  public void runOpMode() {

  }
}
