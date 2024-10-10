package org.firstinspires.ftc.teamcode.drive.opmode.test;


import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="MyColorSensorBlue1", group="6976")


public class MyColorSensorBlue1 extends LinearOpMode {
    Team6976HWMap2023 robot = new Team6976HWMap2023();
    ElapsedTime Time = new ElapsedTime();
    private ColorSensor colorsensor;
    int OPG = 0; // 1 = Orange, 2 = Green, 3 = Purple
    float[] hsvValues = new float[3];
    int count = 0;

    @Override
    public void runOpMode() {

        colorsensor = hardwareMap.get(ColorSensor.class, "Sensor");

        robot.Map(hardwareMap); // hardwareMap og
        double distance = 20;
        double tick;

        if (robot.ColorSensor instanceof SwitchableLight) {
            ((SwitchableLight) robot.ColorSensor).enableLight(true);
        }


        waitForStart();


        distance = 20;
        // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tick = (distance * 537.7) / (4 * Math.PI);
        Time.reset();
        /*robot.DriveRightFront.setPower(multy);
        robot.DriveLeftFront.setPower(multy);
        robot.DriveRightBack.setPower(multy);
        robot.DriveLeftBack.setPower(multy);*/
        //while(opModeIsActive() && Time.milliseconds() < 2900 && robot.DriveLeftFront.getCurrentPosition() < tick) {
        //  telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
        // telemetry.update();

        //robot.DriveRightFront.setPower(0);
        //robot.DriveLeftFront.setPower(0);
        //robot.DriveRightBack.setPower(0);
        //robot.DriveLeftBack.setPower(0);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //  sleep(1500);

        //Time.reset();
        while (opModeIsActive() && count < 50) {
            if (robot.ColorSensor instanceof SwitchableLight) {
                SwitchableLight light = (SwitchableLight) robot.ColorSensor;
                light.enableLight(!light.isLightOn());
            }
            NormalizedRGBA colors = robot.ColorSensor.getNormalizedColors();

            Color.colorToHSV(colors.toColor(), hsvValues);

            telemetry.addLine()
                    .addData("H", "%.3f", hsvValues[0])
                    .addData("S", "%.3f", hsvValues[1])
                    .addData("V", "%.3f", hsvValues[2]);
            telemetry.addLine()
                    .addData("a", "%.3f", colors.alpha)
                    .addData("r", "%.3f", colors.red)
                    .addData("g", "%.3f", colors.green)
                    .addData("b", "%.3f", colors.blue);
            telemetry.update();
            /** We also display a conversion of the colors to an equivalent Android color integer.
             * @see Color */
            int color = colors.toColor();
            /*telemetry.addLine("in color: ")
                    .addData("color", color);
            telemetry.addLine("raw Android color: ")
                    .addData("a", "%02x", Color.alpha(color))
                    .addData("r", "%02x", Color.red(color))
                    .addData("g", "%02x", Color.green(color))
                    .addData("b", "%02x", Color.blue(color));*/

            float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
            colors.red /= max;    //i
            colors.green /= max;
            colors.blue /= max;
            color = colors.toColor();

           /* telemetry.addLine("normalized color:  ")
                    .addData("a", "%02x", Color.alpha(color))
                    .addData("r", "%02x", Color.red(color))
                    .addData("g", "%02x", Color.green(color))
                    .addData("b", "%02x", Color.blue(color));
            telemetry.update(); */
            count++;
        }


        Time.reset();
        while (opModeIsActive() && Time.milliseconds() < 300) {
            int Hvalue = (int) hsvValues[0];
            if (Hvalue >= 65 && Hvalue <= 159) {
                // yellow
                OPG = 1;
            } else if (Hvalue > 160 && Hvalue <= 240) {
                //blue
                OPG = 2;
            } else {
                OPG = 3;
                //red
            }


            // Assuming OPG is determined correctly in the color evaluation section

// Move this section to where you handle detected colors
            if (OPG == 1) {

                robot.Elevator.setPower(0.5);
                sleep(5000);
                robot.Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Elevator.setPower(0);


            } else if (OPG == 2) {
                robot.Elevator.setPower(1);
                sleep(5000);
                robot.Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Elevator.setPower(0);


            } else if (OPG == 3) {

                robot.Elevator.setPower(0); // Reverse power (adjust as needed)
                sleep(5000);
                robot.Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.Elevator.setPower(0);
            }
        }}}


//            if (OPG == 1)
//                distance = 20;
//                multy = 0.50;
//                robot.Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                robot.Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                tick = (distance * 537.7) / (4 * Math.PI);
//                Time.reset();
//                robot.Elevator.setPower(1);
//                //robot.DriveLeftFront.setPower(-multy);
//                // robot.DriveRightBack.setPower(-multy);
//                //robot.DriveLeftBack.setPower(+multy);
//                // while(opModeIsActive() && Time.milliseconds() < 1950 && robot.DriveLeftFront.getCurrentPosition() < tick) {
//                //  telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
//                //telemetry.update();
//            }
//            robot.Elevator.setPower(0);
//            // robot.DriveLeftFront.setPower(0);
//            // robot.DriveRightBack.setPower(0);
//            //robot.DriveLeftBack.setPower(0);
//            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            sleep(5000);
//
//            if (OPG == 2) {
//                sleep(5000);
//            }
//            if (OPG == 3) {
//                distance = 20;
//                multy = 1;
//// robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                tick = (distance * 537.7) / (4 * Math.PI);
//                Time.reset();
//                robot.Elevator.setPower(-multy);
////robot.DriveLeftFront.setPower(+multy);
//// robot.DriveRightBack.setPower(+multy);
//// robot.DriveLeftBack.setPower(-multy);
//// while(opModeIsActive() && Time.milliseconds() < 2000 && robot.DriveLeftFront.getCurrentPosition() < tick) {
////   telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
////telemetry.update();
//
//                robot.Elevator.setPower(0);
//
//    /* robot.DriveLeftFront.setPower(0);
//     robot.DriveRightBack.setPower(0);
//     robot.DriveLeftBack.setPower(0);*/
////  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                sleep(5000);
//
////Back
////            distance = 20;
////            multy = 0.25;
////            // robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////            //  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
////            tick = (distance * 537.7)/(4 * Math.PI);
////            Time.reset();
////            robot.DriveRightFront.setPower(multy);
////            robot.DriveLeftFront.setPower(multy);
////            robot.DriveRightBack.setPower(multy);
////            robot.DriveLeftBack.setPower(multy);
////            while(opModeIsActive() && Time.milliseconds() < 500 && robot.DriveLeftFront.getCurrentPosition() < tick) {
////                telemetry.addData("Encoder Val", robot.DriveLeftFront.getCurrentPosition());
////telemetry.update();
//
//                robot.Elevator.setPower(1);
//
//                //robot.DriveLeftFront.setPower(0);
////robot.DriveRightBack.setPower(0);
////robot.DriveLeftBack.setPower(0);
////  robot.DriveLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
////  robot.DriveLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//                sleep(5000);
//            }
//        }
//    }