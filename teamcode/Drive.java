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
    motorlb.setDirection(DcMotor.Direction.REVERSE);
    powerrf = 0;
    motorrf = rf;
    motorrf.setDirection(DcMotor.Direction.FORWARD);
    powerrb = 0;
    motorrb = rb;
    motorrb.setDirection(DcMotor.Direction.REVERSE);
  }

  public void setPower(double Ly, double Lx, double Rx) {
    double leftfront = (Ly - Lx + Rx)/3;
    double leftback = (Ly + Lx + Rx)/3;
    double rightfront = (Ly + Lx - Rx)/3;
    double rightback = (Ly - Lx - Rx)/3;
    powerlf = leftfront;
    motorlf.setPower(leftfront);
    powerlb = leftback;
    motorlb.setPower(leftback);
    powerrf = rightfront;
    motorrf.setPower(rightfront);
    powerrb = rightback;
    motorrb.setPower(rightback);
  }

  public void setPower(double lf, double lb, double rf, double rb) {
    motorlf.setPower(lf);
    motorlb.setPower(lb);
    motorrf.setPower(rf);
    motorrb.setPower(rb);
  }

  public double getPowerlf() {
    return powerlf;
  }


  public void runOpMode() {
  }
}
