package com.ana.strategies.gun;

import com.ana.strategies.Strategy;
import robocode.AdvancedRobot;

/**
 * Стратегия стрельбы
 */
public abstract class GunStrategy extends Strategy {
    public GunStrategy(AdvancedRobot robot){
        super(robot);
    }
    public abstract void shot();
}
