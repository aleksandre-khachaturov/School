package cz.mendelu.pef.ei.pjj.greenfoot;

import cz.mendelu.pef.ei.pjj.greenfoot.game.IOForm;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hra;
import greenfoot.GreenfootImage;
import greenfoot.World;

public class MyWorld extends World {
    private Hra game;

    public MyWorld() {
        super(600, 400, 1);

        GreenfootImage bg = new GreenfootImage("images/bg.gif");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        game = new Hra();


        addObject(new AddName(game.getPlayer()), 400, 150);
        addObject(new Info(), 365, 200);
        addObject(new Exit(), 473, 200);
        addObject(new SettingsGFButton(game.getPlayer()), 443, 250);
        addObject(new IOForm(game.getPlayer()), 423, 295);
    }

    public MyWorld(Hra game) {
        super(600, 400, 1);

        GreenfootImage bg = new GreenfootImage("images/bg.gif");
        bg.scale(getWidth(), getHeight());
        setBackground(bg);

        this.game = game;


        addObject(new AddName(game.getPlayer()), 400, 150);
        addObject(new Info(), 365, 200);
        addObject(new Exit(), 473, 200);
        addObject(new SettingsGFButton(game.getPlayer()), 443, 250);
        addObject(new IOForm(game.getPlayer()), 423, 295);
    }

    public void setGame(Hra game) {
        this.game = game;
    }

    public Hra getGame() {
        return game;
    }
}
