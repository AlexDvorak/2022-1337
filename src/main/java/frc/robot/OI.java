// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.main.*;
import frc.robot.commands.climbing.*;

public class OI {

  public static final XboxController driver = new XboxController(0);
  public static final XboxController operator = new XboxController(1);

  // Driver Buttons
  public static final JoystickButton driverBButton = new JoystickButton(driver, XboxController.Button.kB.value);
  public static final JoystickButton driverAButton = new JoystickButton(driver, XboxController.Button.kA.value);
  public static final JoystickButton driverYButton = new JoystickButton(driver, XboxController.Button.kY.value);
  public static final JoystickButton driverXButton = new JoystickButton(driver, XboxController.Button.kX.value);

  // Operator Buttons
  public static final JoystickButton operatorBButton = new JoystickButton(operator, XboxController.Button.kB.value);
  public static final JoystickButton operatorAButton = new JoystickButton(operator, XboxController.Button.kA.value);
  public static final JoystickButton operatorYButton = new JoystickButton(operator, XboxController.Button.kY.value);
  public static final JoystickButton operatorXButton = new JoystickButton(operator, XboxController.Button.kX.value);

  public static void mapButtons() {
    // Driver Controls
    // driverAButton.whenHeld(new RunIntake());
    // driverYButton.whenHeld(new RunConveyorBelt(1.0));
    driverBButton.whenHeld(new StartConveyorBelt(1.0));
    driverXButton.whenHeld(new StopConveyorBelt());

    // Operator Controls
    operatorAButton.whenPressed(new ChangeClimberDirection());
  }

  public static double getDrivingSpeed() {
    double accelerate = driver.getLeftTriggerAxis();
    double brake = driver.getRightTriggerAxis();

    if (Math.abs(accelerate - brake) > 0.15)
    return accelerate - brake;
    else
    return 0.0;
  }

  public static double getDrivingTurn() {
    double turn = driver.getLeftX();
    if (turn > 0.15)
    return turn;
    else
    return 0.0;
  }

}
