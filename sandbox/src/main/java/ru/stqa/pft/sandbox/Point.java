package ru.stqa.pft.sandbox;

public class Point {

    protected double x;
    protected double y;

    Point(double xCoord, double yCoord){
        this.x = xCoord;
        this.y = yCoord;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double distance(Point a)
    {
        double dx = a.x - x;
        double dy = a.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
