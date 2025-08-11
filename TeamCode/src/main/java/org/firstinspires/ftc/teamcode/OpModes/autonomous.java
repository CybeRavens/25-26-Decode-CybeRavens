//Stores functions of going forward, backwards, turning, and strafing
//Can import if ever needed

package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "right side auton", group = "Autonomous")

public class autonomous extends LinearOpMode{
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;

    public void forward(double speed, int time) {
        frontRight.setPower(speed);
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
        sleep(time);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void turnLeft(double speed, int time) {
        frontRight.setPower(-speed);
        frontLeft.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        sleep(time);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void turnRight(double speed, int time) {
        frontRight.setPower(speed);
        frontLeft.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        sleep(time);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void strafeRight(double speed, int time) {
        frontRight.setPower(-speed);
        frontLeft.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        sleep(time);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void strafeLeft(double speed, int time) {
        frontRight.setPower(speed);
        frontLeft.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        sleep(time);
        frontRight.setPower(0);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRight = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftMotor");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        forward(0.7, 200);

    }
}
