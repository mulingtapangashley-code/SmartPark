package smartpark;

public class CheckoutPaymentForm extends javax.swing.JFrame {
    CheckoutPaymentForm(SmartParkSession session, ParkingLot.Visit visit) {
        initComponents();
        ownerValue.setText(visit.owner());
        plateValue.setText(visit.plate());
        vehicleValue.setText(visit.type().toString());
        slotValue.setText("%02d".formatted(visit.slot()));
        timeInValue.setText(session.timestamp(visit.timeIn()));
        feeValue.setText(session.money(visit.type().fee()));
        confirmButton.setEnabled(false);
        session.onTextChange(cashField, () -> {
            confirmButton.setEnabled(false);
            changeValue.setText("—");
            messageLabel.setText(" ");
            if (cashField.getText().isBlank()) {
                return;
            }
            try {
                var change = session.cashAmount(cashField.getText()).subtract(visit.type().fee());
                if (change.signum() < 0) {
                    messageLabel.setText("Cash received is short by " + session.money(change.negate()) + ".");
                } else {
                    changeValue.setText(session.money(change));
                    confirmButton.setEnabled(true);
                }
            } catch (IllegalArgumentException ex) {
                messageLabel.setText(ex.getMessage());
            }
        });
        confirmButton.addActionListener(event -> {
            try {
                var receipt = session.lot.checkout(visit, session.cashAmount(cashField.getText()));
                session.open(this, new ReceiptForm(session, receipt));
            } catch (IllegalArgumentException ex) {
                messageLabel.setText(ex.getMessage());
            }
        });
        cancelButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, confirmButton, cashField);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        vehicleDetails = new javax.swing.JPanel();
        vehicleSectionLabel = new javax.swing.JLabel();
        ownerLabel = new javax.swing.JLabel();
        ownerValue = new javax.swing.JLabel();
        plateLabel = new javax.swing.JLabel();
        plateValue = new javax.swing.JLabel();
        vehicleLabel = new javax.swing.JLabel();
        vehicleValue = new javax.swing.JLabel();
        slotLabel = new javax.swing.JLabel();
        slotValue = new javax.swing.JLabel();
        timeInLabel = new javax.swing.JLabel();
        timeInValue = new javax.swing.JLabel();
        paymentPanel = new javax.swing.JPanel();
        paymentSectionLabel = new javax.swing.JLabel();
        feeLabel = new javax.swing.JLabel();
        feeValue = new javax.swing.JLabel();
        cashLabel = new javax.swing.JLabel();
        cashField = new javax.swing.JTextField();
        changeLabel = new javax.swing.JLabel();
        changeValue = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Review checkout");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Review checkout");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        vehicleDetails.setBackground(new java.awt.Color(255, 253, 248));
        vehicleSectionLabel.setText("Vehicle details");
        vehicleSectionLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
        vehicleSectionLabel.setForeground(new java.awt.Color(56, 82, 49));
        ownerLabel.setText("Owner");
        ownerValue.setText("—");
        plateLabel.setText("Plate number");
        plateValue.setText("—");
        vehicleLabel.setText("Vehicle type");
        vehicleValue.setText("—");
        slotLabel.setText("Assigned slot");
        slotValue.setText("—");
        timeInLabel.setText("Time-in");
        timeInValue.setText("—");
        paymentPanel.setBackground(new java.awt.Color(227, 233, 214));
        paymentSectionLabel.setText("Payment");
        paymentSectionLabel.setFont(new java.awt.Font("Segoe UI", 1, 16));
        paymentSectionLabel.setForeground(new java.awt.Color(56, 82, 49));
        feeLabel.setText("Parking fee");
        feeValue.setText("—");
        cashLabel.setText("Cash received (PHP)");
        cashLabel.setLabelFor(cashField);
        changeLabel.setText("Change");
        changeValue.setText("—");
        changeValue.setFont(new java.awt.Font("Segoe UI", 1, 24));
        changeValue.setForeground(new java.awt.Color(56, 82, 49));
        messageLabel.setText(" ");
        messageLabel.setForeground(new java.awt.Color(155, 38, 44));
        cancelButton.setText("Cancel");
        confirmButton.setText("Confirm checkout");

        javax.swing.GroupLayout vehicleDetailsLayout = new javax.swing.GroupLayout(vehicleDetails);
        vehicleDetails.setLayout(vehicleDetailsLayout);
        vehicleDetailsLayout.setHorizontalGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleDetailsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(vehicleSectionLabel, 0, 640, 32767))
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(ownerLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(ownerValue, 0, 640, 32767))
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(plateLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(plateValue, 0, 640, 32767))
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(vehicleLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(vehicleValue, 0, 640, 32767))
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(slotLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(slotValue, 0, 640, 32767))
                    .addGroup(vehicleDetailsLayout.createSequentialGroup()
                        .addComponent(timeInLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(timeInValue, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        vehicleDetailsLayout.setVerticalGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleDetailsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleSectionLabel))
                .addGap(9, 9, 9)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ownerLabel)
                    .addComponent(ownerValue))
                .addGap(9, 9, 9)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateLabel)
                    .addComponent(plateValue))
                .addGap(9, 9, 9)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleLabel)
                    .addComponent(vehicleValue))
                .addGap(9, 9, 9)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slotLabel)
                    .addComponent(slotValue))
                .addGap(9, 9, 9)
                .addGroup(vehicleDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(timeInLabel)
                    .addComponent(timeInValue))
                .addGap(18, 18, 18)));

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(paymentSectionLabel, 0, 640, 32767))
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(feeLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(feeValue, 0, 640, 32767))
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(cashLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(cashField, 0, 400, 32767))
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(changeLabel, 156, 156, 156)
                        .addGap(12, 12, 12)
                        .addComponent(changeValue, 0, 640, 32767))
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addComponent(messageLabel, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        paymentPanelLayout.setVerticalGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentSectionLabel))
                .addGap(9, 9, 9)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(feeLabel)
                    .addComponent(feeValue))
                .addGap(9, 9, 9)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashLabel))
                .addGap(4, 4, 4)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeLabel)
                    .addComponent(changeValue))
                .addGap(9, 9, 9)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(vehicleDetails, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paymentPanel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(cancelButton)
                        .addGap(12, 12, 12)
                        .addComponent(confirmButton)))
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
                    .addComponent(vehicleDetails, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentPanel, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, 38, 40, 40)
                    .addComponent(confirmButton, 38, 40, 40))
                .addGap(12, 20, 32767)
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel vehicleDetails;
    private javax.swing.JLabel vehicleSectionLabel;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JLabel ownerValue;
    private javax.swing.JLabel plateLabel;
    private javax.swing.JLabel plateValue;
    private javax.swing.JLabel vehicleLabel;
    private javax.swing.JLabel vehicleValue;
    private javax.swing.JLabel slotLabel;
    private javax.swing.JLabel slotValue;
    private javax.swing.JLabel timeInLabel;
    private javax.swing.JLabel timeInValue;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JLabel paymentSectionLabel;
    private javax.swing.JLabel feeLabel;
    private javax.swing.JLabel feeValue;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JTextField cashField;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JLabel changeValue;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    // End of variables declaration//GEN-END:variables
}
