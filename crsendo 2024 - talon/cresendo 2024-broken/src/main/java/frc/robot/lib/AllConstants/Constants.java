// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib.AllConstants;

import static frc.robot.lib.AllConstants.Constants.ChassisConstants.MaxVoltage;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants
{
    public static final double SWERVE_POV_MOVE_SPEED = 0.2;

    public final class DriveMotorConstants{
        public static final double Drive_Ks = 0.32 / ChassisConstants.MaxVoltage;
        public static final double Drive_Kv = 1.51/ ChassisConstants.MaxVoltage;
        public static final double Drive_Ka = 0.27 / ChassisConstants.MaxVoltage;
        public static final double Drive_Kp = 0.05;
        public static final double Drive_Ki = 0.0;
        public static final double Drive_Kd = 0.0;

        public static final double Drive_SupplyCurrentLimit = 45;
        public static final double Drive_SupplyCurrentTriggerThresholdCurrent = 0.0;
        public static final double Drive_SupplyCurrentTriggerThresholdTime = 0.0;
        public static final boolean Drive_EnableCurrentLimit = true;

        public static final double Drive_DutyCycleOpenLoopRamp = 0.25;
        public static final double Drive_VoltageOpenLoopRamp = 0.0;

        public static final double Drive_DutyCycleCloseLoopRamp = 0.0;
        public static final double Drive_VoltageCloseLoopRamp = 0.0;

        public static final NeutralModeValue Drive_NeutralMode = NeutralModeValue.Brake;
    
    }

    public final class ChassisConstants{
        public static final double MaxSpeed = 4.5;
        public static final double ChassisLength = 0.62865;
        public static final double ChassisWidth = 0.62865; 
        public static final double WheelCircumference = Units.inchesToMeters(4.0)* Math.PI;
        public static final double MaxVoltage = 12.0;
    }

    public final class AngleMotorConstants{
        public static final double Angle_Kp = 24;
        public static final double Angle_Ki = 0.0;
        public static final double Angle_Kd = 0.1;

        public static final double Angle_SupplyCurrentLimit = 35;
        public static final double Angle_SupplyCurrentTriggerThresholdCurrent = 35;
        public static final double Angle_SupplyCurrentTriggerThresholdTime = 0.1;
        public static final boolean Angle_EnableCurrentLimit = true;

        
    }

    public final class CancoderConstants{
        public static final SensorDirectionValue AngleSensorDirection = SensorDirectionValue.CounterClockwise_Positive;
    }

    public final class intakeConstants{
        public static final double IntakeSpeed = 0.5;
        public static final double Intake_Kp = 0.001;
        public static final double Intake_Ki = 0;
        public static final double Intake_Kd = 0;
        public static final double Intake_GearRatio = 1/30;
        public static final int Intake_SupplyCurrentLimit = 35;
    }

    public final class shooterConstants{
        public static final double ShooterSpeed = 0.5;
        public static final double Shooter_Kp = 0.001;
        public static final double Shooter_Ki = 0;
        public static final double Shooter_Kd = 0;
        public static final double Shooter_GearRatio = 1;
        public static final int Shooter_SupplyCurrentLimit = 35;
    }

    public final class armConstants{
        public static final double Arm_Kp = 0.001;
        public static final double Arm_Ki = 0;
        public static final double Arm_Kd = 0;
        public static final double Arm_GearRatio = 1/300;
        public static final int Arm_SupplyCurrentLimit = 35;
        public static final double Arm_AmpPosition = 58;
        public static final double Arm_IntakePosition = 4;
        public static final double Arm_ShooterPosition = 42;
    }

    public final class MusicConstants{
        public static final String Music = "";
    }

    public final class NavxConstants{
        public static final boolean inverted = false;
    }

}
