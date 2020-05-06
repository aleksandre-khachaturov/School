package cz.mendelu.pef.ei.pjj.greenfoot;

import cz.mendelu.pef.ei.pjj.greenfoot.game.HraOknoFrame;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import javax.swing.*;
import java.awt.*;

public class AddName extends Actor {
    private Hrac player;

    public AddName(Hrac player) {
        this.player = player;
        setImage(new GreenfootImage("  Napis svoje jmeno:  ",
                26, Color.WHITE, Color.BLACK));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            String jmenoHrace = Greenfoot.ask("Jak se jmenujes?");
            //OR THIS: //String path = JOptionPane.showInputDialog("Enter a path");


            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    HraOknoFrame hraFrame = new HraOknoFrame(player);
                    hraFrame.setVisible(true);
                    hraFrame.setTitle("Hra | Autobusovy Management");
                }
            });

            this.player.setJmenoHrace(jmenoHrace);
            System.out.println("Vítej ve hře " + player.getJmenoHrace());
            JOptionPane.showMessageDialog(new JInternalFrame(), "Vítej ve hře " + player.getJmenoHrace(), "Zacatek hry",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}