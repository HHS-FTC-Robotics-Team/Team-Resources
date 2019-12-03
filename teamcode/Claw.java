package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Claw extends LinearOpMode {

  private Servo servo = null;
  private double increment = 0.01; // amount to slew servo each CYCLE_MS cycle
  private int cycle_ms = 50; // period of each cycle
  private double max_pos = .82; // Maximum rotational position
  private double min_pos = .17; // Minimum rotational position
  private double current_pos = 0.5;
  private DistanceSensor dist = null;
  

  public Claw (Servo s/*, DistanceSensor ds*/) {
    servo = s;
    //dist = ds;
  }

  public void grab() {
    servo.setPosition(min_pos);
  }
  public void release() {
    servo.setPosition(max_pos);
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

    if(dist.getDistance(DistanceUnit.MM) < 200) {
      this.grab();
    }
  }



  //TO DO -
  // private void scaleRange(double min, double max)


  public double getPos() {
    return current_pos;
  }

  public void runOpMode() {
    
  }
}
