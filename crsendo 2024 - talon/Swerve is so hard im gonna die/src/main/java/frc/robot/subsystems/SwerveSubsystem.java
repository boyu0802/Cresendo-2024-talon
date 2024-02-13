  // Copyright (c) FIRST and other WPILib contributors.
  // Open Source Software; you can modify and/or share it under the terms of
  // the WPILib BSD license file in the root directory of this project.

  package frc.robot.subsystems;

  import com.ctre.phoenix6.configs.CANcoderConfiguration;
  import com.ctre.phoenix6.configs.MagnetSensorConfigs;
  import com.ctre.phoenix6.configs.Slot0Configs;
  import com.ctre.phoenix6.configs.TalonFXConfiguration;
  import com.ctre.phoenix6.controls.VelocityVoltage;
  import com.ctre.phoenix6.hardware.CANcoder;
  import com.ctre.phoenix6.hardware.TalonFX;
  import com.kauailabs.navx.frc.AHRS;
  import com.revrobotics.CANSparkLowLevel;
  import com.revrobotics.CANSparkMax;
  import com.revrobotics.CANSparkBase.ControlType;

  import edu.wpi.first.math.controller.SimpleMotorFeedforward;
  import edu.wpi.first.math.geometry.Pose2d;
  import edu.wpi.first.math.geometry.Rotation2d;
  import edu.wpi.first.math.geometry.Translation2d;
  import edu.wpi.first.math.kinematics.ChassisSpeeds;
  import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
  import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
  import edu.wpi.first.math.kinematics.SwerveModulePosition;
  import edu.wpi.first.math.kinematics.SwerveModuleState;
  import edu.wpi.first.wpilibj.SPI;
  import edu.wpi.first.wpilibj2.command.SubsystemBase;
  import frc.robot.Constants;

  public class SwerveSubsystem extends SubsystemBase {
    private final static SwerveSubsystem INSTANCE = new SwerveSubsystem();

    @SuppressWarnings("WeakerAccess")
    public static SwerveSubsystem getInstance() {
        return INSTANCE;
    }

    public final AHRS Navx = new AHRS(SPI.Port.kMXP);  //define Navx 
    
    public SwerveDriveOdometry odometry;

    public SwerveModulePosition[]  getModulePosition(){
      return new SwerveModulePosition[]{
          swerveModule[0].getPosition(),      //finds the position of the modules, return in an array
          swerveModule[1].getPosition(),
          swerveModule[2].getPosition(),
          swerveModule[3].getPosition()
      };
    }

    public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics( //define the kinematics of the swerve chassis by defining the positio of each module, kinematics is like mapping out the robot
      new Translation2d(Constants.chassisLengthMeters / 2, Constants.chassisWidthMeters / 2), // Front Left     
      new Translation2d(Constants.chassisLengthMeters / 2, Constants.chassisWidthMeters / 2), // Front Right
      new Translation2d(Constants.chassisLengthMeters / 2, Constants.chassisWidthMeters / 2), // Back Left
      new Translation2d(Constants.chassisLengthMeters / 2, Constants.chassisWidthMeters / 2)  // Back Right
    );


    public static SwerveModule[] swerveModule = new SwerveModule[]{ //define the modules of the swerve chassis, need ids of the motors and the cancoders
      new SwerveModule(Constants.frontLeftDriveMotor_ID, Constants.frontLeftTurningMotor_ID, Constants.frontLeftCancoder_ID, Constants.frontLeftAngleOffset),
      new SwerveModule(Constants.frontRightDriveMotor_ID, Constants.frontRightTurningMotor_ID, Constants.frontRightCancoder_ID, Constants.frontRightAngleOffset),
      new SwerveModule(Constants.backLeftDriveMotor_ID, Constants.backLeftTurningMotor_ID, Constants.backLeftCancoder_ID, Constants.backLeftAngleOffset),
      new SwerveModule(Constants.backRightDriveMotor_ID, Constants.backRightTurningMotor_ID, Constants.backRightCancoder_ID, Constants.backRightAngleOffset)
    };  

    private SwerveSubsystem(){
      Navx.zeroYaw();//reset the Yaw of the Navx to 0 , Yaw is rotation around vertical axis
      odometry = new SwerveDriveOdometry(      //odemetry is used to track the position of the robot on the field
        kinematics,           //kinematics of the Swerve Chassis 
        Navx.getRotation2d(), //finds the degree the robot is facing, can go over 360
        getModulePosition(),  //finds the position of the modules(defined earlier)
        Constants.initialPose); //initial pose of the robot (define this constant later)
    }

    public void resetGyro(){
      Navx.reset(); //resets the Navx yaw to 0, not sure how this is different to zeroYaw
    }

    public void resetPose(Pose2d pose2d){
      odometry.resetPosition(Navx.getRotation2d(), getModulePosition() , pose2d);//resets the postiion of the robot, needs angle of the gyroscope
    }                                                                             // positoin of the modules and the current pose of the robot

    public Pose2d getPose(){
      return odometry.getPoseMeters(); //finds the current pose of the robot
    }

    
    public static void stopAll(){
      for(SwerveModule module : swerveModule){ // this is for loop, like for module in swerveModule
        module.stop(); //a function in the SwerveModule class, stops all the modules
      } 
    }

    public void SyncAll(){
      for(SwerveModule module : swerveModule){ //again for loop
        module.syncPosition(); //a function in the SwerveModule class, syncs all the modules with the cancoders
      } 
    }
    

    public void Drive(double Xspeed, double Yspeed, double rot, boolean fieldrelative){ // drive function needs the x axis speed, y axis speed, rotation and if fieldrelative is wanted(boolean).
      SwerveModuleState[] SwerveModuleStates = kinematics.toSwerveModuleStates(
        fieldrelative ? ChassisSpeeds.fromFieldRelativeSpeeds(Xspeed, Yspeed, rot, Navx.getRotation2d()) : new ChassisSpeeds(Xspeed, Yspeed, rot)); //the statement after ? will run if the fieldrelative is true, the statement after : will run if the fieldrelative is false. field relative is a boolean
      setSwerveModuleStates(SwerveModuleStates);   //set the states of the modules to the states defined above
    }

    public void setSwerveModuleStates(SwerveModuleState[] swerveModuleStates){
      for(int i = 0; i < 4; i ++){
        swerveModule[i].setDesiredState(swerveModuleStates[i]); //set all the modules's states to the states inputted(swerveModuleStates)
      }
    }

    public static class SwerveModule{  //create the class SwerveModule

      public final TalonFX DriveMotor;  // define the motors and cancoders & feedforward
      public final CANSparkMax TurningMotor;
      public final CANcoder CANCODER;
      public final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(
        Constants.Drive_Ks, Constants.Drive_Kv, Constants.Drive_Ka
      );

      
      
      public SwerveModule(int DriveMotorID, int TurningMotorID, int TurningCancoderID, int AngleOffset){ //this is the constructor of the class
        DriveMotor = new TalonFX(DriveMotorID); // give the motors and cancoders the ids defined in the constructor
        TurningMotor = new CANSparkMax(TurningMotorID, CANSparkLowLevel.MotorType.kBrushless);
        CANCODER = new CANcoder(TurningCancoderID);

        DriveMotor.getConfigurator().apply(new TalonFXConfiguration()); // this will reset the motor to factory default
        TurningMotor.restoreFactoryDefaults();// this will reset the motor to factory default
        CANCODER.getConfigurator().apply(new CANcoderConfiguration());// this will reset the cancoder to factory default

        CANCODER.getConfigurator().apply(new MagnetSensorConfigs());//not sure if this is how it works
        // supposed to set the cancoder to absolute position mode,  

        TurningMotor.setIdleMode(CANSparkMax.IdleMode.kBrake); //set the idle mode of the motor to brake

        var slot0Configs = new Slot0Configs(); //create a slot for pid
        slot0Configs.kP = Constants.Drive_KP;
        slot0Configs.kD = Constants.Drive_KD;
        slot0Configs.kI = Constants.Drive_KI;
        DriveMotor.getConfigurator().apply(slot0Configs, 0.050); //aplly the pid to the motor

        final double ANGLE_RATIO = 360 / Constants.CHASSIS_ANGLE_GEAR_RATIO;//do this to find how much the motor has to turn to turn one degrees angle

        TurningMotor.getPIDController().setP(Constants.Turn_KP);//set P,D and F for the turning motor, no I cause we dont use it
        TurningMotor.getPIDController().setD(Constants.Turn_KD);
        TurningMotor.getPIDController().setFF(Constants.Turn_KF);
        TurningMotor.getPIDController().setSmartMotionMaxAccel(Constants.Turn_maxAccel * ANGLE_RATIO, 0); // set the max acceleration and velocity of the turning motor
        TurningMotor.getPIDController().setSmartMotionMaxVelocity(Constants.Turn_maxVelo * ANGLE_RATIO, 0);
        TurningMotor.getPIDController().setSmartMotionAllowedClosedLoopError(Constants.Turn_maxAllowError * ANGLE_RATIO, 0);//set how much error is allowed, make sure less overshoot
        TurningMotor.getEncoder().setPositionConversionFactor(ANGLE_RATIO); //conversion factor
        TurningMotor.getEncoder().setVelocityConversionFactor(ANGLE_RATIO);

        syncPosition(); //sync the position of the encoder w/ the cancoder
      }

      public void syncPosition(){
        TurningMotor.getEncoder().setPosition(CANCODER.getAbsolutePosition().getValue());   //sync the position of the encoder w/ the cancoder   
      }

      public final SwerveModulePosition getPosition(){
        return new SwerveModulePosition( //needs the distance moved by the module and the angle of the module
          DriveMotor.getRotorPosition().getValue()/ getDriveGearRatio()/ 2048.0 * Math.PI *Constants.WheelDiameterMeters, getRotation());//gets the position / state of the module, 2048 is encoder counts per rotation, 
      }

      
      public final double getDriveGearRatio(){
        return switch (Constants.Drive_gearRatio){ // swtich the value in the gear ratio constant depending on which gear box using
          case L1 ->  8.14;
          case L2 -> 6.75;
          case L3 -> 6.12;  
          case L4 -> 5.14;
        };
      }

      public void setDesiredState(SwerveModuleState state){
        double velocity = state.speedMetersPerSecond * getDriveGearRatio()/10/Constants.WheelDiameterMeters * 2048.0/Math.PI; //set velocity
        double FeedForward = feedforward.calculate(state.speedMetersPerSecond); //set feedforward
        state = optimize(state, getRotation()); //optimize the state(define optimize later)
        DriveMotor.setControl(new VelocityVoltage(velocity).withFeedForward(FeedForward)); //set the velocity of the drive motor with feedforward
        TurningMotor.getPIDController().setReference(state.angle.getDegrees(), ControlType.kSmartMotion); //set the angle of the turning motor, using set reference like before in the our pid bot
      }
    
      public static SwerveModuleState optimize(SwerveModuleState desiredState, Rotation2d currentAngle){
        double targetAngle =  placeInAppropriate0To360Scope(currentAngle.getDegrees() , desiredState.angle.getDegrees()); //define placeInAppropriate0To360Scope later
        double targetSpeed = desiredState.speedMetersPerSecond; // set the target speed to the desired speed
        double delta = targetAngle - currentAngle.getDegrees(); // find the difference between the target angle and the current angle
        if(Math.abs(delta)>90){ //if the difference is greater than 90, reverse the speed and add 180 to the target angle this is to make sure the module turns the shortest way
          targetSpeed *= -1;
          if (delta > 90){
            targetAngle -= 180;
          }else{
            targetAngle += 180;
          }
        }

        return new SwerveModuleState(targetSpeed, Rotation2d.fromDegrees(targetAngle));
      }

      public static double placeInAppropriate0To360Scope(double currentAngle, double newAngle){ //finds the best angle, ill ustexplain this too hard to explain w/ words
        double lowerBound;
        double upperBound;
        double loweroffset = currentAngle % 360;
        if (loweroffset >= 0) {
            lowerBound = currentAngle - loweroffset;
            upperBound = currentAngle + (360 - loweroffset);
        } else {
            upperBound = currentAngle - loweroffset;
            lowerBound = currentAngle - (360 + loweroffset);
        }
        while (newAngle < lowerBound) {
            newAngle += 360;
        }
        while (newAngle > upperBound) {
            newAngle -= 360;
        }
        if (newAngle - currentAngle > 180) {
            newAngle -= 360;
        } else if (newAngle - currentAngle < -180) {
            newAngle += 360;
        }
        return newAngle;
      }
    

      public void stop (){
        final VelocityVoltage velocity = new VelocityVoltage(0);
        DriveMotor.setControl(velocity.withVelocity(0)); //set the velocity of the drive motor to 0
        TurningMotor.set(0);
      }

      public Rotation2d getRotation(){
        return Rotation2d.fromDegrees(TurningMotor.getEncoder().getPosition());//get the rotation using the encoder position basisicly how much the motor has turned
      }

    }    
    public enum GearRatio{ //enum is special data type that enables for a variable to be a set of predefined constant, this is used to define the gear ratio
      L1,
      L2,
      L3,
      L4
    }
  }  


