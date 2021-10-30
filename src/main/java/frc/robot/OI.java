package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.command.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import com.spikes2212.control.FeedForwardSettings;
import com.spikes2212.control.PIDSettings;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Shooter;

public class OI {
    private final Joystick left = new Joystick(0);
    private final Joystick right = new Joystick(1);
    private JoystickButton button;

    public double getRightY() {
        return right.getY() * 0.8;
    }

    public double getLeftY() {
        return left.getY() * 0.8;
    }

    public OI() {
//        Shooter shooting = new Shooter( new WPI_VictorSPX(6), new WPI_TalonSRX(5));
//        MoveGenericSubsystem shoot = new MoveGenericSubsystem(shooting, 0.6);
//        button = new JoystickButton(left, 5);
//        button.whileHeld(shoot);
        TankDrivetrain driveTrain = new TankDrivetrain(new SpeedControllerGroup((SpeedController) new WPI_TalonSRX(RobotMap.CAN.leftTalon), new VictorSP(RobotMap.PWM.leftVictor)),
                new SpeedControllerGroup((SpeedController) new WPI_TalonSRX(RobotMap.CAN.rightTalon), new VictorSP(RobotMap.PWM.rightVictor)));
        PIDSettings pidSettings = new PIDSettings(Robot.root.getNumber("kP"), Robot.root.getNumber("kI"), Robot.root.getNumber("kD"), Robot.root.getNumber("tolerance"), Robot.root.getNumber("waitTIme"));

        Encoder leftEncoder = new Encoder(0, 1);
        Encoder rightEncoder = new Encoder(2, 3);
        leftEncoder.setDistancePerPulse(10 / 4096.0);
        rightEncoder.setDistancePerPulse(10 / 4096.0);

        DriveTankWithPID drive = new DriveTankWithPID(driveTrain, pidSettings, pidSettings, 5, 5,
                leftEncoder::getDistance, rightEncoder::getDistance);

        button = new JoystickButton(left, 5);
        button.whenPressed(drive);
    }
}