package goitaca.dnd.event;

import java.awt.event.ActionEvent;

public class DataActionEvent extends ActionEvent 
{
	private static final long serialVersionUID = -3135568479074901268L;
	
	private Object data;
	
	public DataActionEvent(Object source, Object data) 
	{
		super(source, 0, null, System.currentTimeMillis(), -1);
		this.data = data;
	}

	public Object getData()
	{
		return data;
	}
	
}
