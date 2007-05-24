package goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class TimeVerifier extends VerifierDecorator 
{

	private Character placeHolder;
	
    public TimeVerifier()
    {
        this('_', null);    
    }
    
    public TimeVerifier(Character placeHolder)
    {
        this(placeHolder, null);
    }
    
    public TimeVerifier(Character placeHolder, InputVerifier next)
    {
        super(next);
        this.placeHolder = placeHolder;
    }
	

    @Override
    public boolean verify(JComponent input)
    {
    	String data = ((JTextComponent) input).getText();
    	
    	String s = ""+ placeHolder + placeHolder + ":" + placeHolder + placeHolder;
    	
    	if ("".equals(data) || s.equals(data))
    		return super.verify(input);
    	
    	if (!this.validTime(data))
    	{
    		this.output.showMessage();
    		return false;
    	}
    	else 
    		return true;
    }
    
    private boolean validTime(String data)
    {
    	if (data.length() == 5)
    	{
    		String sHours = data.substring(0, 2);
    		String sMinutes = data.substring(3, 5);
    		
    		int hours, minutes;
    		
    		try
    		{
    			hours = Integer.parseInt(sHours);
    			minutes = Integer.parseInt(sMinutes);
    		}
    		catch (NumberFormatException e)
    		{
    			return false;
    		}
    		
    		return hours >= 0 && hours < 24 && minutes >= 0 && minutes < 60;
    	}
    	return false;
    }
    
    public static String mask(String horario)
    {
    	return new StringBuilder()
    		.append(horario.charAt(0))
    		.append(horario.charAt(1))
    		.append(":")
    		.append(horario.charAt(2))
    		.append(horario.charAt(3))
    		.toString();
    }
}
