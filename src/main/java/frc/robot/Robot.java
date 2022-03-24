// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.*;
import frc.robot.commands.autonomous.*;
import frc.robot.commands.main.DriveCommand;

public class Robot extends TimedRobot {

  public static final DriveSubsystem DriveSubsystem = new DriveSubsystem();
  public static final IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  public static final ConveyorBeltSubsystem ConveyorBeltSubsystem = new ConveyorBeltSubsystem();
  public static final ClimberSubsystem ClimberSubsystem = new ClimberSubsystem();

  private final SendableChooser<Command> autonChooser = new SendableChooser<>();
  private Command selectedAuto;

  /** This function is run when the robot is first started up */
  @Override
  public void robotInit() {
    OI.mapButtons();

    autonChooser.setDefaultOption("Do Nothing", new InstantCommand());
    autonChooser.addOption("Taxi Off Tarmac", new DriveStraight(0.3).withTimeout(2));

    SmartDashboard.putData("Auto choices", autonChooser);
  }

  @Override
  public void autonomousInit() {
    selectedAuto = autonChooser.getSelected();

    if (selectedAuto != null) {
      System.out.println("Auto selected: " + selectedAuto.getName());
      selectedAuto.schedule();
    } else {
      System.out.println("Auto selected: No auto was selected");
    }
  }

  @Override
  public void teleopInit() {
    // Set 'DriveCommand' to run whenever no other command requires the DriveSubsystem
    DriveSubsystem.setDefaultCommand(new DriveCommand());
  }

  @Override
  public void disabledInit() {}

  @Override
  public void testInit() {}

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

}
