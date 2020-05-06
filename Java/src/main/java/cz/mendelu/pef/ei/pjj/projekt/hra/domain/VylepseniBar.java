package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public class VylepseniBar implements Serializable {
    private VylepseniLevel boost;
    private VylepseniLevel nextBoost;
    private int level;

    public VylepseniBar(String popis, int bonus, int cena) {
        VylepseniLevel barlvl0 = new VylepseniLevel();
        this.boost = barlvl0;
        this.nextBoost = buildVylepseni(popis, bonus, cena);
        this.level = 0;
    }

    public VylepseniLevel buildVylepseni(String popis, int bonus, int cena) {
        VylepseniLevel barlv1 = new VylepseniLevel(popis, bonus, cena);
        return barlv1;
    }

    public void upgrade(String popis, int bonus, int cena, Hrac player) {
        if (this.level < 3 && player.getZustatek() >= this.boost.getCena()) {
            /*sniz penize hrace*/
            player.setZustatek(player.getZustatek() - this.boost.getCena());
            this.level++;
            this.boost = this.nextBoost;
            this.nextBoost = buildVylepseni(popis, bonus, cena);
        }
    }

    public int getLevel() {
        return level;
    }

    public VylepseniLevel getBoost() {
        return boost;
    }

    public VylepseniLevel getNextBoost() {
        return nextBoost;
    }

    public String getNazev() {
        return "Bar";
    }
}