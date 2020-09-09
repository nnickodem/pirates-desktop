package ResourceHandlers;

import Enum.Rarity;
import dto.Loot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;

public class LootMouseListener implements MouseListener {

    private Loot loot;
    private Rarity rarity;

    public LootMouseListener(final Loot loot, Rarity rarity){
       this.loot = loot;
       this.rarity = rarity;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)){
            loot.alter(rarity, 1);


        }else if (SwingUtilities.isRightMouseButton(e)){
            loot.alter(rarity, -1);
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
}

