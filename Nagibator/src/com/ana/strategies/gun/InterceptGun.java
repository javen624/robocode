package com.ana.strategies.gun;

import com.ana.Enemy;
import com.ana.MathHelper;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 17.04.13
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */
public class InterceptGun extends GunStrategy {
    public InterceptGun(AdvancedRobot robot) {
        super(robot);
    }

    @Override
    public void shot() {

    }



    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        Intercept intercept = new Intercept();

        intercept.calculate
                (
                        robot.getX(),
                        robot.getY(),
                        robot.getX() + Math.sin(Math.toRadians(robot.getHeading() + e.getBearing())) * e.getDistance(),
                        robot.getY() + Math.cos(Math.toRadians(robot.getHeading() + e.getBearing())) * e.getDistance(),
                        e.getHeading(),
                        e.getVelocity(),
                        2,
                        0 // Angular velocity
                );

        double turnAngle = MathHelper.normalizeBearing
                (intercept.bulletHeading_deg - robot.getGunHeading());
        robot.setTurnGunRight(turnAngle);

        if (Math.abs(turnAngle) <= intercept.angleThreshold) {
            // Ensure that the gun is pointing at the correct angle
            if (
                    (intercept.impactPoint.x > 0) &&
                            (intercept.impactPoint.x < robot.getBattleFieldWidth()) &&
                            (intercept.impactPoint.y > 0) &&
                            (intercept.impactPoint.y < robot.getBattleFieldHeight())
                    ) {
                // Ensure that the predicted impact point is within
                // the battlefield
                robot.fire(intercept.bulletPower);
            }
        }
    }
}
