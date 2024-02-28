package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.AllConstants.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.SubSystem.SwerveSubSystem;

public class SwerveCommands extends Command {
    private final SwerveSubSystem swerveSubSystem;
    private final DoubleSupplier translationSupplier;
    private final DoubleSupplier strafeSupplier;
    private final DoubleSupplier rotationSupplier;
    private final BooleanSupplier robotCentricSupplier;
    private final BooleanSupplier limelightEnabled;

    public SwerveCommands(
        SwerveSubSystem swerveSubSystem,
        DoubleSupplier translationSupplier,
        DoubleSupplier strafeSupplier,
        DoubleSupplier rotationSupplier,
        BooleanSupplier robotCentricSupplier,
        BooleanSupplier limelightEnabled
    ){
        this.swerveSubSystem = swerveSubSystem;
        addRequirements(swerveSubSystem);
        this.translationSupplier = translationSupplier;
        this.strafeSupplier = strafeSupplier;
        this.rotationSupplier = rotationSupplier;
        this.robotCentricSupplier = robotCentricSupplier;
        this.limelightEnabled = limelightEnabled;
    }

    @Override
    public void execute(){

        double rotationval = MathUtil.applyDeadband(rotationSupplier.getAsDouble(),Constants.JoystickDeadBand);    
        double translationval = MathUtil.applyDeadband(translationSupplier.getAsDouble(), Constants.JoystickDeadBand);
        double strafeval = MathUtil.applyDeadband(strafeSupplier.getAsDouble(), Constants.JoystickDeadBand);
        
        double visionAimNum = LimelightHelpers.getTX("Limelight");
        if (limelightEnabled.getAsBoolean()&&visionAimNum!=0.0){
            swerveSubSystem.drive(
                new Translation2d(translationval,strafeval).times(Constants.SwerveConstants.SWERVE_MAX_SPEED),
                visionAimNum*Constants.SwerveConstants.SWERVE_MAX_ANGULAR_VELOCITY,
                !robotCentricSupplier.getAsBoolean(),
                true
            );
        }else{    
            swerveSubSystem.drive(
                new Translation2d(translationval,strafeval).times(Constants.SwerveConstants.SWERVE_MAX_SPEED),
                rotationval * Constants.SwerveConstants.SWERVE_MAX_ANGULAR_VELOCITY,
                !robotCentricSupplier.getAsBoolean(),
                true
            );
        }
    }
}
