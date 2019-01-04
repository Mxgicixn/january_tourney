/*
ADB guide can be found at:
https://ftcprogramming.wordpress.com/2015/11/30/building-ftc_app-wirelessly/
*/
package org.firstinspires.ftc.teamcode.Real_Opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.Arrays;

/*
This code is written as an example only.
Obviously, it was not tested on your team's robot.
Teams who use and reference this code are expected to understand code they use.
If you use our code and see us at competition, come say hello!
*/

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="Pranav the varun", group="TeleOp")
public class ArcadeTeleop2 extends OpMode {

    private static final double TRIGGERTHRESHOLD = .2;
    private static final double ACCEPTINPUTTHRESHOLD = .15;
    private static final double SCALEDPOWER = 1; //Emphasis on current controller reading (vs current motor power) on the drive train
    /*private DcMotor leftFrontWheel;
    private DcMotor leftBackWheel;
    private DcMotor rightBackWheel;
    private DcMotor rightFrontWheel;*/

    private static DcMotor leftFrontWheel, leftBackWheel, rightFrontWheel, rightBackWheel;
    @Override
    public void init() {
        leftFrontWheel = hardwareMap.dcMotor.get("lf");
        leftBackWheel = hardwareMap.dcMotor.get("ld");
        rightFrontWheel = hardwareMap.dcMotor.get("rf");
        rightBackWheel = hardwareMap.dcMotor.get("rd");
        leftFrontWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBackWheel.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        leftFrontWheel.setPower(v1);
        rightFrontWheel.setPower(v2);
        leftBackWheel.setPower(v3);
        rightBackWheel.setPower(v4);
    }

    // y - forwards
    // x - side
    // c - rotation

    }
