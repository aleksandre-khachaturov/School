package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public class VylepseniTv implements Serializable {
    private VylepseniLevel boost;
    private VylepseniLevel nextBoost;
    private int level;

    public VylepseniTv(String popis, int bonus, int cena) {
        VylepseniLevel tvlvl0 = new VylepseniLevel();
        this.boost = tvlvl0;
        this.nextBoost = buildVylepseni(popis, bonus, cena);
        this.level = 0;
    }

    public VylepseniLevel buildVylepseni(String popis, int bonus, int cena) {
        VylepseniLevel tvlv1 = new VylepseniLevel(popis, bonus, cena);
        return tvlv1;
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
        return "TV";
    }
}
