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

//import collector class

@TeleOp(name="Teleop 2019", group="Linear Opmode")

public class Teleop1776 extends LinearOpMode {

    private Drive d;
    private SciLift lift;
    //private Grab grab;
    //private Claw claw;
    private Arm arm;

    @Override
    public void runOpMode() {

      d = new Drive(
          hardwareMap.get(DcMotor.class, "right_back"),
          hardwareMap.get(DcMotor.class, "right_front"),
          hardwareMap.get(DcMotor.class, "left_front"),
          hardwareMap.get(DcMotor.class, "left_back")
      );
      //initialize other objects

      lift = new SciLift(hardwareMap.get(DcMotor.class, "liftmotor"));
      arm = new Arm(
        hardwareMap.get(DcMotor.class, "armmotor"),
        hardwareMap.get(DigitalChannel.class, "armtouch")
      );

      waitForStart();
      while (opModeIsActive()) {

        double Ly;
        double Lx;
        double Rx;
        Ly = gamepad1.left_stick_y;
        Lx = -gamepad1.left_stick_x;
        Rx = -gamepad1.right_stick_x;
        d.setPower(Ly,Lx,Rx);


        if (gamepad1.a) {
          //claw.open() // servo.setPosition(whatever open means)
        }
        if (gamepad1.b) {
          //
        }
        if (gamepad1.x) {
          //grab.suck
        }
        if (gamepad1.y) {
          //grab.regurgitate
        }

        if (gamepad1.left_bumper) {
            arm.retract();
        } else if (gamepad1.left_trigger > 0){
            arm.extend(gamepad1.left_trigger);
        } else {
            arm.rest();
        }
        
        if (gamepad1.right_bumper) {
            lift.down();
        } else if (gamepad1.right_trigger > 0){
            lift.up(gamepad1.right_trigger);
        } else {
            lift.rest();
        }

        telemetry.addData("Status", "Run Time: ");
        telemetry.addData("Lift Power", lift.getPower());
        telemetry.addData("Arm Power", arm.getPower());
        telemetry.addData("Ly", gamepad1.left_stick_y);
        telemetry.addData("Lx", gamepad1.left_stick_x);
        telemetry.addData("Rx", gamepad1.right_stick_x);
        telemetry.addData("lf", d.getPowerlf());
        telemetry.addData("lb", d.getPowerlb());
        telemetry.addData("rf", d.getPowerrf());
        telemetry.addData("rb", d.getPowerrb());
        // telemetry.addData("Lift Height", lh);
        // telemetry.addData("Armposition")
        // telemetry.addData("Orientation")
        // telemetry.addData("GrabbyPower")
        // telemetry.addData("distanceSensor")
        // telemetry.addData("State")
        // telemetry.addData("x,y")
        telemetry.update();
        
      }
    }
}
