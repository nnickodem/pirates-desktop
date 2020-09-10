package gui;

import Enum.Rarity;
import ResourceHandlers.FileHandler;
import ResourceHandlers.LootDAO;
import dto.Session;

import java.awt.*;
import javax.swing.*;

//first
public class LootTrackingGUI extends JPanel{
    private Image backGround;

    public LootTrackingGUI(final MainFrame mainFrame, final Session session){
        mainFrame.setTitle("Loot Tracker - " + session.getBoss().getName());

        ImageIcon obj = (GUIUtils.scaleImage("resources/cool-background2.png",600,90));
        backGround = obj.getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int[] rarity = {session.getTotalCount(Rarity.CRUDE), session.getTotalCount(Rarity.COMMON),session.getTotalCount(Rarity.RARE),
                session.getTotalCount(Rarity.FAMED),session.getTotalCount(Rarity.LEGENDARY)};

        String lootNum = "";
        for (int i = 0; i < rarity.length; ++i){
            if (i < rarity.length-1){
                lootNum += rarity[i] + " / ";
            }else{
                lootNum += rarity[i];
            }
        }
        String[] arrLootNum = lootNum.split("/ ");

        String loots = "<html><font color='olive'>" + arrLootNum[0] + "<font color='black'>" + "/ "  + "<font color='orange'>" + arrLootNum[1] + "<font color='black'>" + "/ " +
                "<font color='green'>" + arrLootNum[2] + "<font color='black'>" + "/ " +
                "<font color='blue'>" + arrLootNum[3] + "<font color='black'>" + "/ " + "<font color='red'>" + arrLootNum[4] + "</font></html>";

        JLabel killDisplay = new JLabel("Kill Count: " + session.getTotalCount(null));
        killDisplay.setFont(new Font("Century Gothic", Font.BOLD ,18));
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(killDisplay, c);

        JLabel displayLoot = new JLabel(loots);
        displayLoot.setFont(new Font("Century Gothic", Font.BOLD ,18));
        displayLoot.setPreferredSize(new Dimension(160,50));
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        add(displayLoot, c);

        JButton killButton = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        killButton.setPreferredSize(new Dimension(100,35));
        killButton.addActionListener(e -> {
            mainFrame.createLootEntryGUI();
        });
        c.gridx = 3;
        c.gridy = 0;
        add(killButton, c);

        JButton submit = new JButton(GUIUtils.scaleImage("resources/submit.PNG",100,35));
        submit.setPreferredSize(new Dimension(100,35));
        submit.addActionListener(e ->{
            boolean addedLoot = LootDAO.addLoot(session);
            if(addedLoot) {
                FileHandler.deleteXML(session.getBoss().getName());
                mainFrame.createIntroScreen();
            }
        });
        c.gridx = 4;
        c.gridy = 0;
        add(submit, c);

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, null);
    }




}
