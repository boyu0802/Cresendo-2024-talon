package frc.lib.AllConstants;

import java.util.HashMap;

import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.lib.convert.Convertions;

public class Constants {

    public static final class ChassisConstants{
        public final static double MaxVoltage = 12.0;
        public static final double Chassis_Width = 0.62865;
        public static final double Chassis_Length = 0.62865;
        public static final double Chassis_WheelCircumference = Convertions.inchesToMeters(4.0) * Math.PI;
        public static final double Chassis_MaxSpeed = 8;
        public static final double Chassis_MaxAngularVelocity = 4.5;
        
    }

    public static final class ShooterConstants{
        public static final double Shooter_GearRaio = 1;
        public static final boolean Shooter_Inverted = false;
        public static final boolean Shooter2_Inverted = true;
        public static final double Shooter_Speed = 1200;
        public static final double Shooter_OpenLoopRamp = 0.6;
        public static final double Shooter_ClosedLoopRamp = 0.0;
        public static final int Shooter_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Shooter_NeutralMode = CANSparkBase.IdleMode.kCoast;
        public final static double Shooter_PID[] = {0.05, 0.0, 0.0,0.0};
    } 

    public static final class DriveConstants{
        public static final double Drive_OpenLoopRamp = 0.25;
        public static final double Drive_CloseLoopRamp = 0.0;
        public static final NeutralModeValue Drive_NeutralMode = NeutralModeValue.Brake;
        public static final double Drive_ContinuousCurrentLimit= 35;
        public static final double Drive_PeakCurrentLimit = 35;
        public static final double Drive_PeakCurrentDuration = 0.1;
        public static final boolean Drive_CurrentLimitEnable = true;

        public static final double Drive_PID[] = {0.05, 0.0, 0.0}; // TO DO : Using Tuner.
        public static final double Drive_KS = (0.32/ChassisConstants.MaxVoltage);
        public static final double Drive_KV = (1.51/ChassisConstants.MaxVoltage);
        public static final double Drive_KA = (0.27/ChassisConstants.MaxVoltage);


    }

    public static final class AngleConstants{
        public static final int Angle_CurrentLimit = 35;
        public static final double Angle_PeakCurrentLimit = 35;
        public static final double Angle_PeakCurrentDuration = 0.1;
        public static final boolean Angle_CurrentLimitEnabled = true;
        public static final NeutralModeValue Angle_NeutralMode = NeutralModeValue.Coast;

    }

    public final class NavxConstants{
        public static boolean Navx_Inverted = true;
        public static byte Navx_UpdateRate = 127;
        public static final double Navx_HeadingOffset = -2.50;
    }

    public final class ArmConstants{
        public static final float Lower_Arm_ForwardSoftLimit = 40;
        public static final float Lower_Arm_ReverseSoftLimit = 0;
        public static final double Lower_Arm_PID[] = {0.01, 0.0, 0.0,0.0};// TO DO : Using Tuner.
        public static final double Lower_Arm_OpenLoopRamp = 0.2;
        public static final double Lower_Arm_ClosedLoopRamp = 0.0;
        public static final int Lower_Arm_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Arm_NeutralMode = CANSparkBase.IdleMode.kBrake;
        public static final double Lower_Arm_GearRatio = 1.0/250.0;
        public static final boolean Lower_Arm_Left_Reversed = false;
        public static final boolean Lower_Arm_Right_Reversed = true;


        public static final float Top_Arm_ForwardSoftLimit = 90;
        public static final float Top_Arm_ReverseSoftLimit = 0;
        public static final double Top_Arm_PID[] = {0.01, 0.0, 0.0,0.0};// TO DO : Using Tuner.
        public static final double Top_Arm_OpenLoopRamp = 0.2;
        public static final double Top_Arm_ClosedLoopRamp = 0.0;
        public static final int Top_Arm_CurrentLimit = 35;
        public static final double Top_Arm_GearRatio = 1.0/80.0;
        public static final boolean Top_Arm_Reversed = false;

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
                put(LowerArmPosition.AMP, 58);
                put(LowerArmPosition.SPEAKER, 18);
                put(LowerArmPosition.Intake, 4);
            }
        };

        public static HashMap<TopArmPosition,Integer> TOP_ARM_POSITIONS = new HashMap<TopArmPosition,Integer>(){
            {
                put(TopArmPosition.AMP, 58);
                put(TopArmPosition.SPEAKER, 18);
                put(TopArmPosition.Intake, 4);
            }
        };

    }

    public final class IntakeConstants{
        public static final double Intake_PID[] = {0.05, 0.0, 0.0,0.0};// TO DO : Using Tuner.
        public static final double Intake_OpenLoopRamp = 0.53;
        public static final double Intake_ClosedLoopRamp = 0.0;
        public static final int Intake_CurrentLimit = 35;
        public static final CANSparkBase.IdleMode Intake_NeutrualMode = CANSparkBase.IdleMode.kBrake;
        public static final double Intake_GearRatio = 1.0;

        public static final boolean Intake_Inverted = true;
        public static final double Intake_Speed = 1000;
       
    }

    public final class MusicConstants{
        public static final String Music = "";
    }

    public static final double JoystickDeadBand = 0.07;


    


}
