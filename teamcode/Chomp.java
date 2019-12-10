package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


public class Chomp {
    static final double INCREMENT   = 0.001;     // amount to slew servo each CYCLE_MS cycle
    static final int    CYCLE_MS    =   50;     // period of each cycle
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position

    // Define class members
    Servo   servo;
    double  position = (MAX_POS - MIN_POS) / 2; // Start at halfway position

    boolean rampUp = true;

    public Chomp(Servo s) {
        servo = s;
        servo.setPosition(position);
    }

    public void out() {
        if (position <= 0.99) {
            position += INCREMENT ;
        } else {
            position = 0.99;
        }
        servo.setPosition(position);
    }

    public void out(double amt) {
      position += amt;
      if (position > 0.99) {
        position = 0.99;
      }
      servo.setPosition(position);
    }

    public void in() {
      if (position >= 0.01) {
          position -= INCREMENT ;
      } else {
          position = 0.01;
      }
      servo.setPosition(position);
    }

    public void in(double amt) {
      position -= amt;
      if (position < 0.01) {
        position = 0.01;
      }
      servo.setPosition(position);
    }

    public void runOpMode() {

    }
}
