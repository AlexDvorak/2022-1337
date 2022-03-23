// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class StartConveyorBelt extends InstantCommand {

  private final double speed;

  public StartConveyorBelt(double speed) {
    this.speed = speed;
    addRequirements(Robot.ConveyorBeltSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.ConveyorBeltSubsystem.run(speed);
  }

}
