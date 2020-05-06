package cz.mendelu.pef.ei.pjj.greenfoot;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.awt.*;

public class Exit extends Actor {
    public Exit() {
        setImage(new GreenfootImage("  exit  ", 26, Color.WHITE, Color.BLACK));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            System.exit(0);
        }
    }
}
