package com.ana.strategies.gun;

import robocode.AdvancedRobot;

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
        robot.fire(1);
    }
}
