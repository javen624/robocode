package com.ana.strategies.radar;

import robocode.AdvancedRobot;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 20.03.13
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class TestRadarStrategy extends RadarStrategy {

    public TestRadarStrategy(AdvancedRobot r){
        super(r);
    }
    @Override
    public void radarMovement() {
        robot.turnRadarLeft(360);
    }
}
