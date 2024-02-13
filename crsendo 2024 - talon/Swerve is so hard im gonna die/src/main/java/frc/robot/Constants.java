// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.subsystems.SwerveSubsystem;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final double chassisLengthMeters = 0.0;
  public static final double chassisWidthMeters = 0.0;

  public static final int frontLeftDriveMotor_ID = 0;
  public static final int frontLeftTurningMotor_ID = 0;
  public static final int frontLeftCancoder_ID = 0;
  
  public static final int frontRightDriveMotor_ID = 1;
  public static final int frontRightTurningMotor_ID = 1;
  public static final int frontRightCancoder_ID = 1;

  public static final int backLeftDriveMotor_ID = 2;
  public static final int backLeftTurningMotor_ID = 2;
  public static final int backLeftCancoder_ID = 2;

  public static final int backRightDriveMotor_ID = 3;
  public static final int backRightTurningMotor_ID = 3;
  public static final int backRightCancoder_ID = 3;

  public static final int frontLeftAngleOffset = 0;
  public static final int frontRightAngleOffset = 0;
  public static final int backLeftAngleOffset = 0;
  public static final int backRightAngleOffset = 0;

  public static final double Drive_Ka = 0;
  public static final double Drive_Kv = 0;
  public static final double Drive_Ks = 0;

  public static final double Drive_KP = 0;
  public static final double Drive_KI = 0;
  public static final double Drive_KD = 0;

  public static final double CHASSIS_ANGLE_GEAR_RATIO = 0.0;

  public static final double Turn_KP = 0;
  public static final double Turn_KI = 0;
  public static final double Turn_KD = 0;
  public static final double Turn_KF = 0;

  public static final double Turn_maxAccel = 0;
  public static final double Turn_maxVelo = 0;
  public static final double Turn_maxAllowError = 0;

  public static final SwerveSubsystem.GearRatio Drive_gearRatio = SwerveSubsystem.GearRatio.L1;

  public static final double WheelDiameterMeters = 0.0;

  public static final Pose2d initialPose = new Pose2d(0.0, 0.0, new Rotation2d(0.0));

  public static final double MaxSpeedMetersPerSecond = 0.0;
  public static final double MaxAngularSpeedRadiansPerSecond = 0.0;
  public static final boolean EnableFieldOrientedDrive = false;
}
