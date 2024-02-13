// package frc.robot.subsystems;

// import com.ctre.phoenix6.Orchestra;
// import com.ctre.phoenix6.hardware.TalonFX;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.AllConstants.Constants;
// import frc.robot.AllConstants.RobotMap;

// public class MusicSubsystem extends SubsystemBase {
//     public final Orchestra orchestra;

//     public TalonFX[] motors;

//     public MusicSubsystem(){
//         orchestra = new Orchestra();
//         orchestraConfig();
//     }

//     public void orchestraConfig(){
//         motors = new TalonFX[]{
//             new TalonFX(RobotMap.SwerveMod0.driveMotorID),
//             new TalonFX(RobotMap.SwerveMod1.driveMotorID),
//             new TalonFX(RobotMap.SwerveMod3.driveMotorID),
//             new TalonFX(RobotMap.SwerveMod2.driveMotorID),
//             new TalonFX(RobotMap.SwerveMod0.turningMotorID),
//             new TalonFX(RobotMap.SwerveMod1.turningMotorID),
//             new TalonFX(RobotMap.SwerveMod3.turningMotorID),
//             new TalonFX(RobotMap.SwerveMod2.turningMotorID)
//         };
        
//         for(TalonFX motor : motors){
//             orchestra.addInstrument(motor);
//         }

//         orchestra.loadMusic((Constants.MusicConstants.Music));
//     }
    
//     public void playMusic(){
//         orchestra.play();
//     }

//     public void stopMusic(){
//         orchestra.stop();
//     }

//     public void pauseMusic(){
//         orchestra.pause();
//     }

// }
