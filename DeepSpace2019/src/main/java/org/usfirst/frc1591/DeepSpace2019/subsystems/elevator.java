// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc1591.DeepSpace2019.subsystems;

import java.util.ArrayList;

import org.usfirst.frc1591.DeepSpace2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class elevator extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DigitalInput elevatorTopSwitch;
    private DigitalInput elevatorBottomSwitch;
    private Spark elevatorMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    Encoder enc;
    ArrayList<Double> elevatorDistances;

    final int ELEVATOR_UP_SPEED = 1;
    final int ELEVATOR_DOWN_SPEED = -1;
    boolean bottomState; // state true if bottom limit switch is pushed in
    boolean topState; // state true if top limit switch is pushed in

    int currentPos = 0; // the current position of the elevator ASSUMES CLAW STARTS AT BOTTOM

    // constants for the distances between elevator stops, CURRENTLY PLACEHOLDERS
    double BOTTOM_DIFFERENCE = 20.00;
    double MIDDLE_DIFFERENCE = 15.00;
    double TOP_DIFFERENCE = 25.00;

    public elevator() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        elevatorTopSwitch = new DigitalInput(0);
        addChild("elevatorTopSwitch",elevatorTopSwitch);
        
        
        elevatorBottomSwitch = new DigitalInput(1);
        addChild("elevatorBottomSwitch",elevatorBottomSwitch);
        
        
        elevatorMotor = new Spark(1);
        addChild("elevatorMotor",elevatorMotor);
        elevatorMotor.setInverted(false);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        //construct encoder
        enc = new Encoder(0, 1, false, Encoder.EncodingType.k4X); //default encoder settings, need more info on the one we're using 

        //Add elevator switches to smart dashboard
        SmartDashboard.putData("elevatorTopSwitch", elevatorTopSwitch);
        SmartDashboard.putData("elevatorBottomSwitch", elevatorBottomSwitch);
        SmartDashboard.putData("elevatorMotor", elevatorMotor);

        // create array that holds the different stops on the elevator
        elevatorDistances.add(BOTTOM_DIFFERENCE);
        elevatorDistances.add(MIDDLE_DIFFERENCE);
        elevatorDistances.add(TOP_DIFFERENCE);
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // This code is run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // change the position of the elevator
    public void changePos(int pos){
        switch (pos) {
            case 0:
                if (currentPos < pos) {
                    elevatorMotor.set(1);
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    // get the current position of the elevator
    public int getPos(){
        return currentPos;
    }

    // check if limit switches are pushed in INVERTED (DONT KNOW HOW THEY WILL BE WIRED)
    public boolean getBottomSwitchState(){
        bottomState = !elevatorBottomSwitch.get();
        return bottomState;
    }

    public boolean getTopSwitchState(){
        topState = !elevatorTopSwitch.get();
        return topState;
    }

    // movement of the motor
    public void Up(){
        elevatorMotor.set(ELEVATOR_UP_SPEED);
    }

    public void Down(){
        elevatorMotor.set(ELEVATOR_DOWN_SPEED);
    }

    //called when an elevator command is interrupted or ended
    public void stop(){
        elevatorMotor.set(0);
    }
}

