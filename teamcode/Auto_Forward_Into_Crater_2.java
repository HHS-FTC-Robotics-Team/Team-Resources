package REVRobotics;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Func;

@Autonomous(name="autonomous Forward then Straight into crater")

public class Auto_Forward_Into_Crater_2 extends OpMode { 

    DcMotor right_aft, left_aft, right_fore, left_fore, lifter, collector;
    
    public void init() {
        right_aft = hardwareMap.dcMotor.get("right_back");
        left_aft = hardwareMap.dcMotor.get("left_back");
        right_fore = hardwareMap.dcMotor.get("right_front");
        left_fore = hardwareMap.dcMotor.get("left_front");
        lifter = hardwareMap.dcMotor.get("lifter");
        //collector = hardwareMap.dcMotor.get("sweeper");
        lifter.setPower(1);
        telemetry.addLine("lifter started");
    }
    
    int step = 0;
    public void loop() {
        
        if(step == 0) {
            lowerRobot();
        }
        if(step == 1) {
            moveForward();
        }
        if(step == 2) {
            stopTurn();
        }
        if(step == 3) {
            driveToCrater();
        }
        if(step == 4) {
            continueForwardIntoCrater();
        }
        if(step == 5) {
            continueForwardIntoCrater();
        }
        if(step == 6) {
            continueForwardIntoCrater();
        }
        if(step == 7) {
            continueForwardIntoCrater();
        }
        step++;
    }
    
    public void moveForward() {
        
        right_aft.setPower(0.5);
        left_aft.setPower(0.5);
        right_fore.setPower(-0.5);
        left_fore.setPower(-0.5);
        
        try {
            Thread.sleep(6000);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
        left_fore.setPower(-0.5);
        left_aft.setPower(-0.5);
        right_fore.setPower(-0.4);
        right_aft.setPower(-0.4);
        
    }
    
    public void stopTurn() {
        lifter.setPower(0);
        
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
        left_fore.setPower(0);
        left_aft.setPower(0);
        right_fore.setPower(0);
        right_aft.setPower(0);
    }
    
    public void lowerRobot() {
        lifter.setPower(-0.7);
        try {
            Thread.sleep(800);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        lifter.setPower(1);
    }

    
    public void driveToCrater() {
        left_fore.setPower(0.7);
        left_aft.setPower(0.7);
        right_fore.setPower(-0.7);
        right_aft.setPower(-0.7);
        
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        
        left_fore.setPower(1);
        left_aft.setPower(1);
        right_fore.setPower(-1);
        right_aft.setPower(-1);
        
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            
        }
        
        left_fore.setPower(0);
        left_aft.setPower(0);
        right_fore.setPower(0);
        right_fore.setPower(0);
        
    }
    
    public void continueForwardIntoCrater() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}    
