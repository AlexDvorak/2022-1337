package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class Controller {

  private final double kTriggerDeadzone;

  public final XboxController controller;

  public final JoystickButton ButtonA, ButtonB, ButtonX, ButtonY;
  public final JoystickButton BumperLeft, BumperRight;
  public final JoystickButton ButtonStart, ButtonBack;

  public final POVButton DPadUp, DPadDown, DPadLeft, DPadRight;

  /** Allows for using the triggers as buttons, for example <pre>m_Controller.LeftTrigger.whenHeld(new CommandToRun()) */
  public final Button LeftTrigger, RightTrigger;

  /**
   * @param port The port index on the Driver Station that the controller is plugged into.
   * @param triggerDeadzone The minimum amount the trigger must be pressed (from 0.0 to 1.0) for it to be registered as a button press
   */
  public Controller(int port, double triggerDeadzone) {
    this.kTriggerDeadzone = triggerDeadzone;

    controller = new XboxController(port);

    ButtonA = new JoystickButton(controller, XboxController.Button.kA.value);
    ButtonB = new JoystickButton(controller, XboxController.Button.kB.value);
    ButtonX = new JoystickButton(controller, XboxController.Button.kX.value);
    ButtonY = new JoystickButton(controller, XboxController.Button.kY.value);

    BumperLeft = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
    BumperRight = new JoystickButton(controller, XboxController.Button.kRightBumper.value);

    ButtonStart = new JoystickButton(controller, XboxController.Button.kStart.value);
    ButtonBack = new JoystickButton(controller, XboxController.Button.kBack.value);

    DPadUp = new POVButton(controller, 0);
    DPadRight = new POVButton(controller, 90);
    DPadDown = new POVButton(controller, 180);
    DPadLeft = new POVButton(controller, 270);

    LeftTrigger = new Button(() -> getLeftTrigger() > kTriggerDeadzone);
    RightTrigger = new Button(() -> getRightTrigger() > kTriggerDeadzone);
  }

  /**
   * @param port The port index on the Driver Station that the controller is plugged into.
   */
  public Controller(int port) {
    this(port, 0.05); // default trigger deadzone of 0.05;
  }

  /** The X (left/right) position of the right joystick on the controller from -1.0 to 1.0 */
  public double getRightStickX() { return controller.getRightX(); }

  /** The Y (up/down) position of the right joystick on the controller from -1.0 to 1.0 */
  public double getRightStickY() { return controller.getRightY(); }

  /** The X (left/right) position of the left joystick on the controller from -1.0 to 1.0 */
  public double getLeftStickX() { return controller.getLeftX(); }

  /** The Y (up/down) position of the left joystick on the controller from -1.0 to 1.0 */
  public double getLeftStickY() { return controller.getLeftY(); }

  /** How much the right trigger on the controller is pressed from 0.0 to 1.0 */
  public double getRightTrigger() { return controller.getRightTriggerAxis(); }

  /** How much the left trigger on the controller is pressed from 0.0 to 1.0 */
  public double getLeftTrigger() { return controller.getLeftTriggerAxis(); }

}
