// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
 
public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  //assigns motors (these ids are random right now)
  public CANSparkMax frontLeft = new CANSparkMax(1, MotorType.kBrushless);
  public CANSparkMax frontRight = new CANSparkMax(2, MotorType.kBrushless);
  public CANSparkMax backLeft = new CANSparkMax(3, MotorType.kBrushless);
  public CANSparkMax backRight = new CANSparkMax(4, MotorType.kBrushless);

  public Encoder driveEncoder = new Encoder(0,1);

  //puts the left and right side motors together (similar to master and follower with talon srx)
  private SpeedControllerGroup leftSideMotors = new SpeedControllerGroup(frontLeft, backLeft);
  private SpeedControllerGroup rightSideMotors = new SpeedControllerGroup(frontRight, backRight);

  //constructs a robot differential drive
  public DifferentialDrive robotDifferentialDrive = new DifferentialDrive(leftSideMotors, rightSideMotors);

  public DriveSubsystem() {}

  //stops motors
  public void stop(){
    frontLeft.set(0);
    frontRight.set(0);
    backLeft.set(0);
    backRight.set(0);
  }
  
  //creates the arcade drive method, returns nothing, and takes in a speed and rotation parameter
  //we will pass in these parameters with the xbox controller
  public void drive(double speed, double rotation){
    robotDifferentialDrive.arcadeDrive(speed, rotation);
  }

  //add encoder stuff after this
  public double getDriveDistance() {
    return driveEncoder.getDistance();
  }

  public void resetEncoder(){
    driveEncoder.reset();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
