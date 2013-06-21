package com.ana;

import com.ana.strategies.gun.DestroyerGunStrategy;
import com.ana.strategies.gun.GunStrategy;
import com.ana.strategies.gun.NatashaGunStrategy;
import com.ana.strategies.gun.WallsKillerStrategy;
import com.ana.strategies.movement.LinkedWaveMovement;
import com.ana.strategies.movement.MovementStrategy;
import com.ana.strategies.movement.RoundMovement;
import com.ana.strategies.radar.RadarStrategy;
import com.ana.strategies.radar.SmartRadarStrategy;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 19.03.13
 * Time: 22:20
 * To change this template use File | Settings | File Templates.
 */
public class Nagibator extends AdvancedRobot {
    private RadarStrategy radarStrategy;
    private MovementStrategy movementStrategy;
    private GunStrategy gunStrategy;

    public Nagibator(){
        radarStrategy = new SmartRadarStrategy(this);
        gunStrategy = /*new DestroyerGunStrategy(this);*/new NatashaGunStrategy(this);
        movementStrategy = new RoundMovement(this);//new MinimumRiscMovement(this);
    }

    public void run() {
        while (true) {
            radarStrategy.radarMovement();
            movementStrategy.robotMovement();
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (radarStrategy instanceof SmartRadarStrategy)
            ((SmartRadarStrategy) radarStrategy).getInfo(e);
        Enemy enemy = MathHelper.getClosestEnemy();
        if(enemy.IsWalls) gunStrategy=new WallsKillerStrategy(this);
        else gunStrategy=new DestroyerGunStrategy(this);
        gunStrategy.shot();
        //gunStrategy.onScannedRobot(e);
//        execute();
    }

    public void onHitWall(HitWallEvent e){
        LinkedWaveMovement.direction *= -1;
    }

    @Override
    public void onRobotDeath(RobotDeathEvent event) {
        Enemy enemy = MathHelper.getEnemy(event);
        MathHelper.enemies.remove(enemy);
    }

    public void onPaint(Graphics2D g){
       //movementStrategy.onPaint(g);
        gunStrategy.onPaint(g);
    }
}
