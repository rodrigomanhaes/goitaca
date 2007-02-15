package goitaca.frameadapter;

import goitaca.utils.SwingUtils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowListener;

import javax.accessibility.AccessibleContext;
import javax.swing.JLayeredPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.InternalFrameListener;

public class DefaultFrame implements FrameAdapter
{
	private FrameAdapter adapter;
	private FrameType type;
	
    private boolean centralized = true;
    
	public DefaultFrame(FrameType type) 
	{
		this.type = type;
    	adapter = type.getFrame().newInstance();
	}

	public void initialize()
	{
		this.build();
		adapter.setVisible(true);
		this.checkProperties();
	}
	
	public FrameType getType()
	{
		return type;
	}
	
	private void checkProperties()
    {
        this.checkCentralized();
    }
    
    private void checkCentralized()
    {
        if (this.isCentralized())
            adapter.setLocation(
                (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - adapter.getWidth() / 2),
                (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - adapter.getHeight() / 2));
    }
    
    private JPanel topPanel1 = new JPanel();
    private JPanel topPanel2 = new JPanel();
    private JPanel bottomPanel1 = new JPanel();
    private JPanel bottomPanel2 = new JPanel();
    private JPanel centerPanel = new JPanel();
    
    private void build()
    {
        adapter.setClosable(true);
        
        Container container = adapter.getContentPane();
        
        container.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        
        if (topPanel1.getComponentCount() > 0)
        {
        	gbc.gridy++;
	        //topPanel1.setBorder(BorderFactory.createTitledBorder(""));
        	container.add(topPanel1, gbc);
        }
        
        if (topPanel2.getComponentCount() > 0)
        {
	        gbc.gridy++;
	        container.add(topPanel2, gbc);
        }
        
        if (centerPanel.getComponentCount() > 0)
        {
	        gbc.gridy++;
	        container.add(centerPanel, gbc);
        }
        
        if (bottomPanel1.getComponentCount() > 0)
        {
	        gbc.gridy++;
	        container.add(bottomPanel1, gbc);        
        }
        
        if (bottomPanel2.getComponentCount() > 0)
        {
	        gbc.gridy++;
	        container.add(bottomPanel2, gbc);
        }
    }
    
    public void setTopComponent1(Component component)
    {
    	doIt(topPanel1, component);
    	}
    
    public void setTopComponent2(Component component)
    {
    	doIt(topPanel2, component);
    }
    
    public void setBottomComponent1(Component component)
    {
    	doIt(bottomPanel1, component);
    }
    
    public void setBottomComponent2(Component component)
    {
    	doIt(bottomPanel2, component);
    }
    
    public void setCenterComponent(Component component)
    {
    	doIt(centerPanel, component);
    }
    
    private void doIt(JPanel panel, Component component)
    {
    	panel.setLayout(new GridBagLayout());
    	SwingUtils.addGridBagComponent(panel, component, 0, 0, 1, 1, 
			GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, 
			100, 100, new Insets(0,0,0,0));
    }
    
    
    /*
     * (non-Javadoc)
     * @see br.uenf.ururau.gui.frameadapter.FrameAdapter#setTitle(java.lang.String)
     */

	/*
     * properties
     */
    
    public boolean isCentralized()
    {
        return this.centralized;
    }
    
    public boolean isClosed()
    {
        return adapter.isClosed();
    }
    
    public void setCentralized(boolean centralized)
    {
        this.centralized = centralized;
    }
	
    public AccessibleContext getAccessibleContext()
	{
		return adapter.getAccessibleContext();
	}

	public Container getContentPane()
	{
		return adapter.getContentPane();
	}

	public int getDefaultCloseOperation()
	{
		return adapter.getDefaultCloseOperation();
	}

	public Component getGlassPane()
	{
		return adapter.getGlassPane();
	}

	public JMenuBar getJMenuBar()
	{
		return adapter.getJMenuBar();
	}

	public JLayeredPane getLayeredPane()
	{
		return adapter.getLayeredPane();
	}

	public JRootPane getRootPane()
	{
		return adapter.getRootPane();
	}

	public void remove(Component comp)
	{
		adapter.remove(comp);
	}

	public void setContentPane(Container contentPane)
	{
		adapter.setContentPane(contentPane);
	}

	public void setDefaultCloseOperation(int operation)
	{
		adapter.setDefaultCloseOperation(operation);
	}

	public void setGlassPane(Component glassPane)
	{
		adapter.setGlassPane(glassPane);
	}

	public void setJMenuBar(JMenuBar menubar)
	{
		adapter.setJMenuBar(menubar);
	}

	public void setLayeredPane(JLayeredPane layeredPane)
	{
		adapter.setLayeredPane(layeredPane);
	}

	public void setLayout(LayoutManager manager)
	{
		adapter.setLayout(manager);
	}

	public void update(Graphics g)
	{
		adapter.update(g);
	}
	
	public int getHeight()
	{
		return adapter.getHeight();
	}

	public int getWidth()
	{
		return adapter.getWidth();
	}

	public boolean isClosable()
	{
		return adapter.isClosable();
	}

	public boolean isVisible()
	{
		return adapter.isVisible();
	}

	public void pack()
	{
		adapter.pack();
	}

	public void setClosable(boolean closable)
	{
		adapter.setClosable(closable);
	}

	public void setLocation(int x, int y)
	{
		adapter.setLocation(x, y);
	}

	public void setVisible(boolean visible)
	{
		adapter.setVisible(visible);
	}
    
	public void setTitle(String title)
	{
		adapter.setTitle(title);
	}

	public void close()
	{
		adapter.close();
	}

	public boolean isResizable()
	{
		return adapter.isResizable();
	}

	public void setResizable(boolean resizable)
	{
		adapter.setResizable(resizable);
	}

	public Container getParent()
	{
		return adapter.getParent();
	}

	public String getTitle()
	{
		return adapter.getTitle();
	}

	public Container getFrame()
	{
		return adapter.getFrame();
	}

	public void addWindowListener(WindowListener listener)
	{
		adapter.addWindowListener(listener);
	}

	public void addInternalFrameListener(InternalFrameListener listener)
	{
		adapter.addInternalFrameListener(listener);
	}
	
	public FrameAdapter newInstance()
	{
		return adapter.newInstance();
	}

}