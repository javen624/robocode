package com.ana.strategies.movement;

import com.ana.MathHelper;
import robocode.AdvancedRobot;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 24.03.13
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
public class MinimumRiscMovement extends MovementStrategy {
    private Cell[][] cells = new Cell[11][15];;

    public MinimumRiscMovement(AdvancedRobot robot){
        super(robot);

        initPoints();
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 15; j++){
                System.out.println(" X= " + cells[i][j].x + " Y= " + cells[i][j].y  );
            }
            System.out.println("\n");
        }
    }

    @Override
    public void robotMovement() {
        recalculateDamage();
        choseCell();
    }

    private void initPoints(){

        int x;
        int y = 0;
        for(int i = 0; i < 11; i++){
            x = 50;
            y += 50;
            for (int j = 0; j < 15; j++){
                cells[i][j] = new Cell();
                cells[i][j].x = x;
                cells[i][j].y = y;
                cells[i][j].calculateDanger(robot);
                x += 50;
            }

        }
    }

    private void recalculateDamage(){
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 15; j++){
                cells[i][j].calculateDanger(robot);
            }
        }
    }

    private void choseCell(){
        int a, b;
        Cell chosedCell ;
        if (robot.getDistanceRemaining() < 1){
            chosedCell = cells[0][0];
            for (int i = 0; i < 11; i++){
                for (int j = 0; j < 11; j++){


                    if (chosedCell.danger > cells[i][j].danger){
                        chosedCell = cells[i][j];
                        a = i; b = j;
                    }
                    cells[i][j].isChosed = false;
                }
            }
            chosedCell.isChosed = true;
            goTo(chosedCell.x, chosedCell.y, chosedCell.distanceToMe);
            chosedCell.danger = Double.POSITIVE_INFINITY;
        }




    }

    private void findChosedCell(){

    }

    int turnTo(double angle) {
        double ang;
        int dir;
        ang = MathHelper.normalizeBearing(robot.getHeading() - angle);
        if (ang > 90) {
            ang -= 180;
            dir = -1;
        }
        else if (ang < -90) {
            ang += 180;
            dir = -1;
        }
        else {
            dir = 1;
        }
        robot.setTurnLeft(ang);
        return dir;
    }

    void goTo(double x, double y, double dist) {

       /* double angle = Math.toDegrees(MathHelper.absoluteBearing(robot.getX(),robot.getY(),x,y));
        double r = turnTo(angle);
        robot.setAhead(dist * r);   */
        robot.setTurnRightRadians(
                normalRelativeAngleRadians(
                        absoluteBearingRadians(new Point2D.Double(robot.getX(), robot.getY()), new Point2D.Double(x, y))
                                - robot.getHeadingRadians()
                )
        );
        robot.setAhead(new Point2D.Double(robot.getX(), robot.getY()).distance(new Point2D.Double(x, y)));

    }

    private double normalRelativeAngleRadians(double angle){
        return Math.atan2(Math.sin(angle), Math.cos(angle));
    }

    private double absoluteBearingRadians(Point2D source, Point2D target) {
        return Math.atan2(target.getX() -
                source.getX(), target.getY() - source.getY());
    }

    @Override
    public void onPaint(Graphics2D g) {
        String s;
        g.setColor(Color.RED);
        for (int i = 0; i < 11; i++){
            for (int j = 0; j < 15; j++){
                if (cells[i][j].isChosed)
                    g.fillRect(cells[i][j].x - 25, cells[i][j].y - 25, 50, 50);
                g.drawRect(cells[i][j].x - 25, cells[i][j].y - 25, 50, 50);
                s = ((Double)(Math.rint(cells[i][j].danger * 100) / 100)).toString();
                g.drawChars(s.toCharArray(), 0, s.length()
                        , cells[i][j].x, cells[i][j].y);
            }
        }

    }
}
