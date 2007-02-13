package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class LetterVerifier extends VerifierDecorator
{
    
    public LetterVerifier()
    {
        this(null);
    }
    
    public LetterVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        JTextComponent textComponent = (JTextComponent) input;
        String text = textComponent.getText();
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < text.length(); i++)
            if (Character.isLetter(text.charAt(i)))
                result.append(text.charAt(i));
        textComponent.setText(result.toString());
        return super.verify(input);
    }

}
