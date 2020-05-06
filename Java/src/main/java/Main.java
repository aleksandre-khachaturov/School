import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;

public class Main {

    public static void main(String[] args) {
        Hrac player = new Hrac();
        System.out.println(player.getJmenoHrace());
        System.out.println(player.getZustatek());
        System.out.println(player.getAutobusy().getPocetAutobusu());
        player.printInfo();
        player.setJmenoHrace("Jozef");
        System.out.println(player.getJmenoHrace());
        System.out.println(player.getAutobusy().getPoleAutobusu().get(0).getStav().getRozbity());
        System.out.println(player.getAutobusy().getPoleAutobusu().get(0).getBoosty().getBar().getNextBoost());


    /*int vyber;
        do {
            System.out.println("  1. Nova hra   2.Konec ");
            System.out.println("Vyberte cislo: ");
            Scanner vyberCislo = new Scanner(System.in);
            vyber = vyberCislo.nextInt();
            if (vyber == 1) {
                switch (vyber) {
                    case 1:
                        System.out.println("Pro zacatek hry napis svoje jmeno: ");
                        Scanner userName = new Scanner(System.in);
                        String login = userName.nextLine();
                        Hra game = new Hra();
                        game.Game(login);
                }
            } else if (vyber == 2) {
                    System.out.println("Ukoncil jste hru. Konec hry");
                }
        }
             while (vyber < 1 || vyber > 2) ;
*/
    }
}