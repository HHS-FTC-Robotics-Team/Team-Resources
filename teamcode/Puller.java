package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public Puller extends LinearOpMode {

  private Servo servo = null;
  private double increment = 0.01; // amount to slew servo each CYCLE_MS cycle
  private int cycle_ms = 50; // period of each cycle
  private double max_pos = .82; // Maximum rotational position
  private double min_pos = .17; // Minimum rotational position
  private double current_pos = 0.5;

  public Puller (Servo s) {
    servo = s;
  }

  public void grab() {
    servo.setPosition(min);
    current_pos = min;
  }
  public void release() {
    servo.setPosition(max);
    current_pos = max;
  }

  public double getPos() {
    return current_pos;
  }

  public void runOpMode() {

  }
}
