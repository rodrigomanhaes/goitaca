package goitaca.frameadapter;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public enum FrameType
{
	FRAME(JFrameAdapter.class, JFrame.class),
	INTERNAL_FRAME(JInternalFrameAdapter.class, JInternalFrame.class),
	DIALOG(JDialogAdapter.class, JDialog.class);
	
	private static final Log log = LogFactory.getLog(FrameType.class);
	
	private Class<? extends FrameAdapter> adapterClass;
	private Class<?> frameClass;
	
	private FrameType(Class<? extends FrameAdapter> adapterClass, Class<?> frameClass)
	{
		this.adapterClass = adapterClass;
		this.frameClass = frameClass;
	}
	
	public FrameAdapter newFrame()
	{
		try
		{
			return adapterClass.getConstructor(frameClass).newInstance(frameClass.newInstance());
		}
		catch (Exception e)
		{
			log.error(e);
			return null;
		}
	}
}
