package goitaca.format;

import javax.swing.InputVerifier;
import javax.swing.JComponent;

public abstract class VerifierDecorator extends InputVerifier
{
    private InputVerifier next;
    protected OutputCommand output;
    
    public VerifierDecorator(InputVerifier next)
    {
        super();
        this.next = next;
    }
    
    public void setOutput(OutputCommand output)
    {
        this.output = output;
    }
    
    public OutputCommand getOutput()
    {
        return this.output;
    }
    
    public InputVerifier getNext()
    {
        return this.next;
    }
    
    public boolean verify(JComponent input)
    {
        if (this.next != null)
            return next.verify(input);
        else
            return true;
    }
}
