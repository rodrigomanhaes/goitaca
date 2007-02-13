package org.goitaca.factory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import org.goitaca.format.CNPJVerifier;
import org.goitaca.format.CPFVerifier;
import org.goitaca.format.CapitalizeVerifier;
import org.goitaca.format.DateVerifier;
import org.goitaca.format.EMailVerifier;
import org.goitaca.format.MuteFormattedTextField;
import org.goitaca.format.LetterVerifier;
import org.goitaca.format.LowerVerifier;
import org.goitaca.format.NumberVerifier;
import org.goitaca.format.OptionPaneOutputCommand;
import org.goitaca.format.OutputCommand;
import org.goitaca.format.UpperVerifier;

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
        command.setMessage("Data inválida!");
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
        command.setMessage("CPF inválido!");
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
        field.setInputVerifier(verifier);
        return field;
    }
    
    public static JFormattedTextField getCNPJField()
    {
        OptionPaneOutputCommand command = new OptionPaneOutputCommand();
        command.setMessage("CNPJ inválido!");
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
        verifier.setOutput(new OptionPaneOutputCommand("Este campo requer um valor numérico"));
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