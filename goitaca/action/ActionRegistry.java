package goitaca.action;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class ActionRegistry 
{
	private Map<Object, CommandAction> map;
	
	public ActionRegistry()
	{
		map = new HashMap<Object, CommandAction>();
	}
	
	public void register(ActionFactory factory, CommandAction action)
	{
		map.put(factory, action);
	}
	
	public JMenuItem toMenuItem(ActionFactory factory)
	{
		final CommandAction action = map.get(factory);
		
		JMenuItem menuItem = new JMenuItem((String) action.getValue(Action.NAME))
		{
			private static final long serialVersionUID = 3480607932277203043L;
			
			@Override
			public boolean isEnabled()
			{
				return action.isEnabled();
			}
			
			@Override
			public boolean isVisible()
			{
				return action.isVisible();
			}
		};
		menuItem.setIcon((Icon) action.getValue(Action.SMALL_ICON));
		menuItem.setMnemonic(((String) action.getValue(Action.MNEMONIC_KEY)).charAt(0));
		String toolTip = (String) action.getValue(Action.SHORT_DESCRIPTION);
		menuItem.setToolTipText((toolTip == null) ? menuItem.getText() : toolTip);
		menuItem.setAccelerator((KeyStroke) action.getValue(Action.ACCELERATOR_KEY));
		menuItem.addActionListener(action);
	
		return menuItem;
	}
	
	public JMenu toMenu(ActionFactory factory, Object... keyItems)
	{
		final CommandAction action = map.get(factory);
		
		JMenu menu = new JMenu((String) action.getValue(Action.NAME));
		if (action.getValue(Action.SMALL_ICON) != null)
			menu.setIcon((Icon) action.getValue(Action.SMALL_ICON));
		menu.setMnemonic(((String) action.getValue(Action.MNEMONIC_KEY)).charAt(0));
		menu.addActionListener(action);
		menu.addMenuListener(
			new MenuListener()
			{
				public void menuSelected(MenuEvent e) 
				{
					((JMenu) e.getSource()).setEnabled(action.isEnabled());
					((JMenu) e.getSource()).setVisible(action.isVisible());
				}

				public void menuDeselected(MenuEvent e) 
				{
				}

				public void menuCanceled(MenuEvent e) 
				{
				}
			}
		);
		
/*		for (Object object: keyItems)
			menu.add(toMenuItem(object));*/
		
		return menu;
	}
	
	public CommandAction get(Object key)
	{
		return map.get(key);
	}
}
