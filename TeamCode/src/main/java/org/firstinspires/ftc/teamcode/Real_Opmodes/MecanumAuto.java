package org.firstinspires.ftc.teamcode.Real_Opmodes;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMUImpl;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.ftclib.internal.controller.ErrorTimeThresholdFinishingAlgorithm;
import org.firstinspires.ftc.teamcode.ftclib.internal.controller.FinishableIntegratedController;
import org.firstinspires.ftc.teamcode.ftclib.internal.controller.PIDController;
import org.firstinspires.ftc.teamcode.ftclib.internal.drivetrain.HeadingableMecanumDrivetrain;
import org.firstinspires.ftc.teamcode.ftclib.internal.sensor.IntegratingGyroscopeSensor;

/**
 * Created by Michaela on 1/3/2018.
 * Demonstrates the use of a HeadingableMecanumDrivetrain to autonomously rotate a robot to specific headings and hold those headings.
 * Tested and found fully functional by Gabriel on 2018-8-4.
 */


@Autonomous(name = "MecanumAuto", group = "auto")

public class MecanumAuto extends LinearOpMode {
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public HeadingableMecanumDrivetrain drivetrain;

    public FinishableIntegratedController controller;

    public BNO055IMUImpl imu;

    /**
     * Override this method and place your code here.
     * <p>
     * Please do not swallow the InterruptedException, as it is used in cases
     * where the op mode needs to be terminated early.
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {   //Notice that this is almost the exact same code as in HeadingableOmniwheelRotationAutonomous.
        frontLeft = hardwareMap.get(DcMotor.class, "lf");
        frontRight = hardwareMap.get(DcMotor.class, "rf");
        backLeft = hardwareMap.get(DcMotor.class, "ld");
        backRight = hardwareMap.get(DcMotor.class, "rd");

        imu = hardwareMap.get(BNO055IMUImpl.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //Add calibration file?
        parameters.loggingEnabled = true;   //For debugging
        parameters.loggingTag = "IMU";      //For debugging
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();  //Figure out why the naive one doesn't have a public constructor
        imu.initialize(parameters);
        while (!imu.isGyroCalibrated()) ;
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        encoder(1000, 0.5);
        sleep(100);
        encoder(100, 0.5);


    }

    public void encoder(int value, double power){
        frontLeft.setTargetPosition(value);
        frontRight.setTargetPosition(value);
        backRight.setTargetPosition(value);
        backLeft.setTargetPosition(value);
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        while(frontRight.isBusy() && frontLeft.isBusy() && backRight.isBusy() && backLeft.isBusy() && opModeIsActive()){




        }


        while(!frontRight.isBusy() && !frontLeft.isBusy() && !backRight.isBusy() && !backLeft.isBusy() && opModeIsActive()){
            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        }


    }
}


