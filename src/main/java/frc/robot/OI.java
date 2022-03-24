// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.main.*;
import frc.robot.commands.climbing.*;

public class OI {

  public static final Controller driver = new Controller(0);
  public static final Controller operator = new Controller(1);

  public static void mapButtons() {
    // Driver Controls
    // driver.ButtonA.whenHeld(new RunIntake());
    // driver.ButtonY.whenHeld(new RunConveyorBelt(1.0));
    driver.ButtonB.whenPressed(new StartConveyorBelt(1.0));
    driver.ButtonX.whenPressed(new StopConveyorBelt());

    // Operator Controls
    operator.ButtonA.whenPressed(new ChangeClimberDirection());
  }

  public static double getDrivingSpeed() {
    double accelerate = driver.getLeftTrigger();
    double brake = driver.getRightTrigger();

    if (Math.abs(accelerate - brake) > 0.15)
    return accelerate - brake;
    else
    return 0.0;
  }

  public static double getDrivingTurn() {
    double turn = driver.getLeftStickX();
    if (turn > 0.15)
    return turn;
    else
    return 0.0;
  }

}
