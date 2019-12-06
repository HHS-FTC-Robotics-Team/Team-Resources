package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.Drive;


@TeleOp(name="Arm Test", group="Linear Opmode")

public class ArmTest extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    // private DcMotor leftDrive = null;
     private DcMotor arm = null;
    

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        
        
        // leftDrive  = hardwareMap.get(DcMotor.class, "motorlf");
        arm = hardwareMap.get(DcMotor.class, "arm");

        // leftDrive.setDirection(DcMotor.Direction.FORWARD);
        // rightDrive.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();
        runtime.reset();
        
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            //double Ly;
            //double Lx;
            double Rx;
            
            //Ly = gamepad1.left_stick_y;
            //Lx = -gamepad1.left_stick_x;
            //Rx = -gamepad1.right_stick_x;
            // leftPower  = -gamepad1.left_stick_y ;
            double armpower = -gamepad1.right_stick_y ;
            

            // leftDrive.setPower(leftPower);
            //d.setPower(Rx);
            arm.setPower(armpower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            // telemetry.addData("leftPower", leftPower);
            // telemetry.addData("rightPower", rightPower);
            telemetry.update();
        }
    }
}
