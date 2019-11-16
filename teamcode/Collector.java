package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled:
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode. TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//import distance sensor

public Collection extends LinearOpMode {
  
  private DcMotor Lmtr = null;
  private DcMotor Rmtr = null;
  private DistanceSensor dist = null;
  
  public Collection (DcMotor Lmtr, Dcmotor Rmtr, DistanceSensor d) {
    
    Lmtr.setDirection(DcMotor.Direction.FORWARD);
    Rmtr.setDirection(DcMotor.Direction.REVERSE):
    dist = d;
    
  }
  
  public float getDistance() {
    float distance = dist.getDistance(DistanceUnit.MM);
    return distance;
  }
  
  public boolean getBlock () {
    boolean gotBlock;
    
    
    if(c.getDistance() < /*something*/) {
      gotBlock = true;
    } else {
      gotBlock = false;
    }
    
    return gotBlock;
  }
  
  
  public void update() {
    
  }
    
    
  public void in (); {
    
    
    Lmtr.setPower(1);
    Rmtr.setPower(1);
  }
    
  
  public void out (); {
    
    Lmtr.setPower(0);
    Rmtr.setPower(0);
  }
    
  public void home() {
    
  }
}