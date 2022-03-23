package frc.robot.commands.main;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class RunConveyorBelt extends CommandBase {

  private final double speed;

  public RunConveyorBelt(double speed) {
    this.speed = speed;
    addRequirements(Robot.ConveyorBeltSubsystem);
  }

  @Override
  public void initialize() {
    Robot.ConveyorBeltSubsystem.run(speed);
  }

  @Override
  public void end(boolean interrupted) {
    Robot.ConveyorBeltSubsystem.stop();
  }

}
