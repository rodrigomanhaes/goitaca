package goitaca.renderer.table;

import java.awt.Component;
import java.awt.SystemColor;
import java.util.Arrays;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;

public class FixedTableCellRenderer extends TableCellRendererDecorator 
{
	private static final long serialVersionUID = -8223392127183298183L;

	private List<Integer> fixedRows;
	private boolean fixedAll = false;
	
	public FixedTableCellRenderer()
	{
		super();
		fixedAll = true;
	}
	
	public FixedTableCellRenderer(Integer... fixedRows)
	{
		super();
		this.fixedRows = Arrays.asList(fixedRows);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, 
		boolean isSelected, boolean hasFocus, int row, int column) 
	{
		JLabel component = (JLabel) 
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		if (fixedAll || fixedRows.contains(column))
		{
			component.setBackground(table.getTableHeader().getBackground());
			component.setForeground(table.getTableHeader().getForeground());
			component.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		}
		else
		{
			component.setBackground(SystemColor.text);
			component.setForeground(SystemColor.textText);
		}
		
		return component;
	}
}
