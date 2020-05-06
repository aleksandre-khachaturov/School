package cz.mendelu.pef.ei.pjj.greenfoot.game;

import cz.mendelu.pef.ei.pjj.projekt.hra.domain.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class HraOkno {
    private JPanel rootPanel;
    private JComboBox level0;
    private JComboBox seznamAut;
    private JComboBox level1;
    private JTabbedPane infoObusu;
    private JLabel printInfoObusu;
    private JButton koupitAutobus;
    private JTabbedPane infoPodniku;
    private JComboBox level3;
    private JComboBox level2;
    private JButton ioMenuButton;
    private JTextArea vypis;
    private JButton buttonPodnikInfo;
    private JTextArea vypisPodnik;
    private JButton buttonVyslatBus;
    private JCheckBox CBvylepseniBaru0;
    private JCheckBox CBvylepseniPersonalu0;
    private JCheckBox CBvylepseniSedadel0;
    private JCheckBox CBvybava0;
    private JCheckBox CBtv0;
    private JCheckBox CBsocialni0;
    private boolean status = true;
    private int n, m, zustatek;
    private Hrac player;


    Autobusy autobusy = new Autobusy();// proč vytváříš nový objekty když už vytvořený jsou?
    Hrac hrac = new Hrac(); //autobusy se vytvoří s hráčem, hráč se automaticky vytváří při startu hry (píšu Ti to i sem protože poprvé jsi mě očividně nepochopil)

    public String[] updateBusList() {
        String[] buses = new String[autobusy.poleAutobusu.size()];
        for (m = 0; m < autobusy.poleAutobusu.size(); m++) {
            buses[m] = autobusy.poleAutobusu.get(m).getJmeno();
        }
        return buses;
    }

    public HraOkno(Hrac player) throws HeadlessException {
        this.player = player;
        String[] buses = updateBusList();
        for (n = 0; n < autobusy.poleAutobusu.size(); n++) {
            seznamAut.addItem(buses[n]);
        }

        Hra hra = new Hra();

        seznamAut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String buses = (String) seznamAut.getSelectedItem();

                vypis.setText("");
                Autobus autobus = new Autobus("Sko", 10, 20);
                vypis.append("Jmeno: " + autobus.getJmeno() + "\n");
                vypis.append("Cena opravy: " + Integer.toString(autobusy.getCenaOpravy()) + "\n");
                vypis.append(Integer.toString(autobus.getZisk()));

                //  hra.infoAutobusu();

            }
        });

//////////////////Podnik info Button//////////////////////

        buttonPodnikInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vypisPodnik.setText(null);
                vypisPodnik.append("Jmeno riditele: " + hrac.getJmenoHrace() + "\n");
                vypisPodnik.append("Pocet busu: " + (autobusy.poleAutobusu.size() + "\n"));
                vypisPodnik.append("Zustatek: " + (hrac.getZustatek()) + "\n");

            }
        });

//////////////////IO menu/////////////////////////////////

        ioMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame ioframe = new JFrame();
                IOForm ioform = new IOForm(player);
                ioframe.setContentPane(ioform.ioPanel);
                ioframe.pack();
                ioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ioframe.setVisible(true);
            }
        });


        ///vyslat bus///

        buttonVyslatBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Autobus bus = new Autobus("jmenoBusu", 5, 5);
                //hra.vyslatAutobus(bus);
                disableButtons();
            }
        });


        VylepseniBar vylepseniBar = new VylepseniBar("barLvL0", 36, 65);
        vylepseniBar.upgrade("Vylepseni baru 0 lvl", 36, 65, hrac);

        CBvylepseniBaru0.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    vypisPodnik.setText(null);
                    zustatek = (hrac.getZustatek() - vylepseniBar.getBoost().getCena());
                    vypisPodnik.setText("\n Avtobusovy BAR se vylepsil. \n Cena: "
                            + vylepseniBar.getBoost().getCena()
                            + "\n Zustatek:" + zustatek);
                    CBvylepseniBaru0.setSelected(true);
                } else {
                    vypisPodnik.setText("\n Avtobusovy BAR NE vylepsil. \n Zustatek: " + (vylepseniBar.getBoost().getCena() + zustatek));
                    hrac.setZustatek(vylepseniBar.getBoost().getCena() + zustatek);
                    CBvylepseniBaru0.setSelected(false);
                }
            }
        });


        CBtv0.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    VylepseniTv vylepseniTv = new VylepseniTv("Nejlepsi TV", 999, 1500);
                    vylepseniTv.upgrade("Vylepseni sedadla", 500, 954, hrac);

                    System.out.println("Avtobusove TV upgradeovaly");
                    CBtv0.setSelected(true);
                } else {
                    hrac.getZustatek();
                    hrac.getAutobusy().getPoleAutobusu().get(0).getStav().setCenaOpravy(10000);
                    System.out.println("Avtobusovy TV NE upgradeobvaly");
                    CBtv0.setSelected(false);
                }
            }
        });

    }

    public void disableButtons() {
        status = true;

        buttonPodnikInfo.setEnabled(status);
        level0.setEnabled(status);
        level1.setEnabled(status);
        level2.setEnabled(status);
        level3.setEnabled(status);
        seznamAut.setEnabled(status);
        ioMenuButton.setEnabled(status);
        koupitAutobus.setEnabled(status);
        vypis.setEnabled(status);
        vypisPodnik.setEnabled(status);
        buttonVyslatBus.setEnabled(status);
    }


    public JPanel getRootPanel() {

        return rootPanel;
    }
}

