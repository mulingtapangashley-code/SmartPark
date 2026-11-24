package smartpark;

public class ReceiptForm extends javax.swing.JFrame {
    ReceiptForm(SmartParkSession session, ParkingLot.Receipt receipt) {
        initComponents();
        receiptArea.setText(session.receiptText(receipt));
        receiptArea.setCaretPosition(0);
        printButton.addActionListener(event -> {
            try {
                messageLabel.setText(" ");
                receiptArea.print();
            } catch (java.awt.print.PrinterException ex) {
                messageLabel.setText("Could not print the receipt. Check your printer and try again.");
            }
        });
        doneButton.addActionListener(event -> session.open(this, new ParkingDeskForm(session)));
        session.prepare(this, doneButton, doneButton);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        brandLabel = new javax.swing.JLabel();
        headingLabel = new javax.swing.JLabel();
        receiptScroll = new javax.swing.JScrollPane();
        receiptArea = new javax.swing.JTextArea();
        messageLabel = new javax.swing.JLabel();
        printButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartPark | Checkout complete");

        brandLabel.setText("SmartPark");
        brandLabel.setFont(new java.awt.Font("Segoe UI", 1, 19));
        brandLabel.setForeground(new java.awt.Color(56, 82, 49));
        headingLabel.setText("Checkout complete");
        headingLabel.setFont(new java.awt.Font("Segoe UI", 1, 34));
        receiptScroll.setViewportView(receiptArea);
        receiptArea.setText("SmartPark parking receipt");
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        receiptArea.setWrapStyleWord(true);
        messageLabel.setText(" ");
        messageLabel.setForeground(new java.awt.Color(155, 38, 44));
        printButton.setText("Print receipt");
        doneButton.setText("Done");



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
                        .addComponent(receiptScroll, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messageLabel, 0, 640, 32767))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 32767)
                        .addComponent(printButton)
                        .addGap(12, 12, 12)
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
                    .addComponent(receiptScroll, 120, 240, 32767))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageLabel))
                .addGap(12, 12, 12)
                .addGap(12, 20, 32767)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(printButton, 38, 40, 40)
                    .addComponent(doneButton, 38, 40, 40))
                .addGap(22, 22, 22)));
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel brandLabel;
    private javax.swing.JLabel headingLabel;
    private javax.swing.JScrollPane receiptScroll;
    private javax.swing.JTextArea receiptArea;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JButton printButton;
    private javax.swing.JButton doneButton;
    // End of variables declaration//GEN-END:variables
}
