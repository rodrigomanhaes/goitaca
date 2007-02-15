package goitaca.action;

import java.awt.AWTEvent;

public class CommandActionEvent extends AWTEvent 
{
	private static final long serialVersionUID = 5853505488132998150L;
	
	private boolean success;
	private Object cargo;
	
	public Object getCargo() 
	{
		return cargo;
	}

	public void setCargo(Object cargo) 
	{
		this.cargo = cargo;
	}

	public boolean isSuccess() 
	{
		return success;
	}

	public void setSuccess(boolean success) 
	{
		this.success = success;
	}

	public CommandActionEvent(Object source, boolean success, Object cargo)
	{
		super(source, 0);
	}
	
}