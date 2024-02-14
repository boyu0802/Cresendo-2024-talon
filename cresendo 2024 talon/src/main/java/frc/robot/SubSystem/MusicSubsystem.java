package frc.robot.SubSystem;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.AllConstants.Constants;
import frc.lib.AllConstants.RobotMap;

public class MusicSubsystem extends SubsystemBase {
    public final Orchestra orchestra;

    public TalonFX[] motors;

    public MusicSubsystem(){
        orchestra = new Orchestra();
        orchestraConfig();
    }

    public void orchestraConfig(){
        motors = new TalonFX[]{
            new TalonFX(RobotMap.BACK_LEFT_DRIVE_MOTOR_ID),
            new TalonFX(RobotMap.BACK_RIGHT_DRIVE_MOTOR_ID),
            new TalonFX(RobotMap.FRONT_LEFT_DRIVE_MOTOR_ID),
            new TalonFX(RobotMap.FRONT_RIGHT_DRIVE_MOTOR_ID),
            new TalonFX(RobotMap.BACK_LEFT_ANGLE_MOTOR_ID),
            new TalonFX(RobotMap.BACK_RIGHT_ANGLE_MOTOR_ID),
            new TalonFX(RobotMap.FRONT_LEFT_ANGLE_MOTOR_ID),
            new TalonFX(RobotMap.FRONT_RIGHT_ANGLE_MOTOR_ID)
        };
        
        for(TalonFX motor : motors){
            orchestra.addInstrument(motor);
        }

        orchestra.loadMusic((Constants.MusicConstants.Music));
    }
    
    public void playMusic(){
        orchestra.play();
    }

    public void stopMusic(){
        orchestra.stop();
    }

    public void pauseMusic(){
        orchestra.pause();
    }

}
