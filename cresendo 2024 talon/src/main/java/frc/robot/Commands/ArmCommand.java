package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.SubSystem.ArmSubSystem;

public class ArmCommand extends Command {
    private final ArmSubSystem armSubSystem;
    private final DoubleSupplier lowerArmUp;
    private final DoubleSupplier lowerArmDown;

    public ArmCommand(ArmSubSystem armSubSystem, DoubleSupplier lowerArmUp, DoubleSupplier lowerArmDown){
        this.armSubSystem = armSubSystem;
        this.lowerArmUp = lowerArmUp;
        this.lowerArmDown = lowerArmDown;
        addRequirements(armSubSystem);   
    }

    @Override
    public void execute(){
        armSubSystem.setLowerArm(lowerArmUp.getAsDouble() - lowerArmDown.getAsDouble());
    }
}
