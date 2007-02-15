package goitaca.frameadapter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;

import javax.accessibility.AccessibleContext;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class JInternalFrameAdapter implements FrameAdapter
{
	private JInternalFrame frame;
	
	private boolean closed = true;
	
	public JInternalFrameAdapter(JInternalFrame frame)
	{
		this.frame = frame;
		frame.addInternalFrameListener(
            new InternalFrameAdapter()
            {
                public void internalFrameOpened(InternalFrameEvent e)
                {
                    closed = false;
                }
                
                public void internalFrameClosed(InternalFrameEvent e)
                {
                    closed = true;
                }
            }
        );
	}

	public AccessibleContext getAccessibleContext()
	{
		return frame.getAccessibleContext();
	}

	public Container getContentPane()
	{
		return frame.getContentPane();
	}

	public int getDefaultCloseOperation()
	{
		return frame.getDefaultCloseOperation();
	}

	public Component getGlassPane()
	{
		return frame.getGlassPane();
	}

	public JMenuBar getJMenuBar()
	{
		return frame.getJMenuBar();
	}

	public JLayeredPane getLayeredPane()
	{
		return frame.getLayeredPane();
	}

	public JRootPane getRootPane()
	{
		return frame.getRootPane();
	}

	public void remove(Component comp)
	{
		frame.remove(comp);
	}

	public void setContentPane(Container contentPane)
	{
		frame.setContentPane(contentPane);
	}

	public void setDefaultCloseOperation(int operation)
	{
		frame.setDefaultCloseOperation(operation);
	}

	public void setGlassPane(Component glassPane)
	{
		frame.setGlassPane(glassPane);
	}

	public void setJMenuBar(JMenuBar menubar)
	{
		frame.setJMenuBar(menubar);
	}

	public void setLayeredPane(JLayeredPane layeredPane)
	{
		frame.setLayeredPane(layeredPane);
	}

	public void setLayout(LayoutManager manager)
	{
		frame.setLayout(manager);
	}

	public void update(Graphics g)
	{
		frame.update(g);
	}

	public boolean isClosed()
	{
		return closed;
	}
	
	public int getHeight()
	{
		return frame.getHeight();
	}

	public int getWidth()
	{
		return frame.getWidth();
	}

	public boolean isClosable()
	{
		return frame.isClosable();
	}

	public boolean isVisible()
	{
		return frame.isVisible();
	}

	public void pack()
	{
		frame.pack();
	}

	public void setClosable(boolean closable)
	{
		frame.setClosable(closable);
	}

	public void setLocation(int x, int y)
	{
		frame.setLocation(x, y);
	}

	public void setVisible(boolean visible)
	{
		frame.setVisible(visible);
	}

	public void close()
	{
		try
		{
			frame.setClosed(true);
		}
		catch (PropertyVetoException e)
		{
			e.printStackTrace(System.err);
		}
	}

	public void setTitle(String title)
	{
		frame.setTitle(title);
	}
	
	public boolean isResizable()
	{
		return frame.isResizable();
	}

	public void setResizable(boolean resizable)
	{
		frame.setResizable(resizable);
	}
	
	public Container getParent()
	{
		return frame.getParent();
	}
	
	public String getTitle()
	{
		return frame.getTitle();
	}
	
	public Container getFrame()
	{
		return frame;
	}

	public void addWindowListener(WindowListener listener)
	{
		throw new IllegalArgumentException("Este objeto não se refere a um Window");
	}

	public void addInternalFrameListener(InternalFrameListener listener)
	{
		frame.addInternalFrameListener(listener);
	}
	
	public FrameAdapter newInstance()
	{
		return new JInternalFrameAdapter(new JInternalFrame());
	}
	
}
