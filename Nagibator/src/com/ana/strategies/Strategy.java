package com.ana.strategies;

import robocode.AdvancedRobot;

/**
 * Родительский класс для всех стратегий.
 */
public abstract class Strategy {
    protected AdvancedRobot robot;
    public Strategy(AdvancedRobot robot){
        this.robot = robot;
    }
}
