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
    return threshold(accelerate - brake, 0.15);
  }

  public static double getDrivingTurn() {
    double turn = driver.getLeftStickX();
    return threshold(turn, 0.15);
  }

  /** Returns 0 if the absolute value of the input is less than the threshold, otherwise returns the input */
  private static double threshold(double input, double thresh) {
    return Math.abs(input) > thresh ? input : 0;
  }

}
