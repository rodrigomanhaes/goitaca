package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

import org.goitaca.utils.CalendarUtils;

public class DateVerifier extends VerifierDecorator
{
    private Character placeHolder;
    
    public DateVerifier()
    {
        this('_', null);
    }
    
    public DateVerifier(Character placeHolder)
    {
        this(placeHolder, null);
    }
    
    public DateVerifier(Character placeHolder, InputVerifier next)
    {
        super(next);
        this.placeHolder = placeHolder;
    }
    
    public boolean verify(JComponent input)
    {
        String data = ((JTextComponent) input).getText();
        
        String s = "" + placeHolder + placeHolder + "/" + placeHolder + placeHolder + "/" + placeHolder + placeHolder + placeHolder + placeHolder;
        if ("".equals(data) || s.equals(data))
            return super.verify(input);
        
        if (!CalendarUtils.isValidDate(data))
        {
           this.output.showMessage();
           return false;
        }
        else
            return super.verify(input);
    }
    
}
