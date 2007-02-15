package goitaca.renderer.table;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public abstract class TableCellRendererDecorator implements TableCellRenderer 
{
	private TableCellRenderer decored;
	
	public Component getTableCellRendererComponent(JTable table, Object value, 
		boolean isSelected, boolean hasFocus, int row, int column) 
	{
		return decored != null ? 
			decored.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column) :
			new DefaultTableCellRenderer()	
				.getTableCellRendererComponent(table, value, isSelected,
					hasFocus, row, column);
	}
	
	public TableCellRenderer setDecored(TableCellRenderer decored)
	{
		this.decored = decored;
		return this;
	}
}
