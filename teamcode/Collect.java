package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

public class Collect extends LinearOpMode {

  private DcMotor Lmtr = null;
  private DcMotor Rmtr = null;
  private DistanceSensor dist = null;

  public Collect (DcMotor l, DcMotor r, DistanceSensor d) {
    Lmtr = l;
    Rmtr = r;
    Lmtr.setDirection(DcMotor.Direction.FORWARD);
    Rmtr.setDirection(DcMotor.Direction.REVERSE);
    dist = d;

  }

  public double getDistance() {
    double distance = dist.getDistance(DistanceUnit.MM);
    return distance;
  }

  public boolean getBlock() {
    boolean gotBlock;

    if(this.getDistance() < 5) {
      gotBlock = true;
    } else {
      gotBlock = false;
    }

    return gotBlock;
  }


  public void update() {

  }


  public void in() {
    Lmtr.setPower(1);
    Rmtr.setPower(1);
  }


  public void out() {
    Lmtr.setPower(-1);
    Rmtr.setPower(-1);
  }

  public void rest() {
    Lmtr.setPower(0);
    Rmtr.setPower(0);
  }

  public String getPower() {
    double lp = Lmtr.getPower();
    double rp = Rmtr.getPower();
    String s = lp + " / " + rp;
    return s;
  }


  public void home() {

  }
  public void runOpMode() {

  }
}
