package smartpark;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.event.HierarchyEvent;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.Scrollable;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.table.DefaultTableModel;
import smartpark.ParkingLot.Receipt;
import smartpark.ParkingLot.VehicleType;
import smartpark.ParkingLot.Visit;

final class SmartParkPanel extends JPanel {
    private static final Color INK = new Color(20, 43, 41);
    private static final Color MUTED = new Color(72, 101, 96);
    private static final Color TEAL = new Color(0, 94, 80);
    private static final Color MINT = new Color(224, 244, 236);
    private static final Color PAPER = new Color(240, 247, 244);
    private static final Color LINE = new Color(196, 217, 210);
    private static final Color ERROR = new Color(155, 38, 44);
    private static final Color DEEP_TEAL = new Color(12, 56, 51);
    private static final String FONT = new Font("Segoe UI Variable", Font.PLAIN, 15)
            .getFamily().equals(Font.DIALOG) ? "Segoe UI" : "Segoe UI Variable";
    private static final String DISPLAY_FONT = new Font("Bahnschrift", Font.PLAIN, 36)
            .getFamily().equals(Font.DIALOG) ? FONT : "Bahnschrift";
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter
            .ofPattern("MMM d, uuuu, h:mm:ss a").withZone(ZoneId.systemDefault());

    private final ParkingLot lot = new ParkingLot();
    private final Accounts accounts = new Accounts();
    private JLabel screenTitle;

    SmartParkPanel() {
        super(new BorderLayout(0, 14));
        setBackground(PAPER);
        setBorder(BorderFactory.createEmptyBorder(22, 36, 20, 36));
        setPreferredSize(new Dimension(920, 640));
        showLogin();
    }

    @Override
    public void doLayout() {
        if (screenTitle != null) {
            float size = getWidth() < 640 ? 29f : 37f;
            if (screenTitle.getFont().getSize2D() != size) {
                screenTitle.setFont(screenTitle.getFont().deriveFont(size));
            }
        }
        super.doLayout();
    }

    private void showLogin() {
        showLogin("", false);
    }

    private void showLogin(String accountName, boolean accountCreated) {
        JPanel form = surface(rows(), Color.WHITE);
        form.add(displayLabel("Attendant login", 32, INK));
        form.add(Box.createVerticalStrut(14));
        JTextField username = new JTextField(24);
        username.setText(accountName);
        JPasswordField password = new JPasswordField(24);
        JLabel error = errorLabel();
        if (accountCreated) {
            error.setForeground(TEAL);
            error.setText("Account created. Sign in to continue.");
        }
        stackedField(form, "Username", username);
        stackedField(form, "Password", password);
        form.add(error);

        JButton login = button("Log in", () -> {
            char[] entered = password.getPassword();
            error.setForeground(ERROR);
            try {
                if (accounts.matches(username.getText(), entered)) {
                    showMenu();
                    return;
                }
                error.setText("Username or password is incorrect.");
                password.selectAll();
                password.requestFocusInWindow();
            } catch (IOException ex) {
                error.setText("Could not read the local accounts. Try again.");
            } finally {
                Arrays.fill(entered, '\0');
            }
        });
        JButton exit = button("Exit", () -> SwingUtilities.getWindowAncestor(this).dispose());
        JButton create = button("Create an account", this::showAccountRegistration);
        form.add(actions(FlowLayout.TRAILING, exit, create, login));
        show("", centered(form), login, accountCreated ? password : username);
    }

    private void showAccountRegistration() {
        JPanel form = surface(rows(), Color.WHITE);
        form.add(displayLabel("Create an account", 32, INK));
        form.add(Box.createVerticalStrut(14));
        JTextField username = new JTextField(24);
        JPasswordField password = new JPasswordField(24);
        JPasswordField confirmation = new JPasswordField(24);
        stackedField(form, "Username", username);
        stackedField(form, "Password (at least 8 characters)", password);
        stackedField(form, "Confirm password", confirmation);
        JLabel error = errorLabel();
        form.add(error);

        JButton create = button("Create account", () -> {
            char[] entered = password.getPassword();
            char[] repeated = confirmation.getPassword();
            try {
                if (!Arrays.equals(entered, repeated)) {
                    error.setText("Passwords do not match.");
                    return;
                }
                String name = username.getText().strip();
                accounts.register(name, entered);
                showLogin(name, true);
            } catch (IllegalArgumentException ex) {
                error.setText(ex.getMessage());
            } catch (IOException ex) {
                error.setText("Could not save the account on this PC. Try again.");
            } finally {
                Arrays.fill(entered, '\0');
                Arrays.fill(repeated, '\0');
            }
        });
        form.add(actions(FlowLayout.TRAILING, button("Back to sign in", this::showLogin), create));
        show("", centered(form), create, username);
    }

    private static JPanel centered(JPanel form) {
        JPanel centered = new JPanel(new GridBagLayout());
        centered.setOpaque(false);
        centered.add(form);
        return centered;
    }

    private void showMenu() {
        JPanel content = rows();
        content.add(counts());
        JLabel status = errorLabel();
        if (lot.available() == 0) {
            status.setText("Parking is full. Check out a vehicle to free a slot.");
        } else if (lot.available() == ParkingLot.CAPACITY) {
            status.setForeground(INK);
            status.setText("No vehicles are currently parked.");
        }
        status.setAlignmentX(LEFT_ALIGNMENT);
        content.add(status);

        JButton register = button("Register a vehicle", this::showRegistration);
        register.setEnabled(lot.available() > 0);
        JButton checkout = button("Check out a vehicle", this::showLookup);
        checkout.setEnabled(lot.available() < ParkingLot.CAPACITY);
        JButton registered = button("Registered vehicles", this::showRegisteredVehicles);
        JButton exit = button("Exit", () -> SwingUtilities.getWindowAncestor(this).dispose());
        JPanel shortcuts = new ResponsiveGroup(3, 670);
        for (JButton action : new JButton[]{register, checkout, registered}) {
            action.setBorder(BorderFactory.createEmptyBorder(12, 14, 12, 14));
        }
        shortcuts.add(register);
        shortcuts.add(checkout);
        shortcuts.add(registered);
        shortcuts.setAlignmentX(LEFT_ALIGNMENT);
        content.add(Box.createVerticalStrut(12));
        content.add(shortcuts);
        show("Parking desk", content, register.isEnabled() ? register : checkout,
                null, exit);
    }

    private void showRegisteredVehicles() {
        List<Visit> vehicles = lot.parkedVehicles();
        JButton back = button("Back to parking desk", this::showMenu);
        if (vehicles.isEmpty()) {
            JPanel content = surface(rows(), Color.WHITE);
            content.add(new JLabel("No vehicles are currently parked."));
            show("Registered vehicles", content, back, back, back);
            return;
        }

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Slot", "Owner", "Plate number", "Vehicle type", "Time-in"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (Visit visit : vehicles) {
            model.addRow(new Object[]{"%02d".formatted(visit.slot()), visit.owner(), visit.plate(),
                visit.type().toString(), TIMESTAMP.format(visit.timeIn())});
        }
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setBackground(Color.WHITE);
        table.setForeground(INK);
        table.setFont(new Font(FONT, Font.PLAIN, 14));
        table.setRowHeight(42);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setShowVerticalLines(false);
        table.setGridColor(PAPER);
        table.setSelectionBackground(MINT);
        table.setSelectionForeground(INK);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(0, 34));
        table.getTableHeader().setDefaultRenderer((grid, value, selected, focused, row, column) -> {
            JLabel header = label(value.toString(), 13, Font.BOLD, TEAL);
            header.setOpaque(true);
            header.setBackground(MINT);
            header.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
            return header;
        });
        int[] widths = {62, 165, 128, 130, 190};
        for (int column = 0; column < widths.length; column++) {
            table.getColumnModel().getColumn(column).setPreferredWidth(widths[column]);
        }
        JPanel content = new JPanel(new BorderLayout(0, 10));
        content.setOpaque(false);
        content.add(label(vehicles.size() == 1 ? "1 vehicle parked"
                : vehicles.size() + " vehicles parked", 14, Font.PLAIN, MUTED), BorderLayout.NORTH);
        content.add(scroller(table), BorderLayout.CENTER);
        show("Registered vehicles", content, back, back, back);
    }

    private void showRegistration() {
        JPanel form = surface(rows(), Color.WHITE);
        JTextField owner = new JTextField(24);
        JTextField plate = new JTextField(24);
        JComboBox<VehicleType> type = new JComboBox<>(VehicleType.values());
        type.setSelectedIndex(-1);
        JLabel error = errorLabel();
        field(form, "Vehicle owner's name", owner);
        field(form, "Plate number", plate);
        field(form, "Vehicle type", type);
        form.add(error);

        JButton register = button("Register", () -> {
            try {
                showAvailability(lot.park(owner.getText(), plate.getText(), (VehicleType) type.getSelectedItem()));
            } catch (IllegalArgumentException ex) {
                error.setText(ex.getMessage());
            }
        });
        show("Register a vehicle", form, register, owner, button("Cancel", this::showMenu), register);
    }

    private void showAvailability(Visit visit) {
        JPanel content = rows();
        JPanel summary = new ResponsiveGroup(2, 730);
        summary.setAlignmentX(LEFT_ALIGNMENT);
        summary.add(counts());
        summary.add(vehiclePicture(visit.type()));
        content.add(summary);
        JButton next = button("View parking confirmation", () -> showConfirmation(visit));
        show("Parking availability", content, next, next, next);
    }

    private void showConfirmation(Visit visit) {
        JPanel content = visitDetails(visit);
        JLabel slot = displayLabel("Slot %02d".formatted(visit.slot()), 42, TEAL);
        content.add(slot, 0);
        content.add(Box.createVerticalStrut(10), 1);
        detail(content, "Parking fee", money(visit.type().fee()) + " per visit");
        JButton done = button("Done", this::showMenu);
        show("Vehicle successfully parked", content, done, done, done);
    }

    private void showLookup() {
        JPanel form = surface(rows(), Color.WHITE);
        JTextField plate = new JTextField(24);
        JLabel error = errorLabel();
        field(form, "Vehicle plate number", plate);
        form.add(error);

        JButton lookup = button("Find vehicle", () -> {
            Visit visit = lot.find(plate.getText());
            if (visit == null) {
                error.setText(plate.getText().isBlank() ? "Enter a plate number."
                        : "No parked vehicle matches that plate.");
                return;
            }
            showCheckout(visit);
        });
        show("Vehicle time-out", form, lookup, plate, button("Cancel", this::showMenu), lookup);
    }

    private void showCheckout(Visit visit) {
        JPanel content = rows();
        JPanel checkout = new ResponsiveGroup(2, 840);
        checkout.setAlignmentX(LEFT_ALIGNMENT);
        checkout.add(visitDetails(visit));
        JPanel payment = surface(rows(), MINT);
        detail(payment, "Parking fee", label(money(visit.type().fee()), 16, Font.BOLD, INK));
        JTextField cash = new JTextField(12);
        field(payment, "Cash received (PHP)", cash);
        JLabel change = label("—", 20, Font.BOLD, TEAL);
        detail(payment, "Change", change);
        JLabel error = errorLabel();
        payment.add(error);
        checkout.add(payment);
        content.add(checkout);

        JButton confirm = button("Confirm checkout", () -> {
            try {
                showReceipt(lot.checkout(visit, cashAmount(cash.getText())));
            } catch (IllegalArgumentException ex) {
                error.setText(ex.getMessage());
            }
        });
        confirm.setEnabled(false);
        Runnable update = () -> {
            confirm.setEnabled(false);
            change.setText("—");
            error.setText(" ");
            if (cash.getText().isBlank()) {
                return;
            }
            try {
                BigDecimal difference = cashAmount(cash.getText()).subtract(visit.type().fee());
                if (difference.signum() < 0) {
                    error.setText("Cash received is short by " + money(difference.negate()) + ".");
                    return;
                }
                change.setText(money(difference));
                confirm.setEnabled(true);
            } catch (IllegalArgumentException ex) {
                error.setText(ex.getMessage());
            }
        };
        cash.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent event) { update.run(); }
            @Override public void removeUpdate(DocumentEvent event) { update.run(); }
            @Override public void changedUpdate(DocumentEvent event) { update.run(); }
        });
        show("Review checkout", content, confirm, cash, button("Cancel", this::showMenu), confirm);
    }

    private void showReceipt(Receipt receipt) {
        Visit visit = receipt.visit();
        Duration duration = Duration.between(visit.timeIn(), receipt.timeOut());
        JTextArea ticket = new JTextArea("""
                SmartPark parking receipt

                Owner          %s
                Plate number   %s
                Vehicle type   %s
                Assigned slot  %02d
                Time-in        %s
                Time-out       %s
                Duration       %dh %02dm %02ds

                Parking fee    %s
                Cash received  %s
                Change         %s
                """.formatted(visit.owner(), visit.plate(), visit.type(), visit.slot(),
                TIMESTAMP.format(visit.timeIn()), TIMESTAMP.format(receipt.timeOut()),
                duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart(),
                money(visit.type().fee()), money(receipt.received()), money(receipt.change())));
        ticket.setEditable(false);
        ticket.setBackground(Color.WHITE);
        ticket.setForeground(INK);
        ticket.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        ticket.setMargin(new java.awt.Insets(14, 14, 14, 14));
        ticket.setLineWrap(true);
        ticket.setWrapStyleWord(true);
        ticket.setCaretPosition(0);

        JPanel content = new JPanel(new BorderLayout(0, 8));
        content.setOpaque(false);
        content.add(scroller(ticket), BorderLayout.CENTER);
        JLabel error = errorLabel();
        content.add(error, BorderLayout.SOUTH);
        JButton print = button("Print receipt", () -> {
            try {
                error.setText(" ");
                ticket.print();
            } catch (PrinterException ex) {
                error.setText("Could not print the receipt. Check your printer and try again.");
            }
        });
        JButton done = button("Done", this::showMenu);
        show("Checkout complete", content, done, done, print, done);
    }

    private void show(String title, JComponent content, JButton primary, JComponent focus,
            JButton... actions) {
        removeAll();
        primary.putClientProperty("primaryAction", true);
        JPanel heading = rows();
        heading.add(displayLabel("SmartPark", 19, TEAL));
        screenTitle = null;
        if (!title.isEmpty()) {
            heading.add(Box.createVerticalStrut(6));
            screenTitle = displayLabel(title, 37, INK);
            heading.add(screenTitle);
        }
        add(heading, BorderLayout.NORTH);

        if (content.getLayout() instanceof BoxLayout || content.getLayout() instanceof GridBagLayout) {
            add(scrollBody(content), BorderLayout.CENTER);
        } else {
            add(content, BorderLayout.CENTER);
        }
        if (actions.length > 0) {
            add(actions(FlowLayout.TRAILING, actions), BorderLayout.SOUTH);
        }
        revalidate();
        repaint();

        if (getRootPane() != null) {
            getRootPane().setDefaultButton(primary);
        } else {
            addHierarchyListener(new java.awt.event.HierarchyListener() {
                @Override
                public void hierarchyChanged(HierarchyEvent event) {
                    if (getRootPane() != null) {
                        getRootPane().setDefaultButton(primary);
                        removeHierarchyListener(this);
                    }
                }
            });
        }
        JComponent focusTarget = focus == null ? primary : focus;
        SwingUtilities.invokeLater(() -> {
            if (focusTarget.isShowing()) {
                focusTarget.requestFocusInWindow();
                focusTarget.scrollRectToVisible(new Rectangle(focusTarget.getSize()));
            }
        });
    }

    private JPanel counts() {
        JPanel panel = new JPanel(new BorderLayout(16, 8)) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(DEEP_TEAL);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
                g.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 22, 14, 22));
        int available = lot.available();
        panel.add(counter("Available slots", available, 54), BorderLayout.CENTER);
        JPanel totals = new JPanel(new GridLayout(1, 2, 20, 0));
        totals.setOpaque(false);
        totals.add(counter("Occupied", ParkingLot.CAPACITY - available, 30));
        totals.add(counter("Total slots", ParkingLot.CAPACITY, 30));
        panel.add(totals, BorderLayout.EAST);

        JPanel strip = new JPanel(new GridLayout(1, ParkingLot.CAPACITY, 3, 0));
        strip.setOpaque(false);
        boolean[] occupied = new boolean[ParkingLot.CAPACITY];
        for (Visit visit : lot.parkedVehicles()) {
            occupied[visit.slot() - 1] = true;
        }
        for (boolean parked : occupied) {
            JPanel segment = new JPanel();
            segment.setPreferredSize(new Dimension(0, 6));
            segment.setBackground(parked ? new Color(76, 135, 123) : MINT);
            strip.add(segment);
        }
        panel.add(strip, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel counter(String caption, int count, int size) {
        JPanel panel = rows();
        panel.add(label(caption, 14, Font.BOLD, MINT));
        panel.add(displayLabel(Integer.toString(count), size, Color.WHITE));
        return panel;
    }

    private JScrollPane scrollBody(JComponent content) {
        ViewportBody body = new ViewportBody();
        body.add(content, content.getLayout() instanceof BoxLayout ? BorderLayout.NORTH : BorderLayout.CENTER);
        JScrollPane scroll = new JScrollPane(body, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setUI(new BasicScrollPaneUI());
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.setViewportBorder(BorderFactory.createEmptyBorder());
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        return scroll;
    }

    private static final class ViewportBody extends JPanel implements Scrollable {
        ViewportBody() {
            super(new BorderLayout());
            setOpaque(false);
        }

        @Override public Dimension getPreferredScrollableViewportSize() { return getPreferredSize(); }
        @Override public int getScrollableUnitIncrement(Rectangle visible, int orientation, int direction) { return 24; }
        @Override public int getScrollableBlockIncrement(Rectangle visible, int orientation, int direction) {
            return Math.max(24, visible.height - 24);
        }
        @Override public boolean getScrollableTracksViewportWidth() { return true; }
        @Override public boolean getScrollableTracksViewportHeight() {
            return getParent() != null && getParent().getHeight() > getPreferredSize().height;
        }
    }

    private final class ResponsiveGroup extends JPanel {
        private final int columns;
        private final int breakpoint;

        ResponsiveGroup(int columns, int breakpoint) {
            super(new GridLayout(0, columns, 12, 12));
            this.columns = columns;
            this.breakpoint = breakpoint;
            setOpaque(false);
        }

        private void updateColumns() {
            int width = SmartParkPanel.this.getWidth();
            ((GridLayout) getLayout()).setColumns(width > 0 && width < breakpoint ? columns - 1 : columns);
        }

        @Override public Dimension getPreferredSize() {
            updateColumns();
            return super.getPreferredSize();
        }

        @Override public void doLayout() {
            updateColumns();
            super.doLayout();
        }
    }

    private static JPanel visitDetails(Visit visit) {
        JPanel panel = surface(rows(), Color.WHITE);
        detail(panel, "Owner", visit.owner());
        detail(panel, "Plate number", visit.plate());
        detail(panel, "Vehicle type", visit.type().toString());
        detail(panel, "Assigned slot", "%02d".formatted(visit.slot()));
        detail(panel, "Time-in", TIMESTAMP.format(visit.timeIn()));
        return panel;
    }

    private static JPanel rows() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        return panel;
    }

    private static JPanel surface(JPanel panel, Color background) {
        panel.setOpaque(true);
        panel.setBackground(background);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(LINE, 1, true),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        return panel;
    }

    private static JScrollPane scroller(JComponent content) {
        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(BorderFactory.createLineBorder(LINE));
        scroll.getViewport().setBackground(Color.WHITE);
        return scroll;
    }

    private static JLabel label(String text, int size, int weight, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(FONT, weight, size));
        label.setForeground(color);
        label.setAlignmentX(LEFT_ALIGNMENT);
        return label;
    }

    private static JLabel displayLabel(String text, int size, Color color) {
        JLabel label = label(text, size, Font.BOLD, color);
        label.setFont(new Font(DISPLAY_FONT, Font.BOLD, size));
        return label;
    }

    private static JPanel actions(int alignment, JButton... buttons) {
        JPanel panel = new JPanel(new FlowLayout(alignment, 8, 0));
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setOpaque(false);
        for (JButton button : buttons) {
            panel.add(button);
        }
        return panel;
    }

    private static void stackedField(JPanel panel, String caption, JTextField input) {
        JLabel label = label(caption, 14, Font.BOLD, INK);
        label.setLabelFor(input);
        styleInput(input);
        label.setAlignmentX(LEFT_ALIGNMENT);
        input.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(4));
        panel.add(input);
        panel.add(Box.createVerticalStrut(12));
    }

    private static void field(JPanel panel, String caption, JComponent component) {
        JLabel label = label(caption, 14, Font.PLAIN, INK);
        label.setLabelFor(component);
        if (component instanceof JTextField input && input.isEditable()) {
            styleInput(input);
        } else {
            component.setFont(new Font(FONT, Font.PLAIN, 14));
            component.setForeground(INK);
        }
        addRow(panel, label, component);
    }

    private static void styleInput(JTextField input) {
        input.setFont(new Font(FONT, Font.PLAIN, 15));
        input.setBackground(Color.WHITE);
        input.setForeground(INK);
        Border padding = BorderFactory.createEmptyBorder(5, 9, 5, 9);
        Border normal = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(LINE, 2, true), padding);
        Border focused = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(TEAL, 2, true), padding);
        input.setBorder(normal);
        input.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override public void focusGained(java.awt.event.FocusEvent event) { input.setBorder(focused); }
            @Override public void focusLost(java.awt.event.FocusEvent event) { input.setBorder(normal); }
        });
    }

    private static void detail(JPanel panel, String caption, String value) {
        JTextField display = new JTextField(value);
        display.setEditable(false);
        display.setFocusable(false);
        display.setBorder(BorderFactory.createEmptyBorder());
        display.setOpaque(false);
        field(panel, caption, display);
    }

    private static void detail(JPanel panel, String caption, JLabel value) {
        addRow(panel, label(caption, 14, Font.PLAIN, INK), value);
    }

    private static void addRow(JPanel panel, JLabel label, JComponent component) {
        JPanel row = new JPanel(new BorderLayout(12, 0));
        row.setOpaque(false);
        row.setAlignmentX(LEFT_ALIGNMENT);
        row.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
        label.setPreferredSize(new Dimension(165, 24));
        row.add(label, BorderLayout.WEST);
        row.add(component, BorderLayout.CENTER);
        panel.add(row);
    }

    private static JLabel errorLabel() {
        JLabel label = label(" ", 14, Font.PLAIN, ERROR);
        label.setBorder(BorderFactory.createEmptyBorder(8, 0, 0, 0));
        return label;
    }

    private static JButton button(String text, Runnable action) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics graphics) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                boolean primary = Boolean.TRUE.equals(getClientProperty("primaryAction"));
                boolean active = getModel().isRollover() || getModel().isPressed();
                Color fill = isEnabled() ? (primary ? (active ? INK : TEAL)
                        : (active ? MINT : Color.WHITE)) : PAPER;
                g.setColor(fill);
                g.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 12, 12);
                g.setColor(isFocusOwner() ? TEAL : LINE);
                if (isFocusOwner() || !primary || !isEnabled()) {
                    int inset = isFocusOwner() ? 0 : 2;
                    g.drawRoundRect(inset, inset, getWidth() - 1 - 2 * inset,
                            getHeight() - 1 - 2 * inset, 12, 12);
                }
                g.dispose();
                super.paintComponent(graphics);
            }

            @Override
            public Color getForeground() {
                return Boolean.TRUE.equals(getClientProperty("primaryAction")) && isEnabled()
                        ? Color.WHITE : INK;
            }
        };
        button.setUI(new BasicButtonUI());
        button.setFont(new Font(FONT, Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(11, 17, 11, 17));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setRolloverEnabled(true);
        button.addActionListener(event -> action.run());
        return button;
    }

    private static BigDecimal cashAmount(String input) {
        String amount = input.strip();
        if (!amount.matches("[0-9]+(?:\\.[0-9]{1,2})?")) {
            throw new IllegalArgumentException("Enter a cash amount such as 50 or 50.25.");
        }
        return new BigDecimal(amount).setScale(2);
    }

    private static String money(BigDecimal amount) {
        return "₱" + amount.setScale(2).toPlainString();
    }

    private static JComponent vehiclePicture(VehicleType type) {
        JPanel picture = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Graphics2D g = (Graphics2D) graphics.create();
                g.translate((getWidth() - 200) / 2, (getHeight() - 120) / 2);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(INK);
                g.fillOval(28, 70, 34, 34);
                g.fillOval(138, 70, 34, 34);
                g.setColor(TEAL);
                if (type == VehicleType.CAR) {
                    g.fillRoundRect(12, 54, 176, 31, 16, 16);
                    g.fillPolygon(new int[]{34, 62, 132, 161}, new int[]{57, 28, 28, 57}, 4);
                    g.setColor(PAPER);
                    g.fillPolygon(new int[]{52, 69, 126, 145}, new int[]{53, 35, 35, 53}, 4);
                } else {
                    g.setStroke(new java.awt.BasicStroke(7, java.awt.BasicStroke.CAP_ROUND,
                            java.awt.BasicStroke.JOIN_ROUND));
                    g.drawPolyline(new int[]{45, 73, 109, 138, 155, 130, 117},
                            new int[]{87, 55, 84, 47, 87, 30, 30}, 7);
                    g.fillRoundRect(58, 46, 44, 13, 8, 8);
                    g.fillOval(93, 43, 41, 22);
                }
                g.dispose();
            }
        };
        surface(picture, Color.WHITE);
        picture.setPreferredSize(new Dimension(245, 174));
        JLabel caption = label(type.toString(), 14, Font.BOLD, MUTED);
        caption.setHorizontalAlignment(SwingConstants.CENTER);
        picture.add(caption, BorderLayout.SOUTH);
        return picture;
    }
}
