package org.goitaca.renderer;

import java.awt.Component;
import java.awt.SystemColor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

public class FixedTableCellRenderer extends DefaultTableCellRenderer 
{
	private static final long serialVersionUID = -8223392127183298183L;

	private List<Integer> fixedRows;
	
	public FixedTableCellRenderer(Integer... fixedRows)
	{
		super();
		this.fixedRows = Arrays.asList(fixedRows);
	}
	
	public Component getTableCellRendererComponent(JTable table, Object value, 
		boolean isSelected, boolean hasFocus, int row, int column) 
	{
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (fixedRows.contains(column))
		{
			this.setBackground(table.getTableHeader().getBackground());
			this.setForeground(table.getTableHeader().getForeground());
			this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		}
		else
		{
			this.setBackground(SystemColor.text);
			this.setForeground(SystemColor.textText);
		}
		
		return this;
	}
	
	

}
