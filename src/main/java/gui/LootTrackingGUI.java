package gui;

import ResourceHandlers.FileHandler;
import ResourceHandlers.LootDAO;
import dto.Boss;
import dto.Loot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

//first
public class LootTrackingGUI extends JPanel{
    private Loot loot;
    private MainFrame mainFrame;
    public LootTrackingGUI(final MainFrame mainFrame, final String user, final Boss boss){
        this.mainFrame = mainFrame;

        mainFrame.setTitle("Loot Tracker - " + boss.getName());

        loot = FileHandler.getSave(boss.getName());
        loot.setUser(user);
        loot.setBossName(boss.getName());
        loot.setBossId(boss.getId());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        int[] rarity = {loot.getCrudeCount(),loot.getCommonCount(), loot.getRareCount(), loot.getFamedCount(), loot.getLegendaryCount()};

        String lootNum = "";
        for (int i = 0; i < rarity.length; ++i){
            if (i < 4){
                lootNum += rarity[i] + " / ";
            }else{
                lootNum += rarity[i];
            }
        }
        String[] arrLootNum = lootNum.split("/ ");

        String loots = "<html><font color='olive'>" + arrLootNum[0] + "<font color='black'>" + "/ "  + "<font color='orange'>" + arrLootNum[1] + "<font color='black'>" + "/ " +
                "<font color='green'>" + arrLootNum[2] + "<font color='black'>" + "/ " +
                "<font color='blue'>" + arrLootNum[3] + "<font color='black'>" + "/ " + "<font color='red'>" + arrLootNum[4] + "</font></html>";

        JLabel killDisplay = new JLabel("Kill Count - " + loot.getKillCount());
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
            loot.alterKillCount(1);
            mainFrame.createLootEntryGUI(user, boss, rarity);
        });
        c.gridx = 3;
        c.gridy = 0;
        add(killButton, c);

        JButton submit = new JButton("submit");
        submit.setPreferredSize(new Dimension(100,35));
        submit.addActionListener(e ->{
            boolean addedLoot = LootDAO.addLoot(loot);
            if(addedLoot) {
                FileHandler.deleteXML(boss.getName());
                mainFrame.createIntroScreen();
            }
        });
        c.gridx = 4;
        c.gridy = 0;
        add(submit, c);

    }


}
