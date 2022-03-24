// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.Conveyor;

public class ConveyorBeltSubsystem extends SubsystemBase {

  private final CANSparkMax motor;

  public ConveyorBeltSubsystem() {
    motor = new CANSparkMax(Conveyor.motorID, MotorType.kBrushless);
  }

  /** @param speed desired speed for conveyor belt going forward */
  public void run(double speed) {
    motor.set(speed);
  }

  public void stop() {
    motor.set(0);
  }

}
