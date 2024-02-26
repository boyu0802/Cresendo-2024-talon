package frc.lib.AllConstants;

import java.util.HashMap;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.lib.convert.Convertions;

public class Constants {


    public static final class ShooterConstants{
        public static final double Shooter_GearRaio = 2;
        public static final boolean Shooter_Inverted = false;
        public static final boolean Shooter2_Inverted = true;
        public static final double Shooter_Speed = 1200;
        public static final double Shooter_OpenLoopRamp = 0.6;
        public static final double Shooter_ClosedLoopRamp = 0.0;
        public static final int Shooter_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Shooter_NeutralMode = CANSparkBase.IdleMode.kCoast;
        public final static double Shooter_PID[] = {0.00003, 0.00005, 0.0,0.0007};
    } 
    


    

    public final class ArmConstants{
        public static final float Lower_Arm_ForwardSoftLimit = 90;
        public static final float Lower_Arm_ReverseSoftLimit = 0;
        public static final double Lower_Arm_PID[] = {0.001, 0.0, 0.0,0.0};// TO DO : Using Tuner.
        public static final double Lower_Arm_OpenLoopRamp = 0.2;
        public static final double Lower_Arm_ClosedLoopRamp = 0.0;
        public static final int Lower_Arm_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Arm_NeutralMode = CANSparkBase.IdleMode.kBrake;
        public static final double Lower_Arm_GearRatio = 1.0/250.0;
        public static final boolean Lower_Arm_Left_Reversed = true;
        public static final boolean Lower_Arm_Right_Reversed = false;


        public static final float Top_Arm_ForwardSoftLimit = 130;
        public static final float Top_Arm_ReverseSoftLimit = 0;
        public static final double Top_Arm_PID[] = {0.001, 0.0, 0.0,0.0};// TO DO : Using Tuner.
        public static final double Top_Arm_OpenLoopRamp = 0.2;
        public static final double Top_Arm_ClosedLoopRamp = 0.0;
        public static final int Top_Arm_CurrentLimit = 35;
        public static final double Top_Arm_GearRatio = 1.0/80.0;
        public static final boolean Top_Arm_Reversed = true;

        public enum LowerArmPosition {
            AMP,
            SPEAKER,
            Intake
        }

        public enum TopArmPosition {
            AMP,
            SPEAKER,
            Intake
        }


        public static HashMap<LowerArmPosition,Integer> LOWER_ARM_POSITIONS = new HashMap<LowerArmPosition,Integer>(){
            {
                put(LowerArmPosition.AMP, 30);
                put(LowerArmPosition.SPEAKER, 42);
                put(LowerArmPosition.Intake, 90);
            }
        };

        public static HashMap<TopArmPosition,Integer> TOP_ARM_POSITIONS = new HashMap<TopArmPosition,Integer>(){
            {
                put(TopArmPosition.AMP, 114);
                put(TopArmPosition.SPEAKER, 28);
                put(TopArmPosition.Intake, 128);
            }
        };

    }

    public final class IntakeConstants{
        public static final double Intake_PID[] = {0.0012, 0.0001, 0.0,0.0002};// TO DO : Using Tuner.
        public static final double Intake_OpenLoopRamp = 0.53;
        public static final double Intake_ClosedLoopRamp = 0.0;
        public static final int Intake_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Intake_NeutrualMode = CANSparkBase.IdleMode.kCoast;
        public static final double Intake_GearRatio = 1/25;

        public static final boolean Intake_Inverted = true;
        public static final double Intake_Speed = 1000;
       
    }

    public final class MusicConstants{
        public static final String Music = "";
    }

    public static final double JoystickDeadBand = 0.07;

    public final class SwerveConstants{
    
        public static final double SWERVE_CHASSIS_TRACKWIDTH_METERS = 0.62865;
        public static final double SWERVE_CHASSIS_WHEELBASE_METERS = 0.62865;
        public static final double SWERVE_WHEEL_CIRCUMFERENCE = Convertions.inchesToMeters(4.0) * Math.PI;
        public static final double SWERVE_MAX_SPEED = 8; //Wait for test.
        public static final double SWERVE_MAX_ANGULAR_VELOCITY = 4.5;//Wait for test.

        public static final double SWERVE_POV_MOVE_SPEED = 0.2;

        public static final int SWEVRVE_PERIOD_MS = 10;
        public static final double SWERVE_VOLTAGE_COMPENSATION = 12.0;
        public static final int SWERVE_ANGLE_CURRENT_LIMIT = 35;
        public static final double SWERVE_ANGLE_CONTINUOUS_CURRENT_LIMIT = 35;
        public static final double SWERVE_ANGLE_PEAK_CURRENT_LIMIT = 35;
        public static final double SWERVE_ANGLE_PEAK_CURRENT_DURATION = 0.1;
        public static final boolean SWERVE_ANGLE_CURRENT_ENABLED = true;

        public static final double SWERVE_DRIVE_CONTINUOUS_CURRENT_LIMIT = 35;
        public static final double SWERVE_DRIVE_PEAK_CURRENT_LIMIT = 35;
        public static final double SWERVE_DRIVE_PEAK_CURRENT_DURATION = 0.1;
        public static final boolean SWERVE_DRIVE_CURRENT_ENABLED = true;

        public static final double SWERVE_DRIVE_PID[] = {0.05, 0.0, 0.0}; // TO DO : Using Tuner.
        public static final double SWERVE_DRIVE_KS = (0.32 / 12);
        public static final double SWERVE_DRIVE_KV = (1.51 / 12);
        public static final double SWERVE_DRIVE_KA = (0.27 / 12);

        public static final double SWERVE_AUTO_XY_PID[] = {5.0, 0.0, 0.0}; // TO DO : Using Tuner.
        public static final double SWERVE_AUTO_Z_PID[] = {5.0, 0.0, 0.0};

        public static final double SWERVE_DRIVE_MOTOR_OPENLOOPRAMP = 0.25;
        public static final double SWERVE_DRIVE_MOTOR_CLOSELOOPRAMP = 0.0;


        public static final NeutralModeValue DRIVE_NEUTRAL_MODE = NeutralModeValue.Brake;
        public static final NeutralModeValue ANGLE_NEUTRAL_MODE = NeutralModeValue.Coast;

        public static final double DRIVEJOYSTICK_DEADBAND = 0.07;

        public static final SwerveDriveKinematics SwerveDriveKinematics = new SwerveDriveKinematics(new Translation2d(SWERVE_CHASSIS_TRACKWIDTH_METERS / 2, SWERVE_CHASSIS_WHEELBASE_METERS / 2), new Translation2d(-SWERVE_CHASSIS_TRACKWIDTH_METERS / 2, SWERVE_CHASSIS_WHEELBASE_METERS / 2), new Translation2d(-SWERVE_CHASSIS_TRACKWIDTH_METERS / 2, -SWERVE_CHASSIS_WHEELBASE_METERS / 2), new Translation2d(SWERVE_CHASSIS_TRACKWIDTH_METERS / 2, -SWERVE_CHASSIS_WHEELBASE_METERS / 2));

            
        public static boolean NAVX_INVERTED = true;
        public static byte NAVX_UPDATE_RATE = 127;
        public static final double NAVX_FUSEDHEADING_OFFSET = -2.50;
    }

}
