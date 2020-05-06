package cz.mendelu.pef.ei.pjj.greenfoot.game;

import cz.mendelu.pef.ei.pjj.greenfoot.MyWorld;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hra;
import cz.mendelu.pef.ei.pjj.projekt.hra.domain.Hrac;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class IOForm extends Actor {
    private JTextField loadField;
    private JButton loadButton;
    private JTextField saveField;
    private JButton saveButton;
    private JLabel sourceName;
    private JTextArea vypis;
    public JPanel ioPanel;
    private JButton clearButton;
    private JFrame ioframe;
    private IOForm ioform;
    private Hrac player;

    // SAVE and LOAD

    File file = new File("save.bin");

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            this.ioframe = new JFrame();
            //----------------------------
            this.ioform = new IOForm(player);
            //---------------------------
            this.ioframe.setContentPane(this.ioform.ioPanel);
            this.ioframe.pack();
            //ioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.ioframe.setVisible(true);
            this.ioframe.setTitle("Load/Save");
        }
    }

    public IOForm(Hrac player) {
        this.player = player;
        setImage(new GreenfootImage(" Load/Save ", 26, Color.WHITE, Color.BLACK));

        loadButton.addActionListener(e -> clickOnLoadButton(e));
        saveButton.addActionListener(e -> clickOnSaveButton(e));
        clearButton.addActionListener(e -> clickOnClearButton(e));
    }


    //SAVE BUTTON
    private void clickOnSaveButton(ActionEvent e) {

        String fileName = saveField.getText();
        System.out.println("Před: " + player.getZustatek());
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(player);
            System.out.println("Po: " + player.getZustatek());
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
            /*oos.writeUTF(hrac.getJmenoHrace());
            oos.writeInt(hrac.getZustatek());
            oos.writeUTF(car.getJmeno());
            oos.writeInt(car.getZisk());
            oos.writeUTF(car.getStav().toString());*/


        //  oos.writeInt(car.getBoosty());

    }

    //LOAD BUTTON

    private void clickOnLoadButton(ActionEvent e) {
        // ~~~Nacteni souboru

        if (!file.exists()) {
            loadField.setText("FILE NOT FOUND!");
            return;
        }


        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Před: " + player.getZustatek());
            Hrac player = (Hrac) ois.readObject();
            System.out.println("Po: " + player.getZustatek());
            Hra game = new Hra(player);
            MyWorld myWorld = new MyWorld(game);
            Greenfoot.setWorld(myWorld);
            Greenfoot.start();
            HraOknoFrame hraFrame = new HraOknoFrame(player);
            hraFrame.setVisible(true);
            hraFrame.setTitle("Hra | Autobusovy Management");

            System.out.println(myWorld.getGame().getPlayer().getJmenoHrace());
            System.out.println(myWorld.getGame().getPlayer().getZustatek());

        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }

    //CLEAR button

    private void clickOnClearButton(ActionEvent e) {
        vypis.setText(null);
    }
}