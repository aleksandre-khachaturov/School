package cz.mendelu.pef.ei.pjj.projekt.hra.domain;


import java.io.Serializable;

public class Autobus implements Serializable {
    private String jmeno;
    private Vylepseni boosty;
    private StavAutobusu stav;
    private int zisk;

    public Autobus(String jmeno, int cenaPaliva, int cenaOpravy) {
        this.jmeno = jmeno;
        this.zisk = 5000;
        Vylepseni upgrady = new Vylepseni();
        StavAutobusu stav = new StavAutobusu(cenaOpravy, cenaPaliva);
        this.boosty = upgrady;
        this.stav = stav;
    }


    public StavAutobusu getStav() {
        return stav;
    }

    public void vypisVylepseni() {
        System.out.println("TV:");
        System.out.println(this.boosty.getTv().getLevel());
        System.out.println(this.boosty.getTv().getBoost().getPopis());
        System.out.println(this.boosty.getTv().getBoost().getBonus());

        System.out.println("Sedadla:");
        System.out.println(this.boosty.getSedadla().getLevel());
        System.out.println(this.boosty.getSedadla().getBoost().getPopis());
        System.out.println(this.boosty.getSedadla().getBoost().getBonus());

        System.out.println("Socialní zařízení:");
        System.out.println(this.boosty.getSocialni().getLevel());
        System.out.println(this.boosty.getSocialni().getBoost().getPopis());
        System.out.println(this.boosty.getSocialni().getBoost().getBonus());

        System.out.println("Bar:");
        System.out.println(this.boosty.getBar().getLevel());
        System.out.println(this.boosty.getBar().getBoost().getPopis());
        System.out.println(this.boosty.getBar().getBoost().getBonus());

        System.out.println("Vybava:");
        System.out.println(this.boosty.getVybava().getLevel());
        System.out.println(this.boosty.getVybava().getBoost().getPopis());
        System.out.println(this.boosty.getVybava().getBoost().getBonus());

        System.out.println("Personal:");
        System.out.println(this.boosty.getPersonal().getLevel());
        System.out.println(this.boosty.getPersonal().getBoost().getPopis());
        System.out.println(this.boosty.getPersonal().getBoost().getBonus());
    }

    public int vyjed() {
        if (this.stav.getPalivo() != 0) {
            if (this.stav.getRozbity() == false) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 2;
        }
    }

    public void prijed(Hrac player) {
        this.stav.snizPalivo();
        this.stav.rozbijSe();
        player.setZustatek(player.getZustatek() + this.zisk + boosty.getCelkovyBonus());
        System.out.println(this.stav.getRozbity());
        System.out.println(this.stav.getPalivo());
    }


    public String getJmeno() {
        return jmeno;
    }

    public Vylepseni getBoosty() {
        return boosty;
    }

    public int getZisk() {
        return zisk;
    }

    //printInfo


    public void infoAutobus() {
        // Autobus autobus = new Autobus(getJmeno(),stav.getCenaPaliva(),stav.getCenaOpravy());
        System.out.println("Nazev autobusu: " + getJmeno());
        System.out.println("Cena paliva autobusu: " + stav.getCenaPaliva());
        System.out.println("Cena opravy autobusu: " + stav.getCenaOpravy());
        System.out.println("Zisk autobusu: " + getZisk());
        System.out.println("Stav autobusu: " + getStav());
        System.out.println("Boosty autobusu: " + getBoosty());
        vypisVylepseni();
    }
}