package ResourceHandlers;

import dto.Session;
import Enum.ChestType;
import Enum.Rarity;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LootMouseListener implements MouseListener {

    private Session session;
    private ChestType chestType;
    private Rarity rarity;

    public LootMouseListener(Session session, ChestType chestType, Rarity rarity){
       this.session = session;
       this.chestType = chestType;
       this.rarity = rarity;
    }

    public void setChestType(ChestType chestType){
        this.chestType = chestType;
    }


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isLeftMouseButton(e)){
                session.alter(1, chestType, rarity);


            }else if (SwingUtilities.isRightMouseButton(e)){
                session.alter(-1, chestType, rarity);
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

