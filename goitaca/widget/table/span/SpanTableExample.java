package goitaca.widget.table.span;

import goitaca.renderer.table.FixedTableCellRenderer;
import goitaca.utils.SwingUtils;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTable;

public class SpanTableExample extends JFrame 
{
	private static final long serialVersionUID = -4798197363981978091L;
	
	public SpanTableExample()
	{
		super("Span table example");
		
		JTable table = new JTable();
		
		DefaultSpanTableModel model = new DefaultSpanTableModel();
		model.setDataVector(
			new Object[][]
                { {0, 1, 2, 3, 4 },
                  {5, 6, 7, 8, 9 }, 
                	{10, 11, 12, 13, 14 },
                	{15, 16, 17, 18, 19 }},
            new Object[] { "zero", "um", "dois", "três", "quatro"}   
		);
		model.setSpan(2, 0, 2, 1);
		model.setSpan(0, 0, 2, 2);
		model.setSpan(2, 1, 3, 1);
		model.setSpan(1, 2, 4, 2);
		
		table.setModel(model);
		table.setDefaultRenderer(Object.class, new FixedTableCellRenderer());
		
		this.getContentPane().setLayout(new FlowLayout());
		//this.getContentPane().add(table);
		this.getContentPane().add(SwingUtils.scrollComponent(table, 150));
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SwingUtils.centralize(this);
		this.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		new SpanTableExample();
	}
}
