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
    ArrayList<Integer> elevatorPositions;

    final double ELEVATOR_UP_SPEED = 0.5;
    final double ELEVATOR_DOWN_SPEED = -0.5;
    boolean bottomState; // state true if bottom limit switch is pushed in
    boolean topState; // state true if top limit switch is pushed in

    double encoder;

    boolean state;

    // constants for the elevator stops, CURRENTLY PLACEHOLDERS
    int L1HATCH_POS = 250;
    int L1CARGO_POS = 500;
    int CARGO_SHIP_POS = 750;
    int L2HATCH_POS = 1000;

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
        
        // construct arraylist
        elevatorPositions = new ArrayList<Integer>();
        
        //construct encoder
        enc = new Encoder(2, 3, false, Encoder.EncodingType.k4X); //default encoder settings, need more info on the one we're using 
        SmartDashboard.putData("encoder", enc);

        //Add elevator switches to smart dashboard
        SmartDashboard.putData("elevatorTopSwitch", elevatorTopSwitch);
        SmartDashboard.putData("elevatorBottomSwitch", elevatorBottomSwitch);
        SmartDashboard.putData("elevatorMotor", elevatorMotor);

        // create array that holds the different stops on the elevator
        elevatorPositions.add(L1HATCH_POS);
        elevatorPositions.add(L1CARGO_POS);
        elevatorPositions.add(CARGO_SHIP_POS);
        elevatorPositions.add(L2HATCH_POS);
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
        if (elevatorMotor.get() > 0 && getTopSwitchState() == true) {
            stop();
            System.out.println("Top switch pressed. ABORT ABORT ABORT");
        }
        else if(elevatorMotor.get() < 0 && getBottomSwitchState() == true) {
            stop();
            System.out.println("Bottom switch pressed. ABORT ABORT ABORT");
        }
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // get the current position of the elevator
    public int getCurrentPos(){
        return enc.get();
    }

    // get any elevator position out of the elevatorPositions array
    public int getElevatorPositions(int index) {
        return elevatorPositions.get(index);
    }

    // get the direction needed to be travelled by the elevator. 1 is up, -1 id down, 0 is not move
    public int getdirection(int targetPosIndex) {
        System.out.println("Moving from " + getCurrentPos() + " to " + getElevatorPositions(targetPosIndex));
        int direction;
        if (getElevatorPositions(targetPosIndex) > getCurrentPos()) {
            direction = 1;
        }
        else if (getElevatorPositions(targetPosIndex) < getCurrentPos()) {
            direction = -1;
        }
        else {
            direction = 0;
        }
        return direction;
    }

    // check if limit switches are pushed in, true means pushed in INVERTED (DONT KNOW HOW THEY WILL BE WIRED)
    public boolean getBottomSwitchState(){
        return !elevatorBottomSwitch.get();
    }

    public boolean getTopSwitchState(){
        return !elevatorTopSwitch.get();
    }

    //returns true if either switch is pushed in
    public boolean getSwitchesStates() {
        if(getTopSwitchState() == true || getBottomSwitchState() == true) {
            state = true;
        }
        else {
            state = false;
        }
        return state;
    }

    // movement of the motor
    public void up(){
        System.out.println("Moving on up!");
        elevatorMotor.set(ELEVATOR_UP_SPEED);
    }

    public void down(){
        System.out.println("Moving down below!");
        elevatorMotor.set(ELEVATOR_DOWN_SPEED);
    }

    public void directionMove(int direction) {
        switch(direction) {
            case 1:
                up();
                break;
            case -1:
                down();
                break;
            case 0:
                stop();
                break;
        }
    }

    // reset encoder
    public void resetEncoder() {
        enc.reset();
    }

    //called when an elevator command is interrupted or ended
    public void stop(){
        elevatorMotor.set(0);
    }
}

