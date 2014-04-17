package com.ana.strategies.radar;

import robocode.AdvancedRobot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

public class OneOnOneStrategy extends RadarStrategy {
    public OneOnOneStrategy(AdvancedRobot robot) {
        super(robot);
    }

    @Override
    public void radarMovement() {

    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        double angleToEnemy = robot.getHeadingRadians() + e.getBearingRadians();

        // Subtract current radar heading to get the turn required to face the enemy, be sure it is normalized
        double radarTurn = Utils.normalRelativeAngle(angleToEnemy - robot.getRadarHeadingRadians());

        // Distance we want to scan from middle of enemy to either side
        // The 36.0 is how many units from the center of the enemy robot it scans.
        double extraTurn = Math.min( Math.atan( 36.0 / e.getDistance() ), Rules.RADAR_TURN_RATE_RADIANS );

        // Adjust the radar turn so it goes that much further in the direction it is going to turn
        // Basically if we were going to turn it left, turn it even more left, if right, turn more right.
        // This allows us to overshoot our enemy so that we get a good sweep that will not slip.
        radarTurn += (radarTurn < 0 ? -extraTurn : extraTurn);

        //Turn the radar
        robot.setTurnRadarRightRadians(radarTurn);
    }
}
