package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import java.io.Serializable;

public class VylepseniPersonal implements Serializable {
    private VylepseniLevel boost;
    private VylepseniLevel nextBoost;
    private int level;

    public VylepseniPersonal(String popis, int bonus, int cena) {
        VylepseniLevel perlvl0 = new VylepseniLevel();
        this.boost = perlvl0;
        this.nextBoost = buildVylepseni(popis, bonus, cena);
        this.level = 0;
    }

    public VylepseniLevel buildVylepseni(String popis, int bonus, int cena) {
        VylepseniLevel perlv1 = new VylepseniLevel(popis, bonus, cena);
        return perlv1;
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

    public VylepseniLevel getNextBoost() {
        return nextBoost;
    }

    public int getLevel() {
        return level;
    }

    public VylepseniLevel getBoost() {
        return boost;
    }

    public String getNazev() {
        return "Personal";
    }
}