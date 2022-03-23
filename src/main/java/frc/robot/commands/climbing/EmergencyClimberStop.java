// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climbing;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class EmergencyClimberStop extends InstantCommand {

  public EmergencyClimberStop() {
    super(Robot.ClimberSubsystem::stopMotors, Robot.ClimberSubsystem);
  }

}
