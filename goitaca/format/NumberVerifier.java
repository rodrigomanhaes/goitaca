package goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class NumberVerifier extends VerifierDecorator
{
    public NumberVerifier()
    {
        this(null);
    }
    
    public NumberVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        String text = ((JTextComponent) input).getText();
        
        if (text == null || "".equals(text))
            return true;
        try
        {
            Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException e)
        {
            this.output.showMessage();
            return false;
        }
    }
    
}
