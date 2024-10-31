package org.firstinspires.ftc.teamcode.drive.opmode.test;



import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.annotation.Target;

public class MotorEncodersPractice extends OpMode {
    DcMotor elevator;
    double ticks = 2781.1;
    double newTarget;
    int turnage;

    @Override
    public void init() {
        elevator = hardwareMap.get(DcMotor.class, "elevatorA");
        telemetry.addData("Hardware", "initialized");
        elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    @Override
    public void loop() {
        if(gamepad1.a){
            encoder(3);
        }
        telemetry.addData("Motor Ticks: ", elevator.getCurrentPosition());
        if(gamepad1.b){
            tracker();
        }

    }
    public void encoder(int turnage){
        newTarget = ticks/turnage;
        elevator.setTargetPosition((int)newTarget);
        elevator.setPower(0.5);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void tracker(){
        elevator.setTargetPosition(0);
        elevator.setPower(0.8);
        elevator.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}