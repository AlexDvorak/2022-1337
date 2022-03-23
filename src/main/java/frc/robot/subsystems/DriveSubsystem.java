// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap.Drive;

public class DriveSubsystem extends SubsystemBase {

    CANSparkMax L1motor, L2motor;
    CANSparkMax R1motor, R2motor;

    public DriveSubsystem() {
    L1motor = new CANSparkMax(Drive.Left1ID, MotorType.kBrushless);
    L2motor = new CANSparkMax(Drive.Left2ID, MotorType.kBrushless);
    R1motor = new CANSparkMax(Drive.Right1ID, MotorType.kBrushless);
    R2motor = new CANSparkMax(Drive.Right2ID, MotorType.kBrushless);

    R1motor.setInverted(true);
    R2motor.setInverted(true);
  }

  public void TankDrive(double left, double right) {
    L1motor.set(left);
    L2motor.set(left);
    R1motor.set(right);
    R2motor.set(right);
  }

  public void ArcadeDrive(double speed, double turn) {
    TankDrive(speed - turn, speed + turn);
  }
}
