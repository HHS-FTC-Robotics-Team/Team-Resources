package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public Claw extends LinearOpMode {

  private Servo servo = null;
  private double increment = 0.01; // amount to slew servo each CYCLE_MS cycle
  private int cycle_ms = 50; // period of each cycle
  private double max_pos = .82; // Maximum rotational position
  private double min_pos = .17; // Minimum rotational position
  private double current_pos = 0.5;
  private double current_pos = 0.5;

  public Claw (Servo s) {
    servo = s;
  }

  public void grab() {
    servo.setPosition(min);
  }
  public void release() {
    servo.setPosition(max);
  }

  public void update() { // might not need
    // if (current_pos < target_pos) {
    //   position += increment ;
    //   if (position >= max_pos ) {
    //     position = max_pos;
    //   }
    // }
    // else if (current_pos < target_pos) {
    //   position -= INCREMENT ;
    //   if (position <= min_pos ) {
    //     position = min_pos;
    //   }
    // }
  }



  //TO DO -
  // private void scaleRange(double min, double max)


  public double getPos() {
    return current_pos;
  }

}
