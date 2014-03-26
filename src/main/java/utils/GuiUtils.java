package utils;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GuiUtils {

    private static final int PADDING = 10;

    public static BorderLayout newPaddedBorderLayout() {
	final BorderLayout borderLayout = new BorderLayout();
	borderLayout.setVgap(PADDING);
	borderLayout.setHgap(PADDING);
	return borderLayout;
    }

    public static GridLayout newPaddedGridLayout(final int rows, final int columns) {
	final GridLayout gridLayout = new GridLayout(rows, columns);
	gridLayout.setVgap(PADDING);
	gridLayout.setHgap(PADDING);
	return gridLayout;
    }

    public static JPanel newEmptyBorderedPanel() {
	final JPanel panel = new JPanel();
	panel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
	return panel;
    }
}
