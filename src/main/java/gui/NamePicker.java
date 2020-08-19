package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NamePicker extends JPanel{

    private final JLabel label, userName;
    private final JTextField textField, textField2;
    private String bossName, user;
    private final MainFrame mainFrame;

    public NamePicker(final MainFrame mainFrame){

        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Icon icon = new ImageIcon("resources/track.PNG");

        userName = new JLabel("Username: ");
        userName.setPreferredSize(new Dimension(100,40));
        constraints.insets = new Insets(5,0,5,0);
        userName.setFont(userName.getFont().deriveFont(16.0f));
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(userName, constraints);

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(100,30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(textField2, constraints);

        label = new JLabel("Boss: ");
        label.setPreferredSize(new Dimension(100,40));
        label.setFont(label.getFont().deriveFont(16.0f));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(label, constraints);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(100,30));
        textField.addActionListener(e -> setBossName(textField.getText(),textField2.getText()));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(textField, constraints);

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(120,45));
        button.addActionListener(e -> setBossName(textField.getText(), textField2.getText()));
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(button, constraints);

    }

    private void setBossName(final String bossName, final String user){
        this.bossName = bossName;
        this.user = user;
        mainFrame.createSecondGUI(bossName, user);
        //ResourceHandlers.FileHandler.updateSave("legendary", "4", bossName);
    }

}
