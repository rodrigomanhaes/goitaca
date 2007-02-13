package org.goitaca.action;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBarTool extends JMenuBar
{
	private static final long serialVersionUID = -6453147033270100853L;

	public static JMenu addMenu(Action action, Object[] items)
	{
		JMenu menu = new JMenu((String) action.getValue(Action.NAME));
		if (action.getValue(Action.SMALL_ICON) != null)
			menu.setIcon((Icon) action.getValue(Action.SMALL_ICON));
		menu.setMnemonic(((String) action.getValue(Action.MNEMONIC_KEY)).charAt(0));
		menu.addActionListener(action);
		if (items == null)
			return null;
		for (int i = 0; i < items.length; i++)
		{
			if (items[i] == null)
				menu.addSeparator();
			else if (items[i] instanceof Action)
				menu.add(MenuBarTool.makeMenuItem((Action)items[i]));
			else if (items[i] instanceof JMenu)
				menu.add((JMenu)items[i]);								
		}
		return menu;
	}
	
	private static JMenuItem makeMenuItem(Action action)
	{
		JMenuItem menuItem = new JMenuItem((String) action.getValue(Action.NAME));
		menuItem.setIcon((Icon) action.getValue(Action.SMALL_ICON));
		menuItem.setMnemonic(((String) action.getValue(Action.MNEMONIC_KEY)).charAt(0));
		String toolTip = (String) action.getValue(Action.SHORT_DESCRIPTION);
		menuItem.setToolTipText((toolTip == null) ? menuItem.getText() : toolTip);
		menuItem.setAccelerator((KeyStroke) action.getValue(Action.ACCELERATOR_KEY));
		menuItem.addActionListener(action);
		
		return menuItem;
	}
}