package frc.lib.util;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.SwerveTypeConstants;


public class CTREConfigs {
    public static TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
    public static TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
    public static CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();

    public CTREConfigs(){

        //drive motor configs

        driveMotorConfig.Slot0.kP = Constants.DriveConstants.Drive_PID[0];
        driveMotorConfig.Slot0.kI = Constants.DriveConstants.Drive_PID[1];
        driveMotorConfig.Slot0.kD = Constants.DriveConstants.Drive_PID[2];
        driveMotorConfig.Slot0.kV = Constants.DriveConstants.Drive_KV;
        driveMotorConfig.Slot0.kA = Constants.DriveConstants.Drive_KA;
        driveMotorConfig.Slot0.kS = Constants.DriveConstants.Drive_KS;

        driveMotorConfig.CurrentLimits.SupplyCurrentLimit = Constants.DriveConstants.Drive_ContinuousCurrentLimit;
        driveMotorConfig.CurrentLimits.SupplyCurrentThreshold = Constants.DriveConstants.Drive_PeakCurrentLimit;
        driveMotorConfig.CurrentLimits.SupplyTimeThreshold = Constants.DriveConstants.Drive_PeakCurrentDuration;
        driveMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.DriveConstants.Drive_CurrentLimitEnable;

        driveMotorConfig.Feedback.SensorToMechanismRatio = SwerveTypeConstants.SDSMK4I_L1().driveGearRatio;
        
        driveMotorConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Constants.DriveConstants.Drive_OpenLoopRamp;
        driveMotorConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = Constants.DriveConstants.Drive_OpenLoopRamp;

        driveMotorConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Constants.DriveConstants.Drive_CloseLoopRamp;
        driveMotorConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Constants.DriveConstants.Drive_CloseLoopRamp;

        driveMotorConfig.MotorOutput.NeutralMode = Constants.DriveConstants.Drive_NeutralMode;
        driveMotorConfig.MotorOutput.Inverted = SwerveTypeConstants.SDSMK4I_L1().driveMotorInverted;

        //angle motor cofigs

        angleMotorConfig.Slot0.kP = SwerveTypeConstants.SDSMK4I_L1().anglePIDF[0];
        angleMotorConfig.Slot0.kI = SwerveTypeConstants.SDSMK4I_L1().anglePIDF[1];
        angleMotorConfig.Slot0.kD = SwerveTypeConstants.SDSMK4I_L1().anglePIDF[2];

        angleMotorConfig.CurrentLimits.SupplyCurrentLimit = Constants.AngleConstants.Angle_CurrentLimit;
        angleMotorConfig.CurrentLimits.SupplyCurrentThreshold = Constants.AngleConstants.Angle_PeakCurrentLimit;
        angleMotorConfig.CurrentLimits.SupplyTimeThreshold = Constants.AngleConstants.Angle_PeakCurrentDuration;
        angleMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.AngleConstants.Angle_CurrentLimitEnabled;
        
        angleMotorConfig.MotorOutput.Inverted = SwerveTypeConstants.SDSMK4I_L1().angleMotorInverted;
        angleMotorConfig.MotorOutput.NeutralMode = Constants.AngleConstants.Angle_NeutralMode;

        angleMotorConfig.Feedback.SensorToMechanismRatio = SwerveTypeConstants.SDSMK4I_L1().angleGearRatio;
        angleMotorConfig.ClosedLoopGeneral.ContinuousWrap = true;

        //cancoder configs
        cancoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;

        cancoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
    }
}
