package frc.robot.SubSystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.RobotMap;

public class ShootSubSystem extends SubsystemBase {
    private static CANSparkMax ShooterMotor1;
    private static CANSparkMax ShooterMotor2;
    public ShootSubSystem() {
        ShooterMotor1 = new CANSparkMax(RobotMap.RIGHT_SHOOT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        ShooterMotor2 = new CANSparkMax(RobotMap.LEFT_SHOOT_MOTOR_ID, CANSparkMax.MotorType.kBrushless);
        shootConfig();
    }

    private void shootConfig() {
        ShooterMotor1.restoreFactoryDefaults();
        ShooterMotor1.clearFaults();

        ShooterMotor1.setSmartCurrentLimit(Constants.ShooterConstants.Shooter_CurrentLimit);
        ShooterMotor1.setIdleMode(Constants.ShooterConstants.Shooter_NeutralMode);
        ShooterMotor1.getEncoder().setPositionConversionFactor(Constants.ShooterConstants.Shooter_GearRaio);
        ShooterMotor1.getEncoder().setVelocityConversionFactor(Constants.ShooterConstants.Shooter_GearRaio / 60.0);

        ShooterMotor1.setInverted(Constants.ShooterConstants.Shooter_Inverted);

        ShooterMotor1.getPIDController().setP(Constants.ShooterConstants.Shooter_PID[0], 0);
        ShooterMotor1.getPIDController().setI(Constants.ShooterConstants.Shooter_PID[1], 0);
        ShooterMotor1.getPIDController().setD(Constants.ShooterConstants.Shooter_PID[2], 0);
        ShooterMotor1.getPIDController().setFF(Constants.ShooterConstants.Shooter_PID[3], 0);

        ShooterMotor1.burnFlash();


        ShooterMotor2.restoreFactoryDefaults();
        ShooterMotor2.clearFaults();

        ShooterMotor2.setSmartCurrentLimit(Constants.ShooterConstants.Shooter_CurrentLimit);
        ShooterMotor2.setIdleMode(Constants.ShooterConstants.Shooter_NeutralMode);
        ShooterMotor2.getEncoder().setPositionConversionFactor(Constants.ShooterConstants.Shooter_GearRaio);
        ShooterMotor2.getEncoder().setVelocityConversionFactor(Constants.ShooterConstants.Shooter_GearRaio / 60.0);

        ShooterMotor2.setInverted(Constants.ShooterConstants.Shooter2_Inverted);

        ShooterMotor2.getPIDController().setP(Constants.ShooterConstants.Shooter_PID[0], 0);
        ShooterMotor2.getPIDController().setI(Constants.ShooterConstants.Shooter_PID[1], 0);
        ShooterMotor2.getPIDController().setD(Constants.ShooterConstants.Shooter_PID[2], 0);
        ShooterMotor2.getPIDController().setFF(Constants.ShooterConstants.Shooter_PID[3], 0);

        ShooterMotor2.burnFlash();

        ShooterMotor2.follow(ShooterMotor1);
    }

    public void enableShoot(boolean inverted, boolean enableShoot) {
        
        double rpm = inverted ? -Constants.ShooterConstants.Shooter_Speed : Constants.ShooterConstants.Shooter_Speed;
        if(enableShoot){
            ShooterMotor1.getPIDController().setReference(rpm, CANSparkMax.ControlType.kVelocity);
        }
    }


    public void disableableShoot() {
        ShooterMotor1.getPIDController().setReference(0, CANSparkMax.ControlType.kVelocity);
    }

}
