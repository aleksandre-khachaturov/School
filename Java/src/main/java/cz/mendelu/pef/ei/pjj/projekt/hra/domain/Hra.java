package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

//tohle je fasáda pro logiku hry přes tuhle třídu se bude vše řídit
public class Hra {
    private Hrac player;

    public Hra() {
        this.player = new Hrac();
        //System.out.println(this.player.getAutobusy().getPoleAutobusu().get(0).getBoosty().getTv().getNextBoost().getCena());
    }

    public Hra(Hrac player) {
        this.player = player;
    }

    public Hrac getPlayer() {
        return player;
    }

    /*public void vyslatAutobus(Autobus autobus){
        if (autobus.vyjed()==0) {
            Vlakno vlakno = new Vlakno(10,autobus,player);
            Thread t1 = new Thread(vlakno, "Vyslany");
            t1.start();
        }
        else {
            System.out.println("Chyba " + autobus.vyjed());
        }

    }*/

}
