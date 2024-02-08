// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SwerveCommand;
// import frc.robot.commands.ArmCommand;
// import frc.robot.subsystems.ArmSubsystem;
// import frc.robot.subsystems.Intake_ShooterSubsystem;
// import frc.robot.subsystems.MusicSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import pabeles.concurrency.ConcurrencyOps.NewInstance;



/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
    public final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();
    // public final MusicSubsystem musicSubsystem = new MusicSubsystem();
    // public final ArmSubsystem armSubsystem = new ArmSubsystem();
    // public final Intake_ShooterSubsystem intake_ShooterSubsystem = new Intake_ShooterSubsystem();
    public final XboxController driverController = new XboxController(0);
    public final XboxController operatorController = new XboxController(1);

    public RobotContainer()
    {
        swerveSubsystem.setDefaultCommand(
            new SwerveCommand(
            swerveSubsystem, 
            ()->driverController.getRawAxis(XboxController.Axis.kLeftY.value) , 
            ()->driverController.getRawAxis(XboxController.Axis.kLeftX.value), 
            ()->driverController.getRawAxis(XboxController.Axis.kRightX.value), 
            ()->driverController.getBButton()
            )
        );
        // armSubsystem.setDefaultCommand(
        //     new ArmCommand(
        //         armSubsystem,
        //         ()-> operatorController.getPOV()
        //     )
        // );
        configureBindings();


    }
    
    
    private void configureBindings()
    {
        new JoystickButton(driverController, XboxController.Button.kRightBumper.value)
        .onTrue( new InstantCommand(swerveSubsystem::zeroGyro));

        // new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value).whileTrue(
        //     new InstantCommand(intake_ShooterSubsystem::setIntakeSpeed));
        
        // new JoystickButton(operatorController, XboxController.Button.kRightBumper.value).whileTrue(
        //     new InstantCommand(intake_ShooterSubsystem::setShooterSpeed));

        // new JoystickButton(operatorController, XboxController.Button.kA.value).onTrue(
        //     new InstantCommand(musicSubsystem::playMusic));

        // new JoystickButton(operatorController, XboxController.Button.kB.value).onTrue(
        //     new InstantCommand(musicSubsystem::pauseMusic));

        // new JoystickButton(operatorController, XboxController.Button.kY.value).onTrue(
        //     new InstantCommand(musicSubsystem::stopMusic));

    }
    
    
    

}
