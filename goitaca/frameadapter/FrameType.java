package goitaca.frameadapter;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public enum FrameType
{
	FRAME(new JFrameAdapter(new JFrame())),
	INTERNAL_FRAME(new JInternalFrameAdapter(new JInternalFrame()));
	
	private FrameAdapter adapter;
	
	private FrameType(FrameAdapter adapter)
	{
		this.adapter = adapter;
	}
	
	public FrameAdapter getFrame()
	{
		return adapter;
	}
}
