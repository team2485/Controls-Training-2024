package frc.robot.subsystems.NoteHandling;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// Imports go here
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Interpolation.InterpolatingTable;
import frc.robot.commands.Interpolation.ShotParameter;

import static frc.robot.Constants.ShooterConstants.*;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.*;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.*;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.controls.*;


public class Shooter extends SubsystemBase {

    public enum ShooterStates{
        // MAKE STATES
        // -i brainstorm states with people, and go over ones that might not be intuitive like StatePodium

        // ||||||||||||||||||||||||||||||||
    }

    public static ShooterStates m_shooterRequestedState;
    public static ShooterStates m_shooterCurrentState;
 
    // CREATE TALON MOTORS HERE
    // THE SHOOTER HAS TWO TALON MOTORS ON IT, ONE OF THE LEFT AND ONE ON THE RIGHT
    // -i need to talk about canbus

    // -i to explain motion magic or to not explain motion magic, that is the question
    // -i update, I'm probably going to have to explain motion magic, f
    //private final MotionMagicVelocityVoltage request = new MotionMagicVelocityVoltage(0).withSlot(0);


    private double desiredVelocity = 0;
    private double desiredVoltage = 0;

    // -i explain what a supplier is (it's a lambda, it's always a fucking lambda)
    //DoubleSupplier distanceFromSpeaker;

    public Shooter(DoubleSupplier distanceFromSpeaker) {

        //this.distanceFromSpeaker = distanceFromSpeaker;

        // CREATE THE CONFIGURATIONS FOR THE TALONS HERE
        // TALON CONFIGS ARE SET UP DIFFERENTLY THAN SPARKS, WHERE WE SPECIFY THEM TO A 'TalonFXConfiguration' CLASS INSTEAD OF THE MOTOR ITSELF
        // THEN AFTERWARDS WE APPLY THE 'TalonFXConfiguration' TO THE TALONS THEMSELVES
        var talonFXConfigs = new TalonFXConfiguration();
        
        // ||||||||||||||||||||||||||||||||

        m_shooterCurrentState = ShooterStates.StateDEFAULT;
        m_shooterRequestedState = ShooterStates.StateDEFAULT;

    }
        
    @Override
    public void periodic() {


        // SWITCH STATEMENT GOES HERE

        // ||||||||||||||||||||||||||||||||
     
        runControlLoop();
    
        // ERROR CHECKING GOES HERE

        // ||||||||||||||||||||||||||||||||

    }

      public void runControlLoop() {
        // -i voltage babyyyyyyyyyyyyyyyyyyy
      }
    
      // SO MANY METHODS TO MAKE (like 4), SO LITTLE TIME TO DO IT (literally 6 hours)

      public double getVelocity() {
        // CHANGE DIS PLZ
        return 0;
      }
    
      public double getError() {
        // CHANGE DIS PLZ
        return 0;
      }
     
      public void requestState(ShooterStates requestedState) {
        // CHANGE DIS PLZ
      }
     
      // example of a "getter" method
      public ShooterStates getCurrentState() {
        // CHANGE DIS PLZ
        return null;
      }

        // ||||||||||||||||||||||||||||||||

    }

    