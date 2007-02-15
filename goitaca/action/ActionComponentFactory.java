package goitaca.action;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * ActionComponentFactory &eacute; uma classe que fabrica componentes visuais configurados para um
 * espec&iacute;fico. Seu prop&oacute;sito &eacute; evitar a enfadonha 
 * repeti&ccedil;&atilde;o da codifica&ccedil;&atilde;o necess&aacute;ria &agrave; citada 
 * configura&ccedil;&atilde;o.
 * 
 * ActionComponentFactory is a class that creates and configures visual componentes to work with a
 * specific CommandAction. It avoids the codification of this tedious configuration.
 * 
 * @version 1.0 RC1
 * @since 1.0
 * @see CommandAction
 * @see Action
 */
public class ActionComponentFactory
{
    /**
     * Retorna um JButton configurado para uso com um <code>CommandAction</code> espec&iacute;fico.
     * Apesar de <code>CommandAction</code> ser uma subclasse de <code>Action</code> do Swing o uso do m&eacute;todo 
     * <code>setAction()</code> n&atilde;o surtir&aacute; o efeito desejado, sendo necess&aacute;ria 
     * configura&ccedil;&atilde;o adicional, que &eacute; fornecida por este m&eacute;todo.
     * 
     * Returns a JButton configured to work with a specific <code>CommandAction</code>. Besides
     * <code>CommandAction</code> be a subclass of Swing's <code>Action</code>, using the <code>
     * setAction</code> method won't be suitable, being necessary an aditional configuration, that is
     * executed by this method. 
     * 
     * @param action a configura&ccedil;&atilde;o &agrave; qual se submeter&aacute; o bot&atilde;o a ser retornado
     * @return um bot&atilde;o configurado com o {@link CommandAction} passado.
     */
    public static JButton getJButton(CommandAction action)
    {
        JButton button = new JButton();
        
        button.setText((String) action.getValue(Action.NAME));
        button.setIcon((Icon) action.getValue(Action.SMALL_ICON));
        if (!"".equals(action.getValue(Action.MNEMONIC_KEY)) && action.getValue(Action.MNEMONIC_KEY) != null)
            button.setMnemonic(((String) action.getValue(Action.MNEMONIC_KEY)).charAt(0));
        button.setToolTipText((String) action.getValue(Action.SHORT_DESCRIPTION));
        button.addActionListener(action);
        
        return button;
    }
}
