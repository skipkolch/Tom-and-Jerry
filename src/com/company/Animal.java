package com.company;

import java.awt.Point;

public class Animal
{
    private String name;
    private int age;
    protected int height;
    protected int width;
    protected Point position;

    public Animal(String name, int age, Point position, int Height,int Width)
    {
        this.age = age;
        this.name = name;
        this.position = position;

        setSize(Height,Width);
    }

    public Animal(String name, int x, int y, int Height,int Width)
    {
        this.age = 0;
        this.name = name;
        this.position = new Point(x,y);

        setSize(Height,Width);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    private void setSize(int H,int W)
    {
        this.height = H;
        this.width = W;
    }
}
