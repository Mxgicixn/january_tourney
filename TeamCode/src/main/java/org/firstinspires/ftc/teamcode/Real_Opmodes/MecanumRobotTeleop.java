package org.firstinspires.ftc.teamcode.Real_Opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftclib.internal.drivetrain.MecanumDrivetrain;

/**
 * Created by yer boi
 */

//@Disabled
@TeleOp(name = "Mecanum Tele", group = "Tele")

public class MecanumRobotTeleop extends OpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor armSlide;
    /*public DigitalChannel digitalTouch; //what is DigitalChannel
    public DcMotor latch;
    public CRServo latchServo;*/

    public MecanumDrivetrain drivetrain;

    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "ld");
        backRight = hardwareMap.get(DcMotor.class, "rd");
        armSlide = hardwareMap.get(DcMotor.class, "armSlide");
       // latch = hardwareMap.get(DcMotor.class, "latch");

        drivetrain = new MecanumDrivetrain(new DcMotor[]{frontLeft, frontRight, backLeft, backRight});
    }
    public void start(){
        //digitalTouch = hardwareMap.get(DigitalChannel.class, "sensor_digital");
       // digitalTouch.setMode(DigitalChannel.Mode.INPUT);

    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        double course = Math.atan2(-gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.PI/4;
        double velocity = Math.hypot(gamepad1.right_stick_x, gamepad1.right_stick_y);
        double rotation = -gamepad1.left_stick_x;
        double latchPower = gamepad2.left_stick_x;

        while(gamepad1.left_bumper){
                armSlide.setPower(-1);
        }

        while(!gamepad1.left_bumper && !gamepad1.right_bumper ){
            armSlide.setPower(0);
        }

        while(gamepad1.right_bumper){
            armSlide.setPower(1);
        }
        //latch.setPower(latchPower);
        /*latchServo.setPower(latchPower);
        while(digitalTouch.getState() == true && latchServo.getPower() == latchPower){
            latch.setPower(0);
            latchServo.setPower(0);
        }
        if (digitalTouch.getState() == true) {
            telemetry.addData("Digital Touch", "Is Not Pressed");
        } else {
            telemetry.addData("Digital Touch", "Is Pressed");
        }*/


        drivetrain.setCourse(course);
        drivetrain.setVelocity(velocity);
        drivetrain.setRotation(rotation);

        telemetry.addData("course", course);
        telemetry.addData("velocity", velocity);
        telemetry.addData("rotation", rotation);
        telemetry.update();

    }
}

