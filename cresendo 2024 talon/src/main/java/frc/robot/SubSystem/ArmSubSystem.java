package frc.robot.SubSystem;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.RobotMap;

public class ArmSubSystem extends SubsystemBase {
    private CANSparkMax lowerLeftArm;
    private CANSparkMax lowerRightArm;
    private CANSparkMax topArmMotor;

    private double lowerPosition;
    private double topPosition;


    public ArmSubSystem() {
        lowerLeftArm = new CANSparkMax(RobotMap.ARM_LOWER_LEFT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        lowerRightArm = new CANSparkMax(RobotMap.ARM_LOWER_RIGHT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        topArmMotor = new CANSparkMax(RobotMap.ARM_TOP_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        lowerArmConfig(lowerLeftArm, lowerLeftArm.getEncoder(), Constants.ArmConstants.Lower_Arm_Left_Reversed);
        lowerArmConfig(lowerRightArm, lowerRightArm.getEncoder(), Constants.ArmConstants.Lower_Arm_Right_Reversed);
        topArmConfig();

    }

    private void lowerArmConfig(CANSparkMax lowerArmMotor, RelativeEncoder armEncoder, boolean Inverted){
        lowerArmMotor.restoreFactoryDefaults();
        lowerArmMotor.getPIDController().setP(Constants.ArmConstants.Lower_Arm_PID[0], 0);
        lowerArmMotor.getPIDController().setI(Constants.ArmConstants.Lower_Arm_PID[1], 0);
        lowerArmMotor.getPIDController().setD(Constants.ArmConstants.Lower_Arm_PID[2], 0);
        lowerArmMotor.getPIDController().setFF(Constants.ArmConstants.Lower_Arm_PID[3], 0);
        lowerArmMotor.setSmartCurrentLimit(Constants.ArmConstants.Lower_Arm_CurrentLimit);
        lowerArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.ArmConstants.Lower_Arm_ForwardSoftLimit);
        lowerArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.ArmConstants.Lower_Arm_ReverseSoftLimit);
        lowerArmMotor.setIdleMode(Constants.ArmConstants.Arm_NeutralMode);
        lowerArmMotor.setInverted(Inverted);
        armEncoder.setPosition(0);
        armEncoder.setPositionConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio);
        armEncoder.setVelocityConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio / 60.0);
        lowerArmMotor.burnFlash();
    }

    private void topArmConfig(){
        topArmMotor.restoreFactoryDefaults();
        topArmMotor.getPIDController().setP(Constants.ArmConstants.Lower_Arm_PID[0], 0);
        topArmMotor.getPIDController().setI(Constants.ArmConstants.Lower_Arm_PID[1], 0);
        topArmMotor.getPIDController().setD(Constants.ArmConstants.Lower_Arm_PID[2], 0);
        topArmMotor.getPIDController().setFF(Constants.ArmConstants.Lower_Arm_PID[3], 0);
        topArmMotor.setSmartCurrentLimit(Constants.ArmConstants.Lower_Arm_CurrentLimit);
        topArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.ArmConstants.Lower_Arm_ForwardSoftLimit);
        topArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.ArmConstants.Lower_Arm_ReverseSoftLimit);
        topArmMotor.setIdleMode(Constants.ArmConstants.Arm_NeutralMode);
        topArmMotor.setInverted(Constants.ArmConstants.Top_Arm_Reversed);
        topArmMotor.getEncoder().setPosition(0);
        topArmMotor.getEncoder().setPositionConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio);
        topArmMotor.getEncoder().setVelocityConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio / 60.0);
        topArmMotor.burnFlash();
    }

    public void setArm(double lowerSetPoint, double topSetPoint) {
        // SmartDashboard.putNumber("Arm Refference", setPoint);
        lowerLeftArm.getPIDController().setReference(lowerSetPoint, CANSparkMax.ControlType.kPosition);
        lowerRightArm.getPIDController().setReference(lowerSetPoint, CANSparkMax.ControlType.kPosition);
        topArmMotor.getPIDController().setReference(topSetPoint, CANSparkMax.ControlType.kPosition);
    }


    public void toIntakePosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.Intake);
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.Intake);
        setArm(lowerPosition,topPosition);
    }
    public void toAmpPosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.AMP);
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.AMP);
        setArm(lowerPosition,topPosition);
    }
    public void toSpeakerPosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.SPEAKER);
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.SPEAKER);
        setArm(lowerPosition,topPosition);
    }

    @Override
    public void periodic() {
      
    }
}
