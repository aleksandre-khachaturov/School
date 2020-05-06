package cz.mendelu.pef.ei.pjj.greenfoot;

import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import javax.swing.*;
import java.awt.*;

public class Info extends Actor {
    public Info() {
        setImage(new GreenfootImage("  INFO o hre:  ", 26, Color.WHITE, Color.BLACK));
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            JOptionPane.showMessageDialog(new JInternalFrame(),
                    "Hra je vytvorena jako semestralni projekt pro predmet programovaci jazyk JAVA (PPJ). \n" +
                            "Nová autobusová firma, která nabízí cestujícím dopravu do různých měst, chce zůstat nejlepší firmou na trhu. \n Kvůli velké konkurence musí dělat maximum pro to: poskytovat nejlepší servis a uspokojovat co nejvíce zákazníků.\n" +
                            "Na začátku firma má jenom jeden autobus. Při každém příjezdu autobus má kontrolu stavu. Autobus může se vrátit rozbity, někdy bude potřeba doplnit benzin, někdy vyměnit sedadla...\n" +
                            "Kvůli tomu, že poptávka na trhu začala růst, vzniklo hodně autobusových firem, které nabízí různé varianty dopravních služeb. \n Pro to aby naši autobusová firma zůstala na trhu, budeme ve hře vylepšovat autobus \n a nabízet domácnosti autobusové dopravní služby s více moderními doplňky.  \n Například je možné dokoupit a nainstalovat v autobusu TV, vyměnit sedadla na mnohem luxusnější, přidat bar ke každému sedadlu, vylepšit personál...\n" +
                            "Hlavním cílem hráče je vydělat co nejvíc peněz. Když počet cestujících začne klesat, hráč bude muset nějak reagovat na tuto situace a například vylepšit autobus, \n provést modernizace a přilákat nové cestující. Ceny budou natolik optimální, že nové zákazníky budou používat více a více naši autobusy, což vyvolá růst příjmu.\n \n \n ________________________________________________________________ \n \nProjekt pripravili Zdeněk Illek a Aleksandre Khachaturov.",
                    "HRA | Autobusovy management ",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
