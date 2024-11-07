package org.firstinspires.ftc.teamcode.drive.opmode.test;


import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class Team6976HWMap2023 {
    // public DcMotor DriveRightBack = null;
    //  public DcMotor DriveLeftBack = null;
    //public DcMotor DriveLeftFront = null;
    //public DcMotor DriveRightFront = null;

    public DcMotor elevator = null;
    public TouchSensor SensorTouch = null;
    public Servo Light = null;
    //public Servo Intake = null;

   public ColorSensor sensor = null;
    public NormalizedColorSensor ColorSensor = null;
    public DistanceSensor sD = null;
    public DcMotor armMotor = null;
    public Servo claw = null;
    public Servo intake = null;


    HardwareMap hwMap =  null;

    public void Map(HardwareMap hardwareMap)
    {
        hwMap = hardwareMap;
         /* DriveLeftFront = hwMap.get(DcMotor.class,"DriveLeftFront");
          DriveRightFront = hwMap.get(DcMotor.class,"DriveRightFront");
          DriveLeftBack = hwMap.get(DcMotor.class,"DriveLeftBack");
          DriveRightBack = hwMap.get(DcMotor.class,"DriveRightBack");

          DriveLeftFront.setDirection(DcMotor.Direction.FORWARD);
          DriveLeftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          DriveLeftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

          DriveRightFront.setDirection(DcMotor.Direction.REVERSE);
          DriveRightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          DriveRightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

          DriveLeftBack.setDirection(DcMotor.Direction.FORWARD);
          DriveLeftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          DriveLeftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

          DriveRightBack.setDirection(DcMotor.Direction.REVERSE);
          DriveRightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
          DriveRightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); */

        elevator = hwMap.get(DcMotor.class, "elevatorA");
        elevator.setDirection(DcMotorSimple.Direction.REVERSE);
        elevator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        armMotor = hwMap.get(DcMotor.class, "arm_motor");
        claw = hwMap.get(Servo.class,"claw");
        intake = hardwareMap.get(Servo.class, "intake");

        // ColorSensor = hwMap.get(NormalizedColorSensor.class, "Sensor");
      //  ColorSensor = hwMap.get(ColorSensor.class,"Sensor")
         SensorTouch = hwMap.get(TouchSensor.class, "SensorTouch");
        Light = hwMap.get(Servo.class, "Lights");
        sD = hardwareMap.get(DistanceSensor.class, "SourishDistance");


    }

}
