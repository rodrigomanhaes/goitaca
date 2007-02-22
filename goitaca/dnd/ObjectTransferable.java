package goitaca.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;

public class ObjectTransferable implements Transferable 
{
	public static DataFlavor FLAVOR = null;
	
	static
	{
		try
		{
			FLAVOR = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	private Object data;
	private JComponent source;
	
	public ObjectTransferable(JComponent source, Object data)
	{
		this.source = source;
		this.data = data;
	}
	
	public JComponent getSource()
	{
		return source;
	}
	
	public Object getTransferData(DataFlavor flavor)
		throws UnsupportedFlavorException, IOException
	{
		if (!isDataFlavorSupported(flavor))
			throw new UnsupportedFlavorException(flavor);
		return data;
	}
	
	public DataFlavor[] getTransferDataFlavors()
	{
		return new DataFlavor[] { FLAVOR };
	}
	
	public boolean isDataFlavorSupported(DataFlavor flavor)
	{
		return FLAVOR.equals(flavor);
	}

}