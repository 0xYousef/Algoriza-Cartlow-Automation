package org.algoriza.otp;

import javax.swing.*;
import java.awt.*;

public class OtpDialog extends InputDialog {
    private JTextField otpField;

    public OtpDialog(String title, String message) {
        super(title, message);
    }

    @Override
    protected JComponent createInputField() {
        otpField = new JTextField();
        otpField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        otpField.setHorizontalAlignment(JTextField.CENTER);
        return otpField;
    }

    @Override
    protected String getValue(JComponent input) {
        return otpField.getText().trim();
    }
}
