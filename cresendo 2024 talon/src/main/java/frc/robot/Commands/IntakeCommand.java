package frc.robot.Commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.SubSystem.IntakeSubSystem;

public class IntakeCommand extends Command {
    private final IntakeSubSystem intakeSubsystem;
    private final BooleanSupplier intakeReversed;
    private final BooleanSupplier intakeEnabled;
    public IntakeCommand(
        IntakeSubSystem intakeSubSystem, BooleanSupplier intakeReversed, BooleanSupplier intakeEnabled
    ){
        this.intakeSubsystem = intakeSubSystem;
        this.intakeReversed = intakeReversed;
        this.intakeEnabled = intakeEnabled;
        addRequirements(intakeSubSystem);
    }

    @Override
    public void execute(){
        intakeSubsystem.enableIntake(intakeReversed.getAsBoolean(),intakeEnabled.getAsBoolean());

    }

}
