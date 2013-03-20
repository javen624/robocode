package com.ana.strategies.radar;

import robocode.AdvancedRobot;

/**
 * Стратегия сканирования.
 */
public abstract class RadarStrategy  {
    AdvancedRobot robot;

    public RadarStrategy(AdvancedRobot r){
        this.robot = r;
    }
    public abstract void radarMovement();
}
