package frc.robot.Commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.SubSystem.ShootSubSystem;

public class ShooterCommand extends Command {
    private final ShootSubSystem shootSubSystem;
    private final BooleanSupplier shooterInverted;
    private final BooleanSupplier shooterEnabled;
    public ShooterCommand(ShootSubSystem shootSubSystem, BooleanSupplier shooterInvereted, BooleanSupplier shooterEnabled){
        this.shooterInverted = shooterInvereted;
        this.shooterEnabled = shooterEnabled;
        this.shootSubSystem = shootSubSystem;
        addRequirements(shootSubSystem);
    }

    @Override
    public void execute(){
        if (shooterEnabled.getAsBoolean()){
            shootSubSystem.enableShoot();
        }else if (shooterInverted.getAsBoolean()){
            shootSubSystem.reverseShoot();
        }
    }
}
