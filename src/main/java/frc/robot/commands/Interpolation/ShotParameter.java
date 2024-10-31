package frc.robot.commands.Interpolation;

import edu.wpi.first.math.MathUtil;

public class ShotParameter {
    public final double shooterSpeedRotationsPerSecond;
    public final double pivotAngleRotations;

    public ShotParameter(double shooterSpeedRps, double pivotAngleRotations) {
        this.shooterSpeedRotationsPerSecond = shooterSpeedRps;
        this.pivotAngleRotations = pivotAngleRotations;
    }

    public ShotParameter interpolate(ShotParameter end, double t) {
    return new ShotParameter(
        MathUtil.interpolate(shooterSpeedRotationsPerSecond, end.shooterSpeedRotationsPerSecond, 1-t),
        MathUtil.interpolate(pivotAngleRotations, end.pivotAngleRotations, 1-t));
  }
}