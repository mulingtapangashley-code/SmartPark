package smartpark;

public class SmartParkFrame extends javax.swing.JFrame {
    public SmartParkFrame() {
        SmartParkSession session = new SmartParkSession();
        initComponents();
        loginButton.addActionListener(event -> {
            char[] entered = passwordField.getPassword();
            try {
                if (session.accounts.matches(usernameField.getText(), entered)) {
                    session.open(this, new ParkingDeskForm(session));
                } else {
                    messageLabel.setText("Username or password is incorrect.");
                    passwordField.selectAll();
                    passwordField.requestFocusInWindow();
                }
            } catch (java.io.IOException ex) {
                messageLabel.setText("Could not read the local accounts. Try again.");
            } finally {
                java.util.Arrays.fill(entered, '\0');
            }
        });
        session.applyLoginTheme(this, loginCard, exitButton);
        exitButton.addActionListener(event -> dispose());
        session.prepare(this, loginButton, usernameField);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new SmartParkFrame().setVisible(true));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        loginCard = new javax.swing.JPanel();
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        messageLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Attendant login");

        loginCard.setBackground(new java.awt.Color(255, 253, 248));
        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Attendant login");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        usernameLabel.setText("Username");
        usernameLabel.setLabelFor(usernameField);
        passwordLabel.setText("Password");
        passwordLabel.setLabelFor(passwordField);
        messageLabel.setText(" ");
        messageLabel.setForeground(new java.awt.Color(155, 38, 44));
        loginButton.setText("Log in");
        exitButton.setText("Exit");

        javax.swing.GroupLayout loginCardLayout = new javax.swing.GroupLayout(loginCard);
        loginCard.setLayout(loginCardLayout);
        loginCardLayout.setHorizontalGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginCardLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(brandLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(headingLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(usernameField, 0, 400, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(passwordLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(passwordField, 0, 400, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(messageLabel, 0, 640, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addComponent(loginButton, 0, 640, 32767))
                    .addGroup(loginCardLayout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(exitButton)))
                .addGap(22, 22, 22)));
        loginCardLayout.setVerticalGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginCardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brandLabel))
                .addGap(4, 4, 4)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel))
                .addGap(9, 9, 9)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel))
                .addGap(4, 4, 4)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel))
                .addGap(4, 4, 4)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel))
                .addGap(9, 9, 9)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginButton, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(loginCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, 38, 40, 40))
                .addGap(18, 18, 18)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 36, 32767)
                .addComponent(loginCard, 0, 420, 440)
                .addGap(20, 36, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 36, 32767)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginCard, -2, -1, -2))
                .addGap(20, 36, 32767)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel loginCard;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton exitButton;
    // End of variables declaration//GEN-END:variables
}
