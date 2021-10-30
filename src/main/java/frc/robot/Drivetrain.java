package frc.robot;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

public class Drivetrain extends TankDrivetrain {
private Encoder leftEncoder;
private Encoder rightEncoder;
private double leftDistancePerPulse;
private double rightDistancePerPulse;

    public Drivetrain(SpeedController leftSP, SpeedController rightSP,
                      Encoder leftEncoder, Encoder rightEncoder,
                      double rightDistancePerPulse, double leftDistancePerPulse) {
        super(leftSP, rightSP);
        this.leftEncoder=leftEncoder;
        this.rightEncoder=rightEncoder;
        this.leftDistancePerPulse=leftDistancePerPulse;
        this.rightDistancePerPulse=rightDistancePerPulse;
        leftEncoder.setDistancePerPulse(10/4096.0);
        rightEncoder.setDistancePerPulse(10/4096.0);
    }

    public double getLeftEncoder() {
        return leftEncoder.getDistance();
    }

    public double getRightEncoder() {
        return rightEncoder.getDistance();
    }

    public void resetEncoder(){
        rightEncoder.reset();
        leftEncoder.reset();
    }
}
