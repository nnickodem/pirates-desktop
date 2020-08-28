package gui;
import javax.swing.JPanel;

//switch this with gui.NamePicker in gui.MainFrame for other GUI

public class SecondGUI extends JPanel {
/*
    private JLabel crudeLabel, commonLabel, rareLabel, famedLabel, legendaryLabel, kcLabel, killsText;
    private MainFrame mainFrame;
    private Loot loot;

    public SecondGUI(final MainFrame mainFrame, final String user, final Boss boss, final boolean shiftToggle) {

        if (shiftToggle){
            try {
                GlobalScreen.registerNativeHook();
            } catch (Exception e) {
                System.out.println("failed to register hook");
            }
            GlobalScreen.addNativeKeyListener(new ShiftListener(this));
        }

        this.mainFrame = mainFrame;

        mainFrame.setTitle("Loot Tracker - " + boss.getName());

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        mainFrame.setFocusableWindowState(false);

        Dimension dimension = new Dimension(90, 35);

        JButton crudeInc = new JButton("Crude");
        crudeInc.setPreferredSize(dimension);
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(5,3,5,3);
        add(crudeInc, constraints);
        crudeInc.addActionListener((ActionEvent e) -> {
            crudeLabel.setText(String.valueOf(loot.alterCrudeCount(1)));
        });

        JButton commonInc = new JButton("Common");
        commonInc.setPreferredSize(dimension);
        constraints.gridx = 2;
        constraints.gridy = 3;
        add(commonInc, constraints);
        commonInc.addActionListener((ActionEvent e) -> {
            commonLabel.setText(String.valueOf(loot.alterCommonCount(1)));
        });

        JButton rareInc = new JButton("Rare");
        rareInc.setPreferredSize(dimension);
        constraints.gridx = 2;
        constraints.gridy = 4;
        add(rareInc, constraints);
        rareInc.addActionListener((ActionEvent e) -> {
            rareLabel.setText(String.valueOf(loot.alterRareCount(1)));
        });

        JButton famedInc = new JButton("Famed");
        famedInc.setPreferredSize(dimension);
        constraints.gridx = 2;
        constraints.gridy = 5;
        add(famedInc,constraints);
        famedInc.addActionListener((ActionEvent e) -> {
            famedLabel.setText(String.valueOf(loot.alterFamedCount(1)));
        });

        JButton legendaryInc = new JButton("Legendary");
        legendaryInc.setPreferredSize(dimension);
        constraints.gridx = 2;
        constraints.gridy = 6;
        add(legendaryInc, constraints);
        legendaryInc.addActionListener((ActionEvent e) -> {
            legendaryLabel.setText(String.valueOf(loot.alterLegendaryCount(1)));
        });

        JButton kcDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 1;
        kcDec.setPreferredSize(new Dimension(80,30));
        add(kcDec, constraints);
        kcDec.addActionListener((ActionEvent e) -> {
            kcLabel.setText(String.valueOf(loot.alterKillCount(-1)));
        });

        JButton kcInc = new JButton("+");
        constraints.gridx = 2;
        constraints.gridy = 1;
        kcInc.setPreferredSize(new Dimension(85,30));
        add(kcInc, constraints);
        kcInc.addActionListener((ActionEvent e) -> {
            kcLabel.setText(String.valueOf(loot.alterKillCount(1)));
        });
        
        JButton backButton = new JButton("Back");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(backButton, constraints);
        backButton.addActionListener((ActionEvent e) -> {
            mainFrame.setFocusableWindowState(true);
            mainFrame.createIntroScreen();
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException nativeHookException) {
                nativeHookException.printStackTrace();
            }
        });

        JButton crudeDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(crudeDec, constraints);
        crudeDec.addActionListener((ActionEvent e) -> {
            crudeLabel.setText(String.valueOf(loot.alterCrudeCount(-1)));
        });

        JButton commonDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(commonDec, constraints);
        commonDec.addActionListener((ActionEvent e) -> {
            commonLabel.setText(String.valueOf(loot.alterCommonCount(-1)));
        });

        JButton rareDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(rareDec, constraints);
        rareDec.addActionListener((ActionEvent e) -> {
            rareLabel.setText(String.valueOf(loot.alterRareCount(-1)));
        });

        JButton famedDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(famedDec, constraints);
        famedDec.addActionListener((ActionEvent e) -> {
            famedLabel.setText(String.valueOf(loot.alterFamedCount(-1)));
        });

        JButton legendaryDec = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(legendaryDec, constraints);
        legendaryDec.addActionListener((ActionEvent e) -> {
            legendaryLabel.setText(String.valueOf(loot.alterLegendaryCount(-1)));
        });

        JButton saveButton = new JButton("Save");
        constraints.gridx = 2;
        constraints.gridy = 0;
        add(saveButton, constraints);
        saveButton.addActionListener((ActionEvent e) -> {
            boolean addedLoot = LootDAO.addLoot(loot);
            if(addedLoot) {
                FileHandler.deleteXML(boss.getName());
                mainFrame.createIntroScreen();
            }
        });

        killsText = new JLabel("Kills");
        killsText.setPreferredSize(dimension);
        killsText.setHorizontalAlignment(JLabel.CENTER);
        killsText.setFont(new Font("Helvetica", Font.BOLD, 36));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(killsText, constraints);

        crudeLabel = new JLabel();
        crudeLabel.setPreferredSize(dimension);
        crudeLabel.setHorizontalAlignment(JLabel.CENTER);
        crudeLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        crudeLabel.setText(String.valueOf(loot.getCrudeCount()));
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(crudeLabel, constraints);

        commonLabel = new JLabel();
        commonLabel.setPreferredSize(dimension);
        commonLabel.setHorizontalAlignment(JLabel.CENTER);
        commonLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        commonLabel.setText(String.valueOf(loot.getCommonCount()));
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(commonLabel, constraints);

        rareLabel = new JLabel();
        rareLabel.setPreferredSize(dimension);
        rareLabel.setHorizontalAlignment(JLabel.CENTER);
        rareLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        rareLabel.setText(String.valueOf(loot.getRareCount()));
        constraints.gridx = 1;
        constraints.gridy = 4;
        add(rareLabel, constraints);

        famedLabel = new JLabel();
        famedLabel.setPreferredSize(dimension);
        famedLabel.setHorizontalAlignment(JLabel.CENTER);
        famedLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        famedLabel.setText(String.valueOf(loot.getFamedCount()));
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(famedLabel, constraints);

        legendaryLabel = new JLabel();
        legendaryLabel.setPreferredSize(dimension);
        legendaryLabel.setHorizontalAlignment(JLabel.CENTER);
        legendaryLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        legendaryLabel.setText(String.valueOf(loot.getLegendaryCount()));
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(legendaryLabel, constraints);

        kcLabel = new JLabel(String.valueOf(loot.getKillCount()));
        kcLabel.setPreferredSize(dimension);
        kcLabel.setHorizontalAlignment(JLabel.CENTER);
        kcLabel.setFont(crudeLabel.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(kcLabel, constraints);
    }

    public void killIncrement() {
        kcLabel.setText(String.valueOf(loot.alterKillCount(1)));
    }
    public void killDecrement(){
        kcLabel.setText(String.valueOf(loot.alterKillCount(-1)));
    }

 */
}
