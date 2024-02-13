

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.SwerveSubsystem;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;


public class SwerveDriveCommand extends Command {
    public final SwerveSubsystem swerveSubsystem = SwerveSubsystem.getInstance();
    public final XboxController controller;

    public SwerveDriveCommand(XboxController controller) {
        addRequirements(this.swerveSubsystem);

        this.controller = controller;
    }


    @Override
    public void initialize() {
    }


    @Override
    public void execute() {
        double Yval = MathUtil.applyDeadband(-controller.getLeftY(), 0.1); //set dedband and also define the Yval as the leftY controller
        double Xval = MathUtil.applyDeadband(-controller.getLeftX(), 0.1);
        double Turnval = MathUtil.applyDeadband(controller.getRightX(), 0.1);

        Xval *= Constants.MaxSpeedMetersPerSecond; // to increase the xVal by the max speed, so like the controller can only ouput -1 to 1, so multiply the max speed to get more speed
        Yval *= Constants.MaxSpeedMetersPerSecond;
        Turnval *= Constants.MaxAngularSpeedRadiansPerSecond;

        swerveSubsystem.Drive(Xval, Yval, Turnval, Constants.EnableFieldOrientedDrive);

    }


    @Override
    public void end(boolean interrupted) {
        SwerveSubsystem.stopAll(); //stop all define in subsystem
    }


    @Override
    public boolean isFinished() {
        return false;
    }
}
