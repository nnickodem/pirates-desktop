package gui;

import ResourceHandlers.LootDAO;
import dto.Boss;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

    private JPanel currentPanel;
    private List<Boss> bosses;

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
        setAlwaysOnTop(true);
    }

    public void createIntroScreen() {

        NamePicker namePicker = new NamePicker(this);

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

    public void createSecondGUI(final String bossName, final String user) {

        SecondGUI secondGUI = new SecondGUI(this, bossName);

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
