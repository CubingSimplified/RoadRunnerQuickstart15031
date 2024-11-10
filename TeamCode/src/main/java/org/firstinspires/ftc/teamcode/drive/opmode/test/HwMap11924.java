package org.firstinspires.ftc.teamcode.drive.opmode.test;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HwMap11924 {
    public DcMotor leftFrontDrive, leftBackDrive, rightFrontDrive, rightBackDrive, armMotor, elevator = null;
    //    private Encoder leftODO, rightODO = null;
    public Servo claw, bucket = null;
    public CRServo intake = null;
    HardwareMap hwMap =  null;
   public void HwMap(HardwareMap hardwareMap){
        hwMap = hardwareMap;


       leftFrontDrive = hwMap.get(DcMotor.class, "left_front_drive");
       leftBackDrive = hwMap.get(DcMotor.class, "left_back_drive");
       rightFrontDrive = hwMap.get(DcMotor.class, "right_front_drive");
       rightBackDrive = hwMap.get(DcMotor.class, "right_back_drive");

       armMotor = hwMap.get(DcMotor.class, "arm_motor");
       elevator = hwMap.get(DcMotor.class, "elevator_motor");
       claw = hwMap.get(Servo.class, "claw");
       bucket = hardwareMap.get(Servo.class,"bucket");
       intake = hardwareMap.get(CRServo.class, "intake");

////////////////////////////////////////////////////////////////////////////////////
       //drive
       leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
       leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
       rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
       rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

       armMotor.setDirection(DcMotorSimple.Direction.FORWARD);
       elevator.setDirection(DcMotor.Direction.REVERSE);
       armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); // Reset encoder to zero*/

   }


}
