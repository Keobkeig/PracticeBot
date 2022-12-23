package com.stuypulse.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stuypulse.stuylib.control.Controller;
import com.stuypulse.stuylib.network.SmartNumber;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{

    private CANSparkMax[] leftMotors;
    private CANSparkMax[] rightMotors;

    private Encoder leftGrayhill;
    private Encoder rightGrayhill;

    SmartNumber leftTargetVelocity;
    SmartNumber rightTargetVelocity;

    private Controller leftController;
    private Controller rightController;

    

    public Drivetrain() {
        leftMotors =
                new CANSparkMax[] {
                    new CANSparkMax(10, MotorType.kBrushless),
                    new CANSparkMax(11, MotorType.kBrushless),
                    new CANSparkMax(12, MotorType.kBrushless)
                };

        rightMotors =
                new CANSparkMax[] {
                    new CANSparkMax(13, MotorType.kBrushless),
                    new CANSparkMax(14, MotorType.kBrushless),
                    new CANSparkMax(15, MotorType.kBrushless)
                };
        
        leftGrayhill = new Encoder(0, 1);
        rightGrayhill = new Encoder(2, 3);

        leftTargetVelocity = new SmartNumber("Left Target Velocity", 0);
        rightTargetVelocity = new SmartNumber("Right Target Velocity", 0);
    }

    public void stop() {
        for(CANSparkMax motor: leftMotors)
            motor.stopMotor();
        for(CANSparkMax motor: rightMotors)
            motor.stopMotor();    
    }

    private void setEncoderDistancePerPulse(double distance) {
        rightGrayhill.setDistancePerPulse(distance);
        rightGrayhill.reset();

        leftGrayhill.setDistancePerPulse(distance);
        leftGrayhill.reset();
    }

    public void periodic() {    
    }
}
