package org.firstinspires.ftc.teamcode.ftclib.internal.sensor;

import com.qualcomm.robotcore.hardware.GyroSensor;

/**
 * Created by Gabriel on 2018-01-02.
 */

public class GyroSensorSensor implements DerivativeSensor {
    GyroSensor gyro;
    HeadingConverter converter = new HeadingConverter();
    public GyroSensorSensor(GyroSensor gyro) {
        this.gyro = gyro;
    }
    @Override
    public double getValue() {
        converter.update(-gyro.getHeading()*Math.PI/180);
        return converter.getConvertedHeading();
    }

    @Override
    public double getDerivative() {
        return gyro.rawZ();
    }
}
