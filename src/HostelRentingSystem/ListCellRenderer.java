package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class ListCellRenderer extends DefaultListCellRenderer {
    private final JSeparator separator;

    public ListCellRenderer() {
        separator = new JSeparator(JSeparator.HORIZONTAL);
    }

    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus), BorderLayout.CENTER);
        if (index < list.getModel().getSize() - 1) {
            panel.add(separator, BorderLayout.SOUTH);
        }
        return panel;
    }
}
