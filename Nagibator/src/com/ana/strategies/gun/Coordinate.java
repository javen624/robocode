package com.ana.strategies.gun;

/**
 * Created with IntelliJ IDEA.
 * User: Пекарь
 * Date: 17.04.13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class Coordinate {
    public double x, y;
    public Coordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Coordinate(){

    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
}
