package goitaca.frameadapter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.accessibility.AccessibleContext;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameListener;

public class JFrameAdapter implements FrameAdapter 
{
	private JFrame frame;
	
	private boolean closed = true;
	
	public JFrameAdapter(JFrame frame)
	{
		this.frame = frame;
		frame.addWindowListener(
            new WindowAdapter()
            {
				public void windowClosed(WindowEvent e)
				{
					closed = true;
				}

				public void windowOpened(WindowEvent e)
				{
					closed = false;
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
		return false;
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
		frame.setVisible(false);
		frame.dispose();
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
		frame.addWindowListener(listener);
	}

	public void addInternalFrameListener(InternalFrameListener listener)
	{
		throw new IllegalArgumentException("Este objeto não se refere a um JInternalFrame");
	}

	public FrameAdapter newInstance()
	{
		return new JFrameAdapter(new JFrame());
	}
	
}
