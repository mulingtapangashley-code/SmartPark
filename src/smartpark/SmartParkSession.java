package smartpark;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicProgressBarUI;
import javax.swing.table.DefaultTableCellRenderer;

final class SmartParkSession {
    private static final Color INK = new Color(24, 35, 21);
    private static final Color FOREST = new Color(56, 82, 49);
    private static final Color SAGE = Color.decode("#E3E9D6");
    private static final Color MOSS = Color.decode("#5F7A52");
    private static final Color PAPER = new Color(247, 248, 242);
    private static final Color SURFACE = new Color(255, 253, 248);
    private static final Color LINE = new Color(179, 193, 166);
    private static final String QUIET_ACTION = "SmartPark.quietAction";
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter
            .ofPattern("MMM d, uuuu, h:mm:ss a").withZone(ZoneId.systemDefault());

    final ParkingLot lot = new ParkingLot();
    final Accounts accounts = new Accounts();

    void applyLoginTheme(JFrame frame, JPanel card, JButton exit) {
        paintSurface((JComponent) frame.getContentPane(), SAGE, MOSS, 0);
        paintSurface(card, card.getBackground(), null, 22);
        exit.putClientProperty(QUIET_ACTION, true);
    }

    void applyDeskTheme(JPanel capacity, JButton registered, JButton exit) {
        paintSurface(capacity, SAGE, MOSS, 20);
        registered.putClientProperty(QUIET_ACTION, true);
        exit.putClientProperty(QUIET_ACTION, true);
    }

    void prepare(JFrame frame, JButton primary, JComponent focus) {
        frame.getContentPane().setBackground(PAPER);
        for (Component component : frame.getContentPane().getComponents()) {
            style(component, primary);
        }
        frame.getRootPane().setDefaultButton(primary);
        frame.pack();
        Dimension minimum = frame.getMinimumSize();
        frame.setMinimumSize(new Dimension(Math.max(500, minimum.width), minimum.height));
        frame.setSize(Math.max(720, frame.getWidth()), Math.max(450, frame.getHeight()));
        if (focus != null) {
            SwingUtilities.invokeLater(focus::requestFocusInWindow);
        }
    }

    void open(JFrame current, JFrame next) {
        Dimension minimum = next.getMinimumSize();
        next.setBounds(current.getX(), current.getY(),
                Math.max(current.getWidth(), minimum.width),
                Math.max(current.getHeight(), minimum.height));
        current.dispose();
        next.setVisible(true);
    }

    void showCounts(JLabel available, JLabel occupied, JProgressBar meter) {
        int open = lot.available();
        available.setText(open + " of " + ParkingLot.CAPACITY + " slots available");
        occupied.setText((ParkingLot.CAPACITY - open) + " occupied");
        meter.setValue(ParkingLot.CAPACITY - open);
        meter.getAccessibleContext().setAccessibleName("Occupied parking slots");
    }

    void onTextChange(JTextField field, Runnable update) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent event) { update.run(); }
            @Override public void removeUpdate(DocumentEvent event) { update.run(); }
            @Override public void changedUpdate(DocumentEvent event) { update.run(); }
        });
    }

    String timestamp(Instant time) {
        return TIMESTAMP.format(time);
    }

    BigDecimal cashAmount(String input) {
        String amount = input.strip();
        if (!amount.matches("[0-9]+(?:\\.[0-9]{1,2})?")) {
            throw new IllegalArgumentException("Enter a cash amount such as 50 or 50.25.");
        }
        return new BigDecimal(amount).setScale(2);
    }

    String money(BigDecimal amount) {
        return "₱" + amount.setScale(2).toPlainString();
    }

    String receiptText(ParkingLot.Receipt receipt) {
        var visit = receipt.visit();
        var duration = Duration.between(visit.timeIn(), receipt.timeOut());
        return """
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
                timestamp(visit.timeIn()), timestamp(receipt.timeOut()),
                duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart(),
                money(visit.type().fee()), money(receipt.received()), money(receipt.change()));
    }

    Icon vehicleIcon(ParkingLot.VehicleType type) {
        return new Icon() {
            @Override public int getIconWidth() { return 200; }
            @Override public int getIconHeight() { return 96; }

            @Override
            public void paintIcon(Component component, Graphics graphics, int x, int y) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.translate(x, y - 12);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setColor(INK);
                g.fillOval(28, 70, 34, 34);
                g.fillOval(138, 70, 34, 34);
                g.setColor(FOREST);
                if (type == ParkingLot.VehicleType.CAR) {
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
    }

    private void style(Component component, JButton primary) {
        if (component instanceof JButton button) {
            if (button.getFont() instanceof UIResource) {
                button.setFont(new Font("Segoe UI", Font.BOLD, 14));
            }
            if (button.getBackground() instanceof UIResource) {
                button.setBackground(button == primary ? FOREST : SURFACE);
            }
            if (button.getForeground() instanceof UIResource) {
                button.setForeground(button == primary ? Color.WHITE : INK);
            }
            if (button.getBorder() instanceof UIResource) {
                button.setUI(new ActionButtonUI(button == primary));
                button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
                button.setContentAreaFilled(false);
                button.setOpaque(false);
                button.setRolloverEnabled(true);
            }
        } else if (component instanceof JTextField field) {
            styleField(field);
        } else if (component instanceof JComboBox<?> combo) {
            if (combo.getFont() instanceof UIResource) {
                combo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
            }
            if (combo.getBackground() instanceof UIResource) {
                combo.setBackground(SURFACE);
            }
        } else if (component instanceof JTextArea text) {
            if (text.getFont() instanceof UIResource) {
                text.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
            }
            if (text.getMargin() instanceof UIResource) {
                text.setMargin(new java.awt.Insets(12, 12, 12, 12));
            }
        } else if (component instanceof JTable table) {
            styleTable(table);
        } else if (component instanceof JProgressBar meter) {
            meter.setUI(new CapacityMeterUI());
            meter.setBorderPainted(false);
            meter.setOpaque(false);
            if (meter.getForeground() instanceof UIResource) {
                meter.setForeground(FOREST);
            }
            if (meter.getBackground() instanceof UIResource) {
                meter.setBackground(PAPER);
            }
        } else if (component instanceof JLabel label && label.getFont() instanceof UIResource) {
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        } else if (component instanceof JScrollPane scroll) {
            scroll.setBorder(BorderFactory.createLineBorder(LINE));
            scroll.getViewport().setBackground(Color.WHITE);
            style(scroll.getViewport().getView(), primary);
        } else if (component instanceof JPanel panel) {
            if (panel.isOpaque() && !(panel.getBackground() instanceof UIResource) && panel.getBorder() == null) {
                paintSurface(panel, panel.getBackground(), null, 18);
            }
            for (Component child : panel.getComponents()) {
                style(child, primary);
            }
        }
    }

    private static void styleField(JTextField field) {
        if (field.getFont() instanceof UIResource) {
            field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        }
        if (!(field.getBorder() instanceof UIResource)) {
            return;
        }
        var padding = BorderFactory.createEmptyBorder(4, 9, 4, 9);
        var idle = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(LINE, 2, true), padding);
        var focused = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(FOREST, 2, true), padding);
        field.setBorder(idle);
        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent event) { field.setBorder(focused); }
            @Override public void focusLost(FocusEvent event) { field.setBorder(idle); }
        });
    }

    private static void styleTable(JTable table) {
        if (table.getFont() instanceof UIResource) {
            table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        }
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setGridColor(SAGE);
        table.setSelectionBackground(SAGE);
        table.setSelectionForeground(INK);
        var header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 36));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable grid, Object value,
                    boolean selected, boolean focused, int row, int column) {
                super.getTableCellRendererComponent(grid, value, selected, focused, row, column);
                setFont(new Font("Segoe UI", Font.BOLD, 13));
                setForeground(FOREST);
                setBackground(SAGE);
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
                return this;
            }
        });
    }

    private static void paintSurface(JComponent component, Color start, Color end, int arc) {
        component.setOpaque(false);
        component.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component owner, Graphics graphics, int x, int y, int width, int height) {
                Graphics2D g = (Graphics2D) graphics.create();
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (end == null) {
                    g.setColor(start);
                } else {
                    g.setPaint(new GradientPaint(x, y, start, x + width, y + height, end));
                }
                g.fillRoundRect(x, y, width, height, arc, arc);
                g.dispose();
            }
        });
    }

    private static final class ActionButtonUI extends BasicButtonUI {
        private final boolean primary;

        ActionButtonUI(boolean primary) {
            this.primary = primary;
        }

        @Override
        public void paint(Graphics graphics, JComponent component) {
            JButton button = (JButton) component;
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            boolean quiet = Boolean.TRUE.equals(button.getClientProperty(QUIET_ACTION));
            boolean active = button.getModel().isRollover() || button.getModel().isPressed();
            int width = button.getWidth() - 4;
            int height = button.getHeight() - 4;
            if (!quiet || active) {
                g.setColor(!button.isEnabled() ? PAPER : active
                        ? (primary ? button.getBackground().darker() : SAGE) : button.getBackground());
                g.fillRoundRect(2, 2, width, height, 13, 13);
            }
            if (button.isFocusOwner() || (!primary && !quiet)) {
                g.setColor(button.isFocusOwner() ? MOSS : LINE);
                g.drawRoundRect(1, 1, width + 1, height + 1, 14, 14);
            }
            g.dispose();
            super.paint(graphics, component);
        }

        @Override
        protected void paintFocus(Graphics graphics, javax.swing.AbstractButton button,
                java.awt.Rectangle view, java.awt.Rectangle text, java.awt.Rectangle icon) {
            // The outline is painted with the button surface.
        }
    }

    private static final class CapacityMeterUI extends BasicProgressBarUI {
        @Override
        protected void paintDeterminate(Graphics graphics, JComponent component) {
            Graphics2D g = (Graphics2D) graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = component.getWidth();
            int height = component.getHeight();
            g.setColor(component.getBackground());
            g.fillRoundRect(0, 0, width, height, height, height);
            int filled = (int) Math.round(width * progressBar.getPercentComplete());
            if (filled > 0) {
                g.setColor(component.getForeground());
                g.fillRoundRect(0, 0, filled, height, height, height);
            }
            g.dispose();
        }
    }
}
