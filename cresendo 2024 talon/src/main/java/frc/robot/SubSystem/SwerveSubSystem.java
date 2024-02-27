package frc.robot.SubSystem;


import com.kauailabs.navx.frc.AHRS;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.AllConstants.Constants.SwerveConstants;
import frc.lib.AllConstants.RobotMap;
import frc.lib.AllConstants.SwerveTypeConstants;
import frc.robot.Robot;


public class SwerveSubSystem extends SubsystemBase {

    public swerveModule[] swerveModules;
    public SwerveDriveKinematics swerveDriveKinematics = SwerveConstants.SwerveDriveKinematics;
    public SwerveDriveOdometry swerveDriveOdometry ;
    public AHRS navX;

    public SwerveSubSystem(){
        navX = new AHRS(SPI.Port.kMXP, SwerveConstants.NAVX_UPDATE_RATE);
        navX.reset();


        swerveModules = new swerveModule[]{
            new swerveModule(
                0,SwerveTypeConstants.SDSMK4I_L1(),
                RobotMap.FRONT_LEFT_DRIVE_MOTOR_ID,
                RobotMap.FRONT_LEFT_ANGLE_MOTOR_ID,
                RobotMap.FRONT_LEFT_CANCODER_MOTOR_ID,
                RobotMap.FRONT_LEFT_ANGLE_OFFSET),
            new swerveModule(
                1,SwerveTypeConstants.SDSMK4I_L1(),
                RobotMap.BACK_LEFT_DRIVE_MOTOR_ID,
                RobotMap.BACK_LEFT_ANGLE_MOTOR_ID,
                RobotMap.BACK_LEFT_CANCODER_MOTOR_ID,
                RobotMap.BACK_LEFT_ANGLE_OFFSET),
            new swerveModule(
                2,SwerveTypeConstants.SDSMK4I_L1(),
                RobotMap.BACK_RIGHT_DRIVE_MOTOR_ID,
                RobotMap.BACK_RIGHT_ANGLE_MOTOR_ID,
                RobotMap.BACK_RIGHT_CANCODER_MOTOR_ID,
                RobotMap.BACK_RIGHT_ANGLE_OFFSET),
            new swerveModule(
                3,SwerveTypeConstants.SDSMK4I_L1(),
                RobotMap.FRONT_RIGHT_DRIVE_MOTOR_ID,
                RobotMap.FRONT_RIGHT_ANGLE_MOTOR_ID,
                RobotMap.FRONT_RIGHT_CANCODER_MOTOR_ID,
                RobotMap.FRONT_RIGHT_ANGLE_OFFSET),
        };

        Timer.delay(10.0);
        resetModulesToAbsolute();

        

        swerveDriveKinematics = SwerveConstants.SwerveDriveKinematics;
        swerveDriveOdometry = new SwerveDriveOdometry(swerveDriveKinematics, getYaw(), getModulePositions());
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop){
        SwerveModuleState[] swerveModuleStates = 
        swerveDriveKinematics.toSwerveModuleStates(
           fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                translation.getX(),
                translation.getY(),
                rotation,
                getYaw())
            : new ChassisSpeeds(
                translation.getX(),
                translation.getY(),
                rotation));
            SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates,SwerveConstants.SWERVE_MAX_SPEED);
            for (swerveModule module : swerveModules){
                module.setDesireState(swerveModuleStates[module.moduleNumber], isOpenLoop);
            }
    }
    public void setModuleStates(SwerveModuleState[] swerveModuleStates){
        
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, SwerveConstants.SWERVE_MAX_SPEED);
        for (swerveModule module : swerveModules){
           
            module.setDesireState(swerveModuleStates[module.moduleNumber], false);
        }
    }

    public Pose2d getPose(){
        return swerveDriveOdometry.getPoseMeters();
    }
    public Rotation2d getRotation2d(){
        return navX.getRotation2d();
    }
    public void reseetOdometry(Pose2d pose){
        swerveDriveOdometry.resetPosition(getYaw(),getModulePositions(),pose);
    }
    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] modulePositions = new SwerveModulePosition[4];
        for (swerveModule module : swerveModules){
            modulePositions[module.moduleNumber] = module.getPosition();
        }

        return modulePositions;
    }

    public Rotation2d getYaw(){
       return (SwerveConstants.NAVX_INVERTED) ? Rotation2d.fromDegrees(360 - navX.getYaw()) : Rotation2d.fromDegrees(navX.getYaw());
    }
    public void resetModulesToAbsolute(){
        SmartDashboard.putBoolean("stuff", true);
        for(swerveModule module : swerveModules){
            module.resetToAbosolute();
        }
    }
    public void zeroGyro(){
        System.out.println("asdfghjkl");
        navX.reset();
    }
    public SwerveModuleState[] getModuleStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(swerveModule mod : swerveModules){
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    @Override
    public void periodic() {
        swerveDriveOdometry.update(getYaw(), getModulePositions());
        for(swerveModule module : swerveModules){
            SmartDashboard.putNumber("Mod " + module.moduleNumber + " CanCoder",module.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + module.moduleNumber + " Integrated", module.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + module.moduleNumber + " Velocity", module.getState().speedMetersPerSecond);
            
        }
    }

   
}