
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.command.drivetrains.commands.DriveTank;
import com.spikes2212.command.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.RootNamespace;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.util.function.Supplier;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  public static final RootNamespace root = new RootNamespace("Drivetrain PID");

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    final Supplier<Double> kP = root.addConstantDouble("kP", 0);
    final Supplier<Double> kI = root.addConstantDouble("kI", 0);
    final Supplier<Double> kD = root.addConstantDouble("kD", 0);
    final Supplier<Double> tolerance = root.addConstantDouble("tolerance", 0);
    final Supplier<Double> waitTime = root.addConstantDouble("waitTime", 0);


    TankDrivetrain driveTrain = new TankDrivetrain(new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.leftTalon), new VictorSP(RobotMap.PWM.leftVictor)),
            new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.rightTalon), new VictorSP(RobotMap.PWM.rightVictor)));

//    OI oi = new OI();
//    DriveTank driveTank = new DriveTank(driveTrain, oi::getLeftY, oi::getRightY);
//    driveTrain.setDefaultCommand(driveTank);
    Encoder leftEncoder = new Encoder(0,1);
    Encoder rightEncoder = new Encoder(2,3);
    PIDSettings pidSettings = new PIDSettings(kP, kI, kD, tolerance, waitTime);
    DriveTankWithPID drive = new DriveTankWithPID(driveTrain, pidSettings, pidSettings, 5, 5,
            leftEncoder::getDistance, rightEncoder::getDistance);
    Drivetrain drivetrain = new Drivetrain(new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.leftTalon), new VictorSP(RobotMap.PWM.leftVictor)),
            new SpeedControllerGroup(new WPI_TalonSRX(RobotMap.CAN.rightTalon), new VictorSP(RobotMap.PWM.rightVictor)), leftEncoder, rightEncoder,
            10/4096.0, 10/4096.0);
  root.putData("drivetrain",drivetrain);
  }
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   *
   */
  @Override
  public void autonomousInit() {


    // schedule the autonomous command (example)
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}