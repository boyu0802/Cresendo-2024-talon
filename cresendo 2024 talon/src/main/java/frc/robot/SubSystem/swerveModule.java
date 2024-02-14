package frc.robot.SubSystem;



import com.ctre.phoenix6.configs.CANcoderConfiguration;

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
import frc.lib.convert.Convertions;
import frc.lib.util.CTREConfigs;
import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.RobotMap;
import frc.lib.AllConstants.SwerveTypeConstants;
import frc.lib.util.OptimizeAngle;


public class swerveModule {

    public int moduleNumber;
    private SwerveTypeConstants swerveTypeConstants;
    private Rotation2d angleOffset ;


    private TalonFX driveMotor;
    private TalonFX angleMotor;
    private CANcoder canCoder;


    //private RelativeEncoder mAngleEncoder;
    private final DutyCycleOut driveDutyCycle = new DutyCycleOut(0);
    private final VelocityVoltage driveVelocityVoltage = new VelocityVoltage(0);
    private final PositionVoltage anglePositionVoltage = new PositionVoltage(0);

    //private final PositionVoltage ANGLE_POSITION = new PositionVoltage(0);

    private final SimpleMotorFeedforward motorFeedforward = new SimpleMotorFeedforward(
            Constants.DriveConstants.Drive_KS, Constants.DriveConstants.Drive_KV, Constants.DriveConstants.Drive_KA);

    public swerveModule(int moduleNumber,
                        SwerveTypeConstants swerveTypeConstants,
                        int driveMotorID, int angleMotorID, int canCoderID,Rotation2d angleOffset){
        this.moduleNumber = moduleNumber;
        this.swerveTypeConstants = swerveTypeConstants;
        this.angleOffset = angleOffset;

        canCoder = new CANcoder(canCoderID);
        canCoder.getConfigurator().apply(CTREConfigs.cancoderConfig);

        driveMotor = new TalonFX(driveMotorID,RobotMap.SWERVE_CANBUS_TYPE);
        driveMotor.getConfigurator().apply(CTREConfigs.driveMotorConfig);
        angleMotor = new TalonFX(angleMotorID,RobotMap.SWERVE_CANBUS_TYPE);
        angleMotor.getConfigurator().apply(CTREConfigs.angleMotorConfig);
        resetToAbosolute();

    }

    public void setDesireState(SwerveModuleState desiredState, boolean isOpenLoop){
        desiredState = OptimizeAngle.optimized(desiredState, getState().angle);
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);
    }



    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop){
        if(isOpenLoop){
            driveDutyCycle.Output = desiredState.speedMetersPerSecond / Constants.ChassisConstants.Chassis_MaxSpeed;
            driveMotor.setControl(driveDutyCycle);
        }
        else{
            driveVelocityVoltage.Velocity = desiredState.speedMetersPerSecond;
            driveVelocityVoltage.FeedForward = motorFeedforward.calculate(desiredState.speedMetersPerSecond);
            driveMotor.setControl(driveVelocityVoltage);
        }
    }
    private void setAngle(SwerveModuleState desiredState){
        angleMotor.setControl(anglePositionVoltage.withPosition(desiredState.angle.getRotations()));
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
                Convertions.falconToMPS(driveMotor.getPosition().getValue(), swerveTypeConstants.wheelCircumference, swerveTypeConstants.driveGearRatio),
                getAngle()
        );
    }
    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(
                Convertions.falconToMeters(driveMotor.getPosition().getValue(), swerveTypeConstants.wheelCircumference, swerveTypeConstants.driveGearRatio),
                getAngle()
        );
    }


    private Rotation2d getAngle(){
        return Rotation2d.fromRotations(angleMotor.getPosition().getValue());
    }


    public Rotation2d getCanCoder(){
        return Rotation2d.fromRotations(canCoder.getAbsolutePosition().getValue());
    }

        public void resetToAbosolute(){
        double absolute = (getCanCoder().getDegrees() - angleOffset.getDegrees());
        angleMotor.setPosition(Convertions.degreesToFalcon(absolute));
    }


    
}