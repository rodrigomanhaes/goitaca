package goitaca.validation;

import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.JTextComponent;

public class DateRange 
{
	public static boolean validateRange(JTextComponent begin, JTextComponent end, boolean acceptEquals)
	{
		try
		{
			if ("__/__/____".equals(begin.getText()) || "__/__/____".equals(end.getText()))
				return true;
			
			Date beginDate = new SimpleDateFormat("dd/MM/yyyy").parse(begin.getText());
			Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end.getText());
			return acceptEquals ? 
				beginDate.getTime() <= endDate.getTime() :
				beginDate.getTime() < endDate.getTime();
		}
		catch (ParseException e)
		{
			return false;
		}
	}
	
	public static void validateRange(JTextComponent begin, JTextComponent end, 
		String labelBegin, String labelEnd)
	{
		FocusListener listener = new DateValidationFocusListener(begin, end,  
			"A data inicial de aula condensada", "a data final de aula condensada");
		begin.addFocusListener(listener);		
		end.addFocusListener(listener);
	}
}
