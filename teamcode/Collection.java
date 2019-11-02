package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled:
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode. TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public Collection extends LinearOpMode {
  
  private DcMotor Lmtr = null;
  private DcMotor Rmtr = null;
  private Servo servo = null;
  
  
  public Collection (DcMotor Lmtr, Dcmotor Rmtr, Servo sv) {
    
    static final double INCREMENT   = 0.01;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;
    
    
    Lmtr.setDirection(DcMotor.Direction.FORWARD);
    Rmtr.setDirection(DcMotor.Direction.REVERSE):
    
    //Servo
    servo = sv;
    
    //double position0 = (MAX_POS - MIN_POS) / 2
  }
  
  public void update() {
    
  }
    
    
  public void out (); {
    
    
    Lmtr.setPower(1);
    Rmtr.setPower(1);
  }
    
  
  public void in (); {
    
    Lmtr.setPower(0);
    Rmtr.setPower(0);
  }
    
  public void home() {
    
  }
}