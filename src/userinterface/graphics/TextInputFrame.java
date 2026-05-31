package userinterface.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class TextInputFrame extends JFrame {
    private JTextField inputField;
    private JButton confirmButton;

    public TextInputFrame(String title) throws HeadlessException {
        super(title);
        this.inputField = new JTextField(15);
        this.confirmButton = new JButton("Confirm");

        this.setName("InputFrame");
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setSize(300, 100);

        this.add(inputField);
        this.add(confirmButton);

        this.setVisible(true);
    }

    public JTextField getInputField() {
        return inputField;
    }
    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }
    public void setConfirmButton(JButton confirmButton) {
        this.confirmButton = confirmButton;
    }
}
