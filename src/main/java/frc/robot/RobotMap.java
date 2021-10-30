package frc.robot;

public class RobotMap {

    public interface PWM {
        int rightVictor = 1;
        int leftVictor = 0;

    }

    public interface CAN {
        int rightTalon = 1;
        int leftTalon = 2;
        int ShooterMotor = 4;
        int ShootingTalon = 5;
        int ShootingVictor = 6;
    }


}