package gui;

import Enum.ChestType;
import Enum.Rarity;
import ResourceHandlers.LootMouseListener;
import dto.Loot;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

//second
public class LootEntryGUI extends JPanel {

    private Loot loot;
    private List<LootMouseListener> mouseListeners;
    private ChestType chestType;

    public LootEntryGUI(final MainFrame mainFrame){
        this.mouseListeners = new ArrayList<>();
        this.chestType = ChestType.NONE;
        loot = new Loot();

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
        lootPouch.addActionListener(e -> chestType = ChestType.LOOT_POUCH);
        add(lootPouch, c);

        c.gridx = 0;
        c.gridy = 1;
        lootChest.addActionListener(e -> chestType = ChestType.LOOT_CHEST);
        add(lootChest, c);

        c.gridx = 0;
        c.gridy = 2;
        skullChest.addActionListener(e -> chestType = ChestType.SKULL_CHEST);
        add(skullChest, c);

        c.gridx = 0;
        c.gridy = 3;
        none.addActionListener(e -> chestType = ChestType.NONE);
        add(none, c);

        c.fill = GridBagConstraints.NONE;

        JButton crudeInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        crudeInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 1;
        c.gridy = 0;
        add(crudeInc, c);
        LootMouseListener crudeMouseListener = new LootMouseListener(loot, Rarity.CRUDE);
        mouseListeners.add(crudeMouseListener);
        crudeInc.addMouseListener(crudeMouseListener);

        JButton commonInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        commonInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 2;
        c.gridy = 0;
        add(commonInc, c);
        LootMouseListener commonMouseListener = new LootMouseListener(loot, Rarity.COMMON);
        mouseListeners.add(commonMouseListener);
        commonInc.addMouseListener(commonMouseListener);

        JButton rareInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        rareInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 3;
        c.gridy = 0;
        add(rareInc, c);
        LootMouseListener rareMouseListener = new LootMouseListener(loot, Rarity.RARE);
        mouseListeners.add(rareMouseListener);
        rareInc.addMouseListener(rareMouseListener);

        JButton famedInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        famedInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 1;
        c.gridy = 1;
        add(famedInc,c);
        LootMouseListener famedMouseListener = new LootMouseListener(loot, Rarity.FAMED);
        mouseListeners.add(famedMouseListener);
        famedInc.addMouseListener(famedMouseListener);

        JButton legendaryInc = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        legendaryInc.setPreferredSize(new Dimension(100,35));
        c.gridx = 2;
        c.gridy = 1;
        add(legendaryInc, c);
        LootMouseListener legendaryMouseListener = new LootMouseListener(loot, Rarity.LEGENDARY);
        mouseListeners.add(legendaryMouseListener);
        legendaryInc.addMouseListener(legendaryMouseListener);

        JButton save = new JButton(GUIUtils.scaleImage("resources/potcobutton2.png",100,35));
        save.setPreferredSize(new Dimension(100,35));
        c.gridx = 3;
        c.gridy = 1;
        add(save, c);
        save.addActionListener((ActionEvent e) -> {
            loot.alter(null, 1);
            mainFrame.submitLootEntry(loot, chestType);

        });
    }
}
