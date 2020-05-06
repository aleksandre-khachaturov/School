package cz.mendelu.pef.ei.pjj.greenfoot.game;

import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;

import javax.swing.*;
import java.awt.*;

public class HraOknoFrame extends JFrame {
    public HraOknoFrame(Hrac player) throws HeadlessException {
        //this.setSize(800, 600);

        HraOknoNew hra = new HraOknoNew(player);
        this.setContentPane(hra.getPanel1());
        this.pack();
    }
}
