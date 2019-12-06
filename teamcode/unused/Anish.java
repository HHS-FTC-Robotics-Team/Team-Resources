package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

public class Anish {
  
  private String message;
  
  public int x;
  public int y;
  public int speed;
  public Boolean cute;
    
  public Anish() {
    x = 0;
    y = 0;
    speed = 1000000;
    cute = true;
  }
  
  public int getX() {
    return x;
  }
  
  // public void update() {
  //   if(state == 'home') {
  //     home();
  //     state = 'rest';
  //   }
  // }
  
  private void home() {
    
  }
  
  // private void goTo(int x, int y) {
  //   int x, y = nav.getLocation();
  // }

}
