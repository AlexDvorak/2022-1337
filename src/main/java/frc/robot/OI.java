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
    driverBButton.whenHeld(new ConveyorBeltCommandForward());
    driverXButton.whenHeld(new ConveyorBeltCommandStop());
    //driverAButton.whenHeld(new IntakeCommand());

    // Operator Controls
    operatorAButton.whenPressed(new ClimberCommandMove(Robot.ClimberSubsystem));
  }

  // method that takes speed to go forwards or backwards from bumpers of controller depending on how hard driver presses
  public double getSpeed() {
    double accelerate = driver.getLeftTriggerAxis();
    double brake = driver.getRightTriggerAxis();
    if (Math.abs(accelerate - brake) > 0.15){
      return accelerate - brake;}
    else
      return 0.0;
  }

  // method that allows for joystick control to determine turns to left/right
  public double getTurn() {
    double rotate = driver.getLeftX();
    if (rotate > 0.15) {
      return rotate;}
    else
      return 0.0;
  }

}
