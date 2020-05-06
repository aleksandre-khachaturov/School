package cz.mendelu.pef.ei.pjj.projekt.hra.domain.Timer;

import cz.mendelu.pef.ei.pjj.greenfoot.game.HraOknoNew;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Autobus;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;


public class Vlakno implements Runnable {
    private int cas;
    private Autobus autobus;
    private Hrac player;
    private HraOknoNew okno;

    public Vlakno(int cas, Autobus autobus, Hrac player, HraOknoNew okno) {
        this.cas = cas;
        this.autobus = autobus;
        this.player = player;
        this.okno = okno;
    }

    public int getCas() {
        return cas;
    }

    public void run() {
        System.out.println("Operations with " + autobus.getJmeno() + " DISABLED");
        int index = okno.getBusComboBox().getSelectedIndex();
        for (int i = cas; i > 0; i--) {
            if (okno.getBusComboBox().getSelectedIndex() == index) {
                okno.getNatankovatPlnouButton().setEnabled(false);
                okno.getOpravitButton().setEnabled(false);
                okno.getVylepsitButton().setEnabled(false);
                okno.getVyslatNaCestuButton().setEnabled(false);
                okno.getNaCesteCas().setText("Za " + Integer.toString(i) + " vteřin přijede");
            } else {
                okno.getNatankovatPlnouButton().setEnabled(true);
                okno.getOpravitButton().setEnabled(true);
                okno.getVylepsitButton().setEnabled(true);
                okno.getVyslatNaCestuButton().setEnabled(true);
                okno.getNaCesteCas().setText("Není právě na cestě.");
            }
            System.out.println(i);
            Thread.yield();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Vlaknu se nechce ale vstává.");
            }
        }
        System.out.println("Operations with " + autobus.getJmeno() + " ENABLED");
        autobus.prijed(player);
        System.out.println(player.getZustatek());
        okno.updateHracInfo(player);
        okno.updateBusInfo(player);
        okno.getNatankovatPlnouButton().setEnabled(true);
        okno.getOpravitButton().setEnabled(true);
        okno.getVylepsitButton().setEnabled(true);
        okno.getVyslatNaCestuButton().setEnabled(true);
        okno.getNaCesteCas().setText("Není právě na cestě.");

    }
}
