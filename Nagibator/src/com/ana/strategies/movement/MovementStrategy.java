package com.ana.strategies.movement;

import com.ana.strategies.Strategy;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

/**
 * Стратегия движения.
 */
public abstract class MovementStrategy extends Strategy {
    public MovementStrategy(AdvancedRobot robot){
        super(robot);
    }
    public abstract void robotMovement();

   public void onPaint(Graphics2D g){}


}
