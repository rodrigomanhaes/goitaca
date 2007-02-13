package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class EMailVerifier extends VerifierDecorator
{

    public EMailVerifier()
    {
        this(null);
    }
    
    public EMailVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        JTextComponent textComponent = (JTextComponent) input;
        String text = textComponent.getText();
        if (!("".equals(text) || text.contains("@")))
            return false;
        else
            return super.verify(input);
    }

}
