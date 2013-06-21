package com.ana.strategies.gun;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 20.03.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class TestGunStrategy extends GunStrategy {
    public TestGunStrategy(AdvancedRobot robot){
        super(robot);
    }
    @Override
    public void shot() {







    }

    Point enemyNextMax;
    Point enemyNextMin;
    Point enemyNext;
    Point ourNext;

    public void onScannedRobot(ScannedRobotEvent event){
        long curTime = System.currentTimeMillis();
        long curTick = robot.getTime();
        Point our=new Point(robot.getX(), robot.getY());
        ourNext=our.getNext(robot.getVelocity(), robot.getHeadingRadians());
        double distance=event.getDistance();
        Point enemy=our.getPointByDistance(distance, robot.getHeadingRadians()+event.getBearingRadians());
        enemyNext=enemy.getNext(robot.getVelocity(), event.getHeadingRadians());
        double nextDistance=our.getNext(robot.getVelocity(), robot.getHeadingRadians()).getDistanceToPoint(enemyNext);
        double turnPossibility=0.1;
        double angleToEnemy=our.getNormalAngleRad(enemy);
        if(robot.getGunHeat()==0 && robot.getEnergy()>20){
            double bullet=2;
            if(distance<100) bullet=3;
            else if(distance>200 && distance<300) bullet=1;
            else if(distance>=300) bullet=0.1;
            int tick=(int)Math.ceil(distance/(20-3*bullet));
            if(event.getVelocity()==0){
                if(robot.getGunHeadingRadians()<=angleToEnemy+Math.toRadians(1) || robot.getGunHeadingRadians()>=angleToEnemy-Math.toRadians(1)) robot.setFire(bullet);
            }
            else{
                Point next1=enemy.getNextLeftMin(tick, event.getVelocity(), event.getHeadingRadians(), turnPossibility);
                Point next2=enemy.getNextMax(tick, event.getVelocity(), event.getHeadingRadians());
                double angle1=our.getNormalAngleRad(next1);
                double angle2=our.getNormalAngleRad(next2);
                if(Math.abs(angle2-angle1)>Math.PI){
                    if(angle2<Math.PI/2) angle2+=Math.PI*2;
                    else if(angle1<Math.PI/2) angle1+=Math.PI*2;
                }
                System.out.println(angle1+" "+angle2);
                if(Math.min(angle1,angle2)<=robot.getGunHeadingRadians() && robot.getGunHeadingRadians()<=Math.max(angle1, angle2))
                    robot.fire(bullet);
            }
        }
        int time=(int)Math.ceil(nextDistance/15);
        enemyNextMax=enemy.getNextMax(time, robot.getVelocity(), event.getHeadingRadians());
        enemyNextMin=enemy.getNextMin(time, robot.getVelocity(), event.getHeadingRadians(), turnPossibility);
        double angle1=our.getNext(robot.getVelocity(), robot.getHeadingRadians()).getNormalAngleRad(enemyNextMax);
        double angle2=our.getNext(robot.getVelocity(), robot.getHeadingRadians()).getNormalAngleRad(enemyNextMin);
        if(Math.abs(angle2-angle1)>Math.PI){
            if(angle2<Math.PI/2) angle2+=Math.PI*2;
            else if(angle1<Math.PI/2) angle1+=Math.PI*2;
        }
        angleToEnemy=our.getNext(robot.getVelocity(), robot.getHeading()).getNormalAngleRad(enemy);
        if(event.getVelocity()!=0) angleToEnemy=(angle2+angle1)/2;
        System.out.println("enemy: "+enemy+"\nenemyNext: "+enemyNext+"\nangleToEnemy: "+angleToEnemy);
        double gunBearing=angleToEnemy-robot.getGunHeadingRadians();
        if(Math.abs(angleToEnemy-robot.getGunHeadingRadians()) > Math.abs(angleToEnemy-robot.getGunHeadingRadians()+Math.PI*2)) gunBearing+=Math.PI*2;
        robot.setTurnGunRightRadians(Math.min(Math.toRadians(20), Math.abs(gunBearing)) * Math.signum(gunBearing));
        double nextAngle=our.getNext(robot.getVelocity(), robot.getHeadingRadians()).getNormalAngleRad(enemyNext);
        if(event.getVelocity()==0) nextAngle=angleToEnemy;
        double radarBearing=nextAngle-robot.getRadarHeadingRadians();
        if(Math.abs(nextAngle-robot.getRadarHeadingRadians()) > Math.abs(nextAngle-robot.getRadarHeadingRadians()+Math.PI*2)) radarBearing+=Math.PI*2;
        robot.setTurnRadarRightRadians(radarBearing);
        System.out.println("Затрачено времени: " + (System.currentTimeMillis() - curTime));
        System.out.println("Затрачено тиков: " +(robot.getTime() - curTick));
    }

    @Override
    public void onPaint(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect((int)enemyNext.x - 3, (int)enemyNext.y - 3, 6, 6);
        g.setColor(Color.BLUE);
        g.fillRect((int)ourNext.x - 3, (int)ourNext.y - 3, 6, 6);
    }

    class Point{
        double x;
        double y;

        Point(double x, double y){
            this.x=x;
            this.y=y;
        }

        Point getPointByDistance(double distance, double angleRad){
            Point p=new Point(x+distance*Math.sin(angleRad), y+distance*Math.cos(angleRad));
            return p;
        }

        double getDistanceToPoint(Point p){
            return Math.sqrt(Math.pow(p.x-x, 2)+Math.pow(p.y-y, 2));
        }

        double getNormalAngleRad(Point p){
            double dx=p.x-x;
            double dy=p.y-y;
            double angle=0;
            if(dx==0 && dy<0) angle=Math.PI;
            else if(dy==0 && dx>0) angle=Math.PI/2;
            else if(dy==0 && dx<0) angle=3*Math.PI/2;
            else if(dx>0 && dy>0) angle=Math.atan(dx/dy);
            else if((dx>0 && dy<0)) angle=Math.atan(dx/dy)+Math.PI;
            else if(dx<0 && dy>0) angle=Math.atan(dx/dy)+Math.PI*2;
            else if(dx<0 && dy<0) angle=Math.atan(dx/dy)+Math.PI;
            return angle;
        }

        String getInfo(){
            return " x="+x+" y="+y;
        }

        public Point getNextLeftMin(int time, double velocity, double heading, double turnPosibility){
            double distance=0;
            double v;
            int t;
            double angle=0;
            for(t=0, v=velocity; v<=8; v++, t++){
                distance+=v;
                angle+=10-0.75*Math.abs(v);
            }
            distance+=(time-t)*v;
            angle+=(time-t)*(10-0.75*Math.abs(v));
            angle*=turnPosibility;
            angle=Math.toRadians(angle);
            return getPointByDistance(distance, heading-angle);
        }

        public Point getNextRightMin(int time, double velocity, double heading, double turnPosibility){
            double distance=0;
            double v;
            int t;
            double angle=0;
            for(t=0, v=velocity; v<=8; v++, t++){
                distance+=v;
                angle+=10-0.75*Math.abs(v);
            }
            distance+=(time-t)*v;
            angle+=(time-t)*(10-0.75*Math.abs(v));
            angle*=angle*=turnPosibility;;
            return getPointByDistance(distance, heading+angle);
        }

        public Point getNextMin(int time, double velocity, double heading, double turnPosibility){
            if(Math.random()>0.5) return getNextRightMin(time, velocity, heading, turnPosibility);
            return getNextLeftMin(time, velocity, heading, turnPosibility);
        }

        public Point getNextMax(int time, double velocity, double heading){
            double distance=0;
            double v;
            int t;
            for(t=0, v=velocity; v<=8; v++, t++){
                distance+=v;
            }
            distance+=(time-t)*v;
            return getPointByDistance(distance, heading);
        }

        public Point getNext(double velocity, double heading){
            return getPointByDistance(velocity, heading);
        }
    }

}
