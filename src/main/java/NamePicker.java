import javax.swing.*;
import java.awt.*;


public class NamePicker extends JPanel{

    private final JLabel label;
    private final JTextField textField;
    private String bossName;
    private final MainFrame guiStorage;

    public NamePicker(final MainFrame guiStorage){

        this.guiStorage = guiStorage;

        Icon icon = new ImageIcon("C:\\Users\\tomah\\Pictures\\TLOPO buttons\\track.PNG"); //../../../track.PNG


        textField = new JTextField();
        textField.setPreferredSize(new Dimension(100,40));
        textField.addActionListener(e -> setBossName(textField.getText()));


        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(120,45));
        button.addActionListener(e -> setBossName(textField.getText()));


        label = new JLabel("Type name of boss: ");


        //setBorder(BorderFactory.createEmptyBorder(40, 40, 60, 40));
        setLayout(new GridBagLayout());
        add(label);
        add(textField);
        add(button);



    }


    private void setBossName(final String bossName){
        this.bossName = bossName;
        guiStorage.createSecondGUI(bossName);
        //FileHandler.updateSave("legendary", "4", bossName);
    }


}
