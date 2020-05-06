package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class StavAutobusu implements Serializable {
    private int palivoMax;
    private int palivo;
    private boolean rozbity;
    private int cenaPaliva;
    private int cenaOpravy;

    StavAutobusu(int cenaOpravy, int cenaPaliva) {
        this.palivoMax = 100;
        this.palivo = 100;
        this.rozbity = false;
        this.cenaOpravy = cenaOpravy;
        this.cenaPaliva = cenaPaliva;
        //this.cenaOpravy=10000;
        //this.cenaPaliva=2000;
    }

    public void snizPalivo() {
        this.palivo = this.palivo - 20;
    }

    public void doplnPalivo(Hrac player) {
        if (player.getZustatek() >= this.cenaPaliva) {
            this.palivo = 100;
            player.setZustatek(player.getZustatek() - this.cenaPaliva);
        } else {
            System.out.println("Nemáte dostatek financí.");
        }
    }

    public void rozbijSe() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 99 + 1);
        if (randomNum <= 65) {
            this.rozbity = true;
        } else {
            this.rozbity = false;
        }
        System.out.println(randomNum);
    }

    public void opravit(Hrac player) {
        if (player.getZustatek() >= this.cenaOpravy) {
            player.setZustatek(player.getZustatek() - this.cenaOpravy);
            this.rozbity = false;
        } else {
            System.out.println("Nemáte dostatek financí.");
        }
    }

    public int getPalivo() {
        return palivo;
    }

    public void stav() {
        if (rozbity) {
            System.out.println("Autobus je rozbitý");
        } else {
            System.out.println("Autobus je v pořádku");
        }
        System.out.println("Stav nádrže: " + this.palivo + " / " + this.palivoMax);
    }

    public boolean getRozbity() {
        return this.rozbity;
    }

    public int getCenaOpravy() {
        return cenaOpravy;
    }

    public int getCenaPaliva() {
        return cenaPaliva;
    }

    public void setCenaOpravy(int cenaOpravy) {
        this.cenaOpravy = cenaOpravy;
    }

    public void setCenaPaliva(int cenaPaliva) {
        this.cenaPaliva = cenaPaliva;
    }
}
