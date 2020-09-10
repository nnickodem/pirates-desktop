package gui;

import Enum.ChestType;
import Enum.Rarity;
import ResourceHandlers.LootMouseListener;
import dto.Loot;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

//second
public class LootEntryGUI extends JPanel {

    private Loot loot;
    private List<LootMouseListener> mouseListeners;
    private ChestType chestType;
    private Image backGround;

    public LootEntryGUI(final MainFrame mainFrame){
        this.mouseListeners = new ArrayList<>();
        this.chestType = ChestType.NONE;
        loot = new Loot();

        ImageIcon obj = (GUIUtils.scaleImage("resources/treasure-chest.png",600,250));
        backGround = obj.getImage();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        updateChestType();

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
        lootPouch.addActionListener(e -> {
            chestType = ChestType.LOOT_POUCH;
            updateChestType();
        });
        lootPouch.setOpaque(false);
        lootPouch.setForeground(mainFrame.color);
        lootPouch.setFont(lootPouch.getFont().deriveFont(24.0f));
        add(lootPouch, c);

        c.gridx = 0;
        c.gridy = 1;
        lootChest.addActionListener(e -> {
            chestType = ChestType.LOOT_CHEST;
            updateChestType();
        });
        lootChest.setOpaque(false);
        lootChest.setForeground(mainFrame.color);
        lootChest.setFont(lootPouch.getFont().deriveFont(24.0f));
        add(lootChest, c);

        c.gridx = 0;
        c.gridy = 2;
        skullChest.addActionListener(e -> {
            chestType = ChestType.SKULL_CHEST;
            updateChestType();
        });
        skullChest.setOpaque(false);
        skullChest.setForeground(mainFrame.color);
        skullChest.setFont(lootPouch.getFont().deriveFont(24.0f));
        add(skullChest, c);

        c.gridx = 0;
        c.gridy = 3;
        none.addActionListener(e -> {
            chestType = ChestType.NONE;
            updateChestType();
        });
        none.setOpaque(false);
        none.setForeground(mainFrame.color);
        none.setFont(lootPouch.getFont().deriveFont(24.0f));
        add(none, c);

        c.fill = GridBagConstraints.NONE;

        JButton crudeInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        crudeInc.setPreferredSize(new Dimension(100,35));
        crudeInc.setText("Crude: " + loot.getVariable(Rarity.CRUDE));
        crudeInc.setHorizontalTextPosition(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 0;
        add(crudeInc, c);
        LootMouseListener crudeMouseListener = new LootMouseListener(loot, Rarity.CRUDE, crudeInc);
        mouseListeners.add(crudeMouseListener);
        crudeInc.addMouseListener(crudeMouseListener);

        JButton commonInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        commonInc.setPreferredSize(new Dimension(100,35));
        commonInc.setText("Common: " + loot.getVariable(Rarity.COMMON));
        commonInc.setHorizontalTextPosition(SwingConstants.CENTER);
        c.gridx = 2;
        c.gridy = 0;
        add(commonInc, c);
        LootMouseListener commonMouseListener = new LootMouseListener(loot, Rarity.COMMON, commonInc);
        mouseListeners.add(commonMouseListener);
        commonInc.addMouseListener(commonMouseListener);

        JButton rareInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        rareInc.setPreferredSize(new Dimension(100,35));
        rareInc.setText("Rare: " + loot.getVariable(Rarity.RARE));
        rareInc.setHorizontalTextPosition(SwingConstants.CENTER);
        c.gridx = 3;
        c.gridy = 0;
        add(rareInc, c);
        LootMouseListener rareMouseListener = new LootMouseListener(loot, Rarity.RARE, rareInc);
        mouseListeners.add(rareMouseListener);
        rareInc.addMouseListener(rareMouseListener);

        JButton famedInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        famedInc.setPreferredSize(new Dimension(100,35));
        famedInc.setText("Famed: " + loot.getVariable(Rarity.FAMED));
        famedInc.setHorizontalTextPosition(SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 2;
        add(famedInc,c);
        LootMouseListener famedMouseListener = new LootMouseListener(loot, Rarity.FAMED, famedInc);
        mouseListeners.add(famedMouseListener);
        famedInc.addMouseListener(famedMouseListener);

        JButton legendaryInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        legendaryInc.setPreferredSize(new Dimension(100,35));
        legendaryInc.setText("Legendary: " + loot.getVariable(Rarity.LEGENDARY));
        legendaryInc.setFont(new Font("Comic Sans",Font.PLAIN, 11));
        legendaryInc.setHorizontalTextPosition(SwingConstants.CENTER);
        c.gridx = 2;
        c.gridy = 2;
        add(legendaryInc, c);
        LootMouseListener legendaryMouseListener = new LootMouseListener(loot, Rarity.LEGENDARY, legendaryInc);
        mouseListeners.add(legendaryMouseListener);
        legendaryInc.addMouseListener(legendaryMouseListener);

        JButton save = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        save.setPreferredSize(new Dimension(100,35));
        c.gridx = 3;
        c.gridy = 2;
        add(save, c);
        save.addActionListener((ActionEvent e) -> {
            loot.alter(null, 1);
            mainFrame.submitLootEntry(loot, chestType);

        });
    }

    public void updateChestType() {
        if (chestType.equals(ChestType.NONE)) {
            mouseListeners.forEach(m -> m.setEnabled(false));
        } else {
            mouseListeners.forEach(m -> m.setEnabled(true));
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, null);
    }
}
