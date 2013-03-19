package com.ana;

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

    public void run() {
        while (true) {
            ahead(100);
            turnGunRight(360);
            //back(100);
            turnGunRight(360);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }
}
