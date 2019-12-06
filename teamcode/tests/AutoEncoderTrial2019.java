/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.android.AndroidGyroscope;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.android.AndroidAccelerometer;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="EncoderTrial", group="Linear Opmode")

public class AutoEncoderTrial2019 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private AndroidAccelerometer accel = new AndroidAccelerometer();
    private BNO055IMU imu = null;
    private Orientation lastAngles = new Orientation();
    private double  globalAngle, power = .30, correction;
    
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftBack = hardwareMap.get(DcMotor.class, "left_back");
        rightBack = hardwareMap.get(DcMotor.class, "right_back");
        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");
        
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        
        accel.startListening();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        
        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);

        telemetry.addData("Mode", "calibrating...");
        telemetry.update();

        // make sure the imu gyro is calibrated before continuing.
        while (!isStopRequested() && !imu.isGyroCalibrated())
        {
            sleep(50);
            idle();
        }

        telemetry.addData("Mode", "waiting for start");
        telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.update();

        
        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        
        
        
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;


            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            //double drive = -gamepad1.left_stick_y;
            //double turn  =  gamepad1.right_stick_x;
            //leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            //rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            
            //leftPower = 1;
            //rightPower = 0;

            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = gamepad1.left_stick_y ;
            // rightPower = gamepad1.right_stick_y ;
            
            float Ly; //= 1/gamepad1.left_stick_y;
            float Lx; //= 1/gamepad1.left_stick_x;
            double Rx; //= 1/gamepad1.right_stick_x;
            
            Ly = 0; //-1
            Lx = 0;
            Rx = 0.5;
           
            /*
            if(gamepad1.left_stick_y != 0) {
                Ly = 1/gamepad1.left_stick_y;
            } else {
                Ly = 0;
            }
            if(gamepad1.left_stick_x != 0) {
                Lx = 1/gamepad1.left_stick_x;
            } else {
                Lx = 0;            
            }
            if(gamepad1.right_stick_x != 0) {
                Rx = 1/gamepad1.right_stick_x;
            } else {
                Rx = 0;
            }
            */
           // double Rx = (90 - getAngle())/90;
            //if(Rx < 0.25) {
                //Rx = 0.25;
            //}
            
            double leftfront = (Ly - Lx + Rx)/3;
            double leftback = (Ly + Lx + Rx)/3;
            double rightfront = (Ly + Lx - Rx)/3;
            double rightback = (Ly - Lx - Rx)/3;

            //test encoder stuff
            int lfpos = leftFront.getCurrentPosition();
            telemetry.addData("Encoder Position", lfpos);
            if (getAngle() > 86) {
                leftBack.setPower(0);
                rightBack.setPower(0);
                leftFront.setPower(0);
                rightFront.setPower(0);
            } else {
                leftBack.setPower(leftback);
                rightBack.setPower(rightback);
                leftFront.setPower(leftfront);
                rightFront.setPower(rightfront);
            }
                
            // int rfpos = rightFront.getCurrentPosition();
            // telemetry.addData("Encoder Position", rfpos);
            // if (lfpos < -14193 - 280) {
            //     leftBack.setPower(0);
            //     rightBack.setPower(0);
            //     leftFront.setPower(0);
            //     rightFront.setPower(0);
            // } else {
            //     leftBack.setPower(leftback);
            //     rightBack.setPower(rightback);
            //     leftFront.setPower(leftfront);
            //     rightFront.setPower(rightfront);
            // }
            
            // int rbpos = rightBack.getCurrentPosition();
            // telemetry.addData("Encoder Position", rbpos);
            // if (lfpos < -14193 - 280) {
            //     leftBack.setPower(0);
            //     rightBack.setPower(0);
            //     leftFront.setPower(0);
            //     rightFront.setPower(0);
            // } else {
            //     leftBack.setPower(leftback);
            //     rightBack.setPower(rightback);
            //     leftFront.setPower(leftfront);
            //     rightFront.setPower(rightfront);
            // }
            
            // int lbpos = leftBack.getCurrentPosition();
            // telemetry.addData("Encoder Position", lbpos);
            // if (lfpos < -14193 - 280) {
            //     leftBack.setPower(0);
            //     rightBack.setPower(0);
            //     leftFront.setPower(0);
            //     rightFront.setPower(0);
            // } else {
            //     leftBack.setPower(leftback);
            //     rightBack.setPower(rightback);
            //     leftFront.setPower(leftfront);
            //     rightFront.setPower(rightfront);
            // }
            
            
                
            double theta = getAngle();
            telemetry.addData("Angle: ", theta);
            
            
            
            
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Ly", gamepad1.left_stick_y);
            telemetry.addData("Lx", gamepad1.left_stick_x);
            telemetry.addData("Rx",gamepad1.right_stick_x);
            
            telemetry.addData("leftfront", leftfront);
            telemetry.addData("leftback", leftback);
            telemetry.addData("rightfront", rightfront);
            telemetry.addData("rightback", rightback);
            
            telemetry.addData("isAvailable", accel.isAvailable());
            telemetry.addData("accel_x", accel.getX());
            telemetry.addData("accel_y", accel.getY());
            telemetry.addData("accel_z", accel.getZ());
            // telemetry.addData("rotation: ", rot);
            
            // telemetry.addData("isAvailable", ori.isAvailable());
            // telemetry.addData("ori_azi", ori.getAzimuth());
            // telemetry.addData("ori_mag", ori.getMagnitude());
            // telemetry.addData("ori_pit", ori.getPitch());
            // telemetry.addData("ori_rol", ori.getRoll());


            // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
}

    
    /**
     * Resets the cumulative angle tracking to zero.
     */
    private void resetAngle()
    {
        lastAngles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right.
     */
    private double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

    /**
     * See if we are moving in a straight line and if not return a power correction value.
     * @return Power adjustment, + is adjust left - is adjust right.
     */
    private double checkDirection()
    {
        // The gain value determines how sensitive the correction is to direction changes.
        // You will have to experiment with your robot to get small smooth direction changes
        // to stay on a straight line.
        double correction, angle, gain = .10;

        angle = getAngle();

        if (angle == 0)
            correction = 0;             // no adjustment.
        else
            correction = -angle;        // reverse sign of angle for correction.

        correction = correction * gain;

        return correction;
    }
}
