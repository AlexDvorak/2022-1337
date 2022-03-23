// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.Intake;

public class IntakeSubsystem extends SubsystemBase {

  private final DoubleSolenoid intakeArm;
  private final CANSparkMax motor;

  public IntakeSubsystem() {
    motor = new CANSparkMax(Intake.motorID, MotorType.kBrushless);
    intakeArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 5);
    retract(); // start with intake up
  }

  public void deploy() {
    intakeArm.set(DoubleSolenoid.Value.kForward);
  }

  public void retract(){
    intakeArm.set(DoubleSolenoid.Value.kReverse);
  }

  public void spin(double speed){
    motor.set(speed);
  }

  public void stopMotor() {
    spin(0);
  }

}