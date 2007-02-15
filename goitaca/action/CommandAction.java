package goitaca.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

/**
 * CommandAction &eacute; a classe respons&aacute;vel por armazenar informa&ccedil;&otilde;es referentes 
 * a um commando espec&iacute;fico (ver padr&atilde;o de projeto Command, da GoF), permitindo, 
 * al&eacute;m disto, especificar caracter&iacute;sticas de representa&ccedil;&atilde;o visual, como 
 * &iacute;cones, mnem&ocirc;nicos, aceleradores e tooltips. 
 * 
 * @version 1.0 RC1
 * @since 1.0
 */
public class CommandAction extends AbstractAction
{
	private static final long serialVersionUID = 676182495685424116L;

	private List<ActionListener> listeners; 
	
	public CommandAction()
	{
		listeners = new ArrayList<ActionListener>();
	}
	
	public CommandAction addActionListener(ActionListener listener)
	{
		listeners.add(listener);
		return this;
	}
	
	/**
     * Cria um CommandAction especificando nome, &iacute;cone, acelerador, mnem&ocirc;nico e tooltip.
     * 
     * @param name nome da a&ccedil;&atilde;o
     * @param icon &iacute;cone referente &agrave; a&ccedil;&atilde;o
     * @param keyStroke acelerador que aciona a a&ccedil;&atilde;o
     * @param mnemonic mnem&ocirc;nico do texto (name) que acompanha a a&ccedil;&atilde;o
     * @param toolTip dica a ser apresentada sobre a a&ccedil;&atilde;o
     */
	public CommandAction(String name, URL icon, KeyStroke keyStroke,
		String mnemonic, String toolTip)
	{
		this();
        if (name != null)
            this.putValue(Action.NAME, name);
		if (icon != null)
			this.putValue(Action.SMALL_ICON, new ImageIcon(icon));
        if (keyStroke != null)
            this.putValue(Action.ACCELERATOR_KEY, keyStroke);
        if (mnemonic != null)
            this.putValue(Action.MNEMONIC_KEY, mnemonic);
        if (toolTip != null)
            this.putValue(Action.SHORT_DESCRIPTION, toolTip);
        
        commandListeners = new ArrayList<CommandActionListener>();
	}
	
	public CommandAction(String name, String mnemonic)
	{
		this(name, null, null, mnemonic, null);
	}
	
	private boolean enabled = true;
	private boolean visible = true;
	private boolean marked = true;
	private boolean markable = false;
	
	public boolean isMarkable() 
	{
		return markable;
	}
	public void setMarkable(boolean markable) 
	{
		this.markable = markable;
	}
	public boolean isMarked() 
	{
		return marked;
	}
	public void setMarked(boolean marked) 
	{
		this.marked = marked;
	}
	public boolean isEnabled() 
	{
		return enabled;
	}
	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}
	public boolean isVisible() 
	{
		return visible;
	}
	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}
	
	private List<CommandActionListener> commandListeners;
	
	public void addCommandActionListener(CommandActionListener listener)
	{
		commandListeners.add(listener);
	}
	
	public void removeCommandActionListener(CommandActionListener listener)
	{
		commandListeners.remove(listener);
	}
	
	protected void notifyListeners(Object source, boolean success, Object cargo)
	{
		for (CommandActionListener listener: commandListeners)
		{
			CommandActionEvent event = new CommandActionEvent(source, success, cargo);
			listener.actionExecuted(event);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		for (ActionListener listener: listeners)
			listener.actionPerformed(e);
	}
}