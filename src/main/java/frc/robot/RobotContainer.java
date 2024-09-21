// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.WarlordsLib.WL_CommandXboxController;
//import frc.robot.commands.AutoCommandBuilder;
//import frc.robot.commands.ClimbCommandBuilder;
//import frc.robot.commands.DriveCommandBuilder;
//import frc.robot.commands.DriveWithController;
import frc.robot.commands.NoteHandlingCommandBuilder;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.NoteHandling.GeneralRoller;
//import frc.robot.subsystems.Climb.Climber;
//import frc.robot.subsystems.NoteHandling.GeneralRoller;
import frc.robot.subsystems.NoteHandling.Intake;
//import frc.robot.subsystems.NoteHandling.Pivot;
//import frc.robot.subsystems.NoteHandling.Shooter;
//import frc.robot.subsystems.NoteHandling.Pivot.PivotStates;
//import frc.robot.subsystems.Vision.PoseEstimation;
//import frc.robot.subsystems.drive.Drivetrain;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import static frc.robot.Constants.OIConstants.*;
import static frc.robot.Constants.Swerve.wheelCircumference;

import javax.swing.text.Style;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import static frc.robot.Constants.GeneralRollerConstants.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final WL_CommandXboxController m_driver = new WL_CommandXboxController(kDriverPort);
  private final WL_CommandXboxController m_operator = new WL_CommandXboxController(kOperatorPort);

  //private final Drivetrain m_drivetrain = new Drivetrain();
  //PoseEstimation m_poseEstimation = new PoseEstimation(m_drivetrain::getYawMod, m_drivetrain::getModulePositions, m_drivetrain::getChassisSpeeds, m_driver, m_operator, m_drivetrain);
  //private final Intake m_intake = new Intake();
 // private final GeneralRoller m_indexer = new GeneralRoller(kIndexerPort, true);
 // private final GeneralRoller m_feeder = new GeneralRoller(kFeederPort, true);
 // private final Shooter m_shooter = new Shooter(m_poseEstimation::getDistanceToSpeaker);

  //private final GeneralRoller m_feeder = new GeneralRoller(kFeederPort, false);
//  private final Pivot m_pivot = new Pivot(m_poseEstimation::getPivotAngleCalculated);
 // private final Climber m_climber = new Climber();

 // public final AutoCommandBuilder autoBuilder = new AutoCommandBuilder();

 // SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();

 // DriveCommandBuilder m_driveBuilder = new DriveCommandBuilder(m_poseEstimation, m_drivetrain);
  private final GeneralRoller roller = new GeneralRoller(0, false); //Todo: replace port with an actual damn number, just give it the indexer or sm.
  GenericEntry constantsTest;


  // Replace with CommandPS4Controller or CommandJoystick if needed

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    constantsTest = Shuffleboard.getTab("Swerve").add("GoalXPos", 0).getEntry();
    // Configure the trigger bindings
    configureBindings();
    //m_poseEstimation.addDashboardWidgets(Shuffleboard.getTab("Swerve"));

   // NamedCommands.registerCommand("Fire", AutoCommandBuilder.Fire(m_drivetrain, m_poseEstimation, m_intake, m_shooter, m_pivot, m_feeder, m_indexer));
   // NamedCommands.registerCommand("FireShooterOff", AutoCommandBuilder.FireShooterOff(m_drivetrain, m_poseEstimation, m_intake, m_shooter, m_pivot, m_feeder, m_indexer));
   // NamedCommands.registerCommand("KickOutAuto", NoteHandlingCommandBuilder.kickOutAuto(m_intake, m_indexer, m_feeder));
    // NamedCommands.registerCommand("Intake", NoteHandlingCommandBuilder.intakeAuto(m_intake, m_indexer, m_feeder));
  //  NamedCommands.registerCommand("ScoreInAmp", NoteHandlingCommandBuilder.autoAmp(m_drivetrain, m_pivot, m_shooter, m_feeder, m_indexer, m_poseEstimation));
   // NamedCommands.registerCommand("SetupGyro", NoteHandlingCommandBuilder.autoSetGyro(m_drivetrain, m_poseEstimation));

   // m_autoChooser = AutoBuilder.buildAutoChooser();
//
    // m_autoChooser.setDefaultOption("TestAuto", 
    // AutoCommandBuilder.choreoTestAuto(m_drivetrain, m_poseEstimation, m_intake, m_shooter, m_pivot, m_feeder, m_indexer));

    // m_autoChooser.addOption("TestAutoFire", 
    // AutoCommandBuilder.runNoteScoringChoreoAuto("TestPath1", m_drivetrain, m_poseEstimation, m_intake, m_shooter, m_pivot, m_feeder, m_indexer));   

   // SmartDashboard.putData("Auto Chooser", m_autoChooser);   
   NamedCommands.registerCommand("RollerForward", NoteHandlingCommandBuilder.generalRollerRunForward(roller));
  }


  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // m_drivetrain.setDefaultCommand(
    //   new DriveWithController(
    //       m_driver::getLeftY,
    //       m_driver::getLeftX,
    //       m_driver::getRightX,
    //       () -> true,
    //       () -> false,
    //       m_poseEstimation::getAngleToSpeakerCalculated,
    //       () -> m_driver.y().getAsBoolean(),
    //       m_poseEstimation::getAngleToAmp,
    //       () -> m_operator.x().getAsBoolean(),
    //       m_poseEstimation::getAngleToAmp,
    //       () -> m_driver.rightBumper().getAsBoolean(),
    //       m_poseEstimation::getNoteDetected,
    //       m_poseEstimation::getNoteAngle,
    //       m_poseEstimation::getNoteSetpoint,
    //       m_drivetrain, m_poseEstimation));

    m_driver.rightTrigger().onTrue(

      NoteHandlingCommandBuilder.generalRollerRunForward(roller)

    );
    //m_poseEstimation.setDefaultCommand(NoteHandlingCommandBuilder.noteHaptics(m_driver, m_poseEstimation));

    // m_driver.x().onTrue(new InstantCommand(m_drivetrain::zeroGyro)
    //            .alongWith(new InstantCommand(m_drivetrain::resetToAbsolute)));

    // m_driver.rightTrigger().onTrue(NoteHandlingCommandBuilder.intake(m_intake, m_indexer, m_feeder, m_driver, m_operator))
    //                        .onFalse(NoteHandlingCommandBuilder.intakeOff(m_intake, m_indexer, m_feeder, m_pivot, m_driver));

    // m_driver.leftBumper().onTrue(NoteHandlingCommandBuilder.outtake(m_intake, m_indexer, m_feeder, m_pivot))
    //                      .onFalse(NoteHandlingCommandBuilder.intakeOff(m_intake, m_indexer, m_feeder, m_pivot, m_driver));

    // m_driver.leftTrigger().onTrue(NoteHandlingCommandBuilder.paradeIntake(m_intake, m_indexer, m_feeder, m_pivot))
    //                      .onFalse(NoteHandlingCommandBuilder.intakeOff(m_intake, m_indexer, m_feeder, m_pivot, m_driver));    
    // // m_driver.upperPOV().onTrue(ClimbCommandBuilder.enableClimb(m_climber));

    // m_operator.leftPOV().onTrue(NoteHandlingCommandBuilder.DIAShoot(m_shooter, m_feeder, m_indexer))
    //                    .onFalse(NoteHandlingCommandBuilder.shooterOff(m_shooter, m_feeder, m_indexer));

    // m_operator.leftBumper().onTrue(ClimbCommandBuilder.upPosition(m_climber));
    // m_operator.leftTrigger().onTrue(ClimbCommandBuilder.climb(m_climber));

    // // m_operator.rightBumper().onTrue(NoteHandlingCommandBuilder.runFeeder(m_feeder))
    // //                         .onFalse(NoteHandlingCommandBuilder.feederOff(m_feeder));
    // // m_operator.rightTrigger().whileTrue(NoteHandlingCommandBuilder.shooterSpeaker(m_shooter))
    // //                          .whileFalse(NoteHandlingCommandBuilder.shooterCoast(m_shooter));
    // // m_operator.rightTrigger().whileTrue(NoteHandlingCommandBuilder.shoot(m_shooter, m_feeder, m_indexer))
    // //                         .whileFalse(NoteHandlingCommandBuilder.shooterOff(m_shooter, m_feeder, m_indexer));

    // m_operator.rightTrigger().whileTrue(NoteHandlingCommandBuilder.autoShooterSpeaker(m_pivot, m_shooter, m_feeder, m_indexer))
    //                          .whileFalse(NoteHandlingCommandBuilder.autoShooterOff(m_pivot, m_shooter, m_feeder, m_indexer, m_intake));

    // m_operator.y().whileTrue(NoteHandlingCommandBuilder.shooterSpeaker(m_shooter, m_feeder, m_indexer))
    //               .whileFalse(NoteHandlingCommandBuilder.shooterOff(m_shooter, m_feeder, m_indexer));

    // m_operator.x().whileTrue(NoteHandlingCommandBuilder.shooterPasser(m_shooter, m_feeder, m_indexer))
    //           .whileFalse(NoteHandlingCommandBuilder.shooterOff(m_shooter, m_feeder, m_indexer));
    // m_operator.b().whileTrue(NoteHandlingCommandBuilder.shootTrap(m_shooter, m_feeder, m_indexer))
    //                .whileFalse(NoteHandlingCommandBuilder.shooterOff(m_shooter, m_feeder, m_indexer));


    // m_operator.upperPOV().onTrue(NoteHandlingCommandBuilder.pivotToAmp(m_pivot));

    // m_operator.lowerPOV().onTrue(NoteHandlingCommandBuilder.pivotDown(m_pivot));

    // m_operator.b().onTrue(NoteHandlingCommandBuilder.autoAmp(m_drivetrain, m_pivot, m_shooter, m_feeder, m_indexer, m_poseEstimation))
    //                         .onFalse(NoteHandlingCommandBuilder.autoShooterOff(m_pivot, m_shooter, m_feeder, m_indexer, m_intake));

    // m_operator.a().whileTrue(NoteHandlingCommandBuilder.autoShooterPodiumSetpoint(m_pivot, m_shooter, m_feeder, m_indexer))
    //                          .whileFalse(NoteHandlingCommandBuilder.autoShooterOff(m_pivot, m_shooter, m_feeder, m_indexer, m_intake));
    
    // m_operator.rightPOV().whileTrue(NoteHandlingCommandBuilder.autoShooterStageSetpoint(m_pivot, m_shooter, m_feeder, m_indexer))
    //                           .whileFalse(NoteHandlingCommandBuilder.autoShooterOff(m_pivot, m_shooter, m_feeder, m_indexer, m_intake));
    //m_operator.rightBumper().onTrue();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

   public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return new InstantCommand(()-> System.out.print("autochooser disabled"));
  }
}
