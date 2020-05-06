package cz.mendelu.pef.ei.pjj.greenfoot.game;

import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Autobus;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Vylepseni;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HraOknoNew {
    private JPanel panel1;
    private JTextArea infoOHraciText;
    private JComboBox busComboBox;
    private JLabel infoOHraciLabel;
    private JLabel naCesteLabel;
    private JLabel naCesteCas;
    private JButton koupitAutobusButton;
    private JTextArea infoOAutobusuTextArea;
    private JButton vyslatNaCestuButton;
    private JButton opravitButton;
    private JButton natankovatPlnouButton;
    private JLabel vylepseniLabel;
    private JComboBox vylepseniComboBox;
    private JLabel aktualniVylepseniLabel;
    private JLabel mozneVylepseniLabel;
    private JTextArea aktVylepseniTextArea;
    private JTextArea nextVylepseniTextArea;
    private JButton vylepsitButton;

    public HraOknoNew(Hrac player/*, SettingsFrame ramecek*/) {
        updateHracInfo(player);
        updateBusList(player);
        updateBusInfo(player);
        updateVylepseniInfo(player);

        koupitAutobusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int cena = 1000;
                if (player.getZustatek() >= cena) {
                    player.getAutobusy().koupitAutobus("Autobus" + player.getAutobusy().getPoleAutobusu().size(), player, cena);
                    updateBusList(player);
                    updateHracInfo(player);
                    System.out.println(player.getZustatek());
                }
            }
        });
        busComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //Object item = e.getItem();
                    //System.out.println(item.toString());
                    updateBusInfo(player);
                }
            }
        });
        vyslatNaCestuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vyslatNacestu(player);
            }
        });
       /* button1.setText("Zavřít");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ramecek.setVisible(false);
            }
        });
*/
        opravitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opravAutobus(player);
                updateHracInfo(player);
                updateBusInfo(player);
            }
        });
        natankovatPlnouButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                natankuj(player);
                updateBusInfo(player);
                updateHracInfo(player);
            }
        });
        vylepseniComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //výpis vylepšení

                }
            }
        });
    }

    public JPanel getPanel1() {
        return panel1;
    }

    public void updateBusList(Hrac player) {
        busComboBox.removeAllItems();
        for (int i = 0; i < player.getAutobusy().getPoleAutobusu().size(); i++) {
            busComboBox.addItem(player.getAutobusy().getPoleAutobusu().get(i).getJmeno());
        }
    }

    public void updateHracInfo(Hrac player) {
        infoOHraciText.setText("");
        infoOHraciText.append("Jméno: " + player.getJmenoHrace() + "\n");
        infoOHraciText.append("Zůstatek: " + Integer.toString(player.getZustatek()));
    }

    public void updateBusInfo(Hrac player) {
        int bus = busComboBox.getSelectedIndex();
        Autobus busCesta = player.getAutobusy().getPoleAutobusu().get(bus);
        infoOAutobusuTextArea.setText("");
        infoOAutobusuTextArea.append("Jmeno: " + busCesta.getJmeno() + "\n");
        infoOAutobusuTextArea.append("Zisk: " + busCesta.getZisk() + "\n");
        infoOAutobusuTextArea.append("TV: " + busCesta.getBoosty().getTv().getLevel() + "\n");
        infoOAutobusuTextArea.append("Bar: " + busCesta.getBoosty().getBar().getLevel() + "\n");
        infoOAutobusuTextArea.append("Personal: " + busCesta.getBoosty().getPersonal().getLevel() + "\n");
        infoOAutobusuTextArea.append("Sedadla: " + busCesta.getBoosty().getSedadla().getLevel() + "\n");
        infoOAutobusuTextArea.append("Soc. zařízení: " + busCesta.getBoosty().getSocialni().getLevel() + "\n");
        infoOAutobusuTextArea.append("Vybava: " + busCesta.getBoosty().getVybava().getLevel() + "\n");
        infoOAutobusuTextArea.append("Rozbitý: " + busCesta.getStav().getRozbity() + "\n");
        infoOAutobusuTextArea.append("Nádrž: " + busCesta.getStav().getPalivo() + "/100\n");
    }

    public void vyslatNacestu(Hrac player) {
        int bus = busComboBox.getSelectedIndex();
        Autobus busCesta = player.getAutobusy().getPoleAutobusu().get(bus);
        player.vyslatAutobus(busCesta, this);
        System.out.println("Autobus " + busCesta.getJmeno() + " byl vyslán na cestu.");
        System.out.println(player.getZustatek());
    }

    public void updateVylepseniInfo(Hrac player) {
        int bus = busComboBox.getSelectedIndex();
        Vylepseni vylepseniCesta = player.getAutobusy().getPoleAutobusu().get(bus).getBoosty();
        vylepseniComboBox.addItem(vylepseniCesta.getBar().getNazev());
        vylepseniComboBox.addItem(vylepseniCesta.getTv().getNazev());
        vylepseniComboBox.addItem(vylepseniCesta.getPersonal().getNazev());
        vylepseniComboBox.addItem(vylepseniCesta.getSedadla().getNazev());
        vylepseniComboBox.addItem(vylepseniCesta.getSocialni().getNazev());
        vylepseniComboBox.addItem(vylepseniCesta.getVybava().getNazev());
    }

    public void opravAutobus(Hrac player) {
        int bus = busComboBox.getSelectedIndex();
        player.getAutobusy().getPoleAutobusu().get(bus).getStav().opravit(player);
    }

    public void natankuj(Hrac player) {
        int bus = busComboBox.getSelectedIndex();
        player.getAutobusy().getPoleAutobusu().get(bus).getStav().doplnPalivo(player);
    }

    public JButton getNatankovatPlnouButton() {
        return natankovatPlnouButton;
    }

    public JButton getOpravitButton() {
        return opravitButton;
    }

    public JButton getVylepsitButton() {
        return vylepsitButton;
    }

    public JButton getVyslatNaCestuButton() {
        return vyslatNaCestuButton;
    }

    public JComboBox getBusComboBox() {
        return busComboBox;
    }

    public JLabel getNaCesteCas() {
        return naCesteCas;
    }

}