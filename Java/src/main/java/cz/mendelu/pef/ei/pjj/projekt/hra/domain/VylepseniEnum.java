package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public enum VylepseniEnum implements Serializable {
    VYLEPSENITV("Stará televize u řidiče", 30, 3000),
    VYLEPSENISEDADEL("Polstrované sedačky.", 60, 50000),
    VYLEPSENIBAR("Malá lednička se sodovkami.", 40, 5000),
    VYLEPSENIVYBAVA("Jednorázové klapky na oči a špunty do uší.", 5, 500),
    VYLEPSENISOCIALNI("Kabinka s toaletami.", 50, 100000),
    VYLEPSENIPERSONAL("Stevard - brigádník ze střední", 20, 6000);

    private String popis;
    private int bonus;
    private int cena;

    VylepseniEnum(String popis, int bonus, int cena) {
        this.popis = popis;
        this.bonus = bonus;
        this.cena = cena;
    }

    public String getPopis() {
        return popis;
    }

    public int getBonus() {
        return bonus;
    }

    public int getCena() {
        return cena;
    }

    @Override
    public String toString() {
        return "Vylepseni{" +
                "Popis ='" + popis + '\'' +
                ", Bonus=" + bonus +
                ", Cena='" + cena + '\'' +
                '}';
    }
}
