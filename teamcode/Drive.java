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
  public DcMotor motorlf = null;
  public double powerlb;
  public DcMotor motorlb = null;
  public double powerrf;
  public DcMotor motorrf = null;
  public double powerrb;
  public DcMotor motorrb = null;

  public Drive(DcMotor lf, DcMotor lb, DcMotor rf, DcMotor rb) {
    powerlf = 0;
    motorlf = lf;
    motorlf.setDirection(DcMotor.Direction.FORWARD);
    powerlb = 0;
    motorlb = lb;
    motorlb.setDirection(DcMotor.Direction.FORWARD);
    powerrf = 0;
    motorrf = rf;
    motorrf.setDirection(DcMotor.Direction.REVERSE);
    powerrb = 0;
    motorrb = rb;
    motorrb.setDirection(DcMotor.Direction.REVERSE);
    
    motorlf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    motorlf.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      
  }

  public void setPower(double Ly, double Lx, double Rx, double Trigger) {
    double leftfront = (Ly - Lx + Rx)/3;
    double leftback = (Ly + Lx + Rx)/3;
    double rightfront = (Ly - Lx - Rx)/3;
    double rightback = (Ly + Lx - Rx)/3;
    if (Trigger > 0) {
      double max = findMax(leftfront,leftback,rightfront,rightback);
      max = max / Trigger;
      max = Math.abs(max);
      leftfront = leftfront / max;
      leftback = leftback / max;
      rightfront = rightfront / max;
      rightback = rightback / max;
    }
    powerlf = leftfront;
    motorlf.setPower(leftfront);
    powerlb = leftback;
    motorlb.setPower(leftback);
    powerrf = rightfront;
    motorrf.setPower(rightfront);
    powerrb = rightback;
    motorrb.setPower(rightback);
  }

  // public void setPower(double lf, double lb, double rf, double rb) {
  //   motorlf.setPower(lf);
  //   motorlb.setPower(lb);
  //   motorrf.setPower(rf);
  //   motorrb.setPower(rb);
  // }

  public double getPowerlf() {
    double lf = motorlf.getPower();

    return powerlf;
  }

  public double getClickslf() {
    return motorlf.getCurrentPosition();
  }

  public double getPowerlb() {
    double lb = motorlb.getPower();

    return powerlb;
  }

  public double getPowerrf() {
    double rf = motorrf.getPower();

    return powerrf;
  }

  public double getPowerrb() {
    double rb = motorrb.getPower();

    return powerrb;
  }

  public double findMax(double lf,double lb,double rf,double rb) {
    if (lf >= lb && lf >= rf && lf >= rb) {
      return lf;
    }
    else if (lb >= lf && lb >= rf && lb >= rb) {
      return lb;
    }
    else if (rf >= lf && rf >= lb && rf >= rb) {
      return rf;
    }
    else if (rb >= lf && rb >= lb && rb >= rf) {
      return rb;
    }
    else {
      return 1;
    }
  }


  public void runOpMode() {
  }
}
