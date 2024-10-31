/* Copyright (c) 2021 FIRST. All rights reserved.
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

package org.firstinspires.ftc.teamcode.drive.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.util.Encoder;

/*
 * This file contains an example of a Linear "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is m ade from the menu, the corresponding OpMode is executed.
 *
 * This particular OpMode illustrates driving a 4-motor Omni-Directional (or Holonomic) robot.
 * This code will work with either a Mecanum-Drive or an X-Drive train.
 * Both of these drives are illustrated at https://gm0.org/en/latest/docs/robot-design/drivetrains/holonomic.html
 * Note that a Mecanum drive must display an X roller-pattern when viewed from above.
 *
 * Also note that it is critical to set the correct rotation direction for each motor.  See details below.
 *
 * Holonomic drives provide the ability for the robot to move in three axes (directions) simultaneously.
 * Each motion axis is controlled by one Joystick axis.
 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *
 * This code is written assuming that the right-side motors need to be reversed for the robot to drive forward.
 * When you first test your robot, if it moves backward when you push the left stick forward, then you must flip
 * the direction of all 4 motors (see code below).
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Sourish teleOp"  + "")
//@Disabled

// the current teleop
public class TeleOPMyName extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive, armMotor, elevator = null;
    private Encoder leftODO, rightODO = null;
    //private Servo drone_Launcher_servo = null;
    private ElapsedTime armTimer = new ElapsedTime();


    // private int pos = 0;


    private double finger_score = 0.75;
    private int arm_resting_pos = 100;
    private int arm_scoring_pos = 500;
    private int elevator_scoring_pos = 1800;
    private int elevator_resting_pos = 50;


    @Override
    public void runOpMode() {


/////////////////////////////////////////////////////////////////////////////////////////////////////////
//drive
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        armMotor = hardwareMap.get(DcMotor.class, "arm_motor");
        elevator = hardwareMap.get(DcMotor.class, "elevatorA");


////////////////////////////////////////////////////////////////////////////////////
        //drive
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        armMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator.setDirection(DcMotor.Direction.REVERSE);


        telemetry.addData("Status", "Initialized");


        // telemetry.addData("Front left/Right shoulder pos before start", "%4.2f, %4.2f",elevator.getCurrentPosition(), right_Shoulder_Motor.getCurrentPosition());
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        while (opModeIsActive()) {
            double max;


            //game pad 1
            double axial = -gamepad1.left_stick_y;  // Note: pushing stick forward gives negative value
            double lateral = gamepad1.left_stick_x;
            double yaw = gamepad1.right_stick_x;
            double bruh = -gamepad2.right_stick_y; // elevator
            //drive G1
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;


/*            while(gamepad1.back){/*works reliably with bool buttons
                if(gamepad1.y){/*drone launch __0__
                    drone_Launcher_servo.scaleRange(0,1);
                    drone_Launcher_servo.setPosition(0);
                }
                if(gamepad1.b){ /*drone hold __1__ 
                    drone_Launcher_servo.scaleRange(0,1);
                    drone_Launcher_servo.setPosition(0.5);
                }
            }
            /* outake last stage fixed 1_6_24 
            if (gamepad1.right_trigger >= 0.5){ /*finger 1 2 up 
                finger_one_servo.scaleRange(0,1);
                finger_one_servo.setPosition(0.60);
                finger_two_servo.scaleRange(0,1);
                finger_two_servo.setPosition(0.60);
            }
            if(gamepad1.left_trigger >= 0.5){/*finger 1 2 down 
                finger_one_servo.scaleRange(0,1);
                finger_one_servo.setPosition(0);
                finger_two_servo.scaleRange(0,1);
                finger_two_servo.setPosition(0);
            } /*

            /* intake now fixed organization needed 1_6_24 */


//G2
            // G2
//            if (gamepad2.dpad_left) {/* elbow down */
//                elbow_motor.setTargetPosition(50);
//                elbow_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                elbow_motor.setPower(0.5);
//                telemetry.addData("elbow down: ", elbow_motor.getCurrentPosition());
//                telemetry.update();
//
//                if(gamepad2.start){
//                    elbow_motor.setPower(0);
//                }
//                else{
//                    continue;
//                }
//
//            }
//            if (gamepad2.dpad_right) {/* elbow up */
//                elbow_motor.setTargetPosition(600);
//                elbow_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                elbow_motor.setPower(0.3);
//                elbow_motor.setTargetPosition(650);
//                elbow_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                elbow_motor.setPower(0.3);
//                telemetry.addData("elbow up: ", elbow_motor.getCurrentPosition());
//                telemetry.update();
//                if(gamepad2.start){
//                    elbow_motor.setPower(0);
//                }
//                else{
//                    continue;
//                }
//
//            }
            if (gamepad2.left_stick_y) { /* elevator down */ // might require boolean controller
                elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.setPower(0.8);

            }

            if (gamepad2.y) { /* elevator up */
                elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                elevator.setPower(-0.8);

            }

            elevator.setPower(0);


            if (gamepad2.x) {
                // Move arm to scoring position
                armMotor.setTargetPosition(arm_scoring_pos);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.5);
                telemetry.addData("Arm run: ", armMotor.getCurrentPosition());
                telemetry.update();

                armTimer.reset();
            }

            if (armTimer.seconds() >= 3) {
                armMotor.setTargetPosition(arm_resting_pos);
                armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armMotor.setPower(0.5);
                telemetry.addData("Arm rest: ", armMotor.getCurrentPosition());
                telemetry.update();
            }

            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));


            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

    }

}