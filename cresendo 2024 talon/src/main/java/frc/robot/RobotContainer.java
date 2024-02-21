// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Commands.ArmCommand;
import frc.robot.Commands.IntakeCommand;
import frc.robot.Commands.ShooterCommand;
import frc.robot.Commands.SwerveCommands;
import frc.robot.SubSystem.*;

public class RobotContainer {
  private final XboxController driveController = new XboxController(0);
  private final XboxController operatorController = new XboxController(1);
  private final SwerveSubSystem swerveSubSystem = new SwerveSubSystem();
  private final IntakeSubSystem intakeSubSystem = new IntakeSubSystem();
  private final ShootSubSystem shootSubSystem = new ShootSubSystem();
  private final ArmSubSystem armSubSystem = new ArmSubSystem();
  private final MusicSubsystem musicSubSystem = new MusicSubsystem();


  public RobotContainer() {

    swerveSubSystem.setDefaultCommand(new SwerveCommands(
      swerveSubSystem,
      () -> driveController.getRawAxis(XboxController.Axis.kLeftY.value),
      () -> driveController.getRawAxis(XboxController.Axis.kLeftX.value),
      () -> driveController.getRawAxis(XboxController.Axis.kRightX.value),
      driveController::getLeftBumper
      
      ));


    shootSubSystem.setDefaultCommand(new ShooterCommand(shootSubSystem, 
      operatorController::getLeftBumper, 
      operatorController::getAButton
    ));

    intakeSubSystem.setDefaultCommand(new IntakeCommand(intakeSubSystem, shootSubSystem,
      operatorController::getRightBumper, 
      operatorController::getXButton
    ));

    armSubSystem.setDefaultCommand(new ArmCommand(armSubSystem, operatorController::getLeftTriggerAxis, operatorController::getRightTriggerAxis));
      
    configureBindings();
  }


  private void configureBindings() {
    new JoystickButton(driveController, XboxController.Button.kRightBumper.value)
                    .onTrue(new InstantCommand(swerveSubSystem::zeroGyro));


    // new JoystickButton(driveController, XboxController.Button.kStart.value)
    //                 .onTrue(new InstantCommand(musicSubSystem::playMusic));
    // new JoystickButton(driveController, XboxController.Button.kBack.value)
    //                 .onTrue(new InstantCommand(musicSubSystem::pauseMusic));


//    Bind intake button

    new JoystickButton(operatorController, XboxController.Button.kY.value)
            .onTrue(new InstantCommand(intakeSubSystem::stopIntake));


//    Bind shoot button

    new JoystickButton(operatorController,XboxController.Button.kB.value)
            .onTrue(new InstantCommand(shootSubSystem::disableShoot));



    

  }


  // public Command getDisableCommand(){
        // return new InstantCommand(musicSubSystem::playMusic);
  // }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }


  public void robotInit() {
      
  }
  
}
