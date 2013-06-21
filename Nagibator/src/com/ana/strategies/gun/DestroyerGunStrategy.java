package com.ana.strategies.gun;

import com.ana.Enemy;
import com.ana.MathHelper;
import robocode.AdvancedRobot;
import robocode.Rules;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 16.04.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class DestroyerGunStrategy extends GunStrategy {
    private double TurnCoeff = 2.65;
    public DestroyerGunStrategy(AdvancedRobot robot) {
        super(robot);
    }

    @Override
    public void shot() {
        Enemy enemy = MathHelper.getClosestEnemy();
        double futureX = enemy.futureX;
        double futureY = enemy.futureY;
        double absDeg = MathHelper.absoluteBearing(robot.getX(), robot.getY(), futureX, futureY); //вычисляю на какой угол надо повернуться что бы навестись на врага
        double dispersion = Math.max(Math.min(robot.getEnergy()/3.5,20), 8); //Поправка на изменение врагом скорости - создаю разброс

        if (enemy.velocity == 0)
            dispersion = 0.5;

        double turnTime = absDeg / (TurnCoeff * Rules.GUN_TURN_RATE);
        double myX = robot.getX() + turnTime * robot.getVelocity() * Math.sin(Math.toRadians(robot.getHeading()));
        double myY = robot.getY() + turnTime * robot.getVelocity() * Math.cos(Math.toRadians(robot.getHeading()));
        absDeg = MathHelper.absoluteBearing(myX, myY, futureX, futureY); //Вношу поправку на своё собственное движение

        robot.setTurnGunRight(MathHelper.normalizeBearing(absDeg - robot.getGunHeading()));

        if (robot.getGunHeat() == 0 && Math.abs(robot.getGunTurnRemaining()) <= dispersion &&
                ( (robot.getEnergy() >= 20)||(enemy.energy < robot.getEnergy())||(enemy.energy<5)||(enemy.distance<=30) )
                && robot.getEnergy() > 0.1
                ) //Если мы не перегреты, пушка достаточно повернулась и достаточно энергии - стреляем.
            robot.setFire(enemy.firePower);



    }
}
