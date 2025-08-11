package org.firstinspires.ftc.teamcode.OpModes;
import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class tele extends OpMode {
    // initialize movement motors
    DcMotor frontRight;
    DcMotor frontLeft;
    DcMotor backRight;
    DcMotor backLeft;
    // linear slide
    DcMotor slide;
    // all of the servos
    Servo servo2;
    Servo servo3;
    // variables for movement
    double drive;
    double turn;
    double strafe;
    double fLeftPow, fRightPow, bLeftPow, bRightPow;
    double powerLevel;
    int targetPosition = 50;
    int currentPosition = 0;
    int position;

    @Override

    public void init() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRightMotor");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeftMotor");
        backRight = hardwareMap.get(DcMotor.class, "backRightMotor");
        backLeft = hardwareMap.get(DcMotor.class, "backLeftMotor");
        powerLevel = 0.1;
        telemetry.addData("Hardware: ", "Initialized");
        slide = hardwareMap.get(DcMotor.class, "linearSlide");
        servo2 = hardwareMap.get(Servo.class, "servo0");
        servo3 = hardwareMap.get(Servo.class, "servo1");
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    @Override
    public void loop() {
        // drive checks the movement controllers left joystick up and down
        drive = gamepad1.left_stick_y * -1;
        // turn checks movement controllers right joystick left and right
        turn = gamepad1.right_stick_x * 1;
        telemetry.addData("t", turn);
        // strafe checks movement controllers left joystick left and right
        strafe = gamepad1.left_stick_x * -1;

        // set direction of motors
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // determine power of variables
        fLeftPow = Range.clip(drive + turn + strafe, -0.67, 0.67);
        bLeftPow = Range.clip(drive + turn - strafe, -0.64, 0.64);
        fRightPow = Range.clip(drive - turn - strafe, -0.67, 0.67);
        bRightPow = Range.clip(drive - turn + strafe, -0.64, 0.64);

        frontLeft.setPower(fLeftPow);
        backLeft.setPower(bLeftPow);
        frontRight.setPower(fRightPow);
        backRight.setPower(bRightPow);

        //////////////////slide going up////////////////////////////
        currentPosition = slide.getCurrentPosition();
        telemetry.addData("height: ", currentPosition);
        telemetry.addData("height: ", gamepad2.right_stick_y);
        if (currentPosition <= -8000) {
            if (gamepad2.right_stick_y > 0) {
                slide.setPower(1);
            } else {
                slide.setPower(0);
            }
        } else if (currentPosition >= 10) {
            if (gamepad2.right_stick_y < 0) {
                slide.setPower(-1);
            } else {
                slide.setPower(0);
            }
        } else {
            slide.setPower(gamepad2.right_stick_y);
        }
        //////////////////intake///////////////////////////
        //tightening the block in the arm with servo
        //the servo tight should always be plugged into the first servo port
        if (gamepad2.a) {
            servo3.setPosition(0.6);
            sleep(600);
            telemetry.addData("servo", servo3.getPosition());
        }

        if (gamepad2.b) {
            servo3.setPosition(0.4);
            telemetry.addData("servo", servo3.getPosition());
        }

        //turning
        if (gamepad2.right_bumper) {
            servo2.setPosition(0.8);
            sleep(600);
            telemetry.addData("servo", servo3.getPosition());
        }

        if (gamepad2.left_bumper) {
            servo2.setPosition(0.375);
            sleep(600);
            telemetry.addData("servo", servo3.getPosition());
        }
    }
    }



