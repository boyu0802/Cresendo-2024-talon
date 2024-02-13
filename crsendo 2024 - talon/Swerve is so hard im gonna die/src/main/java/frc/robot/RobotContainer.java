// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.SwerveSubsystem;


public class RobotContainer {
  public final static XboxController controller = new XboxController(0);
  public SwerveSubsystem swerveSubsystem = SwerveSubsystem.getInstance();  
 
  private void configureBindings() {
    new JoystickButton(controller, XboxController.Button.kLeftBumper.value).onTrue(new InstantCommand(swerveSubsystem::resetGyro)); //joystick button to rest the gyroscope, Navx
  }

  public RobotContainer() {
    SwerveDriveCommand swerveDriveCommand = new SwerveDriveCommand(controller); 

    swerveSubsystem.setDefaultCommand(swerveDriveCommand); 

    configureBindings();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
