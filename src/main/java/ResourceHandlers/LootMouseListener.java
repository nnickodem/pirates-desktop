package ResourceHandlers;

import Enum.Rarity;
import dto.Loot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class LootMouseListener implements MouseListener {

    private Loot loot;
    private Rarity rarity;
    private JButton button;
    private boolean enabled;

    public LootMouseListener(final Loot loot, Rarity rarity, JButton button){
       this.loot = loot;
       this.rarity = rarity;
       this.button = button;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (enabled) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                loot.alter(rarity, 1);
            } else if (SwingUtilities.isRightMouseButton(e)) {
                loot.alter(rarity, -1);
            }
            button.setText(rarity.getName() + ": " + loot.getVariable(rarity));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}

