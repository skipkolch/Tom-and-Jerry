package com.company;

import java.awt.*;

public class Cat extends Animal
{
    private double speed;
    private double jump;
    private boolean checkCatchMouse;
    private double distanseCat;

    public Cat(String name, int x, int y , int H, int W)
    {
        super(name,x,y,H,W);

        speed = 1;
        jump = 0.1;
        checkCatchMouse = false;
    }

    public void resetCatch()
    {
        checkCatchMouse = false;
        speed = 1;
    }

    public boolean isCatchMouse() {
        return checkCatchMouse;
    }

    public void CatchMouse(Point mousePoint)
    {
        distanseCat = Math.sqrt( Math.pow(mousePoint.x - position.x,2) + Math.pow(mousePoint.y - position.y,2));

        SpeedUp();

        if(distanseCat <= height || distanseCat <= width)
        {
            checkCatchMouse = true;
//            return;
        }


//        if(mousePoint.x == this.getPosition().x && mousePoint.y == this.getPosition().y )
//        {
//            checkCatchMouse = true;
//            return;
//        }


        if( mousePoint.x >= this.position.x && mousePoint.y <= this.position.y)
        {
            this.position.x += speed;
            this.position.y -= speed;
            return;
        }

        if( mousePoint.x <= this.position.x && mousePoint.y <= this.position.y)
        {
            this.position.x -= speed;
            this.position.y -= speed;
            return;
        }

        if( mousePoint.x <= this.position.x && mousePoint.y >= this.position.y)
        {
            this.position.x -= speed;
            this.position.y += speed;
            return;
        }

        if( mousePoint.x >= this.position.x && mousePoint.y >= this.position.y)
        {
            this.position.x += speed;
            this.position.y += speed;
            return;
        }
    }


    private void SpeedUp()
    {
       speed += jump;
    }
}
