package cz.mendelu.pef.ei.pjj.greenfoot.settings;

import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame extends JFrame {
    public SettingsFrame(Hrac player) throws HeadlessException {
        Settings settings = new Settings(player, this);
        this.setContentPane(settings.getPanel1());
        this.pack();
    }

}
