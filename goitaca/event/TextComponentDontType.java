package goitaca.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TextComponentDontType extends KeyAdapter 
{
    private static TextComponentDontType instance = null;
    
    private TextComponentDontType()
    {
        
    }
    
    public static TextComponentDontType instance()
    {
        if (instance == null)
            instance = new TextComponentDontType();
        return instance;
    }
    
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE)
            e.setKeyCode(0);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        e.setKeyChar('\0');
    }
}