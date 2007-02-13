package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class LowerVerifier extends VerifierDecorator
{
    public LowerVerifier()
    {
        this(null);
    }
    
    public LowerVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        JTextComponent textComponent = (JTextComponent) input;
        textComponent.setText(textComponent.getText().toLowerCase());
        return super.verify(input);
    }

}
