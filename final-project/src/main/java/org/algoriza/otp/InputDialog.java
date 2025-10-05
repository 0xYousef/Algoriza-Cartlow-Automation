package org.algoriza.otp;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;

public abstract class InputDialog {
    protected final String title;
    protected final String message;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;
    private static final Color BG_COLOR = new Color(250, 250, 250);
    private static final Color BTN_COLOR = new Color(66, 133, 244);
    private static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font BTN_FONT = new Font("Segoe UI", Font.BOLD, 16);

    public InputDialog(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String showDialog() {
        AtomicReference<String> result = new AtomicReference<>();

        SwingUtilities.invokeLater(() -> {
            JFrame frame = buildFrame();

            JPanel panel = createRoundedPanel();
            panel.setLayout(null);
            panel.setBounds(0, 0, WIDTH, HEIGHT);

            // Logo
            JLabel logoLabel = buildLogo(frame);
            panel.add(logoLabel);

            // Message label
            JLabel label = new JLabel(message, SwingConstants.CENTER);
            label.setFont(TITLE_FONT);
            label.setForeground(new Color(40, 40, 40));
            label.setBounds(0, 100, WIDTH, 30);
            panel.add(label);

            // Input field
            JComponent inputField = createInputField();
            inputField.setBounds(60, 140, 380, 50);
            panel.add(inputField);

            // Submit button
            JButton submit = createButton();
            submit.setBounds(195, 210, 110, 45);
            submit.addActionListener(e -> {
                result.set(getValue(inputField));
                frame.dispose();
                synchronized (result) {
                    result.notify();
                }
            });
            panel.add(submit);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });

        synchronized (result) {
            try {
                result.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return result.get();
    }

    /** Abstracts for subclasses */
    protected abstract JComponent createInputField();
    protected abstract String getValue(JComponent input);

    /** Builds a borderless, centered frame */
    private JFrame buildFrame() {
        JFrame frame = new JFrame(title);
        frame.setUndecorated(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);
        return frame;
    }

    /** Creates a rounded background panel */
    protected JPanel createRoundedPanel() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BG_COLOR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
            }
        };
    }

    protected JButton createButton() {
        JButton button = new JButton("Verify");
        button.setFont(BTN_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(BTN_COLOR);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    /** Loads logo and sets both dialog + taskbar icon */
    private JLabel buildLogo(JFrame frame) {
        JLabel logoLabel = new JLabel("", SwingConstants.CENTER);
        logoLabel.setBounds((WIDTH - 80) / 2, 10, 80, 80);

        try {
            ImageIcon icon = loadLogo();
            Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaled));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.err.println("⚠️ Logo not found: " + e.getMessage());
        }
        return logoLabel;
    }

    /** Try classpath → fallback to local path */
    private ImageIcon loadLogo() {
        java.net.URL imgUrl = getClass().getResource("/assets/icons/algoriza.png");
        if (imgUrl != null) {
            return new ImageIcon(imgUrl);
        }
        String projectPath = System.getProperty("user.dir");
        String iconPath = projectPath + "/src/main/resources/assets/icons/algoriza.png";
        return new ImageIcon(iconPath);
    }
}
