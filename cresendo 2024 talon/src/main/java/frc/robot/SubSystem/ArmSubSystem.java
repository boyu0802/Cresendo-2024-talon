package frc.robot.SubSystem;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.SparkAbsoluteEncoder.Type;

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
        lowerArmConfig();
        topArmConfig();
        // syncEncoder();

    }

    private void lowerArmConfig(){
        lowerLeftArm.restoreFactoryDefaults();
        lowerRightArm.restoreFactoryDefaults();
        lowerLeftArm.getPIDController().setP(Constants.ArmConstants.Lower_Arm_PID[0], 0);
        lowerRightArm.getPIDController().setP(Constants.ArmConstants.Lower_Arm_PID[0], 0);
        lowerLeftArm.getPIDController().setI(Constants.ArmConstants.Lower_Arm_PID[1], 0);
        lowerRightArm.getPIDController().setI(Constants.ArmConstants.Lower_Arm_PID[1], 0);
        lowerLeftArm.getPIDController().setD(Constants.ArmConstants.Lower_Arm_PID[2], 0);
        lowerRightArm.getPIDController().setD(Constants.ArmConstants.Lower_Arm_PID[2], 0);
        lowerLeftArm.getPIDController().setFF(Constants.ArmConstants.Lower_Arm_PID[3], 0);
        lowerRightArm.getPIDController().setFF(Constants.ArmConstants.Lower_Arm_PID[3], 0);
        lowerLeftArm.setSmartCurrentLimit(Constants.ArmConstants.Lower_Arm_CurrentLimit);
        lowerRightArm.setSmartCurrentLimit(Constants.ArmConstants.Lower_Arm_CurrentLimit);
        lowerLeftArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.ArmConstants.Lower_Arm_ForwardSoftLimit);
        lowerRightArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.ArmConstants.Lower_Arm_ForwardSoftLimit);
        lowerLeftArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.ArmConstants.Lower_Arm_ReverseSoftLimit);
        lowerRightArm.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.ArmConstants.Lower_Arm_ReverseSoftLimit);
        lowerLeftArm.setIdleMode(Constants.ArmConstants.Arm_NeutralMode);
        lowerRightArm.setIdleMode(Constants.ArmConstants.Arm_NeutralMode);
        lowerLeftArm.setInverted(Constants.ArmConstants.Lower_Arm_Left_Reversed);
        lowerRightArm.setInverted(Constants.ArmConstants.Lower_Arm_Right_Reversed);
        lowerLeftArm.getEncoder().setPosition(0);
        lowerLeftArm.getEncoder().setPositionConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio);
        lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).setPositionConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio);
        lowerLeftArm.getEncoder().setVelocityConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio / 60.0);
        lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).setVelocityConversionFactor(Constants.ArmConstants.Lower_Arm_GearRatio / 60.0);
        lowerLeftArm.follow(lowerRightArm, true);
        lowerLeftArm.burnFlash();
        lowerRightArm.burnFlash();
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

    public void setArm(double setPoint) {
        // SmartDashboard.putNumber("Arm Refference", setPoint);
        // lowerLeftArm.getPIDController().setReference(lowerSetPoint, CANSparkMax.ControlType.kPosition);
        // lowerRightArm.getPIDController().setReference(lowerSetPoint, CANSparkMax.ControlType.kPosition);
        // topArmMotor.getPIDController().setReference(topSetPoint, CANSparkMax.ControlType.kPosition);
        lowerRightArm.getPIDController().setReference(setPoint*40,ControlType.kPosition);
    }

    // public void syncEncoder(){
    //     lowerLeftArm.getEncoder().setPosition(lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    //     lowerRightArm.getEncoder().setPosition(lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    //     topArmMotor.getEncoder().setPosition(topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
    // }


    // public void toIntakePosition() {
    //     lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.Intake);
    //     topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.Intake);
    //     setArm(lowerPosition,topPosition);
    // }
    // public void toAmpPosition() {
    //     lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.AMP);
    //     topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.AMP);
    //     setArm(lowerPosition,topPosition);
    // }
    // public void toSpeakerPosition() {
    //     lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.SPEAKER);
    //     topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.SPEAKER);
    //     setArm(lowerPosition,topPosition);
    // }

    @Override
    public void periodic() {
      
    }
}
