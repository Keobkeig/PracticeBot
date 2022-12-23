package com.stuypulse.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.stuypulse.stuylib.control.Controller;
import com.stuypulse.stuylib.control.feedback.PIDController;
import com.stuypulse.stuylib.control.feedforward.Feedforward;
import com.stuypulse.stuylib.network.SmartNumber;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase { 
    // motor and sensor fields
    private CANSparkMax motor;
    private RelativeEncoder encoder;

    private SmartNumber target;
    private Controller controller;
    // 

    public Shooter() {
        motor = new CANSparkMax(0, MotorType.kBrushless);
        encoder = motor.getEncoder();

        target = new SmartNumber("Target RPM", 0.0);
        controller = new Feedforward.Motor(0.2,0.002,0.001).velocity()
            .add(new PIDController(0.1, 0.0, 0.0));
    }
    

    public void setTargetRPM(double target) {
        this.target.set(target);
    }

    public boolean isAtTarget(double epilson) {
        return controller.isDone(epilson);
    }

    
    @Override
    public void periodic() {
        motor.setVoltage(controller.update(target.get(), encoder.getVelocity()));
    }
}
