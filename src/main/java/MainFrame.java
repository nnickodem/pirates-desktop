import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame{

    private JPanel currentPanel;

    public MainFrame(){

        setPreferredSize(new Dimension(350,400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("TLOPO Loot Tracker");
        pack();
        setVisible(true);
        createIntroScreen();
        setResizable(false);
        setAlwaysOnTop(true);

    }

    public void createIntroScreen(){

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

    public void createSecondGUI(final String bossName) {

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
