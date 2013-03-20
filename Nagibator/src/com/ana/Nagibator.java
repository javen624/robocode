package com.ana;

import com.ana.strategies.gun.GunStrategy;
import com.ana.strategies.gun.TestGunStrategy;
import com.ana.strategies.movement.MovementStrategy;
import com.ana.strategies.movement.TestMovementStrategy;
import com.ana.strategies.radar.RadarStrategy;
import com.ana.strategies.radar.TestRadarStrategy;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

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
        radarStrategy = new TestRadarStrategy(this);
        movementStrategy = new TestMovementStrategy(this);
        gunStrategy = new TestGunStrategy(this);
    }
    public void run() {
        while (true) {
            radarStrategy.radarMovement();
            movementStrategy.robotMovement();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        gunStrategy.shot();
    }
}
