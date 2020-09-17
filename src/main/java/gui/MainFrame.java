package gui;

import ResourceHandlers.FileHandler;
import ResourceHandlers.LootDAO;
import dto.Boss;
import dto.Loot;
import dto.Session;
import Enum.ChestType;

import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;

public class MainFrame extends JFrame{

    private JPanel currentPanel;
    private List<Boss> bosses;
    private String user;
    public Color color = new Color(0,0,0);
    public Color color1 = new Color(128,128,0);
    public static Color tan = new Color(255,229,178);
	private Session session;

    public MainFrame() {
        setIconImage(GUIUtils.scaleImage("resources/yawn.PNG", 10000, 10000).getImage());
        bosses = LootDAO.getBosses();
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TLOPO Loot Tracker");
        //setUndecorated(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        createIntroScreen();
        setResizable(false);
    }

    public void createIntroScreen() {

        NamePicker namePicker = new NamePicker(this, FileHandler.getUserInfo(), bosses);

        remove(namePicker);

        if(currentPanel != null){
            remove(currentPanel);
            currentPanel.setVisible(false);
        }
        setPreferredSize(new Dimension(800,700));
        pack();
        add(namePicker);
        namePicker.setVisible(true);
        setContentPane(namePicker);
        revalidate();
        currentPanel = namePicker;

    }

    public void createLootTrackingGUI(final Session session){
        this.session = session;

        FileHandler.saveUserInfo(session.getUser());

        LootTrackingGUI lootTrackingGUI = new LootTrackingGUI(this, session);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) (rect.getMaxX()-600) / 2;
        int y =(int) (rect.getMinY()) ;
        setLocation(x, y);
        setAlwaysOnTop(true);
        setPreferredSize(new Dimension(600,100));
        pack();
        remove(lootTrackingGUI);

        if (currentPanel != null) {
            remove(currentPanel);
            currentPanel.setVisible(false);
        }
        add(lootTrackingGUI);
        lootTrackingGUI.setVisible(true);
        setContentPane(lootTrackingGUI);
        revalidate();
        currentPanel = lootTrackingGUI;
    }

    public void createLootEntryGUI() {

        LootEntryGUI createLootEntryGUI = new LootEntryGUI(this);

        setPreferredSize(new Dimension(600,250));
        pack();

        remove(createLootEntryGUI);

        if (currentPanel != null) {
            remove(currentPanel);
            currentPanel.setVisible(false);
        }
        add(createLootEntryGUI);
        createLootEntryGUI.setVisible(true);
        setContentPane(createLootEntryGUI);
        revalidate();
        currentPanel = createLootEntryGUI;
    }

    public void submitLootEntry(final Loot loot, final ChestType chestType) {
        session.mergeLoot(loot, chestType);
        createLootTrackingGUI(session);
    }
}
