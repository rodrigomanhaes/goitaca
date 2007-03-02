package goitaca.frameadapter;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;

import javax.accessibility.AccessibleContext;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameListener;

/**
 * Adapter (GoF) para que objetos JFrame e JInternalFrame, que possuem interfaces
 * iguais mas incompatíveis, possam ser tratados polimorficamente. 
 * 
 * @author Rodrigo
 * @author Luiz
 */
public interface FrameAdapter
{
	/* JFrame e JInternalFrame */
	public AccessibleContext getAccessibleContext();
	public Container getContentPane();
	public int getDefaultCloseOperation();
	public Component getGlassPane();
	public JMenuBar getJMenuBar();
	public JLayeredPane getLayeredPane();
	public JRootPane getRootPane();
	public void remove(Component comp);
	public void setContentPane(Container contentPane);
	public void setDefaultCloseOperation(int operation);
	public void setGlassPane(Component glassPane);
	public void setJMenuBar(JMenuBar menubar);
	public void setLayeredPane(JLayeredPane layeredPane);
	public void setLayout(LayoutManager manager);
	public void update(Graphics g);
	public void setResizable(boolean resizable);
	public boolean isResizable();
	public void dispose();

	/* Window e JInternalFrame */
	public void pack();
	public void setClosable(boolean closable);
	public boolean isClosable();
	public String getTitle();	
	public void setTitle(String title);
	public void addWindowListener(WindowListener listener);
	public void addInternalFrameListener(InternalFrameListener listener);
	
	/* Component */
	public void setVisible(boolean visible);
	public boolean isVisible();
	public void setLocation(int x, int y);
	public int getWidth();
	public int getHeight();
	public Container getParent();
	
	/* suporte a verificação se a janela está aberta ou fechada */
	public boolean isClosed();
	public void close();
	public Container getFrame();
	public FrameAdapter newInstance();
}
