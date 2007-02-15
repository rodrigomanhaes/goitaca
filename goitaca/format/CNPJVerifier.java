package goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;

public class CNPJVerifier extends VerifierDecorator
{
    private Character placeHolder;
    
    public CNPJVerifier()
    {
        this('_', null);    
    }
    
    public CNPJVerifier(Character placeHolder)
    {
        this(placeHolder, null);
    }
    
    public CNPJVerifier(Character placeHolder, InputVerifier next)
    {
        super(next);
        this.placeHolder = placeHolder;
    }
    
    public boolean verify(JComponent input)
    {
        String data = ((JTextComponent) input).getText();
        
        String s = "" + placeHolder + placeHolder + "." + placeHolder + placeHolder + placeHolder +
            "." + placeHolder + placeHolder + placeHolder + "/" + placeHolder + placeHolder + placeHolder + 
            placeHolder + "-" + placeHolder + placeHolder;
        
        if ("".equals(data) || s.equals(data))
            return super.verify(input);
            
        if (!this.validCNPJ(data))
        {
            this.output.showMessage();
            return false;
        }
        else
            return super.verify(input);
    }
    
    private boolean validCNPJ(String str_cnpj)
    {
        @SuppressWarnings("unused") int soma = 0, aux, dig;
        String cnpj_calc = str_cnpj.substring(0,12);

        if ( str_cnpj.length() != 14 )
          return false;

        char[] chr_cnpj = str_cnpj.toCharArray();

        /* Primeira parte */
        for( int i = 0; i < 4; i++ )
          if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
            soma += (chr_cnpj[i] - 48) * (6 - (i + 1)) ;
        for( int i = 0; i < 8; i++ )
          if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 )
            soma += (chr_cnpj[i+4] - 48) * (10 - (i + 1)) ;
        dig = 11 - (soma % 11);

        cnpj_calc += ( dig == 10 || dig == 11 ) ?
                       "0" : Integer.toString(dig);

        /* Segunda parte */
        soma = 0;
        for ( int i = 0; i < 5; i++ )
          if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
            soma += (chr_cnpj[i] - 48) * (7 - (i + 1)) ;
        for ( int i = 0; i < 8; i++ )
          if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 )
            soma += (chr_cnpj[i+5] - 48) * (10 - (i + 1)) ;
        dig = 11 - (soma % 11);
        cnpj_calc += ( dig == 10 || dig == 11 ) ?
                       "0" : Integer.toString(dig);

        return str_cnpj.equals(cnpj_calc);
    }
}
