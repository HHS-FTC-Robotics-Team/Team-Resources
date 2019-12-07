package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

public class Foundation extends LinearOpMode {

  private Servo left = null;
  private Servo right = null;
  private double rmax = .1; // Maximum rotational position
  private double rmin = .8; // Minimum rotational position
  private double lmax = .1; // Maximum rotational position
  private double lmin = .8; // Minimum rotational position

  public Foundation (Servo l, Servo r) {
    left = l;
    right = r;
  }

  public void grab() {
    left.setPosition(lmin);
    right.setPosition(rmin);
  }
  public void release() {
    left.setPosition(lmax);
    right.setPosition(rmax);
  }

  public void runOpMode() {

  }
}
