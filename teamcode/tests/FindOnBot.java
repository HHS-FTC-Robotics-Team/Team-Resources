package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;
import org.firstinspires.ftc.robotcore.external.tfod.TfodSkyStone;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import java.util.stream.Collector;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Collect;
import org.firstinspires.ftc.teamcode.Drive;
import org.firstinspires.ftc.teamcode.Find;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import java.lang.reflect.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TfodSkyStone;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Find extends LinearOpMode {
  
  private DistanceSensor dist = null;
  private VuforiaSkyStone vuforiaSkyStone;
  private TfodSkyStone tfodSkyStone;
  
  List<Recognition> recognitions;
  int ImageHeight;
  float ObjectHeight;
  double ObjectAngle;
  double ObjectHeightRatio;
  int SkystoneCount;
  boolean SkystoneFound;
  double TargetHeightRatio;
  
  public Find(DistanceSensor d) {
    dist = d;
    vuforiaSkyStone = new VuforiaSkyStone();
    tfodSkyStone = new TfodSkyStone();
   
    // Init Vuforia because Tensor Flow needs it.
    vuforiaSkyStone.initialize(
      "", // vuforiaLicenseKey
      VuforiaLocalizer.CameraDirection.BACK, // cameraDirection
      false, // useExtendedTracking
      true, // enableCameraMonitoring
      VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, // cameraMonitorFeedback
      0, // dx
      0, // dy
      0, // dz
      0, // xAngle
      0, // yAngle
      0, // zAngle
      false); // useCompetitionFieldTargetLocations
      
    telemetry.addData("Vuforia", "initialized");
    telemetry.update();
    // Let's use 70% minimum confidence and
    // and no object tracker.
    tfodSkyStone.initialize(vuforiaSkyStone, 0.7F, false, true);
    telemetry.addData(">", "Press Play to start");
    telemetry.update();
    // Set target ratio of object height to image
    // height value corresponding to the length
    // of the robot's neck.
    TargetHeightRatio = 0.8;
    
    tfodSkyStone.activate();
    // We'll loop until gold block captured or time is up
    SkystoneFound = false;
    
  }
  
  
  // "getter" method
  public double getDistance() {
    double distance = dist.getDistance(DistanceUnit.MM);
    return distance;
  }
  
  public double findSkystoneAngle() {
    recognitions = tfodSkyStone.getRecognitions();
    // Report number of recognitions.
    telemetry.addData("Objects Recognized", recognitions.size());
    // If some objects detected...
    if (recognitions.size() > 0) {
      // ...let's count how many are gold.
      SkystoneCount = 0;
      // Step through the stones detected.
      for (Recognition recognition : recognitions) {
        if (recognition.getLabel().equals("Skystone")) {
          // A Skystone has been detected.
          SkystoneCount = SkystoneCount + 1;
          // We can assume this is the first Skystone
          // because we break out of this loop below after
          // using the information from the first Skystone.
          // We don't need to calculate turn angle to Skystone
          // because TensorFlow has estimated it for us.
          ObjectAngle = recognition.estimateAngleToObject(AngleUnit.DEGREES);
          // Negative angle means Skystone is left, else right.
          telemetry.addData("Estimated Angle", ObjectAngle);
          return ObjectAngle;
        }
      }
    }
    return ObjectAngle;
  }
    
    public int countSkystones() {
      recognitions = tfodSkyStone.getRecognitions();
      // Report number of recognitions.
      telemetry.addData("Objects Recognized", recognitions.size());
      // If some objects detected...
      if (recognitions.size() > 0) {
        // ...let's count how many are gold.
        SkystoneCount = 0;
        // Step through the stones detected.
        for (Recognition recognition : recognitions) {
          if (recognition.getLabel().equals("Skystone")) {
            // A Skystone has been detected.
            SkystoneCount = SkystoneCount + 1;
          }
        }
        return SkystoneCount;
      }
      return SkystoneCount;
    }
  
  

  
  public void runOpMode() {
    
  }
}