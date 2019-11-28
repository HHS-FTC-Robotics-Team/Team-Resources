package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.SciLift;
import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Collector;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.teamcode.Claw;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="15317 Teleop", group="Linear Opmode")

public class Teleop1776 extends LinearOpMode {

    private Drive d;
    private SciLift lift;
    private Collector collector;
    private Claw claw;
    private Arm arm;

    @Override
    public void runOpMode() {

      d = new Drive(
          hardwareMap.get(DcMotor.class, "right_back"),
          hardwareMap.get(DcMotor.class, "right_front"),
          hardwareMap.get(DcMotor.class, "left_front"),
          hardwareMap.get(DcMotor.class, "left_back")
      );
      lift = new SciLift(
        hardwareMap.get(DcMotor.class, "liftmotor")
      );
      arm = new Arm(
        hardwareMap.get(DcMotor.class, "armmotor"),
        hardwareMap.get(DigitalChannel.class, "armtouch")
      );
      collector = new Collector(
        hardwareMap.get(DcMotor.class, "col_left"),
        hardwareMap.get(DcMotor.class, "col_right"),
        hardwareMap.get(DistanceSensor.class, "col_sensor")
      );
      claw = new Claw(
        hardwareMap.get(Servo.class, "claw");
      );

      waitForStart();
      while (opModeIsActive()) {
        //gamepad 1 = drive and collection
        //gamepad 2 = lift, arm, foundation, claw

        // gamepad 1
        d.setPower(
          gamepad1.right_stick_y;,
          -gamepad1.right_stick_x;,
          -gamepad1.left_stick_x;
        );
        if (gamepad1.x) {
          grab.in();
        } else if (gamepad1.y) {
          grab.out();
        } else {
          grab.rest();
        }

        //gamepad 2
        if (gamepad2.b) {
          claw.release()
        }
        if (gamepad2.a) {
          claw.grab()
        }
        // if (gamepad2.y) {
        //   foundation.release()
        // }
        // if (gamepad2.x) {
        //   foundation.open()
        // }

        if (gamepad2.left_bumper) {
            arm.retract();
        } else if (gamepad2.right_bumper){
            arm.extend();
        } else {
            arm.rest();
        }

        if (gamepad2.left_trigger > 0) {
            lift.down(gamepad2.left_trigger);
        } else if (gamepad2.right_trigger > 0){
            lift.up(gamepad2.right_trigger);
        } else {
            lift.rest();
        }

        telemetry.addData("Status", "Run Time: ");
        telemetry.addData("Lift Power", lift.getPower());
        telemetry.addData("Arm Power", arm.getPower());
        // telemetry.addData("Armposition")
        telemetry.addData("Collect Power", collect.getPower());
        telemetry.addData("Collect Dist", collect.getDistance());
        telemetry.addData("Ly", gamepad1.left_stick_y);
        telemetry.addData("Lx", gamepad1.left_stick_x);
        telemetry.addData("Rx", gamepad1.right_stick_x);
        telemetry.addData("lf", d.getPowerlf());
        telemetry.addData("lb", d.getPowerlb());
        telemetry.addData("rf", d.getPowerrf());
        telemetry.addData("rb", d.getPowerrb());
        // telemetry.addData("Orientation")
        // telemetry.addData("State")
        // telemetry.addData("x,y")
        telemetry.update();

      }
    }
}
