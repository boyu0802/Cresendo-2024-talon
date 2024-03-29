package frc.lib.AllConstants;

import com.ctre.phoenix6.signals.InvertedValue;

import frc.lib.convert.Convertions;

public class SwerveTypeConstants {
    public final double wheelDiameter;
    public final double wheelCircumference;
    public final double angleGearRatio;
    public final double driveGearRatio;
    public final double anglePIDF[]; // [P, I, D]

    public final InvertedValue angleMotorInverted;
    public final InvertedValue driveMotorInverted;
    public final InvertedValue canCoderInverted;

    public SwerveTypeConstants(
        double wheelDiameter, double angleGearRatio, double driveGearRatio, 
        double anglePIDF[], 
        InvertedValue angleMotorInverted, InvertedValue driveMotorInverted, InvertedValue canCoderInverted){
        this.wheelDiameter = wheelDiameter;
        this.wheelCircumference = wheelDiameter * Math.PI;
        this.angleGearRatio = angleGearRatio;
        this.driveGearRatio = driveGearRatio;
        this.anglePIDF = anglePIDF;
        this.angleMotorInverted = angleMotorInverted;
        this.driveMotorInverted = driveMotorInverted;
        this.canCoderInverted = canCoderInverted;

    }
    public static SwerveTypeConstants SDSMK4I_L1(){
        double wheelDiameter = Convertions.inchesToMeters(4.0);

        double driveGearRatio = SDSMK4I_L1_DRIVEGEAR;
        double angleGearRatio = SDSMK4I_L1_ANGLEGEAR;

        InvertedValue driveMotorInverted = InvertedValue.Clockwise_Positive;
//        InvertedValue angleMotorInverted = InvertedValue.CounterClockwise_Positive;
        InvertedValue angleMotorInverted = InvertedValue.Clockwise_Positive;
        InvertedValue canCoderInverted = InvertedValue.Clockwise_Positive;

        double anglePIDF[] = {24, 0.0, 0.1};
//        double anglePIDF[] = {100.0, 0.0, 0.2};
        return new SwerveTypeConstants(
            wheelDiameter, angleGearRatio,driveGearRatio,
            anglePIDF, 
            angleMotorInverted, driveMotorInverted, canCoderInverted);
    }

    public static final double SDSMK4I_L1_DRIVEGEAR = 8.14;
    public static final double SDSMK4I_L1_ANGLEGEAR = 150.0/7.0;
}
