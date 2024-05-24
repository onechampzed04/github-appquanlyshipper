package model;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private JButton button;

    public ButtonColumn(JTable table, int column) {
        button = new JButton("Custom Action");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button, "Custom Action performed!");
            }
        });

        // Add mouse listener to the button to handle mouse events
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Notify cell editor that editing has stopped when the button is clicked
                fireEditingStopped();
            }
        });

        table.getColumnModel().getColumn(column).setCellRenderer(this);
        table.getColumnModel().getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public boolean stopCellEditing() {
        return true; // Always return true to stop cell editing
    }
}
