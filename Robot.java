// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;


import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType; - NEVER USED
//import edu.wpi.first.wpilibj.motorcontrol.CANSparkMax; - we believe that we do not need CAN as we are using PWM 
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
//import edu.wpi.first.wpilibj.RobotDrive;
//import com.revRobotics.*;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;


 
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default"; 
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  


  //the ports and locations
  DifferentialDrive myRobot;
  PWMSparkMax leftBackMotor = new PWMSparkMax(9);
  PWMSparkMax leftFrontMotor = new PWMSparkMax(8);
  PWMSparkMax rightBackMotor = new PWMSparkMax(6);
  PWMSparkMax rightFrontMotor = new PWMSparkMax(7);

  //making each two motors a motor controller group (left and right)
  MotorControllerGroup rightGroup = new MotorControllerGroup(rightFrontMotor, rightBackMotor);
  MotorControllerGroup leftGroup = new MotorControllerGroup(leftFrontMotor, leftBackMotor);

  /** THIS IS INTAKE MOTORS
  PWMSparkMax leftIntake = new PWMSparkMax(0);
  PWMSparkMax middleIntake = new PWMSparkMax(0);
  PWMSparkMax rightIntake = new PWMSparkMax(0);

  MotorControllerGroup intakeGroup = new MotorControllerGroup(leftIntake, middleIntake, rightIntake);
  **/

  // Driver Station / controller mapping
  int joyPort1=0; //driver xbox controller
  //int joyPort2=0; //manipulator xbox controller
 
  int leftMotorChannel=1;
  int rightMotorChannel=2;

 
  //Driver Controls
  int lTriggerID = 2;
  int rTriggerID = 3;
  int intakeButt = 4;//Y button, for intake motor starting
  int stopButt = 3;//X button, for stopping intake motor
  int leftStickID = 1; //left and right sticks are joysticks (axis)
  int rightStickID = 5;

  XboxController driveController = new XboxController(joyPort1);

  double rightAxis;
  double leftAxis;
  double timeStart;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
 
  // private double limitAxis(double axis) { causing issue with motors
  //     if (axis > 1.0) {
  //        axis *= .5;
  //     } 
  //     else if (axis < -1.0) {
  //       axis *= 0.5;
  //     }
  //     return axis;
  //   }
    
  @Override
  //taken from example code
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
   
 
   
    //leftMotor = new PWMSparkMax(leftMotorChannel);//motors same as below
    //rightMotor =new PWMSparkMax(rightMotorChannel); //motors. one on each side for steering/go forward
    leftFrontMotor.setInverted(true);
    rightFrontMotor.setInverted(false);
    leftBackMotor.setInverted(true);
    rightBackMotor.setInverted(false);
    //rightGroup.setInverted(false); 

    myRobot = new DifferentialDrive(leftFrontMotor, rightFrontMotor);

    
  }

 //FOR THE NEXT FOUR LINES: we don't use PWMSparkMax anymore 
    //leftFrontPWMSparkMax = new PWMSparkMax(0);
    //rightFrontPWMSparkMax = new PWMSparkMax(1);
    //leftBackPWMSparkMax = new PWMSparkMax(2);
    //rightBackPWMSparkMax = new PWMSparkMax(3);
 
 
 
  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }
 
  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */




  @Override
  public void autonomousInit() {
    
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
   
    //Timer timer = new Timer();
    
    timeStart = Timer.getFPGATimestamp();
    

  }

 
  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        /**
         * double currentTime = timer.get() * Math.pow(10,-6); if (currentTime < 5)
         * { //if time is less than 5 seconds leftDrive.set(-0.5); //drive left motors
         * backwards rightDrive.set(-0.5); //drive right motors backwards } else if
         * (currentTime >= 10 ) { //time after 10 secs. leftDrive.set(0);
         * rightDrive.set(0); }
         **/
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }


    double currentTime = Timer.getFPGATimestamp() - timeStart;
    
    //double currentTime = Timer.getMsClock() - timeStart;

    // //5 SECS ARE PLACEHOLDERS
    if (currentTime < 2.0) { //if time is less than 2 seconds 
      myRobot.tankDrive(0.25, 0.25);
      System.out.println(currentTime);
       
      //myRobot.arcadeDrive(0.25, 0);
      //leftGroup.set(0.25);
      //rightGroup.set(0.08);
      System.out.println("I should be driving");
  
    }	
    else
      myRobot.tankDrive(0.0, 0.0); //replace with command to stop tank drive

  }
 
 
  @Override
  public void teleopInit() {
    /*
    if (autonomousCommand !=null){
      autonomousCommand.cancel();
    }
    */  
  }


  @Override
  public void teleopPeriodic() {
    
    rightAxis = -driveController.getRawAxis(5);
    leftAxis = -driveController.getRawAxis(1);

    //leftAxis = limitAxis(leftAxis);
    //rightAxis = limitAxis(rightAxis);
    
    myRobot.tankDrive( leftAxis, rightAxis); //THE 0.75 IS MEANT TO SLOW DOWN THE SPEED- THIS NEEDS TO BE TESTED!!!!!
//myRobot.arcadeDrive(leftAxis * 0.75, rightAxis);
    //add button ]$3
    /*
    if (driveController.getRawButtonPressed(0)) {
      //intake motors go now
    }  
    if (driveController.getRawButtonReleased(0)) {
      //intake motors stop now
    }
    
    */
  }

  // public void //not sure what was meant to be here

    /*
    if(leftAxis > 0)
    {
        leftMotor.set(1.0);
        runMotor(leftMotor);
    }

    if(rightAxis > 0) {
        rightMotor.set(1.0);
        runMotor(rightMotor);
      }  
    }
    */

  
//begin motor code here
  

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}
 
  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}
 
  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}
 
  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
 

