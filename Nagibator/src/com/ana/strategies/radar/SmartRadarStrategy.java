package com.ana.strategies.radar;

import com.ana.Enemy;
import com.ana.MathHelper;
import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 03.04.13
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */
public class SmartRadarStrategy extends RadarStrategy {

    int radarDirection = 1;
    public SmartRadarStrategy(AdvancedRobot robot) {
        super(robot);
    }

    @Override
    public void radarMovement() {
        robot.setTurnRadarRight(22.5 * radarDirection); //Двигаем радар в одном направлении

        if (isAllUpdated()){ //Пока не обновим информацию о всех роботах
            for (int i = 0; i < MathHelper.enemies.size(); i++)
                MathHelper.enemies.get(i).isUpdated = false;
            radarDirection *= -1; //Меняем направление радара
        }
    }

    public boolean isAllUpdated(){
        int updated = 0;
        for (int i = 0; i < MathHelper.enemies.size(); i++)
            if (MathHelper.enemies.get(i).isUpdated)
                updated++;
        if (updated == robot.getOthers())
            return true;
        else return false;

    }

    public int isUniqueRobot(ScannedRobotEvent e){
        if (MathHelper.enemies.isEmpty())
            return -1;      //Вернуть -1 если массив пуст
        for (int i = 0; i < MathHelper.enemies.size(); i++)
            if (MathHelper.enemies.get(i).name == e.getName())
                return i; //Вернуть индекс врага, которого нужно обновить
        return -1;      //Вернуть -1, если не найден

    }
    public void getInfo(ScannedRobotEvent e){
        int temp = isUniqueRobot(e); //Временная переменная
        if(temp == -1) //Если робота нет в массиве, добавляем его
        {
            MathHelper.enemies.add(new Enemy(e, robot));
//                      System.out.println("Add robot temp: " + temp);
            //              (enemies.get(enemies.size()-1)).debugInfo(); //отладка
        }
        else{
//                      System.out.println("Update robot temp " + temp);
            MathHelper.enemies.get(temp).update(e, robot); //Если робот есть, обновляем его данные
//                      enemies.get(temp).debugInfo();
        }

    }


}
