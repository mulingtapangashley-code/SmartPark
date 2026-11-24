package smartpark;

public class ParkingConfirmationForm extends javax.swing.JFrame {
    ParkingConfirmationForm(SmartParkSession session, ParkingLot.Visit visit) {
        initComponents();
        ownerValue.setText(visit.owner());
        plateValue.setText(visit.plate());
        vehicleValue.setText(visit.type().toString());
        slotHeading.setText("Slot %02d".formatted(visit.slot()));
        timeInValue.setText(session.timestamp(visit.timeIn()));
        feeValue.setText(session.money(visit.type().fee()) + " per visit");
        doneButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, doneButton, doneButton);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        slotPanel = new javax.swing.JPanel();
        slotHeading = new javax.swing.JLabel();
        confirmationDetails = new javax.swing.JPanel();
        ownerLabel = new javax.swing.JLabel();
        ownerValue = new javax.swing.JLabel();
        plateLabel = new javax.swing.JLabel();
        plateValue = new javax.swing.JLabel();
        vehicleLabel = new javax.swing.JLabel();
        vehicleValue = new javax.swing.JLabel();
        timeInLabel = new javax.swing.JLabel();
        timeInValue = new javax.swing.JLabel();
        feeLabel = new javax.swing.JLabel();
        feeValue = new javax.swing.JLabel();
        doneButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Vehicle successfully parked");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Vehicle successfully parked");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        slotPanel.setBackground(new java.awt.Color(227, 233, 214));
        slotHeading.setText("Slot 01");
        slotHeading.setFont(new java.awt.Font("Segoe UI", 1, 38));
        slotHeading.setForeground(new java.awt.Color(56, 82, 49));
        confirmationDetails.setBackground(new java.awt.Color(255, 253, 248));
        ownerLabel.setText("Owner");
        ownerValue.setText("—");
        plateLabel.setText("Plate number");
        plateValue.setText("—");
        vehicleLabel.setText("Vehicle type");
        vehicleValue.setText("—");
        timeInLabel.setText("Time-in");
        timeInValue.setText("—");
        feeLabel.setText("Parking fee");
        feeValue.setText("—");
        doneButton.setText("Done");

        javax.swing.GroupLayout slotPanelLayout = new javax.swing.GroupLayout(slotPanel);
        slotPanel.setLayout(slotPanelLayout);
        slotPanelLayout.setHorizontalGroup(slotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slotPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(slotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(slotPanelLayout.createSequentialGroup()
                        .addComponent(slotHeading, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        slotPanelLayout.setVerticalGroup(slotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slotPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(slotPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slotHeading))
                .addGap(18, 18, 18)));

        javax.swing.GroupLayout confirmationDetailsLayout = new javax.swing.GroupLayout(confirmationDetails);
        confirmationDetails.setLayout(confirmationDetailsLayout);
        confirmationDetailsLayout.setHorizontalGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmationDetailsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmationDetailsLayout.createSequentialGroup()
                        .addComponent(ownerLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(ownerValue, 0, 640, 32767))
                    .addGroup(confirmationDetailsLayout.createSequentialGroup()
                        .addComponent(plateLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(plateValue, 0, 640, 32767))
                    .addGroup(confirmationDetailsLayout.createSequentialGroup()
                        .addComponent(vehicleLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(vehicleValue, 0, 640, 32767))
                    .addGroup(confirmationDetailsLayout.createSequentialGroup()
                        .addComponent(timeInLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(timeInValue, 0, 640, 32767))
                    .addGroup(confirmationDetailsLayout.createSequentialGroup()
                        .addComponent(feeLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(feeValue, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        confirmationDetailsLayout.setVerticalGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmationDetailsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ownerLabel)
                    .addComponent(ownerValue))
                .addGap(9, 9, 9)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateLabel)
                    .addComponent(plateValue))
                .addGap(9, 9, 9)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleLabel)
                    .addComponent(vehicleValue))
                .addGap(9, 9, 9)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeInLabel)
                    .addComponent(timeInValue))
                .addGap(9, 9, 9)
                .addGroup(confirmationDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(feeLabel)
                    .addComponent(feeValue))
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
                        .addComponent(slotPanel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmationDetails, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(doneButton)))
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
                    .addComponent(slotPanel, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(confirmationDetails, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(doneButton, 38, 40, 40))
                .addGap(12, 20, 32767)
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel slotPanel;
    private javax.swing.JLabel slotHeading;
    private javax.swing.JPanel confirmationDetails;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JLabel ownerValue;
    private javax.swing.JLabel plateLabel;
    private javax.swing.JLabel plateValue;
    private javax.swing.JLabel vehicleLabel;
    private javax.swing.JLabel vehicleValue;
    private javax.swing.JLabel timeInLabel;
    private javax.swing.JLabel timeInValue;
    private javax.swing.JLabel feeLabel;
    private javax.swing.JLabel feeValue;
    private javax.swing.JButton doneButton;
    // End of variables declaration//GEN-END:variables
}
