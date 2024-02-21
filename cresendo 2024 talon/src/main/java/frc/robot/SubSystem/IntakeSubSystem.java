package frc.robot.SubSystem;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.RobotMap;

public class IntakeSubSystem extends SubsystemBase {
  private static CANSparkMax Intake;


  public IntakeSubSystem() {
    Intake = new CANSparkMax(RobotMap.COLLECT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
    collectConfig();
  }

  private void collectConfig() {
    Intake.restoreFactoryDefaults();

    Intake.getPIDController().setP(Constants.IntakeConstants.Intake_PID[0], 0);
    Intake.getPIDController().setI(Constants.IntakeConstants.Intake_PID[1], 0);
    Intake.getPIDController().setD(Constants.IntakeConstants.Intake_PID[2], 0);
    Intake.getPIDController().setFF(Constants.IntakeConstants.Intake_PID[3], 0);

    Intake.setSmartCurrentLimit(Constants.IntakeConstants.Intake_CurrentLimit);
    Intake.setIdleMode(Constants.IntakeConstants.Intake_NeutrualMode);
    Intake.getEncoder().setPositionConversionFactor(Constants.IntakeConstants.Intake_GearRatio);
    Intake.getEncoder().setVelocityConversionFactor(Constants.IntakeConstants.Intake_GearRatio / 60.0);

    Intake.setInverted(Constants.IntakeConstants.Intake_Inverted);

    Intake.burnFlash();
  }

  public void enableIntake() {
      Intake.getPIDController().setReference(Constants.IntakeConstants.Intake_Speed, CANSparkBase.ControlType.kVelocity);
  }

  public void reverseIntake() {
    Intake.getPIDController().setReference(-Constants.IntakeConstants.Intake_Speed, CANSparkBase.ControlType.kVelocity);
  }

  public void stopIntake() {
    Intake.stopMotor();
  }


  
}
