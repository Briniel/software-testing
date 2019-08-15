package ru.stqa.pft.sandbox;

public class FirstProg {

    public static void main(String[] args) {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(2, 1);
        System.out.println("Distance between two points = "+ p1.distance(p2));
    }

}
