// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;

public class OI {

    public XboxController driver = new XboxController(0);

    public XboxController operator = new XboxController(1);

    //Driver Controls
    public JoystickButton driverAButton;
    public JoystickButton driverBButton;
    public JoystickButton driverXButton;
    public JoystickButton driverYButton;

    public JoystickButton driverLeftTrigger;
    public JoystickButton driverRightTrigger;

    //Operator Controls
    public JoystickButton operatorAButton;
    public JoystickButton operatorBButton;
    public JoystickButton operatorXButton;
    public JoystickButton operatorYButton;

  public OI() {
    driverAButton = new JoystickButton(driver, XboxController.Button.kA.value);
    driverBButton = new JoystickButton(driver, XboxController.Button.kB.value);
    driverXButton = new JoystickButton(driver, XboxController.Button.kX.value);
    driverYButton = new JoystickButton(driver, XboxController.Button.kY.value);

    driverLeftTrigger = new JoystickButton(driver,  XboxController.Axis.kLeftTrigger.value);
    driverRightTrigger = new JoystickButton(driver, XboxController.Axis.kRightTrigger.value);

    operatorAButton = new JoystickButton(operator, XboxController.Button.kA.value);
    operatorBButton = new JoystickButton(operator, XboxController.Button.kB.value);
    operatorXButton = new JoystickButton(operator, XboxController.Button.kX.value);
    operatorYButton = new JoystickButton(operator, XboxController.Button.kY.value);
  }

  public void mapButtons() {
    // Driver Controls
    // driverYButton.whenHeld(new RunConveyorBelt(1.0));
    driverBButton.whenHeld(new StartConveyorBelt(1.0));
    driverXButton.whenHeld(new StopConveyorBelt());
    //driverAButton.whenHeld(new RunIntake());

    // Operator Controls
    operatorAButton.whenPressed(new ChangeClimberDirection());
  }

  public double getDrivingSpeed() {
    double accelerate = driver.getLeftTriggerAxis();
    double brake = driver.getRightTriggerAxis();
    if (Math.abs(accelerate - brake) > 0.15){
      return accelerate - brake;}
    else
      return 0.0;
  }

  public double getDrivingTurn() {
    double turn = driver.getLeftX();
    if (turn > 0.15) {
      return turn;}
    else
      return 0.0;
  }

}
