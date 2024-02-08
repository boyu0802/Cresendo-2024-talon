// package frc.robot.subsystems;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkBase.ControlType;
// import com.revrobotics.CANSparkBase.IdleMode;
// import com.revrobotics.CANSparkLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.AllConstants.Constants;

// public class Intake_ShooterSubsystem extends SubsystemBase{
//     public final CANSparkMax intakeMotor;
//     public final CANSparkMax shooterMotor;
    
//     public Intake_ShooterSubsystem() {
    
//         intakeMotor = new CANSparkMax(4,MotorType.kBrushless); 
//         shooterMotor = new CANSparkMax(3,MotorType.kBrushless);
//         configIntake();
//         configShooter();
//     }

//     public void configIntake(){
//         intakeMotor.restoreFactoryDefaults();
//         intakeMotor.setInverted(true);
//         intakeMotor.getPIDController().setP(Constants.intakeConstants.Intake_Kp, 0);
//         intakeMotor.getPIDController().setI(Constants.intakeConstants.Intake_Ki, 0);
//         intakeMotor.getPIDController().setD(Constants.intakeConstants.Intake_Kd, 0);
//         intakeMotor.setIdleMode(IdleMode.kCoast);
//         intakeMotor.getEncoder().setPositionConversionFactor(Constants.intakeConstants.Intake_GearRatio);
//         intakeMotor.getEncoder().setVelocityConversionFactor(Constants.intakeConstants.Intake_GearRatio/60);
//         intakeMotor.setSmartCurrentLimit(Constants.intakeConstants.Intake_SupplyCurrentLimit);
//         intakeMotor.burnFlash();

//     }

//     public void configShooter(){
//         shooterMotor.restoreFactoryDefaults();
//         shooterMotor.setInverted(false);
//         shooterMotor.getPIDController().setP(Constants.shooterConstants.Shooter_Kp, 0);
//         shooterMotor.getPIDController().setI(Constants.shooterConstants.Shooter_Ki, 0);
//         shooterMotor.getPIDController().setD(Constants.shooterConstants.Shooter_Kd, 0);
//         shooterMotor.setIdleMode(IdleMode.kCoast);
//         shooterMotor.getEncoder().setPositionConversionFactor(Constants.shooterConstants.Shooter_GearRatio);
//         shooterMotor.getEncoder().setVelocityConversionFactor(Constants.shooterConstants.Shooter_GearRatio/60);
//         shooterMotor.setSmartCurrentLimit(Constants.shooterConstants.Shooter_SupplyCurrentLimit);
//         shooterMotor.burnFlash();
//     }

//     public void setIntakeSpeed(){
//         intakeMotor.getPIDController().setReference(Constants.intakeConstants.IntakeSpeed, ControlType.kVelocity);
//     }

//     public void setShooterSpeed(){
//         shooterMotor.getPIDController().setReference(Constants.shooterConstants.ShooterSpeed, ControlType.kVelocity);
//     }

//     public void stopIntake(){
//         intakeMotor.getPIDController().setReference(0, ControlType.kVelocity);
//     }

//     public void stopShooter(){
//         shooterMotor.getPIDController().setReference(0, ControlType.kVelocity);
//     }


    
// }
