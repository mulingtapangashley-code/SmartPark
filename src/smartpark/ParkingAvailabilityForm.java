package smartpark;

public class ParkingAvailabilityForm extends javax.swing.JFrame {
    ParkingAvailabilityForm(SmartParkSession session, ParkingLot.Visit visit) {
        initComponents();
        session.showCounts(availableValue, occupancyValue, slotMeter);
        vehiclePreview.setText(visit.type().toString());
        vehiclePreview.setIcon(session.vehicleIcon(visit.type()));
        nextButton.addActionListener(event -> session.open(this, new ParkingConfirmationForm(session, visit)));
        session.prepare(this, nextButton, nextButton);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        capacityPanel = new javax.swing.JPanel();
        availableValue = new javax.swing.JLabel();
        occupancyValue = new javax.swing.JLabel();
        slotMeter = new javax.swing.JProgressBar();
        vehiclePanel = new javax.swing.JPanel();
        vehiclePreview = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Parking availability");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Parking availability");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        capacityPanel.setBackground(new java.awt.Color(227, 233, 214));
        availableValue.setText("20 of 20 slots available");
        availableValue.setFont(new java.awt.Font("Segoe UI", 1, 33));
        availableValue.setForeground(new java.awt.Color(24, 35, 21));
        occupancyValue.setText("0 occupied");
        occupancyValue.setForeground(new java.awt.Color(42, 58, 38));
        slotMeter.setMaximum(20);
        vehiclePanel.setBackground(new java.awt.Color(255, 253, 248));
        vehiclePreview.setText("Vehicle illustration");
        vehiclePreview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextButton.setText("View parking confirmation");

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

        javax.swing.GroupLayout vehiclePanelLayout = new javax.swing.GroupLayout(vehiclePanel);
        vehiclePanel.setLayout(vehiclePanelLayout);
        vehiclePanelLayout.setHorizontalGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addComponent(vehiclePreview, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        vehiclePanelLayout.setVerticalGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehiclePreview, 115, 140, 180))
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
                        .addComponent(vehiclePanel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(nextButton)))
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
                    .addComponent(vehiclePanel, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nextButton, 38, 40, 40))
                .addGap(12, 20, 32767)
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
    private javax.swing.JPanel vehiclePanel;
    private javax.swing.JLabel vehiclePreview;
    private javax.swing.JButton nextButton;
    // End of variables declaration//GEN-END:variables
}
