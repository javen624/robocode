package com.ana;

import robocode.AdvancedRobot;
import robocode.Rules;
import robocode.ScannedRobotEvent;

public class Enemy {
    public double velocity;
    public double x;
    public double y;
    public double heading;  //Направление его движения (абсолютное)
    public double bearing;  //Угол поворота до врага
    public double energy;
    public double distance;
    public String name;
    public boolean exist = false; //Эта переменная не будет нужна, когда мы будем работать только с массивом
    public boolean isUpdated = true;

    public long updateTick;
    
    public double futureX;         //Вероятное х для врага
    public double futureY;         //Вероятное y для врага

    public double firePower;       //Сила выстрела
    double time;            //Время полёта снаряда
    public boolean IsWalls;

    public Enemy(ScannedRobotEvent enemy, AdvancedRobot hero){
        update(enemy,hero);
    }

    public Enemy(){

    }

    public boolean isWalls(){
    	if(this.heading%(Math.PI/2)!=0) return false;
    	if((this.x>=17 && this.x<=19) || (this.x>=781 && this.x<=783) || (this.y>=17 && this.y<=19) || (this.y>=581 && this.y<=583) ) return true;
    	return false;
    }
    
    public void update(ScannedRobotEvent enemy, AdvancedRobot hero) {
        this.velocity = enemy.getVelocity();
        this.heading = enemy.getHeadingRadians();
        this.bearing = enemy.getBearingRadians();
        this.energy = enemy.getEnergy();
        this.distance = enemy.getDistance();
        this.name = enemy.getName();
        
        this.updateTick=hero.getTime();

        this.exist = true;
        
        this.x = hero.getX() + Math.sin(hero.getHeadingRadians() + this.bearing) * this.distance;
        this.y = hero.getY() + Math.cos(hero.getHeadingRadians() + this.bearing) * this.distance;

        this.IsWalls=isWalls();
        System.out.println(this.IsWalls);
        
        firePower = Math.max(Math.min((20 / distance) * hero.getEnergy(), ((hero.getEnergy()>20)?3:1.5) ), (hero.getEnergy()>10 && distance <= 30)?3:0.1); // Сила выстрела с учётом энергии и расстояния
        //firePower = 1;
        double bulletSpeed = Rules.getBulletSpeed(firePower);
        time = distance/bulletSpeed;

        isUpdated = true;

        futureX = x + time * velocity * Math.sin(heading);
        futureY = y + time * velocity * Math.cos(heading);
    }

    public void debugInfo(){
        System.out.println("name = " + name +   " x = " + x + " y = " + y +" heading = " + heading + " distance = " + distance + " velocity = " + velocity + "energy = " + energy +  "futureX = " + futureX + " futureY = " + futureY +"\n");
    }
}