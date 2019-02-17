package org.usfirst.frc1591.DeepSpace2019.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1591.DeepSpace2019.Robot;

public class timedliftDrive extends Command {
    final double m_timeout = 2.0;
    
    public timedliftDrive() {
        requires(Robot.lift);
    }

    @Override
    protected void initialize() {
        setTimeout(m_timeout);
        Robot.lift.checkPneumatics();
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    @Override
    protected void end() {
        Robot.lift.stopWheel();
    }

    @Override
    protected void interrupted() {
        end();
    }
}