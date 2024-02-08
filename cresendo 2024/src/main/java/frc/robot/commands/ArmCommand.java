// package frc.robot.commands;

// import java.util.function.DoubleSupplier;
// import java.util.function.IntSupplier;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.Subsystem;
// import frc.robot.AllConstants.Constants;
// import frc.robot.subsystems.ArmSubsystem;

// public class ArmCommand extends Command {
//     private final ArmSubsystem armSubsystem;
//     private final IntSupplier position;

//     public ArmCommand(ArmSubsystem armSubsystem, IntSupplier position) {
//         this.armSubsystem = armSubsystem;
//         this.position = position;
//         addRequirements(armSubsystem);
//     }

//     @Override
//     public void execute(){
//         switch (position.getAsInt()) {
//             case 180:
//                 armSubsystem.setArmPosition(Constants.armConstants.Arm_IntakePosition);
//                 break;
//             case 90:
//                 armSubsystem.setArmPosition(Constants.armConstants.Arm_AmpPosition);
//                 break;
//             case 0:
//                 armSubsystem.setArmPosition(Constants.armConstants.Arm_ShooterPosition);
//                 break;
//             default:
//                 armSubsystem.setArmPosition(Constants.armConstants.Arm_AmpPosition);
//                 break;
//         }
//     }
// }
