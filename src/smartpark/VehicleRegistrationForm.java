package smartpark;

public class VehicleRegistrationForm extends javax.swing.JFrame {
    VehicleRegistrationForm(SmartParkSession session) {
        initComponents();
        registerButton.addActionListener(event -> {
            var selected = vehicleType.getSelectedItem();
            var type = java.util.Arrays.stream(ParkingLot.VehicleType.values())
                    .filter(choice -> choice.toString().equals(selected)).findFirst().orElse(null);
            try {
                var visit = session.lot.park(ownerField.getText(), plateField.getText(), type);
                session.open(this, new ParkingAvailabilityForm(session, visit));
            } catch (IllegalArgumentException ex) {
                messageLabel.setText(ex.getMessage());
            }
        });
        cancelButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, registerButton, ownerField);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        vehicleFields = new javax.swing.JPanel();
        ownerLabel = new javax.swing.JLabel();
        ownerField = new javax.swing.JTextField();
        plateLabel = new javax.swing.JLabel();
        plateField = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        vehicleType = new javax.swing.JComboBox<>();
        messageLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Register a vehicle");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Register a vehicle");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        vehicleFields.setBackground(new java.awt.Color(255, 253, 248));
        ownerLabel.setText("Vehicle owner's name");
        ownerLabel.setLabelFor(ownerField);
        plateLabel.setText("Plate number");
        plateLabel.setLabelFor(plateField);
        typeLabel.setText("Vehicle type");
        typeLabel.setLabelFor(vehicleType);
        vehicleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select vehicle type", "Car", "Motorcycle"}));
        messageLabel.setText(" ");
        messageLabel.setForeground(new java.awt.Color(155, 38, 44));
        cancelButton.setText("Cancel");
        registerButton.setText("Register");

        javax.swing.GroupLayout vehicleFieldsLayout = new javax.swing.GroupLayout(vehicleFields);
        vehicleFields.setLayout(vehicleFieldsLayout);
        vehicleFieldsLayout.setHorizontalGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleFieldsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(ownerLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(ownerField, 0, 400, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(plateLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(plateField, 0, 400, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(typeLabel)
                        .addGap(0, 0, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(vehicleType, 0, 400, 32767))
                    .addGroup(vehicleFieldsLayout.createSequentialGroup()
                        .addComponent(messageLabel, 0, 640, 32767)))
                .addGap(22, 22, 22)));
        vehicleFieldsLayout.setVerticalGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleFieldsLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ownerLabel))
                .addGap(4, 4, 4)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ownerField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateLabel))
                .addGap(4, 4, 4)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plateField, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeLabel))
                .addGap(4, 4, 4)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vehicleType, 38, 40, 40))
                .addGap(9, 9, 9)
                .addGroup(vehicleFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(vehicleFields, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(cancelButton)
                        .addGap(12, 12, 12)
                        .addComponent(registerButton)))
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
                    .addComponent(vehicleFields, -2, -1, -2))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton, 38, 40, 40)
                    .addComponent(registerButton, 38, 40, 40))
                .addGap(12, 20, 32767)
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JPanel vehicleFields;
    private javax.swing.JLabel ownerLabel;
    private javax.swing.JTextField ownerField;
    private javax.swing.JLabel plateLabel;
    private javax.swing.JTextField plateField;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JComboBox<String> vehicleType;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}
