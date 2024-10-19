package org.firstinspires.ftc.teamcode.drive.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="TouchSense", group="6976")
public class TouchSourish extends LinearOpMode {
    Team6976HWMap2023 robot = new Team6976HWMap2023();
    TouchSensor touchSensor;  // Touch sensor Object
  //  DcMotor motor;            // Motor Object
    Servo light;


    @Override
    public void runOpMode() {
        // get a reference to our touchSensor object.
        touchSensor = hardwareMap.get(TouchSensor.class, "SensorTouch");

        // get a reference to our motor object (assumes motor is named "motor").
       //  motor = hardwareMap.get(DcMotor.class, "Elevator");
        light = hardwareMap.get(Servo.class, "Lights");

        // wait for the start button to be pressed.
        waitForStart();

        // while the OpMode is active, loop and read whether the sensor is being pressed.
        while (opModeIsActive()) {
            // Check if the touch sensor is pressed
            if (touchSensor.isPressed()) {
                telemetry.addData("Touch Sensor", "Is Pressed");
                light.setPosition(0.32); // Start motor
            } else {
                telemetry.addData("Touch Sensor", "Is Not Pressed");
                light.setPosition(0.0); // Stop the motor
            }

            telemetry.update();
        }
    }
}
