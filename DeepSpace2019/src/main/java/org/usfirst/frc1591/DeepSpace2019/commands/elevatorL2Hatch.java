// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1591.DeepSpace2019.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1591.DeepSpace2019.Robot;
import org.usfirst.frc1591.DeepSpace2019.subsystems.elevator;

/**
 *
 */
public class elevatorL2Hatch extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    int posIndex = 3;
    
    boolean finished = false;
    int direction;

    boolean limitSwitchState = false;
    boolean distanceReached = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public elevatorL2Hatch() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.elevator);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        distanceReached = false;
        finished = false;

        System.out.println("Moving to L2Hatch Position");
        direction = Robot.elevator.getdirection(posIndex); // get direction and speed motor needs to move
        Robot.elevator.directionMove(direction);
        System.out.println("Motor moving in direction: " + direction);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // limitSwitchState = Robot.elevator.getSwitchesStates();

        switch (direction) {
            case 1:
                if (Robot.elevator.getCurrentPos() >= Robot.elevator.getElevatorPositions(posIndex)) {
                    distanceReached = true;
                }
                break;
            case -1:
                if (Robot.elevator.getCurrentPos() <= Robot.elevator.getElevatorPositions(posIndex)) {
                    distanceReached = true;
                }
                break;
            case 0:
                distanceReached = true;
        }
        
        if (/*limitSwitchState == true || */distanceReached == true) {
            finished = true;
            System.out.println("Motor stopping!");
        }
        return finished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
