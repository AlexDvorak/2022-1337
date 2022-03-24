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

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static final DriveSubsystem DriveSubsystem = new DriveSubsystem();
  public static final IntakeSubsystem IntakeSubsystem = new IntakeSubsystem();
  public static final ConveyorBeltSubsystem ConveyorBeltSubsystem = new ConveyorBeltSubsystem();
  public static final ClimberSubsystem ClimberSubsystem = new ClimberSubsystem();

  private final SendableChooser<Command> autonChooser = new SendableChooser<>();
  private Command selectedAuto;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    autonChooser.setDefaultOption("Do Nothing", new InstantCommand());
    autonChooser.addOption("Taxi Off Tarmac", new DriveStraight(0.3).withTimeout(2));

    SmartDashboard.putData("Auto choices", autonChooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // This is required for the robot to work at all
    CommandScheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
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

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    // Set 'DriveCommand' to run whenever no other command requires the DriveSubsystem
    DriveSubsystem.setDefaultCommand(new DriveCommand());
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

}
