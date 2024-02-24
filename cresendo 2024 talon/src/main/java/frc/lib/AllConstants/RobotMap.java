package frc.lib.AllConstants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import frc.lib.AllConstants.Constants.ChassisConstants;
import frc.lib.AllConstants.Constants.ChassisConstants.*;

public class RobotMap {
    public static final String SWERVE_CANBUS_TYPE = "rio";
    
    // TO DO : Add CAN IDs

    public final static int FRONT_LEFT_DRIVE_MOTOR_ID = 1;
    public final static int BACK_LEFT_DRIVE_MOTOR_ID = 3;
    public final static int BACK_RIGHT_DRIVE_MOTOR_ID = 5;
    public final static int FRONT_RIGHT_DRIVE_MOTOR_ID = 7;

    public final static int FRONT_LEFT_ANGLE_MOTOR_ID = 2;
    public final static int BACK_LEFT_ANGLE_MOTOR_ID = 4;
    public final static int BACK_RIGHT_ANGLE_MOTOR_ID = 6;
    public final static int FRONT_RIGHT_ANGLE_MOTOR_ID = 8;
    
    public final static int FRONT_LEFT_CANCODER_MOTOR_ID = 1;
    public final static int BACK_LEFT_CANCODER_MOTOR_ID = 2;
    public final static int BACK_RIGHT_CANCODER_MOTOR_ID = 3;
    public final static int FRONT_RIGHT_CANCODER_MOTOR_ID = 4;

    public final static int COLLECT_MOTOR_ID = 8;
    public final static int SHOOT_MOTOR1_ID = 6;
    public final static int SHOOT_MOTOR2_ID = 7;
    public final static int ARM_LOWER_LEFT_MOTOR_ID = 1;
    public final static int ARM_LOWER_RIGHT_MOTOR_ID = 2;
    public final static int ARM_TOP_MOTOR_ID = 6;


    public final static Rotation2d FRONT_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(40.781250);
    public final static Rotation2d BACK_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(81.2790315);
    public final static Rotation2d BACK_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(100.634766);
    public final static Rotation2d FRONT_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(254.794922);

    public static final SwerveDriveKinematics SwerveDriveKinematics = new SwerveDriveKinematics(
        new Translation2d(ChassisConstants.Chassis_Width / 2, ChassisConstants.Chassis_Length / 2),
        new Translation2d(-ChassisConstants.Chassis_Width / 2, ChassisConstants.Chassis_Length / 2),
        new Translation2d(-ChassisConstants.Chassis_Width / 2, -ChassisConstants.Chassis_Length / 2),
        new Translation2d(ChassisConstants.Chassis_Width / 2, -ChassisConstants.Chassis_Length / 2)
    );
}
