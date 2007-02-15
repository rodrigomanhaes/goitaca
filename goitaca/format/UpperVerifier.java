package goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class UpperVerifier extends VerifierDecorator
{
    
    public UpperVerifier()
    {
        this(null);
    }
    
    public UpperVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        JTextComponent textComponent = (JTextComponent) input;
        textComponent.setText(textComponent.getText().toUpperCase());
        return super.verify(input);
    }
}
