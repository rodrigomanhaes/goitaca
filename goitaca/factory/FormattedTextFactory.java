package goitaca.factory;

import goitaca.format.CNPJVerifier;
import goitaca.format.CPFVerifier;
import goitaca.format.CapitalizeVerifier;
import goitaca.format.DateVerifier;
import goitaca.format.EMailVerifier;
import goitaca.format.LetterVerifier;
import goitaca.format.LowerVerifier;
import goitaca.format.MuteFormattedTextField;
import goitaca.format.NumberVerifier;
import goitaca.format.OptionPaneOutputCommand;
import goitaca.format.OutputCommand;
import goitaca.format.TimeVerifier;
import goitaca.format.UpperVerifier;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;


public class FormattedTextFactory
{
	public static JFormattedTextField getIntegerField()
    {
        JFormattedTextField field = new JFormattedTextField(NumberFormat.getIntegerInstance());
        return field;
    }
	
    public static JFormattedTextField getIntegerField(int columns)
    {
        JFormattedTextField field = getIntegerField();
        field.setColumns(columns);
        field.setHorizontalAlignment(SwingConstants.RIGHT);
    	return field; 
    }
    
    public static JFormattedTextField getIntegerField(int columns, int alignment)
    {
        JFormattedTextField field = getIntegerField(columns);
        field.setHorizontalAlignment(alignment);
        return field;
    }

    public static JFormattedTextField getDecimalField()
    {
        DecimalFormat format = new DecimalFormat();
        format.setMinimumFractionDigits(0);
        format.setDecimalSeparatorAlwaysShown(false);
        JFormattedTextField field = new JFormattedTextField(format);
        return field;
    }
    
    public static JFormattedTextField getDecimalField(int columns)
    {
        JFormattedTextField field = getDecimalField();
        field.setColumns(columns);
        return field;
    }
    
    public static JFormattedTextField getDateField()
    {
        OptionPaneOutputCommand command = new OptionPaneOutputCommand();
        command.setMessage("Data inv�lida!");
        return getDateField(command);
    }
    
    public static JFormattedTextField getDateField(OutputCommand output)
    {
        MaskFormatter mask = getMaskFormatter("##/##/####");
        mask.setValueContainsLiteralCharacters(true);
        mask.setPlaceholderCharacter('_');
        DateVerifier verifier = new DateVerifier();
        JFormattedTextField field = new JFormattedTextField(mask);
        verifier.setOutput(output);
        field.setInputVerifier(verifier);
        return field;
    }
    
    public static JFormattedTextField getCPFField()
    {
        OptionPaneOutputCommand command = new OptionPaneOutputCommand();
        command.setMessage("CPF inv�lido!");
        return getCPFField(command);
    }
    
    public static JFormattedTextField getCPFField(OutputCommand output)
    {
        
        MaskFormatter mask = getMaskFormatter("###.###.###-##");
        mask.setValueContainsLiteralCharacters(false);
        mask.setPlaceholderCharacter('_');
        CPFVerifier verifier = new CPFVerifier();
        verifier.setOutput(output);
        JFormattedTextField field = new JFormattedTextField(mask);
        field.setFocusLostBehavior(JFormattedTextField.COMMIT);
        field.setInputVerifier(verifier);
        return field;
    }
    
    public static JFormattedTextField getCNPJField()
    {
        OptionPaneOutputCommand command = new OptionPaneOutputCommand();
        command.setMessage("CNPJ inv�lido!");
        return getCNPJField(command);
    }
    
    public static JFormattedTextField getCNPJField(OutputCommand output)
    {
        MaskFormatter mask = getMaskFormatter("##.###.###/####-##");
        mask.setValueContainsLiteralCharacters(false);
        mask.setPlaceholderCharacter('_');
        CNPJVerifier verifier = new CNPJVerifier();
        verifier.setOutput(output);
        JFormattedTextField field = new JFormattedTextField(mask);
        field.setInputVerifier(verifier);
        return field;
    }
    
    public static JFormattedTextField getTimeField()
    {
    	MaskFormatter mask = getMaskFormatter("##:##");
    	mask.setValueContainsLiteralCharacters(false);
    	mask.setPlaceholderCharacter('_');
    	OptionPaneOutputCommand command = new OptionPaneOutputCommand();
        command.setMessage("Hora inv�lida!");
    	TimeVerifier verifier = new TimeVerifier();
    	verifier.setOutput(command);
    	JFormattedTextField field = new JFormattedTextField(mask);
    	field.setInputVerifier(verifier);
    	return field;
    	
    }
    
    public static JTextField getUpperField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setInputVerifier(new UpperVerifier());
        return field;
    }
    
    public static JTextField getOnlyUpperField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setInputVerifier(new LetterVerifier(new UpperVerifier())); 
        return field;
    }
    
    public static JTextField getLowerField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setInputVerifier(new LowerVerifier());
        return field;
    }
    
    public static JTextField getCapitalizedField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setInputVerifier(new CapitalizeVerifier());
        return field;
    }
    
    public static JFormattedTextField getNumberField(int columns)
    {
        JFormattedTextField field = new JFormattedTextField();
        NumberVerifier verifier = new NumberVerifier();
        verifier.setOutput(new OptionPaneOutputCommand("Este campo requer um valor num�rico"));
        field.setInputVerifier(verifier);
        field.setColumns(columns);
        return field;
    }
    
    public static JFormattedTextField getCEPField()
    {
        
        MaskFormatter mask = getMaskFormatter("#####-###");
        mask.setValueContainsLiteralCharacters(false);
        mask.setPlaceholderCharacter('_');
        JFormattedTextField field = new JFormattedTextField(mask);
        return field;
    }
    
    public static JFormattedTextField getPhoneField()
    {
        JFormattedTextField field = new JFormattedTextField();
        MaskFormatter mask = getMaskFormatter("####-####");
        mask.setValueContainsLiteralCharacters(false);
        mask.setPlaceholderCharacter('_');
        mask.install(field);
        return field;
    }
    
    public static JTextField getEMailField(int columns)
    {
        JTextField field = new JTextField(columns);
        field.setInputVerifier(new EMailVerifier(new LowerVerifier()));
        return field;
    }
    
    public static JFormattedTextField getStandardField()
    {
        JFormattedTextField field = new JFormattedTextField();
        return field;
    }
    
    public static MaskFormatter getMaskFormatter(String mask)
    {
        try
        {
            return (mask != null) ? new MaskFormatter(mask) : new MaskFormatter();
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    public static JFormattedTextField getMaskedField(String sMask)
    {
        MaskFormatter mask = getMaskFormatter(sMask);
        mask.setPlaceholderCharacter('_');
        JFormattedTextField field = new JFormattedTextField(mask);
        return field;
    }
    
}

@SuppressWarnings("serial") 
class JFormattedTextField extends MuteFormattedTextField
{
    public JFormattedTextField(MaskFormatter mask)
    {
        super(mask);
    }
    
    public JFormattedTextField(NumberFormat format)
    {
        super(format);
    }
    
    public JFormattedTextField()
    {
        super();
    }
 
}