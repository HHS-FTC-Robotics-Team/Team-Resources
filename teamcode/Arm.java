
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled:
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel

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
  
  public void home() {
    
  }
  
  public void down(int d) {
    position = motor.getCurrentPosition();
    
    if(position >= min) {
      motor.setPower(1);
    } else {
      motor.setPower(0);
      state = "rest";
    }
    
    // kill the motor if the touch sensor gets pressed
  }
  
  public voide setState(int state) {
    return position;
  }
  
  public void runOpMode() {
  }
}
