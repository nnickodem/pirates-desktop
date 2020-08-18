import org.jnativehook.GlobalScreen;
import org.jnativehook.mouse.NativeMouseListener;
import org.jnativehook.mouse.NativeMouseMotionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SecondGUI extends JPanel {

    private JLabel label, label2, label3, label4, label5, label6, label7;
    private MainFrame mainFrame;
    public int f = 0;
    public int l = 0;
    public int cr = 0;
    public int co = 0;
    public int r = 0;
    private int kc = 0;

    //TODO for loops

    public SecondGUI(final MainFrame mainFrame, final String bossName) {

        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            System.out.println("failed to register hook");
        }
        GlobalScreen.addNativeKeyListener(new ShiftListener(this));


        this.mainFrame = mainFrame;

        mainFrame.setTitle("Loot Tracker - " + bossName);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        Dimension dimension = new Dimension(100, 35);

        JButton but1 = new JButton("Crude");
        but1.setPreferredSize(dimension);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(5,3,5,3);
        add(but1, constraints);
        but1.addActionListener((ActionEvent e) -> {
            ++cr;
            label.setText(String.valueOf(cr));
        });

        JButton but2 = new JButton("Common");
        but2.setPreferredSize(dimension);
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(but2, constraints);
        but2.addActionListener((ActionEvent e) -> {
            ++co;
            label2.setText(String.valueOf(co));
        });

        JButton but3 = new JButton("Rare");
        but3.setPreferredSize(dimension);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(but3, constraints);
        but3.addActionListener((ActionEvent e) -> {
            ++r;
            label3.setText(String.valueOf(r));
        });

        JButton but4 = new JButton("Famed");
        but4.setPreferredSize(dimension);
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(but4,constraints);
        but4.addActionListener((ActionEvent e) -> {
            ++f;
            label4.setText(String.valueOf(f));
        });

        JButton but5 = new JButton("Legendary");
        but5.setPreferredSize(dimension);
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(but5, constraints);
        but5.addActionListener((ActionEvent e) -> {
            ++l;
            label5.setText(String.valueOf(l));
        });

        JButton but6 = new JButton("-");
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(but6, constraints);
        but6.addActionListener((ActionEvent e) -> {
            if (kc >= 1)
            {
                --kc;
                label6.setText(String.valueOf(kc));
            }else
            {
                kc = 0;
            }
        });

        JButton but7 = new JButton("+");
        constraints.gridx = 2;
        constraints.gridy = 1;
        add(but7, constraints);
        but7.addActionListener((ActionEvent e) -> {
            ++kc;
            label6.setText(String.valueOf(kc));
        });
        
        JButton but8 = new JButton("Back");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(but8, constraints);
        but8.addActionListener((ActionEvent e) -> mainFrame.createIntroScreen());

        label7 = new JLabel(" Kills:");
        label7.setPreferredSize(dimension);
        label7.setHorizontalAlignment(JLabel.CENTER);
        label7.setFont(new Font("Helvetica", Font.BOLD, 36));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(label7, constraints);

        label = new JLabel();
        label.setPreferredSize(dimension);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(label, constraints);

        label2 = new JLabel();
        label2.setPreferredSize(dimension);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(label2, constraints);

        label3 = new JLabel();
        label3.setPreferredSize(dimension);
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 4;
        add(label3, constraints);

        label4 = new JLabel();
        label4.setPreferredSize(dimension);
        label4.setHorizontalAlignment(JLabel.CENTER);
        label4.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(label4, constraints);

        label5 = new JLabel();
        label5.setPreferredSize(dimension);
        label5.setHorizontalAlignment(JLabel.CENTER);
        label5.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(label5, constraints);

        label6 = new JLabel(String.valueOf(kc));
        label6.setPreferredSize(dimension);
        label6.setHorizontalAlignment(JLabel.CENTER);
        label6.setFont(label.getFont().deriveFont(36.0f));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(label6, constraints);

    }

    public void killIncrement(){
        ++kc;
        label6.setText(String.valueOf(kc));

    }
}
