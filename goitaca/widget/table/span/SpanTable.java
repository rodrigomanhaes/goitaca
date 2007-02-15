package goitaca.widget.table.span;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class SpanTable extends JTable 
{
	private static final long serialVersionUID = -8081889944873815200L;
	
	public SpanTable()
	{
		super();
		this.init();
	}
	
	private void init()
	{
		this.setUI(new SpanTableUI());
		this.setModel(new DefaultSpanTableModel());
	}
	
	@Override
	public AbstractSpanTableModel getModel()
	{
		return (AbstractSpanTableModel) super.getModel();
	}
	
	@Override
	public void setModel(TableModel model)
	{
		if (!(model instanceof AbstractSpanTableModel))
			throw new IllegalArgumentException("Model must be a SpanTableModel");
		super.setModel(model);
	}
}
