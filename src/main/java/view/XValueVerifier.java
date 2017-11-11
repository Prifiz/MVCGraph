package view;

import javax.swing.*;
import java.awt.*;

public class XValueVerifier extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
        boolean verified = false;
        String text = ((JTextField) input).getText();
        try {
            float x = Float.valueOf(text);
            verified = true;
            input.setBackground(Color.WHITE);
        } catch (NumberFormatException e) {
            System.out.println("Not a number");
        }
        return verified;
    }
}
