package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


public class Drive extends LinearOpMode {
  
  public double powerlf;
  public DcMotor motorlf;
    
  public Drive() {
    powerlf = 0;
    motorlf = hardwareMap.get(DcMotor.class, "motorlf");
  }

  public void setPower(double p) {
    powerlf = p;
    motorlf.setPower(powerlf);
  }
  
  public double getPowerlf() {
    return powerlf;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public void runOpMode() {
  }
}