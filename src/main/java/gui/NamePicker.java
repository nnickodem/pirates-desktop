package gui;

import dto.Boss;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

public class NamePicker extends JPanel{

    private final JLabel label, userName, bossError;
    private final JTextField bossField, userField;
    private final MainFrame mainFrame;
    private final List<Boss> bosses;
    private boolean shiftToggle = true;

    public NamePicker(final MainFrame mainFrame, final String user, final List<Boss> bosses){
        this.bosses = bosses;
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

        userField = new JTextField();
        userField.setPreferredSize(new Dimension(100,30));
        if (StringUtils.isNotBlank(user)){
            userField.setText(user);
            userField.setEditable(false);
        }
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(userField, constraints);

        label = new JLabel("Boss: ");
        label.setPreferredSize(new Dimension(100,40));
        label.setFont(label.getFont().deriveFont(16.0f));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(label, constraints);

        bossField = new JTextField();
        bossField.setPreferredSize(new Dimension(100,30));
        bossField.addActionListener(e -> setBossName(bossField.getText(),userField.getText()));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(bossField, constraints);

        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(120,45));
        button.addActionListener(e -> setBossName(bossField.getText(), userField.getText()));
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(button, constraints);

        bossError = new JLabel("Does not match boss list");
        bossError.setVisible(false);
        bossError.setFont(new Font("Didot", Font.BOLD, 22));
        bossError.setForeground(Color.red);
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        add(bossError, constraints);

        ButtonGroup group = new ButtonGroup();
        JRadioButton noShift = new JRadioButton("Do not use shift/alt to alter killcount");
        JRadioButton yesShift = new JRadioButton("Use shift/alt");
        group.add(noShift);
        group.add(yesShift);

        noShift.addActionListener(e -> shiftToggle = false);
        noShift.setSelected(true);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(noShift, constraints);

        yesShift.addActionListener(e -> shiftToggle = true);
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(yesShift, constraints);

    }

    private void setBossName(final String bossName, final String user){
        Optional<Boss> boss = bosses.stream().filter(b -> b.getName().equalsIgnoreCase(bossName)).findAny();
        if (boss.isPresent()){
            mainFrame.createSecondGUI(user, boss.get(), shiftToggle);
        }else{
            bossError.setVisible(true);
        }
    }

}
