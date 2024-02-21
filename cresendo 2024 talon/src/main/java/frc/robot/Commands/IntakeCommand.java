package frc.robot.Commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.SubSystem.IntakeSubSystem;
import frc.robot.SubSystem.ShootSubSystem;

public class IntakeCommand extends Command {
    private final IntakeSubSystem intakeSubsystem;
    private final ShootSubSystem shooterSubSystem;
    private final BooleanSupplier intakeReversed;
    private final BooleanSupplier intakeEnabled;
    public IntakeCommand(
        IntakeSubSystem intakeSubSystem, ShootSubSystem shooterSubSystem, BooleanSupplier intakeReversed, BooleanSupplier intakeEnabled
    ){
        this.intakeSubsystem = intakeSubSystem;
        this.shooterSubSystem = shooterSubSystem;
        this.intakeReversed = intakeReversed;
        this.intakeEnabled = intakeEnabled;
    }

    @Override
    public void execute(){
        if (intakeEnabled.getAsBoolean()){
            intakeSubsystem.enableIntake();
            shooterSubSystem.reverseShoot();
        }else if (intakeReversed.getAsBoolean()){
            intakeSubsystem.reverseIntake();
        }
    }

}
