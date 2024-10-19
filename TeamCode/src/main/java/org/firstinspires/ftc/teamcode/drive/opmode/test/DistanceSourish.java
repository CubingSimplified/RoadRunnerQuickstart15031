package org.firstinspires.ftc.teamcode.drive.opmode.test;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
 * This OpMode illustrates how to use the REV Robotics 2M Distance Sensor.
 *
 * The OpMode assumes that the sensor is configured with a name of "sensor_distance".
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 *
 * See the sensor's product page: https://www.revrobotics.com/rev-31-1505/
 */
@TeleOp(name = "DistanceSensor", group = "6976")

public class DistanceSourish extends LinearOpMode {

    private DistanceSensor sD;
    Team6976HWMap2023 robot = new Team6976HWMap2023();
    @Override
    public void runOpMode() {
        // you can use this as a regular DistanceSensor.
        sD = hardwareMap.get(DistanceSensor.class, "SourishDistance");
        Servo light;
        light = hardwareMap.get(Servo.class, "Lights");

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor) sD;

        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {

            double distanceMeters = sD.getDistance(DistanceUnit.METER);


            if (distanceMeters >= 0.5) {
                light.setPosition(0.32);
            } else {
                light.setPosition(0.0);
            }

            // generic DistanceSensor methods.
            telemetry.addData("deviceName", sD.getDeviceName() );
            telemetry.addData("rangeMM", String.format("%.01f mm", sD.getDistance(DistanceUnit.MM)));
            telemetry.addData("rangeCM", String.format("%.01f cm", sD.getDistance(DistanceUnit.CM)));
            telemetry.addData("rangeM", String.format("%.01f m", sD.getDistance(DistanceUnit.METER)));
            telemetry.addData("rangeINCH", String.format("%.01f in", sD.getDistance(DistanceUnit.INCH)));

            // Rev2mDistanceSensor specific methods.
            telemetry.addData("ID", String.format("%x", sensorTimeOfFlight.getModelID()));
            telemetry.addData("did time out", Boolean.toString(sensorTimeOfFlight.didTimeoutOccur()));


            telemetry.update();
        }
    }

}
