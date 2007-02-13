package org.goitaca.action;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.KeyStroke;

/**
 * CommandActionAdapter &eacute; uma classe que adapta a interface de {@link CommandAction} para que
 * n&atilde;o seja necess&aacute;ria a defini&ccedil;&atilde;o imediata do m&eacute;todo <code>actionPerformed</code>.
 * 
 * @version 1.0 RC1
 * @since 1.0
 */

public class CommandActionAdapter extends CommandAction 
{
	private static final long serialVersionUID = 3126841926121735669L;

	/**
     * Cria um objeto CommandActionAdapter.
     * 
     * @param name nome da a&ccedil;&atilde;o
     * @param icon &iacute;cone referente &agrave; a&ccedil;&atilde;o
     * @param keyStroke acelerador que aciona a a&ccedil;&atilde;o
     * @param mnemonic mnem&ocirc;nico do texto (name) que acompanha a a&ccedil;&atilde;o
     * @param toolTip dica a ser apresentada sobre a a&ccedil;&atilde;o
     */
    public CommandActionAdapter(String name, URL icon, KeyStroke keyStroke,
		String mnemonic, String toolTip)
	{
		super(name, icon, keyStroke, mnemonic, toolTip);
	}
	
    /**
     * Especifica a a&ccedil;&atilde;o a ser executada quando do acionamento do comando.
     * 
     * @param e um objeto <code>ActionEvent</code> contendo informa&ccedil;&otilde;es do comando. 
     */
	public void actionPerformed(ActionEvent e)
	{
	}
}