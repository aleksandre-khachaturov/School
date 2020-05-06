package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Autobusy implements Serializable {
    public ArrayList<Autobus> poleAutobusu;
    private int pocetAutobusu;
    private int cenaPaliva;
    private int cenaOpravy;

    public Autobusy() {
        this.pocetAutobusu = 1;
        this.cenaOpravy = 10000;
        this.cenaPaliva = 2000;
        Autobus bus0 = new Autobus("Škoda1", this.cenaPaliva, this.cenaOpravy);
        Autobus bus1 = new Autobus("Škoda2", this.cenaPaliva, this.cenaOpravy + 62035);
        Autobus bus2 = new Autobus("Škoda3", this.cenaPaliva, this.cenaOpravy - 100);
        Autobus bus3 = new Autobus("BMW", this.cenaPaliva, this.cenaOpravy - 100);

        poleAutobusu = new ArrayList<Autobus>();
        this.poleAutobusu.add(bus0);
        this.poleAutobusu.add(bus1);
        this.poleAutobusu.add(bus2);
        this.poleAutobusu.add(bus3);
    }

    public void koupitAutobus(String jmeno, Hrac player, int cena) {
        Autobus bus = new Autobus(jmeno, this.cenaPaliva, this.cenaOpravy);
        this.poleAutobusu.add(bus);
        this.pocetAutobusu++;
        player.setZustatek(player.getZustatek() - cena);
    }


    public ArrayList<Autobus> getPoleAutobusu() {
        return poleAutobusu;
    }

    public void setCenaPaliva(int cenaPaliva) {
        this.cenaPaliva = cenaPaliva;
    }

    public void setCenaOpravy(int cenaOpravy) {
        this.cenaOpravy = cenaOpravy;
    }

    public int getCenaPaliva() {
        return cenaPaliva;
    }

    public int getCenaOpravy() {
        return cenaOpravy;
    }

    public int getPocetAutobusu() {
        return pocetAutobusu;
    }
}
