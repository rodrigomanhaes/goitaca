package goitaca.dnd;

import goitaca.dnd.event.DataActionEvent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

public class ObjectTransferHandler extends TransferHandler 
{
	private static final long serialVersionUID = 6116490790882531754L;
	
	private Map<JComponent, ActionListener> importActions;
	private Map<JComponent, DataRetrieval> retrievalActions;
	private Map<JComponent, ActionListener> exportActions;
	private Map<JComponent, Integer> sourceActions;
	private int action;
	
	public ObjectTransferHandler(int action)
	{
		super();
		this.action = action;
		this.importActions = new HashMap<JComponent, ActionListener>();
		this.retrievalActions = new HashMap<JComponent, DataRetrieval>();
		this.exportActions = new HashMap<JComponent, ActionListener>();
		this.sourceActions = new HashMap<JComponent, Integer>();
	}
	
	@Override
	public boolean importData(JComponent component, Transferable transferable)
	{
		if (!importActions.keySet().contains(component))
			return false;
		
		try
		{
			DataRetrieval dataRetrieval = (DataRetrieval) 
				transferable.getTransferData(ObjectTransferable.FLAVOR);
			if (dataRetrieval.getSource().equals(component))
				return false;
			Object data = dataRetrieval.getData();
			importActions.get(component).actionPerformed(new DataActionEvent(component, data));
			return true;
		}
		catch (RuntimeException e)
		{
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
			return false;
		}
	}
	
	@Override
	public boolean canImport(JComponent component, DataFlavor[] transferFlavors)
	{
		if (!importActions.keySet().contains(component))
			return false;
		for (DataFlavor flavor: transferFlavors)
			if (ObjectTransferable.FLAVOR.equals(flavor))
				return true;
		return false;
	}
	
	@Override
	public int getSourceActions(JComponent component)
	{
		Integer action = sourceActions.get(component);
		return action != null ?
			action : this.action;
	}
	
	@Override
	protected Transferable createTransferable(JComponent component)
	{
		DataRetrieval retrieval = retrievalActions.get(component);
		return  retrieval != null ?
			new ObjectTransferable(component, retrieval) : null;
	}
	
	@Override
	protected void exportDone(JComponent source, Transferable transferable, int action)
	{
		if (!exportActions.keySet().contains(source))
			return;
		
		if (sourceActions.get(source) != null && sourceActions.get(source) != action)
			return;
		else if (action != this.action)
			return;
		
		if (!source.equals(((ObjectTransferable) transferable).getSource()))
			return;
		
		try
		{
			DataRetrieval dataRetrieval = (DataRetrieval) 
				transferable.getTransferData(ObjectTransferable.FLAVOR);
			Object data = dataRetrieval.getData();
			exportActions.get(source).actionPerformed(new DataActionEvent(source, data));
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	/* adders and removers */
	public void addImportAction(JComponent component, ActionListener action)
	{
		importActions.put(component, action);
	}
	
	public void removeImportAction(JComponent component)
	{
		importActions.remove(component);
	}
	
	public void addExportAction(JComponent component, ActionListener action)
	{
		exportActions.put(component, action);
	}
	
	public void removeExportAction(JComponent component)
	{
		exportActions.remove(component);
	}
	
	public void addSourceAction(JComponent component, int action)
	{
		sourceActions.put(component, action);
	}
	
	public void removeSourceAction(JComponent component)
	{
		sourceActions.remove(component);
	}
	
	public void addRetrievalAction(JComponent component, DataRetrieval dataRetrieval)
	{
		retrievalActions.put(component, dataRetrieval);
	}
	
	public void removeRetrievalAction(JComponent component)
	{
		retrievalActions.remove(component);
	}
}