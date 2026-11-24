package smartpark;

public class RegisteredVehiclesForm extends javax.swing.JFrame {
    RegisteredVehiclesForm(SmartParkSession session) {
        initComponents();
        var vehicles = session.lot.parkedVehicles();
        Object[][] rows = vehicles.stream().map(visit -> new Object[]{
            "%02d".formatted(visit.slot()), visit.owner(), visit.plate(),
            visit.type(), session.timestamp(visit.timeIn())
        }).toArray(Object[][]::new);
        var columns = new String[]{"Slot", "Owner", "Plate number", "Vehicle type", "Time-in"};
        vehicleTable.setModel(new javax.swing.table.DefaultTableModel(rows, columns) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        vehicleTable.setFillsViewportHeight(true);
        vehicleTable.setRowHeight(34);
        vehicleTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        int[] widths = {62, 150, 120, 125, 185};
        for (int column = 0; column < widths.length; column++) {
            vehicleTable.getColumnModel().getColumn(column).setPreferredWidth(widths[column]);
        }
        if (!vehicles.isEmpty()) {
            statusLabel.setText(vehicles.size() == 1 ? "1 vehicle parked" : vehicles.size() + " vehicles parked");
        }
        backButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, backButton, backButton);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        tableScroll = new javax.swing.JScrollPane();
        vehicleTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Registered vehicles");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Registered vehicles");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        statusLabel.setText("No vehicles are currently parked.");
        tableScroll.setViewportView(vehicleTable);
        vehicleTable.setModel(new javax.swing.table.DefaultTableModel(new Object[0][5], new String[]{"Slot", "Owner", "Plate number", "Vehicle type", "Time-in"}) {
            @Override public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        backButton.setText("Back to parking desk");



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
                        .addComponent(statusLabel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScroll, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(backButton)))
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
                    .addComponent(statusLabel))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableScroll, 120, 240, 32767))
                .addGap(12, 12, 12)
                .addGap(12, 20, 32767)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, 38, 40, 40))
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JScrollPane tableScroll;
    private javax.swing.JTable vehicleTable;
    private javax.swing.JButton backButton;
    // End of variables declaration//GEN-END:variables
}
