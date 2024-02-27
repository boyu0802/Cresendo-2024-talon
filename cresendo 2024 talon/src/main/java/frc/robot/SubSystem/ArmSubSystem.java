package frc.robot.SubSystem;


import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.SparkAbsoluteEncoder.Type;

import edu.wpi.first.wpilibj.CAN;
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
        lowerLeftArm = new CANSparkMax(1, CANSparkMax.MotorType.kBrushless);
        lowerRightArm = new CANSparkMax(2, CANSparkMax.MotorType.kBrushless);
        topArmMotor = new CANSparkMax(5, CANSparkMax.MotorType.kBrushless);
        lowerArmConfig();
        topArmConfig();
        syncEncoder();

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
        lowerLeftArm.enableSoftLimit(SoftLimitDirection.kReverse, true);
        lowerRightArm.enableSoftLimit(SoftLimitDirection.kReverse, true);
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
        lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).setPositionConversionFactor(360);
        lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).setVelocityConversionFactor(1);
        lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).setInverted(true);
        lowerLeftArm.follow(lowerRightArm, true);
        lowerRightArm.getEncoder().setPositionConversionFactor((Constants.ArmConstants.Lower_Arm_GearRatio) *360);
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
        topArmMotor.enableSoftLimit(SoftLimitDirection.kForward,true);
        topArmMotor.enableSoftLimit(SoftLimitDirection.kReverse,true);
        topArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, Constants.ArmConstants.Top_Arm_ForwardSoftLimit);
        topArmMotor.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, Constants.ArmConstants.Top_Arm_ReverseSoftLimit);
        topArmMotor.setIdleMode(Constants.ArmConstants.Arm_NeutralMode);
        topArmMotor.setInverted(Constants.ArmConstants.Top_Arm_Reversed);
        topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).setPositionConversionFactor(360);
        topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).setVelocityConversionFactor(1);
        topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).setInverted(true);
        topArmMotor.getEncoder().setPositionConversionFactor((Constants.ArmConstants.Top_Arm_GearRatio)*360);

        topArmMotor.burnFlash();
    }

    // public void setLowerArm(double setPoint) {
    //     // lowerRightArm.getPIDController().setReference(setPoint, CANSparkMax.ControlType.kPosition);
    //     lowerRightArm.set(setPoint*0.7);
    // }

    // public void setTopArm(boolean up, boolean down) {
    //     if(up && down){
    //         topArmMotor.set(0);
    //     }else if (up){
    //         topArmMotor.set(0.7);
    //     }else if(down){
    //         topArmMotor.set(-0.7);
    //     }else{
    //         topArmMotor.set(0);
    //     }
    // }

  

    public void syncEncoder(){
        lowerLeftArm.getEncoder().setPosition(lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
        lowerRightArm.getEncoder().setPosition(lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
        topArmMotor.getEncoder().setPosition(topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
        SmartDashboard.putBoolean("sync encoder", true);
    }
    
    public void setTopArm(double topPosition) {
        topArmMotor.getPIDController().setReference(topPosition, ControlType.kPosition);
    }

    public void setLowerArm(double lowerPosition){
        lowerRightArm.getPIDController().setReference(lowerPosition, ControlType.kPosition);
    }

    public void topToIntakePosition() {
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.Intake);
        setTopArm( topPosition);
    }
    public void topToAmpPosition() {
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.AMP);
        setTopArm(topPosition);
    }
    public void topToSpeakerPosition() {
        topPosition = Constants.ArmConstants.TOP_ARM_POSITIONS.get(Constants.ArmConstants.TopArmPosition.SPEAKER);
        setTopArm( topPosition);
        SmartDashboard.putNumber("top speaker position", topPosition);
    }

    public void lowerToIntakePosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.Intake);
        setLowerArm( lowerPosition);
    }
    public void lowerToAmpPosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.AMP);
        setLowerArm(lowerPosition);
    }
    public void lowerToSpeakerPosition() {
        lowerPosition = Constants.ArmConstants.LOWER_ARM_POSITIONS.get(Constants.ArmConstants.LowerArmPosition.SPEAKER);
        setLowerArm( lowerPosition);
        SmartDashboard.putNumber("lower to speaker", lowerPosition);
    }
    

    @Override
    public void periodic() {
        SmartDashboard.putNumber("SecAbsPos", topArmMotor.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
        SmartDashboard.putNumber("firAbsPos", lowerRightArm.getAbsoluteEncoder(Type.kDutyCycle).getPosition());
        SmartDashboard.putNumber("right arm Pos", lowerRightArm.getEncoder().getPosition());
        SmartDashboard.putNumber("top arm Pos", topArmMotor.getEncoder().getPosition());


    }
}
