package frc.robot.SubSystem;



import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.MagnetSensorConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionDutyCycle;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;


import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.AllConstants.Constants.SwerveConstants;
import frc.lib.AllConstants.RobotMap;
import frc.lib.AllConstants.SwerveTypeConstants;
import frc.lib.convert.Convertions;
import frc.lib.util.OptimizeAngle;


public class swerveModule {

    public int moduleNumber;
    private SwerveTypeConstants swerveTypeConstants;
    private Rotation2d angleOffset ;


    private TalonFX mDriveFalcon;
    private TalonFX mAngleFalcon;
    private CANcoder mAngleCanCoder;

    
    //private RelativeEncoder mAngleEncoder;
    private final DutyCycleOut driveDutyCycle = new DutyCycleOut(0);
    private final VelocityVoltage driveVelocityVoltage = new VelocityVoltage(0);
    private final PositionVoltage anglePositionDutyCycle = new PositionVoltage(0);
    
    //private final PositionVoltage ANGLE_POSITION = new PositionVoltage(0);

    private final SimpleMotorFeedforward fMotorFeedforward = new SimpleMotorFeedforward(
        SwerveConstants.SWERVE_DRIVE_KS, SwerveConstants.SWERVE_DRIVE_KV, SwerveConstants.SWERVE_DRIVE_KA);
    
    public swerveModule(int moduleNumber,
        SwerveTypeConstants swerveTypeConstants,
        int driveMotorID, int angleMotorID, int canCoderID,Rotation2d angleOffset){
        this.moduleNumber = moduleNumber;
        this.swerveTypeConstants = swerveTypeConstants;
        this.angleOffset = angleOffset;

        mAngleCanCoder = new CANcoder(canCoderID);
        mAngleCanCoder.getConfigurator().apply(new MagnetSensorConfigs()
            .withAbsoluteSensorRange(AbsoluteSensorRangeValue.Unsigned_0To1)
            .withSensorDirection(SensorDirectionValue.CounterClockwise_Positive)
        );

        
        mDriveFalcon = new TalonFX(driveMotorID,RobotMap.SWERVE_CANBUS_TYPE);
        mDriveConfig();
        mAngleFalcon = new TalonFX(angleMotorID,RobotMap.SWERVE_CANBUS_TYPE);
        mAngleConfig();
        resetToAbosolute();
        //mAngleEncoder = mAngleNeo.getEncoder();

        //lastAngle = getState().angle;

    }
    
    public void setDesireState(SwerveModuleState desiredState, boolean isOpenLoop){
        desiredState = OptimizeAngle.optimized(desiredState, getState().angle);
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);
    }

    

    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop){
        if(isOpenLoop){
            driveDutyCycle.Output = desiredState.speedMetersPerSecond / SwerveConstants.SWERVE_MAX_SPEED;
            mDriveFalcon.setControl(driveDutyCycle);
        }
        else{
            driveVelocityVoltage.Velocity = desiredState.speedMetersPerSecond;
            driveVelocityVoltage.FeedForward = fMotorFeedforward.calculate(desiredState.speedMetersPerSecond);
            mDriveFalcon.setControl(driveVelocityVoltage);
        }
    }
    private void setAngle(SwerveModuleState desiredState){
//        System.out.println(desiredState.angle.getDegrees());
        mAngleFalcon.setControl(anglePositionDutyCycle.withPosition(desiredState.angle.getRotations()));
//        mAngleFalcon.setControl(anglePositionDutyCycle.withPosition(1));
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            Convertions.falconToMPS(mDriveFalcon.getPosition().getValue(), swerveTypeConstants.wheelCircumference, swerveTypeConstants.driveGearRatio),
            getAngle()
        );
    }   
    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(
            Convertions.falconToMeters(mDriveFalcon.getPosition().getValue(), swerveTypeConstants.wheelCircumference, swerveTypeConstants.driveGearRatio),
            getAngle()
        );
    }
    
    
    private Rotation2d getAngle(){
        //System.out.printf("%.2f",mRelativeEncoder.getPosition());
//        if (mAngleFalcon.getDeviceID() == 2)System.out.println(mAngleFalcon.getPosition().getValue());
        return Rotation2d.fromRotations(mAngleFalcon.getPosition().getValue()%1);
        // Rotation.fromDegrees(Convertions.falconToDegrees(mAngleFalcon.getPosition().getValue(),1));
    }

    public Rotation2d getCanCoder(){
//        if(mAngleCanCoder.getDeviceID() == 1)System.out.println(mAngleCanCoder.getAbsolutePosition().getValue());
        return Rotation2d.fromRotations(mAngleCanCoder.getAbsolutePosition().getValue());
    }
    
    
    private void mDriveConfig(){
        TalonFXConfiguration driveConfig = new TalonFXConfiguration();
        driveConfig.Slot0.kP = SwerveConstants.SWERVE_DRIVE_PID[0];
        driveConfig.Slot0.kI = SwerveConstants.SWERVE_DRIVE_PID[1];
        driveConfig.Slot0.kD = SwerveConstants.SWERVE_DRIVE_PID[2];
        driveConfig.Slot0.kS = SwerveConstants.SWERVE_DRIVE_KS;
        driveConfig.Slot0.kV = SwerveConstants.SWERVE_DRIVE_KV;
        driveConfig.Slot0.kA = SwerveConstants.SWERVE_DRIVE_KA;

        driveConfig.CurrentLimits.StatorCurrentLimitEnable = SwerveConstants.SWERVE_DRIVE_CURRENT_ENABLED;
        driveConfig.CurrentLimits.StatorCurrentLimit = SwerveConstants.SWERVE_DRIVE_CONTINUOUS_CURRENT_LIMIT;
        driveConfig.CurrentLimits.SupplyCurrentThreshold = SwerveConstants.SWERVE_DRIVE_PEAK_CURRENT_LIMIT;
        driveConfig.CurrentLimits.SupplyTimeThreshold = SwerveConstants.SWERVE_DRIVE_PEAK_CURRENT_DURATION;

        driveConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = SwerveConstants.SWERVE_DRIVE_MOTOR_OPENLOOPRAMP;
        driveConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = SwerveConstants.SWERVE_DRIVE_MOTOR_OPENLOOPRAMP;

        driveConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = SwerveConstants.SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP;
        driveConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = SwerveConstants.SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP;

        driveConfig.MotorOutput.NeutralMode = SwerveConstants.DRIVE_NEUTRAL_MODE;
        driveConfig.MotorOutput.Inverted = swerveTypeConstants.driveMotorInverted;
        
        driveConfig.Feedback.SensorToMechanismRatio = swerveTypeConstants.driveGearRatio;
        mDriveFalcon.getConfigurator().apply(driveConfig);
    }
    public void resetToAbosolute(){
        mAngleFalcon.setPosition(Convertions.degreesToFalcon(getCanCoder().getDegrees() - angleOffset.getDegrees()));
    }

    private void mAngleConfig(){
        TalonFXConfiguration angleConfig = new TalonFXConfiguration();
        angleConfig.Slot0.kP = swerveTypeConstants.anglePIDF[0];
        angleConfig.Slot0.kI = swerveTypeConstants.anglePIDF[1];
        angleConfig.Slot0.kD = swerveTypeConstants.anglePIDF[2];

        angleConfig.CurrentLimits.StatorCurrentLimitEnable = SwerveConstants.SWERVE_ANGLE_CURRENT_ENABLED;
        angleConfig.CurrentLimits.StatorCurrentLimit = SwerveConstants.SWERVE_ANGLE_CURRENT_LIMIT;
        angleConfig.CurrentLimits.SupplyCurrentThreshold = SwerveConstants.SWERVE_ANGLE_PEAK_CURRENT_LIMIT;
        angleConfig.CurrentLimits.SupplyTimeThreshold = SwerveConstants.SWERVE_ANGLE_PEAK_CURRENT_DURATION;

        angleConfig.MotorOutput.NeutralMode = SwerveConstants.ANGLE_NEUTRAL_MODE;
        angleConfig.MotorOutput.Inverted = swerveTypeConstants.angleMotorInverted;

        angleConfig.Feedback.SensorToMechanismRatio = swerveTypeConstants.angleGearRatio;
        angleConfig.ClosedLoopGeneral.ContinuousWrap = true;
        mAngleFalcon.getConfigurator().apply(angleConfig);



    }
    
   
    
    
    
}