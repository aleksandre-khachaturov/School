package cz.mendelu.pef.ei.pjj.greenfoot;

import cz.mendelu.pef.ei.pjj.greenfoot.settings.SettingsFrame;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import javax.swing.*;
import java.awt.*;

public class SettingsGFButton extends Actor {
    private Hrac player;
    private SettingsFrame ramecek;

    public SettingsGFButton(Hrac player) {
        setImage(new GreenfootImage("  Nastavení  ",
                26, Color.WHITE, Color.BLACK));
        this.player = player;
        this.ramecek = new SettingsFrame(player);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    ramecek.setTitle("Nastavení hry:");
                    ramecek.setVisible(true);
                }
            });
        }
    }
}