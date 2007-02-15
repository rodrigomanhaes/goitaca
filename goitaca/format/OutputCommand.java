package goitaca.format;

public abstract class OutputCommand
{
    protected String message;
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public abstract void showMessage();
}