package gui;

import ResourceHandlers.LootDAO;
import dto.Boss;

import java.awt.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

    private JPanel currentPanel;
    private List<Boss> bosses;
    private String user;

    public MainFrame() {
        //here for convenience, probably a better spot to move to later
        bosses = LootDAO.getBosses();
        setPreferredSize(new Dimension(350,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TLOPO Loot Tracker");
        pack();
        setVisible(true);
        createIntroScreen();
        setResizable(false);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        int x = (int) rect.getMinX();
        int y =(int) (getWidth() - getWidth() * .3) ;
        setLocation(x, y);

        setAlwaysOnTop(true);
    }

    public void createIntroScreen() {

        NamePicker namePicker = new NamePicker(this, user, bosses);

        remove(namePicker);

        if(currentPanel != null){
            remove(currentPanel);
            currentPanel.setVisible(false);
        }
        add(namePicker);
        namePicker.setVisible(true);
        setContentPane(namePicker);
        revalidate();
        currentPanel = namePicker;

    }

    public void createSecondGUI(final String user, final Boss boss, final boolean shiftToggle) {

        SecondGUI secondGUI = new SecondGUI(this, user, boss, shiftToggle);

        this.user = user;

        remove(secondGUI);

        if (currentPanel != null) {
            remove(currentPanel);
            currentPanel.setVisible(false);
        }
        add(secondGUI);
        secondGUI.setVisible(true);
        setContentPane(secondGUI);
        revalidate();
        currentPanel = secondGUI;

    }
}
