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
import org.firstinspires.ftc.teamcode.Collect;
import org.firstinspires.ftc.teamcode.Flick;
import org.firstinspires.ftc.teamcode.FlickJr;
import org.firstinspires.ftc.teamcode.Foundation;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import org.firstinspires.ftc.teamcode.Claw;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="15317 Teleop", group="Linear Opmode")

public class Teleop15317 extends LinearOpMode {

    private Drive d;
    private SciLift lift;
    private Collect collector;
    private Claw claw;
    private Arm arm;
    private Flick flick;
    private FlickJr flickjr;
    private Foundation foundation;


    @Override
    public void runOpMode() {

      d = new Drive(
          hardwareMap.get(DcMotor.class, "rbmotor"),
          hardwareMap.get(DcMotor.class, "rfmotor"),
          hardwareMap.get(DcMotor.class, "lfmotor"),
          hardwareMap.get(DcMotor.class, "lbmotor")
      );
      lift = new SciLift(
        hardwareMap.get(DcMotor.class, "liftmotor")
      );
      arm = new Arm(
        hardwareMap.get(DcMotor.class, "armmotor"),
        hardwareMap.get(DigitalChannel.class, "armtouch")
      );
      collector = new Collect(
        hardwareMap.get(DcMotor.class, "col_left"),
        hardwareMap.get(DcMotor.class, "col_right"),
        hardwareMap.get(Rev2mDistanceSensor.class, "distance_sensor")
      );
      claw = new Claw(
        hardwareMap.get(Servo.class, "clawleft"),
        hardwareMap.get(Servo.class, "clawright")
      );
      flick = new Flick(
        hardwareMap.get(Servo.class, "flick")
      );
      flickjr = new FlickJr(
        hardwareMap.get(Servo.class, "hit")
      );
      foundation = new Foundation(
        hardwareMap.get(Servo.class, "foundationleft"),
        hardwareMap.get(Servo.class, "foundationright")
      );

      waitForStart();
      while (opModeIsActive()) {
        //gamepad 1 = drive and collection
        //gamepad 2 = lift, arm, foundation, claw

        // gamepad 1
        d.setPower(
          -gamepad1.left_stick_y,
          -gamepad1.left_stick_x,
          -gamepad1.right_stick_x,
          gamepad1.right_trigger
        );

        if (gamepad1.right_bumper) {
          collector.in();
        } else if (gamepad1.left_bumper) {
          collector.out();
        } else {
          collector.rest();
        }

        if (gamepad1.a) { //collector servo
          flick.up();
        } else {
          flick.down();
        }
        if (gamepad1.b) { //collector servo
          telemetry.addData("Flick Jr", "up");
          flickjr.up();
        } else {
          telemetry.addData("Flick Jr", "down");
          flickjr.down();
        }

        if (gamepad1.y) {
          foundation.grab();
        } else if (gamepad1.x) {
          foundation.release();
        }

        //gamepad 2
        if (gamepad2.b /* left_bumper */) {
          claw.release();
        } else if (gamepad2.a  /* right_bumper */) {
          claw.grab();
        }


        if (-gamepad2.left_stick_y > 0) {
            arm.extend(Math.abs(-gamepad2.left_stick_y));
        } else if (-gamepad2.left_stick_y < 0){
            arm.retract(Math.abs(-gamepad2.left_stick_y));
        } else {
            arm.rest();
        }

        if (-gamepad2.right_stick_y > 0) {
            lift.up(Math.abs(-gamepad2.right_stick_y));
        } else if (-gamepad2.right_stick_y < 0){
            lift.down(Math.abs(-gamepad2.right_stick_y));
        } else {
            lift.rest();
        }


        telemetry.addData("Status", "Run Time: ");
        // telemetry.addData("Lift Power", lift.getPower());
        // telemetry.addData("Arm Power", arm.getPower());
        // // telemetry.addData("Armposition")
        telemetry.addData("Collect Power", collector.getPower());
        telemetry.addData("Dist Sensor", collector.getDistance());
        telemetry.addData("Ly", gamepad1.left_stick_y);
        telemetry.addData("Lx", gamepad1.left_stick_x);
        telemetry.addData("Rx", gamepad1.right_stick_x);
        telemetry.addData("lf", d.getPowerlf());
        telemetry.addData("lb", d.getPowerlb());
        telemetry.addData("rf", d.getPowerrf());
        telemetry.addData("rb", d.getPowerrb());
        telemetry.addData("Clicks: ", d.getClickslf());
        telemetry.addData("Lift", lift.getClicks());
        // telemetry.addData("O,rientation")
        // telemetry.addData("State")
        // telemetry.addData("x,y")
        telemetry.update();

      }
    }
}
