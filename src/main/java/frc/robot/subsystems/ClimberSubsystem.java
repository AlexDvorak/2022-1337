// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;

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

  CANSparkMax elevator;
  TalonFX winch;
  DoubleSolenoid solenoid1;
  DoubleSolenoid solenoid2;
  DoubleSolenoid solenoid3;
  DigitalInput limitSwitch1;
  DigitalInput limitSwitch2;
  DigitalInput limitSwitch3;
  DigitalInput elevatorTopSwitch;
  DigitalInput elevatorBottomSwitch;

  int direction = -1;
  double speed = 0.2;

  public ClimberSubsystem() {
    // elevator = new CANSparkMax(RobotMap.ConveyorM, MotorType.kBrushless);
    winch = new TalonFX(Climber.leftWinchMotorID);
    solenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    solenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    solenoid3 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 4, 5);
    elevatorTopSwitch = new DigitalInput(1);
    elevatorBottomSwitch = new DigitalInput(2);

    // limitSwitch1 = new DigitalInput(4);
    // limitSwitch2 = new DigitalInput(5);
    // limitSwitch3 = new DigitalInput(6);
  }

  //activated once when pressed
  public void changeDirection(){
    direction = direction < 0 ? 1 : -1;
  }

  //continuously run when pressed
  public void run(){
    double s = speed;
    if (elevatorTopSwitch.get()){
      s = Math.min(speed * direction, 0);
    } else if (elevatorBottomSwitch.get()) {
      s = Math.max(speed * direction, 0);
    }
    elevator.set(s);
  }


  public void climbLevelOne(double speedofElevator){
    if (limitSwitch1.get()){
      elevator.set(0.0);
      solenoid1.set(Value.kForward);
      elevator.set(-speedofElevator);
      //need a stopping point
    } else {
      elevator.set(speedofElevator);
    }
  }

  public void climbLevelTwo(double speedofWinch){
    if (limitSwitch1.get()){
      winch.set(ControlMode.PercentOutput, 0.0);
      solenoid2.set(Value.kForward);
      //This is where we need the driver to confirm the release
    } else {
      winch.set(ControlMode.PercentOutput, speedofWinch);
    }}

  // //extend motor 1 until limit switch is pressed
  // //set soleniod to move forward
  // //reverse motor 1
  // //rotate winch

  // public void sol1clamp() {
  //   sol1.set(Value.kReverse);
  // }
  // public void sol1Off() {
  //   sol1.set(Value.kOff);
  // }
  // public void sol1Release() {
  //   sol1.set(Value.kForward);
  // }
  // //same method for rest of solenoids

  // //return true if the sensor is triggered
  // public boolean sensor1Status() {
  //   return sensor1.get();
  // }

}