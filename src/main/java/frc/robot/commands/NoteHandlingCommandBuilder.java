package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.NoteHandling.GeneralRoller;
import frc.robot.subsystems.NoteHandling.GeneralRoller.GeneralRollerStates;
import frc.robot.subsystems.NoteHandling.Shooter;
import frc.robot.subsystems.NoteHandling.Shooter.ShooterStates;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import frc.robot.subsystems.NoteHandling.Pivot;
import frc.robot.subsystems.NoteHandling.Pivot.PivotStates;
import frc.robot.subsystems.NoteHandling.Intake;
import frc.robot.subsystems.NoteHandling.Intake.IntakeStates;

// We are gonna be writing commands today, use this doc to progress through the training:
// https://docs.google.com/document/d/1iaDaoYRCIEgX1hY3d1hFmAgDCdyEqP5SY_TVuxs04kA/edit?tab=t.0

public class NoteHandlingCommandBuilder {

    public static Command generalRollerRunForward(GeneralRoller generalRoller) {
        return new InstantCommand(() -> generalRoller.requestState(GeneralRollerStates.StateForward), generalRoller);
    }
    
    public static Command generalRollerRunReverse(GeneralRoller generalRoller) {
        return new InstantCommand(() -> generalRoller.requestState(GeneralRollerStates.StateReverse), generalRoller);
    }

    public static Command generalRollerStop(GeneralRoller generalRoller) {
        return new InstantCommand(() -> generalRoller.requestState(GeneralRollerStates.StateOff), generalRoller);
    }    


    public static Command runFeeder(GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new ParallelCommandGroup(
                                new RunCommand(()->feeder.requestState(GeneralRollerStates.StateForwardFast), feeder),
                                new RunCommand(()->indexer.requestState(GeneralRollerStates.StateForwardFast), indexer)                 
                                );
        return command;
    }

    public static Command feederOff(GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new ParallelCommandGroup(
                        new InstantCommand(()->feeder.requestState(GeneralRollerStates.StateOff), feeder),
                        new InstantCommand(()->indexer.requestState(GeneralRollerStates.StateOff), indexer)                 
                        );
        return command;
    }

    public static Command shooterSpeaker(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer){
        Command command = new SequentialCommandGroup(
            new RunCommand(()->shooter.requestState(ShooterStates.StateSpeaker), shooter).until(()->shooter.getCurrentState()==ShooterStates.StateSpeaker),
            runFeeder(feeder, indexer)
            );

        return command;
    }

    public static Command shooterPasser(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer){
        Command command = new SequentialCommandGroup(
            new RunCommand(()->shooter.requestState(ShooterStates.StatePass), shooter).until(()->Math.abs(shooter.getVelocity()-50)<8),
            runFeeder(feeder, indexer)
            );
            

        return command;
    }


    public static Command DIAShoot(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer){
        Command command = new ParallelCommandGroup(
            new RunCommand(()->shooter.requestState(ShooterStates.StatePass), shooter), runFeeder(feeder, indexer)
        );
        return command;
    }

    public static Command shooterOff(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer){
        Command command = new ParallelCommandGroup(
            new InstantCommand(()->shooter.requestState(ShooterStates.StateOff), shooter),
            feederOff(feeder, indexer));
        return command;
    }

    public static Command shooterCoast(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer){
        Command command = new ParallelCommandGroup(
            new InstantCommand(()->shooter.requestState(ShooterStates.StateCoast), shooter));
            //feederOff(feeder, indexer));
        return command;
    }

    public static Command autoShooterSpeaker(Pivot pivot, Shooter shooter, GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new ParallelCommandGroup(
                        
                        new RunCommand(()->pivot.requestState(PivotStates.StateShooter), pivot), 
                        new RunCommand(()->shooter.requestState(ShooterStates.StateSpeaker), shooter),
                        new RunCommand(()->feeder.requestState(GeneralRollerStates.StateOff), feeder),
                        new RunCommand(()->indexer.requestState(GeneralRollerStates.StateOff), indexer)
                        ).until(()->pivot.getCurrentState() == PivotStates.StateShooter && shooter.getCurrentState() == ShooterStates.StateSpeaker)
                        .andThen(
                            new ParallelCommandGroup(
                                new RunCommand(()->feeder.requestState(GeneralRollerStates.StateForwardFast), feeder),
                                new RunCommand(()->indexer.requestState(GeneralRollerStates.StateForwardFast), indexer)
                            )
                
                        );
        return command.withInterruptBehavior(InterruptionBehavior.kCancelIncoming);
    }

    public static Command autoShooterStageSetpoint(Pivot pivot, Shooter shooter, GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new ParallelCommandGroup(
                        new RunCommand(()->pivot.requestState(PivotStates.StateStageSetpoint), pivot), 
                        new RunCommand(()->shooter.requestState(ShooterStates.StateSpeaker), shooter),
                        new RunCommand(()->feeder.requestState(GeneralRollerStates.StateOff), feeder),
                        new RunCommand(()->indexer.requestState(GeneralRollerStates.StateOff), indexer)
                        ).until(()->pivot.getCurrentState() == PivotStates.StateStageSetpoint && shooter.getCurrentState() == ShooterStates.StateSpeaker)
                        .andThen(
                            new ParallelCommandGroup(
                                new RunCommand(()->feeder.requestState(GeneralRollerStates.StateForwardFast), feeder),
                                new RunCommand(()->indexer.requestState(GeneralRollerStates.StateForwardFast), indexer)
                            )
                        );
        return command.withInterruptBehavior(InterruptionBehavior.kCancelIncoming);
    }
 
    public static Command autoShooterPodiumSetpoint(Pivot pivot, Shooter shooter, GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new ParallelCommandGroup(
                        new RunCommand(()->pivot.requestState(PivotStates.StatePodiumSetpoint), pivot), 
                        new RunCommand(()->shooter.requestState(ShooterStates.StateSpeaker), shooter),
                        new RunCommand(()->feeder.requestState(GeneralRollerStates.StateOff), feeder),
                        new RunCommand(()->indexer.requestState(GeneralRollerStates.StateOff), indexer)
                        ).until(()->pivot.getCurrentState() == PivotStates.StatePodiumSetpoint && shooter.getCurrentState() == ShooterStates.StateSpeaker)
                        .andThen(
                            new ParallelCommandGroup(
                                new RunCommand(()->feeder.requestState(GeneralRollerStates.StateForwardFast), feeder),
                                new RunCommand(()->indexer.requestState(GeneralRollerStates.StateForwardFast), indexer)
                            )
                        );
        return command.withInterruptBehavior(InterruptionBehavior.kCancelIncoming);
    }

    public static Command autoShooterOff(Pivot pivot, Shooter shooter, GeneralRoller feeder, GeneralRoller indexer, Intake intake) {
        Command command = new ParallelCommandGroup(
                        new InstantCommand(()->pivot.requestState(PivotStates.StateDown), pivot),
                        new InstantCommand(()->intake.requestState(IntakeStates.StateOff), intake),
                        shooterOff(shooter, feeder, indexer)
                        );
        return command;
    }

    public static Command autoShooterOffish(Pivot pivot, Shooter shooter, GeneralRoller feeder, GeneralRoller indexer, Intake intake) {
        Command command = new ParallelCommandGroup(
                        new InstantCommand(()->pivot.requestState(PivotStates.StateAutoIntake), pivot),
                        new InstantCommand(()->intake.requestState(IntakeStates.StateOff), intake),
                        shooterCoast(shooter, feeder, indexer)
                        );
        return command;
    }

    public static Command shoot(Shooter shooter, GeneralRoller feeder, GeneralRoller indexer) {
        Command command = new SequentialCommandGroup(
                        new RunCommand(()->shooter.requestState(ShooterStates.StateSpeaker), shooter).until(()->shooter.getCurrentState() == ShooterStates.StateSpeaker),
                        runFeeder(feeder, indexer)
                        );

        return command;
    }
}