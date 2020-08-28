package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIUtils {


    public static ImageIcon scaleImage(final String imageFile, final Integer width, final Integer height) {
        ImageIcon imageIcon = new ImageIcon(imageFile);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        return imageIcon;
    }
}
