package com.ana;

import robocode.RobotDeathEvent;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 24.03.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class MathHelper {
    public static ArrayList<Enemy> enemies = new ArrayList<Enemy>(); //Массив врагов
    public static double absbearing( double x1,double y1, double x2,double y2 )
    {
        double xo = x2-x1;
        double yo = y2-y1;
        double h = getRange( x1,y1, x2,y2 );
        if( xo > 0 && yo > 0 )
        {
            return Math.asin( xo / h );
        }
        if( xo > 0 && yo < 0 )
        {
            return Math.PI - Math.asin( xo / h );
        }
        if( xo < 0 && yo < 0 )
        {
            return Math.PI + Math.asin( -xo / h );
        }
        if( xo < 0 && yo > 0 )
        {
            return 2.0*Math.PI - Math.asin( -xo / h );
        }
        return 0;
    }

    public static double getRange( double x1,double y1, double x2,double y2 )
    {
        double xo = x2-x1;
        double yo = y2-y1;
        double h = Math.sqrt( xo*xo + yo*yo );
        return h;
    }
    public static double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
    public static double absoluteBearing(double x1, double y1, double x2, double y2) {
        double xo = x2-x1;
        double yo = y2-y1;
        double hyp = Math.sqrt(xo*xo + yo*yo);
        double arcSin = Math.toDegrees(Math.asin(xo / hyp));
        double bearing = 0;

        if (xo > 0 && yo > 0) { // обе позиции: внизу слева
            bearing = arcSin;
        } else if (xo < 0 && yo > 0) { // x отриц., y полож.: внизу справа
            bearing = 360 + arcSin; // arcsin  здесь отрицательный, реально 360 - ang
        } else if (xo > 0 && yo < 0) { // x полож., y отриц: вверху слева
            bearing = 180 - arcSin;
        } else if (xo < 0 && yo < 0) { // оба отриц.: вверху слева
            bearing = 180 - arcSin; // arcsin отрицательный, реально 180 + ang
        }

        return bearing;
    }
    public static double getAngleBetweenTwoPoints(Point p1, Point p2){
       // System.out.println(p1.getX() + p2.getX() + p1.getY() + p2.getY());
        return Math.acos((p1.getX() * p2.getX() + p1.getY() * p2.getY()) / (Math.sqrt(Math.pow(p1.getX(), 2) + Math.pow(p1.getY(), 2)) +
                (Math.sqrt(Math.pow(p2.getX(), 2) + Math.pow(p2.getY(), 2)))));
    }

    public static double angleTo(double baseX, double baseY, double x, double y) {
        double theta = Math.asin((y - baseY) / Point2D.distance(x, y, baseX, baseY)) - Math.PI / 2;
        if (x >= baseX && theta < 0) {
            theta = -theta;
        }
        return (theta %= Math.PI * 2) >= 0 ? theta : (theta + Math.PI * 2);
    }

    public static Enemy getClosestEnemy(){
        double distance = Double.MAX_VALUE;
        Enemy closestEnemy = null;
        for (Enemy e : enemies){
            if (e.distance < distance){
                closestEnemy = e;
                distance = closestEnemy.distance;
            }
        }
        return closestEnemy;
    }


    public static Enemy getEnemy(RobotDeathEvent event) {
        for (Enemy e : enemies) {
            if (e.name.equals(event.getName()))
                return e;
        }
        return null;
    }
}
