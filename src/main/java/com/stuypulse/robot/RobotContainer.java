/************************ PROJECT PHIL ************************/
/* Copyright (c) 2023 StuyPulse Robotics. All rights reserved.*/
/* This work is licensed under the terms of the MIT license.  */
/**************************************************************/

package com.stuypulse.robot;

import java.util.function.BooleanSupplier;

import com.stuypulse.robot.commands.auton.DoNothingAuton;
import com.stuypulse.robot.constants.Ports;
import com.stuypulse.stuylib.input.Gamepad;
import com.stuypulse.stuylib.input.gamepads.AutoGamepad;
import com.stuypulse.stuylib.input.gamepads.PS4;
import com.stuypulse.stuylib.network.SmartNumber;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {

    // Gamepads
    public final Gamepad naowal = new PS4(2);
    public final Gamepad driver = new AutoGamepad(Ports.Gamepad.DRIVER); //stores methods when gamepad is pressed
    public final Gamepad operator = new AutoGamepad(Ports.Gamepad.OPERATOR);
    
    // Subsystem

    // Autons
    private static SendableChooser<Command> autonChooser = new SendableChooser<>();

    // Robot container

    public RobotContainer() {
        configureDefaultCommands();
        configureButtonBindings();
        configureAutons();
        //SmartDashboard is software to get values to and from the robot (basically networking to other device)
        SmartDashboard.putBoolean("Left Bumper Pressed", true); //retrives robot values and unchangeable by client
        SmartDashboard.putNumber("Felice/Number", 694.0);
        SmartNumber rahel = new SmartNumber("Numer", 0 ); //stores network entry to robot to use and can read from it...default value
        //SmartNumber can be changed but doesn't change without user changing it.
        rahel.get();
        rahel.set(2.0);
        Shuffleboard.selectTab(5); //program used to see SmartDashboard values
        //Smartdashboard usually used in periodic to for constantly changing values 

    }

    /****************/
    /*** DEFAULTS ***/
    /****************/

    private void configureDefaultCommands() {
        // just run when nothing is happening, caan take in an gamepad to run 
    }

    /***************/
    /*** BUTTONS ***/
    /***************/

    private void configureButtonBindings() {
            driver.getBottomButton().onTrue(new InstantCommand());
            driver.getBottomButton(); //one time runs
            BooleanSupplier r = driver::getRawLeftBumper; // :: allows the command to be ran continuously
            //, doesn't need the () notation for methods for it reference it
            r.getAsBoolean(); //returns boolean
    }

    /**************/
    /*** AUTONS ***/
    /**************/

    public void configureAutons() {
        autonChooser.setDefaultOption("Do Nothing", new DoNothingAuton());

        SmartDashboard.putData("Autonomous", autonChooser);
    }

    public Command getAutonomousCommand() {
        return autonChooser.getSelected();
    }
}
