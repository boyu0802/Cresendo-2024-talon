package frc.lib.AllConstants;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;


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
    public final static int ARM_TOP_MOTOR_ID = 5;


    public final static Rotation2d FRONT_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(63.369140625);
    public final static Rotation2d BACK_LEFT_ANGLE_OFFSET = Rotation2d.fromDegrees(334.86328125);
    public final static Rotation2d BACK_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(289.3359375 - 180);
    public final static Rotation2d FRONT_RIGHT_ANGLE_OFFSET = Rotation2d.fromDegrees(348.92578125 - 180);

}
