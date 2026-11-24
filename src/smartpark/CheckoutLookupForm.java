package smartpark;

public class CheckoutLookupForm extends javax.swing.JFrame {
    CheckoutLookupForm(SmartParkSession session) {
        initComponents();
        lookupButton.addActionListener(event -> {
            var visit = session.lot.find(plateField.getText());
            if (visit == null) {
                messageLabel.setText(plateField.getText().isBlank() ? "Enter a plate number."
                        : "No parked vehicle matches that plate.");
            } else {
                session.open(this, new CheckoutPaymentForm(session, visit));
            }
        });
        cancelButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, lookupButton, plateField);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        lookupPanel = new javax.swing.JPanel();
        plateLabel = new javax.swing.JLabel();
        plateField = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        lookupButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Vehicle time-out");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Vehicle time-out");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        lookupPanel.setBackground(new java.awt.Color(255, 253, 248));
        plateLabel.setText("Vehicle plate number");
        plateLabel.setLabelFor(plateField);
        messageLabel.setText(" ");
        messageLabel.setForeground(new java.awt.Color(155, 38, 44));
        cancelButton.setText("Cancel");
        lookupButton.setText("Find vehicle");

        javax.swing.GroupLayout lookupPanelLayout = new javax.swing.GroupLayout(lookupPanel);
        lookupPanel.setLayout(lookupPanelLayout);
        lookupPanelLayout.setHorizontalGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lookupPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lookupPanelLayout.createSequentialGroup()
                        .addComponent(plateLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(lookupPanelLayout.createSequentialGroup()
                        .addComponent(plateField, 0, 400, 32767))
                    .addGroup(lookupPanelLayout.createSequentialGroup()
                        .addComponent(messageLabel, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        lookupPanelLayout.setVerticalGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lookupPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateLabel))
                .addGap(4, 4, 4)
                .addGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(lookupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel))
                .addGap(18, 18, 18)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(brandLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(headingLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lookupPanel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(cancelButton)
                        .addGap(12, 12, 12)
                        .addComponent(lookupButton)))
                .addGap(28, 28, 28)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(brandLabel))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headingLabel))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lookupPanel, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, 38, 40, 40)
                    .addComponent(lookupButton, 38, 40, 40))
                .addGap(12, 20, 32767)
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel lookupPanel;
    private javax.swing.JLabel plateLabel;
    private javax.swing.JTextField plateField;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton lookupButton;
    // End of variables declaration//GEN-END:variables
}
