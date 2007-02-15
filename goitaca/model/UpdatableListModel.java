package goitaca.model;

import javax.swing.DefaultListModel;

public class UpdatableListModel extends DefaultListModel 
{
	private static final long serialVersionUID = -255540968993662640L;

	public void update()
	{
		this.fireContentsChanged(this, 0, this.getSize() - 1);
	}
}
