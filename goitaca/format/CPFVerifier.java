package org.goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class CPFVerifier extends VerifierDecorator
{
    private Character placeHolder;
    
    public CPFVerifier()
    {
        this('_', null);    
    }
    
    public CPFVerifier(Character placeHolder)
    {
        this(placeHolder, null);
    }
    
    public CPFVerifier(Character placeHolder, InputVerifier next)
    {
        super(next);
        this.placeHolder = placeHolder;
    }
    
    public boolean verify(JComponent input)
    {
        String data = ((JTextComponent) input).getText();
        
        String s = "" + placeHolder + placeHolder + placeHolder + "." + placeHolder + placeHolder + placeHolder +
            "." + placeHolder + placeHolder + placeHolder + "-" + placeHolder + placeHolder;
        
        if ("".equals(data) || s.equals(data))
            return super.verify(input);
            
        if (!this.validCPF(data))
        {
            this.output.showMessage();
            return false;
        }
        else
            return super.verify(input);
    }
    
    private boolean validCPF(String strCpf)
    {
        int     d1, d2;
        int     digito1, digito2, resto;
        int     digitoCPF;
        String  nDigResult;

        strCpf = strCpf.replaceAll("\\.", "");
        strCpf = strCpf.replaceAll("-", "");
        
        if (("11111111111".equals(strCpf)) ||
            ("22222222222".equals(strCpf)) ||
            ("33333333333".equals(strCpf)) ||
            ("44444444444".equals(strCpf)) ||
            ("55555555555".equals(strCpf)) ||
            ("66666666666".equals(strCpf)) ||
            ("77777777777".equals(strCpf)) ||
            ("88888888888".equals(strCpf)) ||
            ("99999999999".equals(strCpf)))
            return false;
        
        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
        {
           digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

           //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
           d1 = d1 + ( 11 - nCount ) * digitoCPF;

           //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
           d2 = d2 + ( 12 - nCount ) * digitoCPF;
        };

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
           digito1 = 0;
        else
           digito1 = 11 - resto;

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
           digito2 = 0;
        else
           digito2 = 11 - resto;

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
    }
    
}
