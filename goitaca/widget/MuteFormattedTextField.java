package goitaca.widget;

import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 * A JFormattedTextField specialization that does not bip when receives an invalid input.
 * 
 * @author Rodrigo
 *
 */
public class MuteFormattedTextField extends JFormattedTextField
{
	private static final long serialVersionUID = -3662097463767056822L;

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