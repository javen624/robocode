package com.ana.strategies.movement;

import com.ana.Enemy;
import com.ana.MathHelper;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

import java.awt.*;

import static java.lang.Math.PI;
import static java.lang.Math.signum;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 09.04.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class LinkedWaveMovement extends MovementStrategy {
    Enemy closestEnemy;
    public static int direction = 1;
    public LinkedWaveMovement(AdvancedRobot robot) {
        super(robot);
    }

    @Override
    public void robotMovement() {
        closestEnemy = getClosestEnemy();
        System.out.println((int)closestEnemy.x);

        double angleToMe = MathHelper.angleTo(robot.getX(), robot.getY(), closestEnemy.x, closestEnemy.y);

        // определяем угловое направление относительно противника (по часовой стрелке, либо против) ...
        double lateralDirection = signum(
                (robot.getVelocity() != 0 ? robot.getVelocity() : 1) * Math.sin(Utils.normalRelativeAngle(robot.getHeadingRadians() - angleToMe)));
        // получаем желаемое направление движения
        double desiredHeading = Utils.normalAbsoluteAngle(angleToMe + Math.PI / 2 * lateralDirection);
        // нормализуем направление по скорости
        double normalHeading = robot.getVelocity() >= 0 ? robot.getHeadingRadians() : Utils.normalAbsoluteAngle(robot.getHeadingRadians() + Math.PI);


        System.out.println(MathHelper.angleTo(robot.getX(), robot.getY(), closestEnemy.x, closestEnemy.y));
        if (closestEnemy != null){
            if (closestEnemy.distance > 200)
                robot.setTurnRightRadians(Utils.normalRelativeAngle(desiredHeading -normalHeading - PI/3));
            else if (closestEnemy.distance < 100)
                robot.setTurnRightRadians(Utils.normalRelativeAngle(desiredHeading -normalHeading + PI/3));
            else
                robot.setTurnRightRadians(Utils.normalRelativeAngle(desiredHeading - normalHeading));
            robot.setAhead(direction*200);
            robot.setMaxVelocity(200);
            robot.execute();
        }

    }

    private Enemy getClosestEnemy(){
        if (MathHelper.enemies.size() == 0)
            return null;
        Enemy enemy = MathHelper.enemies.get(0);

        for (Enemy e : MathHelper.enemies)
            if (enemy.distance > e.distance)
                enemy = e;

        return enemy;
    }


}
