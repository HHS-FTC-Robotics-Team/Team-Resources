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

// Carlo note
// This file will test a color, distance, and touch sensor.
// Their names should be "sensor_color", "sensor_range", and "sensor_digital".

package org.firstinspires.ftc.teamcode;

// opmode stuff
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
// for color
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.hardware.ColorSensor;
// distance
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
// touch
import com.qualcomm.robotcore.hardware.DigitalChannel;


@TeleOp(name = "Full Sensor Test", group = "Sensor")

public class FullSensorTest extends LinearOpMode {

  ColorSensor colorSensor;
  DigitalChannel digitalTouch;
  DistanceSensor sensorRange;

  @Override
  public void runOpMode() {

    // get a reference to our sensors
    colorSensor = hardwareMap.get(ColorSensor.class, "sensor_color");
    sensorRange = hardwareMap.get(DistanceSensor.class, "sensor_range");
    digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");

    // set the digital channel to input (for touch sensor)
    digitalTouch.setMode(DigitalChannel.Mode.INPUT)
    // turn on color sensor
    colorSensor.enableLed(true);

    // wait for the start button to be pressed.
    waitForStart();
    while (opModeIsActive()) {

      telemetry.addData("Clear", colorSensor.alpha());
      telemetry.addData("Red  ", colorSensor.red());
      telemetry.addData("Green", colorSensor.green());
      telemetry.addData("Blue ", colorSensor.blue());

      telemetry.addData("deviceName",sensorRange.getDeviceName() );
      telemetry.addData("range", String.format("%.01f mm", sensorRange.getDistance(DistanceUnit.MM)));
      telemetry.addData("range", String.format("%.01f cm", sensorRange.getDistance(DistanceUnit.CM)));
      telemetry.addData("range", String.format("%.01f m", sensorRange.getDistance(DistanceUnit.METER)));
      telemetry.addData("range", String.format("%.01f in", sensorRange.getDistance(DistanceUnit.INCH)));

      if (digitalTouch.getState() == true) {
          telemetry.addData("Digital Touch", "Is Not Pressed");
      } else {
          telemetry.addData("Digital Touch", "Is Pressed");
      }

      telemetry.update();
    }
  }
}
