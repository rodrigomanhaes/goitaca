package goitaca.validation;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

public class DateValidationFocusListener extends FocusAdapter
{
	private JTextComponent component1, component2;
	private String labelBegin, labelEnd;
	
	public DateValidationFocusListener(JTextComponent component1, JTextComponent component2, 
		String labelBegin, String labelEnd)
	{
		this.component1 = component1;
		this.component2 = component2;
		this.labelBegin = labelBegin;
		this.labelEnd = labelEnd;
	}
	
	@Override 
	public void focusLost(FocusEvent e) 
	{
		if (!DateRange.validateRange(component1, component2, true))
		{
			component1.removeFocusListener(this);
			component2.removeFocusListener(this);
			
			JOptionPane.showMessageDialog(null, 
				new StringBuilder().append(labelBegin).append(" deve ser menor que ")
					.append(labelEnd).append("."));
			((JComponent) e.getSource()).requestFocusInWindow();			
	
			SwingUtilities.invokeLater(
				new Runnable()
				{
					public void run()
					{
						component1.addFocusListener(DateValidationFocusListener.this);
						component2.addFocusListener(DateValidationFocusListener.this);
					}
				}
			);
			
		}
	}
}