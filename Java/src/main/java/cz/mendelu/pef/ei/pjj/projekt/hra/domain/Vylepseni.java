package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public class Vylepseni implements Serializable {
    private VylepseniTv tv;
    private VylepseniSedadel sedadla;
    private VylepseniBar bar;
    private VylepseniVybava vybava;
    private VylepseniSocialni socialni;
    private VylepseniPersonal personal;
    private int modifikator;


    public Vylepseni() {
        VylepseniTv televize = new VylepseniTv("Stará televize u řidiče", 30, 3000);
        VylepseniSedadel sedacky = new VylepseniSedadel("Polstrované sedačky.", 60, 50000);
        VylepseniBar piti = new VylepseniBar("Malá lednička se sodovkami.", 40, 5000);
        VylepseniVybava inventar = new VylepseniVybava("Jednorázové klapky na oči a špunty do uší.", 5, 500);
        VylepseniSocialni hygiena = new VylepseniSocialni("Kabinka s toaletami.", 50, 100000);
        VylepseniPersonal osoby = new VylepseniPersonal("Stevard - brigádník ze střední", 20, 6000);
        this.personal = osoby;
        this.socialni = hygiena;
        this.vybava = inventar;
        this.sedadla = sedacky;
        this.bar = piti;
        this.tv = televize;
        this.modifikator = 1;
    }

    public int getCelkovyBonus() {
        return this.tv.getBoost().getBonus() +
                this.bar.getBoost().getBonus() +
                this.sedadla.getBoost().getBonus() +
                this.vybava.getBoost().getBonus() +
                this.socialni.getBoost().getBonus() +
                this.personal.getBoost().getBonus();
    }

    public VylepseniTv getTv() {
        return tv;
    }

    public VylepseniBar getBar() {
        return bar;
    }

    public VylepseniPersonal getPersonal() {
        return personal;
    }

    public VylepseniSedadel getSedadla() {
        return sedadla;
    }

    public VylepseniSocialni getSocialni() {
        return socialni;
    }

    public VylepseniVybava getVybava() {
        return vybava;
    }


}
