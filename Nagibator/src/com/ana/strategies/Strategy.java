package com.ana.strategies;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

/**
 * Родительский класс для всех стратегий.
 */
public abstract class Strategy {
    protected AdvancedRobot robot;
    public Strategy(AdvancedRobot robot){
        this.robot = robot;
    }

    public void onScannedRobot(ScannedRobotEvent e){

    }

    public void onPaint(Graphics2D graphics2D){}
}
