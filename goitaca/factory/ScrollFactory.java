package goitaca.factory;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ScrollFactory
{
    public static JScrollPane getScrolledTextArea(int rows, int cols, String title)
    {
        JTextArea area = new JTextArea(rows, cols);
        return scrollComponent(area);
    }
    
    public static JScrollPane scrollComponent(Component component)
    {
        return new JScrollPane(component, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    public static JScrollPane scrollComponent(Component component, int preferredWidth, 
        int preferredHeight)
    {
        JScrollPane scroll = new JScrollPane(component, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        scroll.setPreferredSize(new Dimension(
            preferredWidth != -1 ? preferredWidth : component.getPreferredSize().width, 
            preferredHeight != -1 ? preferredHeight : scroll.getPreferredSize().height));            
        
        return scroll;
    }
}
