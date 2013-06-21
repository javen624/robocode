package com.ana.strategies.movement;

import com.ana.Enemy;
import com.ana.MathHelper;
import robocode.AdvancedRobot;

import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 12.04.13
 * Time: 16:10
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
    public Integer x;
    public Integer y;
    public Double danger;
    public Boolean isChosed = false;
    public Double distanceToMe;
    private int howMuchDamage;


    public void calculateDanger(AdvancedRobot robot){
        distanceToMe = Point2D.distance(robot.getX(), robot.getY(), x, y);
        Enemy closestEnemy = MathHelper.getClosestEnemy();
        double distanceToClosestEnemy = 1;
        if (closestEnemy != null)
            distanceToClosestEnemy = Point2D.distance(closestEnemy.x, closestEnemy.y, x, y);
        /*
        1. Чем больше расстояние до врага, тем меньше опасность
        2. Перемещаться в соседние точки безопаснее, чем в далекостоящие
        3.
         */
        danger =   (distanceToMe <= 200 && distanceToMe > 25 ? 1 : distanceToMe < 25 ? Double.POSITIVE_INFINITY : distanceToMe)
                / Math.pow(distanceToClosestEnemy, 1.5) * 10000;
        //danger = ((distanceToMe+100) / Math.pow(distanceToClosestEnemy, 2)) * 1000 ;

    }
}


