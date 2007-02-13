package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class CapitalizeVerifier extends VerifierDecorator
{
    public CapitalizeVerifier()
    {
        this(null);
    }

    public CapitalizeVerifier(InputVerifier next)
    {
        super(next);
    }
    
    public boolean verify(JComponent input)
    {
        JTextComponent textComponent = (JTextComponent) input; 
        String str = textComponent.getText();
        String saux = "";
        char prevCh = ' ';
        for (int k = 0; k < str.length(); k++) 
        {
            char nextCh = str.charAt(k);
            if (Character.isLetter(nextCh) && prevCh == ' ')
                nextCh = Character.toUpperCase(nextCh);
            else
                nextCh = Character.toLowerCase(nextCh);
            saux += nextCh;
            prevCh = nextCh;
        }
        
        String[] words = saux.split(" ");
        String[] lowerWords = { "de", "do", "da", "dos", "das", "e", "y", "of", "von", "van", "del", 
            "della", "la" };
        String aux = "";
        for (String s: words)
        {
            for (String lw: lowerWords)
            {
                if (s.toLowerCase().equals(lw))
                {
                    s = s.toLowerCase();
                    break;
                }
            }
            aux += " " + s;
        }
        aux = aux.trim();
        textComponent.setText(aux);
                
        return super.verify(input);
    }

}
