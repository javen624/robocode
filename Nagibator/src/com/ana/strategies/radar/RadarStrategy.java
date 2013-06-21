package com.ana.strategies.radar;

import com.ana.strategies.Strategy;
import robocode.AdvancedRobot;

/**
 * Стратегия сканирования.
 */


public abstract class RadarStrategy extends Strategy {
    public RadarStrategy(AdvancedRobot robot){
        super(robot);
    }

    public abstract void radarMovement();
}
