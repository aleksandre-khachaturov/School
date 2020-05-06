package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public class VylepseniLevel implements Serializable {
    private String popis;
    private int bonus;
    private int cena;

    VylepseniLevel(String popis, int bonus, int cena) {
        this.bonus = bonus;
        this.cena = cena;
        this.popis = popis;
    }

    VylepseniLevel() {
        this.bonus = 0;
        this.cena = 0;
        this.popis = "";
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
}


