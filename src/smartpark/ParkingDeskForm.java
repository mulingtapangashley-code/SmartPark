package smartpark;

public class ParkingDeskForm extends javax.swing.JFrame {
    ParkingDeskForm(SmartParkSession session) {
        initComponents();
        session.showCounts(availableValue, occupancyValue, slotMeter);
        session.applyDeskTheme(capacityPanel, registeredButton, exitButton);
        int available = session.lot.available();
        registerButton.setEnabled(available > 0);
        checkoutButton.setEnabled(available < ParkingLot.CAPACITY);
        if (available == 0) {
            statusLabel.setText("Parking is full. Check out a vehicle to free a slot.");
        } else if (available == ParkingLot.CAPACITY) {
            statusLabel.setText("No vehicles are currently parked.");
        }
        registerButton.addActionListener(event -> session.open(this, new VehicleRegistrationForm(session)));
        checkoutButton.addActionListener(event -> session.open(this, new CheckoutLookupForm(session)));
        registeredButton.addActionListener(event -> session.open(this, new RegisteredVehiclesForm(session)));
        exitButton.addActionListener(event -> dispose());
        session.prepare(this, registerButton.isEnabled() ? registerButton : checkoutButton, null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        capacityPanel = new javax.swing.JPanel();
        availableValue = new javax.swing.JLabel();
        occupancyValue = new javax.swing.JLabel();
        slotMeter = new javax.swing.JProgressBar();
        statusLabel = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        checkoutButton = new javax.swing.JButton();
        registeredButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Parking desk");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Parking desk");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        capacityPanel.setBackground(new java.awt.Color(227, 233, 214));
        availableValue.setText("20 of 20 slots available");
        availableValue.setFont(new java.awt.Font("Segoe UI", 1, 33));
        availableValue.setForeground(new java.awt.Color(24, 35, 21));
        occupancyValue.setText("0 occupied");
        occupancyValue.setForeground(new java.awt.Color(42, 58, 38));
        slotMeter.setMaximum(20);
        statusLabel.setText(" ");
        registerButton.setText("Register a vehicle");
        checkoutButton.setText("Check out a vehicle");
        registeredButton.setText("Registered vehicles");
        exitButton.setText("Exit");

        javax.swing.GroupLayout capacityPanelLayout = new javax.swing.GroupLayout(capacityPanel);
        capacityPanel.setLayout(capacityPanelLayout);
        capacityPanelLayout.setHorizontalGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capacityPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(capacityPanelLayout.createSequentialGroup()
                        .addComponent(availableValue, 0, 640, 32767))
                    .addGroup(capacityPanelLayout.createSequentialGroup()
                        .addComponent(occupancyValue, 0, 640, 32767))
                    .addGroup(capacityPanelLayout.createSequentialGroup()
                        .addComponent(slotMeter, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        capacityPanelLayout.setVerticalGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(capacityPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(availableValue))
                .addGap(9, 9, 9)
                .addGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(occupancyValue))
                .addGap(9, 9, 9)
                .addGroup(capacityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slotMeter, 8, 8, 8))
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
                        .addComponent(capacityPanel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(statusLabel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerButton)
                        .addGap(12, 12, 12)
                        .addComponent(checkoutButton)
                        .addGap(12, 12, 12)
                        .addComponent(registeredButton)
                        .addGap(0, 0, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(exitButton)))
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
                    .addComponent(capacityPanel, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registerButton, 38, 40, 40)
                    .addComponent(checkoutButton, 38, 40, 40)
                    .addComponent(registeredButton, 38, 40, 40))
                .addGap(12, 12, 12)
                .addGap(12, 20, 32767)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, 38, 40, 40))
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel capacityPanel;
    private javax.swing.JLabel availableValue;
    private javax.swing.JLabel occupancyValue;
    private javax.swing.JProgressBar slotMeter;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JButton registeredButton;
    private javax.swing.JButton exitButton;
    // End of variables declaration//GEN-END:variables
}
