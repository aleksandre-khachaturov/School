package cz.mendelu.pef.ei.pjj.projekt.hra.domain;

import cz.mendelu.pef.ei.pjj.greenfoot.game.HraOknoNew;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Timer.Vlakno;

import java.io.Serializable;

public class Hrac implements Serializable {
    private String jmenoHrace;
    private int zustatek;
    private Autobusy autobusy;

    public Hrac() {
        this.jmenoHrace = "Hrac1";
        this.zustatek = 10000;
        this.autobusy = new Autobusy();
    }


    public String getJmenoHrace() {
        return jmenoHrace;
    }

    public void setJmenoHrace(String jmenoHrace) {
        this.jmenoHrace = jmenoHrace;
    }

    public int getZustatek() {
        return zustatek;
    }

    public Autobusy getAutobusy() {
        return autobusy;
    }

    public void printInfo() {
        System.out.println("Tady bude info o Hrace...");
    }

    public void setZustatek(int zustatek) {
        this.zustatek = zustatek;
    }

    public void vyslatAutobus(Autobus autobus, HraOknoNew okno) {
        if (autobus.vyjed() == 0) {
            Vlakno vlakno = new Vlakno(10, autobus, this, okno);
            Thread t1 = new Thread(vlakno, "Vyslany");
            t1.start();
        } else {
            System.out.println("Chyba " + autobus.vyjed());
        }

    }

}
