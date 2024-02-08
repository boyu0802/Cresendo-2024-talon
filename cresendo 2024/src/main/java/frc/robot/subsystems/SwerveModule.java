package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.AbsoluteSensorRangeValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.lib.AllConstants.Constants;
import frc.robot.lib.AllConstants.SwerveTypeConstants;
import frc.robot.lib.Math.Conversion;
import frc.robot.lib.Util.CTREconfigs;
import frc.robot.lib.Util.OptimizeAngle;



public class SwerveModule {
     private final VelocityVoltage velocity = new VelocityVoltage(0);
     private final DutyCycleOut driveDutyCycle = new DutyCycleOut(0);
     private final PositionVoltage positionVoltage = new PositionVoltage(0);
     private final TalonFX angleMotor;
     private final TalonFX driveMotor;
     private final CANcoder cancoder;

     private final Rotation2d angleOffset;

     private Rotation2d lastAngle;
     public final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
         Constants.DriveMotorConstants.Drive_Ks , Constants.DriveMotorConstants.Drive_Kv, Constants.DriveMotorConstants.Drive_Ka
     );

     public SwerveModule(int driveMotorID, int angleMotorID, int cancoderID, Rotation2d angleOffset){

          angleMotor = new TalonFX(angleMotorID);
          driveMotor = new TalonFX(driveMotorID);
          cancoder = new CANcoder(cancoderID);
          this.angleOffset = angleOffset;
          lastAngle = getState().angle;
          driveMotor.getConfigurator().apply(CTREconfigs.driveMotorConfig);
          angleMotor.getConfigurator().apply(CTREconfigs.angleMotorConfig);
          cancoder.getConfigurator().apply(CTREconfigs.cancoderConfig);
          resetToAbsolute();
          
     }

     public void setSpeed(SwerveModuleState state, boolean isOpenLoop){
          if (isOpenLoop){
               driveDutyCycle.Output =  state.speedMetersPerSecond/Constants.ChassisConstants.MaxSpeed; //doesn't change the output based on sensors
               driveMotor.setControl(driveDutyCycle);
          }else {
               velocity.Velocity = Conversion.MPSToRPS(state.speedMetersPerSecond,SwerveTypeConstants.SDSMK4I_L1().wheelCircumference);
               velocity.FeedForward = feedforward.calculate(state.speedMetersPerSecond);// feedforward can be closed loop as long as it sends a signal back.
               driveMotor.setControl(velocity);
          }

     }
     public void setAngle(SwerveModuleState state){
          Rotation2d angle = (Math.abs(state.speedMetersPerSecond)<= (Constants.ChassisConstants.MaxSpeed*0.01)) ? lastAngle : state.angle;

          angleMotor.setControl(positionVoltage.withPosition(angle.getRotations()));
          lastAngle = angle;
     }

     public void setState(SwerveModuleState swerveModuleState, boolean isOpenLoop){
           SwerveModuleState state = OptimizeAngle.optimized(swerveModuleState, getState().angle);
           setSpeed(state, isOpenLoop);
           setAngle(state);

     }
     public Rotation2d getCancoder(){
          return Rotation2d.fromRotations(cancoder.getAbsolutePosition().getValue()%360);
     }





     public Rotation2d getAngle(){
          return Rotation2d.fromRotations(angleMotor.getPosition().getValue()%360);
     }





     public void resetToAbsolute(){
          angleMotor.setPosition(getCancoder().getRotations() - angleOffset.getRotations());
//          SmartDashboard.putNumber("anglemotor set position to:", getCancoder().getDegrees() - angleOffset.getDegrees());
//          SmartDashboard.putNumber("angle offset: ", angleOffset.getDegrees() );
//          SmartDashboard.putNumber("cancoder degrees", getCancoder().getDegrees());

     }


     public SwerveModulePosition getPosition() {
          return new SwerveModulePosition(
                  Conversion.falconToMeters(SwerveTypeConstants.SDSMK4I_L1().driveGearRatio,SwerveTypeConstants.SDSMK4I_L1().wheelCircumference, driveMotor.getPosition().getValue()) , getAngle()
          );
     }

     public SwerveModuleState getState(){
          return new SwerveModuleState(Conversion.falconToMPS(driveMotor.getPosition().getValue(), SwerveTypeConstants.SDSMK4I_L1().wheelCircumference, SwerveTypeConstants.SDSMK4I_L1().driveGearRatio),getAngle());
     }
     





}


