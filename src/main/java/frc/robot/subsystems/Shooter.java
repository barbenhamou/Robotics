package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import edu.wpi.first.wpilibj.SpeedController;

public class Shooter extends GenericSubsystem {
    private WPI_VictorSPX Victor;
    private WPI_TalonSRX Talon;


    public Shooter(WPI_VictorSPX Victor, WPI_TalonSRX Talon ) {
        super(0.0,0.6);
        this.Victor = Victor;
        this.Talon = Talon;
        Victor.follow(Talon);
        Talon.setInverted(true);
    }

    @Override
    public void apply(double speed) {
        Victor.set(speed);
        Talon.set(speed);

    }

    @Override
    public boolean canMove(double speed) {
        return true;
    }

    @Override
    public void stop() {
        Victor.stopMotor();
        Talon.stopMotor();

    }

}