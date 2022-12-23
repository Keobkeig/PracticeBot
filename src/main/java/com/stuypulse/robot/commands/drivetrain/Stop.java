package com.stuypulse.robot.commands.drivetrain;

import com.stuypulse.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Stop extends CommandBase{
    
    Drivetrain drivetrain;

    public Stop(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;

        addRequirements(drivetrain);
    }

    public void execute() {
        drivetrain.stop();
    }
}
