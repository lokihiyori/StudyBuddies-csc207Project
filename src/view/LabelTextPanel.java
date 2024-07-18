package view;

import javax.swing.*;

public class LabelTextPanel extends JPanel {
    private final JLabel label;
    private final JTextField textField;

    public LabelTextPanel(String labelText) {
        label = new JLabel(labelText);
        textField = new JTextField(20);
        add(label);
        add(textField);
    }

    public LabelTextPanel(JLabel course, JTextField courseSearchField, JLabel label, JTextField textField) {
        this.label = label;
        this.textField = textField;
    }

    public String getText() {
        return textField.getText();
    }
}
