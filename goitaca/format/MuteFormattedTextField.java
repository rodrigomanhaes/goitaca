package org.goitaca.format;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class MuteFormattedTextField extends JFormattedTextField
{
    public MuteFormattedTextField(MaskFormatter mask)
    {
        super(mask);
    }
    
    public MuteFormattedTextField(NumberFormat format)
    {
        super(format);
    }
    
    public MuteFormattedTextField()
    {
        super();
    }
    
    @Override
    public void invalidEdit()
    {
    }

}