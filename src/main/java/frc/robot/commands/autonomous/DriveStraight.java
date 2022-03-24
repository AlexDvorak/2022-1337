package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveStraight extends CommandBase {

  private final double speed;

  public DriveStraight(double speed) {
    this.speed = speed;
    addRequirements(Robot.DriveSubsystem);
  }

  @Override
  public void initialize() {
    Robot.DriveSubsystem.ArcadeDrive(this.speed, 0);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.DriveSubsystem.ArcadeDrive(0, 0);
  }

}
