package frc.robot.Commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.lib.AllConstants.Constants.ArmConstants.TopArmPosition;
import frc.robot.SubSystem.ArmSubSystem;

public class ArmCommand extends Command {
    private final ArmSubSystem armSubSystem;
    private final IntSupplier povSupplier;
    private String lastPosition = "intake";
    // private final DoubleSupplier lowerArmUp;
    // private final DoubleSupplier lowerArmDown;
    // private final BooleanSupplier topArmUp;
    // private final BooleanSupplier topArmDown;

    // public ArmCommand(ArmSubSystem armSubSystem, DoubleSupplier lowerArmUp, DoubleSupplier lowerArmDown, BooleanSupplier topArmUp, BooleanSupplier topArmDown){
    //     this.armSubSystem = armSubSystem;
    //     this.topArmDown = topArmDown;
    //     this.topArmUp = topArmUp;
    //     this.lowerArmDown = lowerArmDown;
        // this.lowerArmUp = lowerArmUp;
    public ArmCommand(ArmSubSystem armSubSystem, IntSupplier povSupplier){    
        this.armSubSystem = armSubSystem;
        this.povSupplier = povSupplier;
        addRequirements(armSubSystem);   
    }

    @Override
    public void execute(){

    //     armSubSystem.setLowerArm(lowerArmUp.getAsDouble() - lowerArmDown.getAsDouble());
    //     armSubSystem.setTopArm( topArmUp.getAsBoolean(), topArmDown.getAsBoolean());

        switch (povSupplier.getAsInt()) {
            case 0:
                SmartDashboard.putString("lol", lastPosition);
                if(lastPosition == "AMP"){
                    armSubSystem.topToSpeakerPosition();
                    armSubSystem.lowerToSpeakerPosition();
                    lastPosition = "speaker";
                }else if(lastPosition == "intake"){
                    armSubSystem.lowerToSpeakerPosition();
                    Timer.delay(2);
                    armSubSystem.topToSpeakerPosition();
                    lastPosition = "speaker";
                }
                break;
            case 90:
                if(lastPosition == "speaker"){
                    armSubSystem.topToAmpPosition();
                    armSubSystem.lowerToAmpPosition();
                    lastPosition = "AMP";
                }else if(lastPosition == "intake"){
                    armSubSystem.topToIntakePosition();
                    armSubSystem.lowerToAmpPosition();
                    Timer.delay(2);
                    armSubSystem.topToAmpPosition();
                    lastPosition = "AMP";
                }
                break;
            case 180:
                    armSubSystem.topToIntakePosition();
                    Timer.delay(2);
                    armSubSystem.lowerToIntakePosition();
                    lastPosition = "intake";
                break;
            default:
                break;
        }
    }    
}
