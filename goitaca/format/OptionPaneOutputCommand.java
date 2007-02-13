package org.goitaca.format;

import javax.swing.JOptionPane;

public class OptionPaneOutputCommand extends OutputCommand
{
    public OptionPaneOutputCommand(String message)
    {
        super();
        this.message = message;
    }
    
    public OptionPaneOutputCommand()
    {
        super();
    }
    
    public void showMessage()
    {
        JOptionPane.showMessageDialog(null, message);
    }
}
