package cz.mendelu.pef.ei.pjj.greenfoot.settings;

import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Settings extends JFrame {
    private JPanel panel1;
    private JCheckBox levnejsiBenzinCheckBox;
    private JCheckBox extraPenizeCheckBox;
    private JCheckBox levnejsiOpravyCheckBox;
    private JButton button1;

    public Settings(Hrac player, SettingsFrame ramecek) {
        extraPenizeCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox byl zaskrtnut
                    player.setZustatek(20000);
                    System.out.println("Peníze byli přidány na Váš účet.");
                    System.out.println(player.getZustatek());
                    extraPenizeCheckBox.setSelected(true);
                } else {//checkbox byl odskrtnut
                    player.setZustatek(10000);
                    System.out.println("Peníze byli nastaveny na původní hodnotu.");
                    System.out.println(player.getZustatek());
                    extraPenizeCheckBox.setSelected(false);
                }
                ;
            }
        });

        levnejsiOpravyCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox byl zaskrtnut
                    player.getAutobusy().setCenaOpravy(8000);
                    player.getAutobusy().getPoleAutobusu().get(0).getStav().setCenaOpravy(8000);
                    System.out.println("Ceny komponentů zlevnily.");
                    levnejsiOpravyCheckBox.setSelected(true);
                } else {//checkbox byl odskrtnut
                    player.getAutobusy().setCenaOpravy(10000);
                    player.getAutobusy().getPoleAutobusu().get(0).getStav().setCenaOpravy(10000);
                    System.out.println("Ceny komponentů zdražily.");
                    levnejsiOpravyCheckBox.setSelected(false);
                }
            }
        });

        levnejsiBenzinCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {//checkbox byl zaskrtnut
                    player.getAutobusy().setCenaPaliva(1000);
                    player.getAutobusy().getPoleAutobusu().get(0).getStav().setCenaPaliva(1000);
                    System.out.println("Cena ropy klesla.");
                    levnejsiBenzinCheckBox.setSelected(true);
                } else {//checkbox byl odskrtnut
                    player.getAutobusy().setCenaPaliva(2000);
                    player.getAutobusy().getPoleAutobusu().get(0).getStav().setCenaPaliva(2000);
                    System.out.println("Cena ropy vzrostla.");
                    levnejsiBenzinCheckBox.setSelected(false);
                }
            }
        });
        button1.setText("Zavřít");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ramecek.setVisible(false);
            }
        });

    }

    public JPanel getPanel1() {
        return panel1;
    }

}
