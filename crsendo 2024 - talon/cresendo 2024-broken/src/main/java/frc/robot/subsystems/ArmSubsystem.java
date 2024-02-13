// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkBase.ControlType;
// import com.revrobotics.CANSparkBase.IdleMode;
// import com.revrobotics.CANSparkLowLevel.MotorType;

// import edu.wpi.first.wpilibj.CAN;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.AllConstants.Constants;

// public class ArmSubsystem extends SubsystemBase {
//     public CANSparkMax leftArmMotor;
//     public CANSparkMax rightArmMotor;

//     public ArmSubsystem() {
//         leftArmMotor = new CANSparkMax(1, MotorType.kBrushless);
//         rightArmMotor = new CANSparkMax(2, MotorType.kBrushless);
//     }

//     public void configArms(){
//         leftArmMotor.restoreFactoryDefaults();
//         rightArmMotor.restoreFactoryDefaults();
       
//         leftArmMotor.setInverted(false);
//         rightArmMotor.setInverted(true);
        
//         // leftArmMotor.getEncoder().setPositionConversionFactor(360/Constants.armConstants.Arm_GearRatio);
//         // rightArmMotor.getEncoder().setPositionConversionFactor(360/Constants.armConstants.Arm_GearRatio);
//         // leftArmMotor.getEncoder().setVelocityConversionFactor(360/(Constants.armConstants.Arm_GearRatio/60));
//         // rightArmMotor.getEncoder().setVelocityConversionFactor(360/(Constants.armConstants.Arm_GearRatio/60));
        
//         leftArmMotor.setIdleMode(IdleMode.kBrake);
//         rightArmMotor.setIdleMode(IdleMode.kBrake);
        
//         leftArmMotor.setSmartCurrentLimit(Constants.armConstants.Arm_SupplyCurrentLimit);
//         rightArmMotor.setSmartCurrentLimit(Constants.armConstants.Arm_SupplyCurrentLimit);
        
//         leftArmMotor.getPIDController().setP(Constants.armConstants.Arm_Kp, 0);
//         leftArmMotor.getPIDController().setI(Constants.armConstants.Arm_Ki, 0);
//         leftArmMotor.getPIDController().setD(Constants.armConstants.Arm_Kd, 0);
        
//         rightArmMotor.getPIDController().setP(Constants.armConstants.Arm_Kp, 0);
//         rightArmMotor.getPIDController().setI(Constants.armConstants.Arm_Ki, 0);
//         rightArmMotor.getPIDController().setD(Constants.armConstants.Arm_Kd, 0);
        
//         leftArmMotor.burnFlash();
//         rightArmMotor.burnFlash();

//         leftArmMotor.follow(rightArmMotor);
//     }
    
//     public void setArmPosition(double position){
//         rightArmMotor.getPIDController().setReference(position, ControlType.kPosition);
//     }
// }
 