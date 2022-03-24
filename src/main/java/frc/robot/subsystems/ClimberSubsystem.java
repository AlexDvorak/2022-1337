// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.Climber;


// 1 Falcon for elevator
// 1 Falcon for winch
// 6 piston powered by 3 solenoids
// 6 limit switches (1 per claw)

public class ClimberSubsystem extends SubsystemBase {

  private final CANSparkMax elevator;
  private final TalonFX winch;
  private final DoubleSolenoid solenoid1, solenoid2, solenoid3;
  private final DigitalInput limitSwitch1, limitSwitch2, limitSwitch3;
  private final DigitalInput elevatorTopSwitch, elevatorBottomSwitch;

  private boolean movingUp = false;
  private double elevatorSpeed = 0.2;

  public ClimberSubsystem() {
    // -- Motors -- //
    winch = new TalonFX(Climber.leftWinchMotorID);
    elevator = new CANSparkMax(Climber.leftWinchMotorID, MotorType.kBrushless);

    // -- Solenoids -- //
    solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    solenoid3 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);

    // -- Limit Switches -- //
    elevatorTopSwitch = new DigitalInput(1);
    elevatorBottomSwitch = new DigitalInput(2);
    limitSwitch1 = new DigitalInput(4);
    limitSwitch2 = new DigitalInput(5);
    limitSwitch3 = new DigitalInput(6);
  }

  public boolean atUpperLimit() {
    return elevatorTopSwitch.get();
  }

  public boolean atLowerLimit() {
    return elevatorBottomSwitch.get();
  }

  public void changeDirection(){
    movingUp = !movingUp;
  }

  public void stopMotors() {
    elevator.set(0);
    winch.set(ControlMode.PercentOutput, 0);
  }

  public void runElevator(){
    double s = elevatorSpeed * (movingUp ? 1 : -1);

    if (atUpperLimit() &&  movingUp) s = 0; // at top, don't allow moving up
    if (atLowerLimit() && !movingUp) s = 0; // at bottom don't allow moving down

    elevator.set(s);
  }

  /**
   * Climbing Process
   * <ul>
   * <li> extend motor 1 until limit switch is pressed
   * <li> set soleniod to move forward
   * <li> reverse motor 1
   * <li> rotate winch
   */
  // this should be in a command
  public void climbLevelOne(double elevatorSpeed){
    if (limitSwitch1.get()){
      elevator.set(0.0);
      solenoid1.set(Value.kForward);
      elevator.set(-elevatorSpeed);
      //need a stopping point
    } else {
      elevator.set(elevatorSpeed);
    }
  }

  // this should be in a command
  public void climbLevelTwo(double winchSpeed){
    if (limitSwitch1.get()){
      winch.set(ControlMode.PercentOutput, 0.0);
      solenoid2.set(Value.kForward);
      //This is where we need the driver to confirm the release
    } else {
      winch.set(ControlMode.PercentOutput, winchSpeed);
    }
  }

}