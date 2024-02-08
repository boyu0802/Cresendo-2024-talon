package frc.robot.lib.Util;


import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import frc.robot.lib.AllConstants.Constants;
import frc.robot.lib.AllConstants.SwerveTypeConstants;


public class CTREconfigs {
    public static TalonFXConfiguration angleMotorConfig = new TalonFXConfiguration();
    public static TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
    public static CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();

    public CTREconfigs(){

        //drive motor configs

        driveMotorConfig.Slot0.kP = Constants.DriveMotorConstants.Drive_Kp;
        driveMotorConfig.Slot0.kI = Constants.DriveMotorConstants.Drive_Ki;
        driveMotorConfig.Slot0.kD = Constants.DriveMotorConstants.Drive_Kd;
        driveMotorConfig.Slot0.kV = Constants.DriveMotorConstants.Drive_Kv;
        driveMotorConfig.Slot0.kA = Constants.DriveMotorConstants.Drive_Ka;
        driveMotorConfig.Slot0.kS = Constants.DriveMotorConstants.Drive_Ks;

        driveMotorConfig.CurrentLimits.SupplyCurrentLimit = Constants.DriveMotorConstants.Drive_SupplyCurrentLimit;
        driveMotorConfig.CurrentLimits.SupplyCurrentThreshold = Constants.DriveMotorConstants.Drive_SupplyCurrentTriggerThresholdCurrent;
        driveMotorConfig.CurrentLimits.SupplyTimeThreshold = Constants.DriveMotorConstants.Drive_SupplyCurrentTriggerThresholdTime;
        driveMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.DriveMotorConstants.Drive_EnableCurrentLimit;

        driveMotorConfig.Feedback.SensorToMechanismRatio = SwerveTypeConstants.SDSMK4I_L1().driveGearRatio;
        
        driveMotorConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Constants.DriveMotorConstants.Drive_DutyCycleOpenLoopRamp;
        driveMotorConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = Constants.DriveMotorConstants.Drive_VoltageOpenLoopRamp;

        driveMotorConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Constants.DriveMotorConstants.Drive_DutyCycleCloseLoopRamp;
        driveMotorConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Constants.DriveMotorConstants.Drive_VoltageCloseLoopRamp;

        driveMotorConfig.MotorOutput.NeutralMode = Constants.DriveMotorConstants.Drive_NeutralMode;
        driveMotorConfig.MotorOutput.Inverted = SwerveTypeConstants.SDSMK4I_L1().driveMotorInvert;

        //angle motor cofigs

        angleMotorConfig.Slot0.kP = Constants.AngleMotorConstants.Angle_Kp;
        angleMotorConfig.Slot0.kI = Constants.AngleMotorConstants.Angle_Ki;
        angleMotorConfig.Slot0.kD = Constants.AngleMotorConstants.Angle_Kd;

        angleMotorConfig.CurrentLimits.SupplyCurrentLimit = Constants.AngleMotorConstants.Angle_SupplyCurrentLimit;
        angleMotorConfig.CurrentLimits.SupplyCurrentThreshold = Constants.AngleMotorConstants.Angle_SupplyCurrentTriggerThresholdCurrent;
        angleMotorConfig.CurrentLimits.SupplyTimeThreshold = Constants.AngleMotorConstants.Angle_SupplyCurrentTriggerThresholdTime;
        angleMotorConfig.CurrentLimits.SupplyCurrentLimitEnable = Constants.AngleMotorConstants.Angle_EnableCurrentLimit;
        
        angleMotorConfig.MotorOutput.Inverted = SwerveTypeConstants.SDSMK4I_L1().angleMotorInvert;
        angleMotorConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;

        angleMotorConfig.Feedback.SensorToMechanismRatio = SwerveTypeConstants.SDSMK4I_L1().angleGearRatio;
        angleMotorConfig.ClosedLoopGeneral.ContinuousWrap = true;

        //cancoder configs
        cancoderConfig.MagnetSensor.AbsoluteSensorRange = AbsoluteSensorRangeValue.Unsigned_0To1;

        cancoderConfig.MagnetSensor.SensorDirection = Constants.CancoderConstants.AngleSensorDirection;
    }
}
