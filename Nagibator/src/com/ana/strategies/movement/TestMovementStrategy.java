package com.ana.strategies.movement;

import robocode.AdvancedRobot;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 20.03.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class TestMovementStrategy extends MovementStrategy {
    public TestMovementStrategy(AdvancedRobot robot){
        super(robot);
    }
    @Override
    public void robotMovement() {
        robot.ahead(100);
    }
}
