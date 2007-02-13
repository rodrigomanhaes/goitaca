package org.goitaca.renderer;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class IconListCellRenderer extends JLabel implements ListCellRenderer 
{
	private static final long serialVersionUID = -6315459883115196296L;

	public IconListCellRenderer(Icon icon)
	{
		this.setIcon(icon);
	}
	
	public Component getListCellRendererComponent(JList list, Object value, 
		int index, boolean isSelected, boolean cellHasFocus) 
	{
		this.setText(value.toString());
		this.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
		this.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
		this.setEnabled(list.isEnabled());
		this.setFont(list.getFont());
		this.setOpaque(true);
		
		return this;
	}
}
