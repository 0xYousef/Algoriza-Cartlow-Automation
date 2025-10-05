package org.algoriza.otp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class PhoneDialog extends InputDialog {
    private final Map<String, String> countryDialCodes;
    private JComboBox<String> countryBox;
    private JTextField phoneField;

    public PhoneDialog(String title, String message) {
        super(title, message);

        countryDialCodes = new LinkedHashMap<>();
        countryDialCodes.put("(+973) Bahrain ", "+973");
        countryDialCodes.put("(+20) Egypt ", "+20");
        countryDialCodes.put("(+964) Iraq", "+964");
        countryDialCodes.put("(+962) Jordan", "+962");
        countryDialCodes.put("(+968) Oman", "+968");
        countryDialCodes.put("(+974) Qatar", "+974");
        countryDialCodes.put("(+27) South Africa", "+27");
        countryDialCodes.put("(+971) United Arab Emirates", "+971");
    }

    @Override
    protected JComponent createInputField() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);

        // Custom light rounded border
        LineBorder roundedBorder = new LineBorder(new Color(200, 200, 200), 1, true);

        // Country selector
        countryBox = new JComboBox<>(countryDialCodes.keySet().toArray(new String[0]));
        countryBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        countryBox.setPreferredSize(new Dimension(120, 38));
        countryBox.setBorder(roundedBorder);

        // Phone input (very wide)
        phoneField = new JTextField();
        phoneField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        phoneField.setBorder(BorderFactory.createCompoundBorder(
                roundedBorder, new EmptyBorder(5, 10, 5, 10)
        ));
        phoneField.setPreferredSize(new Dimension(200, 38));

        panel.add(countryBox, BorderLayout.WEST);
        panel.add(phoneField, BorderLayout.CENTER);

        return panel;
    }

    @Override
    protected String getValue(JComponent input) {
        String selected = (String) countryBox.getSelectedItem();
        String dialCode = countryDialCodes.get(selected);
        return dialCode + phoneField.getText().trim();
    }
}
