/*
Copyright 2019 FIRST Tech Challenge Team 6383
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.Gpsbrain;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Collect;
import org.firstinspires.ftc.teamcode.Find;

@Autonomous

public class Test extends LinearOpMode {
    
    private Drive d;
    private Collect c;
    private Find f;
    private BNO055IMU imu;
    private Gpsbrain gps;

    @Override
    public void runOpMode() {

        d = new Drive(
            hardwareMap.get(DcMotor.class, "lf"),
            hardwareMap.get(DcMotor.class, "lb"),
            hardwareMap.get(DcMotor.class, "rf"),
            hardwareMap.get(DcMotor.class, "rb")
        );
        
        c = new Collect(
        );
            
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        
        f = new Find(
            // hardwareMap.get(DistanceSensor.class, "sensor_range")
        );
    
        gps = new Gpsbrain(d, imu, c, f);
    
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        gps.turn(90);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            telemetry.addData("State:", gps.state);
            
            gps.update();
            
            telemetry.addData("Angle: ", gps.getAngle());
            telemetry.addData("Left Front: ", d.getPower("lf"));
            // double a = gps.getAngle();
            // telemetry.addData("Angle", a);
            // f.senseSkystones();
            // int skystones = f.countSkystones();
            // double angle = f.findSkystoneAngle();
            // telemetry.addData("Skystones", skystones);
            // telemetry.addData("Angle", angle);
            telemetry.update();
        }
    }
}