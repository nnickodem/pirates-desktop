package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import ResourceHandlers.FileHandler;
import ResourceHandlers.MouseListener;
import dto.Boss;
import dto.Loot;
//second
public class LootEntryGUI extends JPanel {

    private MainFrame mainFrame;
    private Loot loot;

    public LootEntryGUI(final MainFrame mainFrame, final String user, final Boss boss, int[] rarity){
        this.mainFrame = this.mainFrame;

        loot = FileHandler.getSave(boss.getName());
        loot.setUser(user);
        loot.setBossName(boss.getName());
        loot.setBossId(boss.getId());

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        ButtonGroup g = new ButtonGroup();
        JRadioButton lootPouch = new JRadioButton("Loot Pouch");
        JRadioButton lootChest = new JRadioButton("Loot Chest");
        JRadioButton skullChest = new JRadioButton("Skull Chest");
        JRadioButton none = new JRadioButton("No chest");
        none.setSelected(true);
        g.add(lootPouch);
        g.add(lootChest);
        g.add(skullChest);
        g.add(none);


        c.insets = new Insets(3,3,3,3);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = .5;
        c.gridx = 0;
        c.gridy = 0;
        add(lootPouch, c);

        c.gridx = 0;
        c.gridy = 1;
        add(lootChest, c);

        c.gridx = 0;
        c.gridy = 2;
        add(skullChest, c);

        c.gridx = 0;
        c.gridy = 3;
        add(none, c);

        c.fill = GridBagConstraints.NONE;

        JButton crudeInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        crudeInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 1;
        c.gridy = 0;
        add(crudeInc, c);
        crudeInc.addActionListener((ActionEvent e) -> {
            rarity[0] = loot.alterCrudeCount(1);
        });
        JButton commonInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        commonInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 2;
        c.gridy = 0;
        add(commonInc, c);
        commonInc.addActionListener((ActionEvent e) -> {
            rarity[1] = loot.alterCommonCount(1);

        });

        JButton rareInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        rareInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 3;
        c.gridy = 0;
        add(rareInc, c);
        rareInc.addActionListener((ActionEvent e) -> {
            rarity[2] = loot.alterRareCount(1);
        });

        JButton famedInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        famedInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 1;
        c.gridy = 1;
        add(famedInc,c);
        famedInc.addActionListener((ActionEvent e) -> {
            rarity[3] = loot.alterFamedCount(1);
        });

        JButton legendaryInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        legendaryInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 2;
        c.gridy = 1;
        add(legendaryInc, c);
        legendaryInc.addActionListener((ActionEvent e) -> {
            rarity[4] = loot.alterLegendaryCount(1);
        });

        JButton save = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        save.setPreferredSize(new Dimension(100,35));
        c.gridx = 3;
        c.gridy = 1;
        add(save, c);
        save.addActionListener((ActionEvent e) -> {
            mainFrame.createLootTrackingGUI(user, boss);

        });


    }


}
