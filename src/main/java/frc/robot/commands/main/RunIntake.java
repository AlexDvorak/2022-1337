// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.main;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class RunIntake extends CommandBase {

  public RunIntake() {
    addRequirements(Robot.IntakeSubsystem);
  }

  @Override
  public void initialize() {
    Robot.IntakeSubsystem.deploy();
    Robot.IntakeSubsystem.spin(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.IntakeSubsystem.stopMotor();
    Robot.IntakeSubsystem.retract();
  }

}
