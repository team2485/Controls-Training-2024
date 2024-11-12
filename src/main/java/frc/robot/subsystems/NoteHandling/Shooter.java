package frc.robot.subsystems.NoteHandling;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
// Imports go here
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.ShooterConstants.*;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.*;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.*;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;
import com.ctre.phoenix6.controls.*;

// FOLLOW ALONG THIS DOCUMENTATION: https://docs.google.com/document/d/143tNsvYQFAErQTJDxO9d1rwM7pv80vpLfLK-WiIEOiw/edit?tab=t.0

public class Shooter extends SubsystemBase {

    public enum ShooterStates{
        // MAKE STATES
        // some considerations: off state, states for shooter at each type of scoring location, and a transition state between states

        // ||||||||||||||||||||||||||||||||

        StateOff,
        StateAmp,
        StateSpeaker,
        StateTransition
    }

    public static ShooterStates m_shooterRequestedState;
    public static ShooterStates m_shooterCurrentState;
 
    // CREATE TALON MOTORS HERE
    // the shooter has two talon motors on it, have fun

    // ||||||||||||||||||||||||||||||||

    private final TalonFX TalonL = new TalonFX(kShooterLeftPort, "Mast");
    private final TalonFX TalonR = new TalonFX(kShooterRightPort, "Mast");

    private double desiredVelocity = 0;
    private double desiredVoltage = 0;

    // you might notice a new type right below here called a "DoubleSupplier," don't worry about it, you won't need to use distanceFromSpeaker for this
    // incase you were wonder though, it is a lambda, cause of course it is
    public Shooter(DoubleSupplier distanceFromSpeaker) {

        // CREATE THE CONFIGURATIONS FOR THE TALONS HERE
        // talon configs are set up differently than sparks, please use the doc if you want to spare your sanity
        var talonFXConfigs = new TalonFXConfiguration();
        
        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kS = kSShooter;
        slot0Configs.kV = kVShooter;
        slot0Configs.kA = kAShooter;
        slot0Configs.kP = kPShooter;
        slot0Configs.kI = kIShooter;
        slot0Configs.kD = kDShooter;

        var motionMagicConfigs = talonFXConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicCruiseVelocity = kShooterCruiseVelocity;
        motionMagicConfigs.MotionMagicAcceleration = kShooterAcceleration;
        motionMagicConfigs.MotionMagicJerk = kShooterJerk;
        
        TalonL.getConfigurator().apply(talonFXConfigs);
        TalonR.getConfigurator().apply(talonFXConfigs);

        // ||||||||||||||||||||||||||||||||

        // give some default state to these guys
        // m_shooterCurrentState;
        // m_shooterRequestedState;

        m_shooterCurrentState = ShooterStates.StateOff;
        m_shooterRequestedState = m_shooterCurrentState;

        
    }
        
    @Override
    public void periodic() {

        // SWITCH/IF STATEMENT GOES HERE

        switch (m_shooterCurrentState) {
          case StateOff:
            desiredVelocity = 0;
        }

        // ||||||||||||||||||||||||||||||||
     
        runControlLoop();
    
        // ERROR CHECKING GOES HERE

        // ||||||||||||||||||||||||||||||||

    }

      public void runControlLoop() {
        // SHOOTER SHENANIGANS GO HERE UNLESS YOU ARE TOO COOL FOR THAT

        // ||||||||||||||||||||||||||||||||
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
     
      public ShooterStates getCurrentState() {
        // CHANGE DIS PLZ
        return null;
      }

        // ||||||||||||||||||||||||||||||||

    }

    