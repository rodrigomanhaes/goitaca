package goitaca.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

public class SwingUtils
{
    public static GridBagConstraints gbc = new GridBagConstraints();
    
    public static void addGridBagComponent(Container container,
        Component component, int gridx, int gridy, int gridwidth,
        int gridheight, int anchor, int fill, double weightx, 
        double weighty, Insets insets)
    {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = anchor;
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        if (insets != null)
            gbc.insets = insets;
        container.add(component, gbc);
    }

    /* sem insets e sem weight */
    public static void addGridBagComponent(Container container,
        Component component, int gridx, int gridy, int gridwidth,
        int gridheight, int anchor, int fill)
    {
        addGridBagComponent(container, component, gridx, gridy, gridwidth,
            gridheight, anchor, fill, 0, 0, null);
    }
    
    /* sem insets */
    public static void addGridBagComponent(Container container,
        Component component, int gridx, int gridy, int gridwidth,
        int gridheight, int anchor, int fill, double weightx, double weighty)
    {
        addGridBagComponent(container, component, gridx, gridy, gridwidth,
            gridheight, anchor, fill, weightx, weighty, null);
    }
    
    /* sem weight */
    public static void addGridBagComponent(Container container,
        Component component, int gridx, int gridy, int gridwidth,
        int gridheight, int anchor, int fill, Insets insets)
    {
        addGridBagComponent(container, component, gridx, gridy, gridwidth,
            gridheight, anchor, fill, 0, 0, insets);
    }
    
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill,
        double weightx, double weighty, Insets insets)
    {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = labelWidth;
        gbc.gridheight = gridHeight;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        if (insets != null)
            gbc.insets = new Insets(insets.top, insets.left, insets.bottom, 3);
        container.add(label, gbc);

        gbc.gridx += labelWidth;
        gbc.gridwidth = textFieldWidth;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = fill;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        if (insets != null)
            gbc.insets = insets;
        container.add(textField, gbc);
    }
    
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill,
        double weightx, double weighty)
    {
    	addGridBagLabelTextField(container, label, textField, gridx, gridy, labelWidth, 
			textFieldWidth, gridHeight, fill, weightx, weighty, null);
    }

    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill,
        double weightx, double weighty)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, gridx, 
                gridy, labelWidth, textFieldWidth, gridHeight, fill, weightx, weighty);
    }
    
    /* sem weight, sem fill e sem insets */
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight)
    {
        addGridBagLabelTextField(container, label, textField, gridx, gridy,
            labelWidth, textFieldWidth, gridHeight, GridBagConstraints.NONE, 
            0, 0, null);
    }

    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, gridx, 
                gridy, labelWidth, textFieldWidth, gridHeight);
    }
    
    /* sem weight e sem fill */
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, Insets insets)
    {
    	addGridBagLabelTextField(container, label, textField, gridx, gridy, 
			labelWidth, textFieldWidth, gridHeight, GridBagConstraints.NONE, 
			0, 0, insets);
    }
    
    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, Insets insets)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, gridx, 
            gridy, labelWidth, textFieldWidth, gridHeight, insets);
    }

    /* sem fill */
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, 
        double weightx, double weighty, Insets insets)
    {
    	addGridBagLabelTextField(container, label, textField, gridx, gridy, 
			labelWidth, textFieldWidth, gridHeight, GridBagConstraints.NONE, 
			weightx, weighty, insets);
    }
    
    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, 
        double weightx, double weighty, Insets insets)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, gridx, 
                gridy, labelWidth, textFieldWidth, gridHeight, weightx, weighty, insets);
    }

    /* sem weight */
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill,
        Insets insets)
    {
    	addGridBagLabelTextField(container, label, textField, gridx, gridy, labelWidth, 
			textFieldWidth, gridHeight, fill, 0, 0, insets);
    }

    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill,
        Insets insets)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, 
            gridx, gridy, labelWidth, textFieldWidth, gridHeight, fill, insets);
    }

    
    /* sem fill e sem weight */
    public static void addGridBagLabelTextField(Container container,
        Component label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill)
    {
        addGridBagLabelTextField(container, label, textField, gridx, gridy,
            labelWidth, textFieldWidth, gridHeight, fill, 0, 0, null);
    }

    // Label como string
    public static void addGridBagLabelTextField(Container container,
        String label, Component textField, int gridx, int gridy,
        int labelWidth, int textFieldWidth, int gridHeight, int fill)
    {
        addGridBagLabelTextField(container, new JLabel(label), textField, gridx, 
            gridy, labelWidth, textFieldWidth, gridHeight, fill);        
    }

    public static Point centerLocation(Component component)
    {
        return new Point((int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - 
    		component.getWidth() / 2), (int) Math.round(
			Toolkit.getDefaultToolkit().getScreenSize().getHeight()/ 2 - 
			component.getHeight() / 2));
    }

    /**
     * Retorna um JScrollPane para um dado componente
     * 
     * @param component
     *            o componente a ser "scrollado"
     * @param height
     *            permite especificar o tamanho para evitar o colapso da tela
     *            (talvez um bug do Swing)
     * @return o scroll pane gerado
     */
    public static JScrollPane scrollComponent(Component component, int height, String title)
    {
    	JScrollPane scroll = new JScrollPane(component,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width,
	            height != -1 ? height : scroll.getPreferredSize().height));
	    
	    if (title != null) 
	    	scroll.setBorder(BorderFactory.createTitledBorder(title));
	    
	    return scroll;
    }
    
     
     
    public static JScrollPane scrollComponent(Component component, int height)
    {
        return scrollComponent(component, height, null);
    }

    public static JScrollPane scrollComponent(Component component, int width,
            int height)
    {
        JScrollPane scroll = new JScrollPane(component,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(width != -1 ? width : scroll
                .getPreferredSize().width, height != -1 ? height : scroll
                .getPreferredSize().height));
        return scroll;
    }

    public static JComponent borderComponent(JComponent component, String title)
    {
        component.setBorder(BorderFactory.createTitledBorder(title));
        return component;
    }

    /* JOptionPane YesNo */
    public static int yesNoPane(String title, String message)
    {
        return yesNoPane(title, message, 0);
    }

    public static int yesNoPane(String title, String message,
            Integer botaoDefault)
    {
        Object[] options = { "Sim", "Não" };

        int retorno = JOptionPane.showOptionDialog(null, message, title,
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
            options, options[botaoDefault]);

        return retorno;
    }

    public static int yesNoCancelPane(String title, String message)
    {
        return yesNoCancelPane(title, message, 0);
    }

    public static int yesNoCancelPane(String title, String message,
            Integer botaoDefault)
    {
        Object[] options = { "Sim", "Não", "Cancela" };

        int retorno = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[botaoDefault]);

        return retorno;
    }

    public static Icon adjustSize(ImageIcon icon, double scale)
    {
        int width = (int) Math.round(icon.getIconWidth() * scale);
        int height = (int) Math.round(icon.getIconHeight() * scale);
        Image image = icon.getImage();
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        icon.setImage(image);
        return icon;
    }

    public static Icon fitIcon(ImageIcon icon, int maxWidth, int maxHeight)
    {
        int imgw = icon.getIconWidth(), imgh = icon.getIconHeight();
        int finalw, finalh;
        if (imgw > imgh)
        {
            finalw = maxWidth;
            finalh = finalw * imgh / imgw;
        }
        else
        {
            finalh = maxHeight;
            finalw = finalh * imgw / imgh;
        }

        return adjustSize(icon, ((double) finalw) / imgw);
    }
    
    public static void testComponentDialog(Component component)
    {
    	testComponentDialog(component, true);
    }
    
    public static void testComponentDialog(Component component, boolean exit)
    {
    	try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
        }
    	
    	JDialog dialog = new JDialog();
    	dialog.setModal(true);
    	dialog.getContentPane().add(component);
    	dialog.pack();
    	dialog.setVisible(true);
    	if (exit)
    		System.exit(0);
    }
    
    public static JTextArea createTablessTextArea(int rows, int cols)
    {
        JTextArea textarea = new JTextArea(rows, cols);
        return create(textarea);
    }
    
    public static JTextArea createTablessTextArea()
    {
        JTextArea textarea = new JTextArea();
        return create(textarea);
    }
    
    private static JTextArea create(JTextArea area)
    {
    	area.addKeyListener(
            new KeyAdapter()
            {
                public void keyPressed(KeyEvent e)
                {
                    if (e.getKeyCode() == KeyEvent.VK_TAB)
                        ((Component)e.getSource()).transferFocus();
                    else if (e.getKeyCode() == KeyEvent.VK_TAB && (e.getModifiersEx() & KeyEvent.SHIFT_DOWN_MASK) != 0)
                    	((Component) e.getSource()).transferFocusBackward();
                }
            }
        );
            
        return area;
    }
    
    public static void installDragAndDrop(final JComponent component, 
    	final TransferHandler handler, final int transferType)
    {
    	try
    	{
    		ReflectionUtils.invokeMethod("setDragEnabled", component, 
				new Class[] {Boolean.class}, new Object[] {true});
    	}
    	catch (IllegalArgumentException e)
    	{
    		throw e;
    	}
    	
    	ReflectionUtils.invokeMethod("setTransferHandler", component, 
    		new Class[] { TransferHandler.class }, new Object[] { handler });
    	
    	MouseInputAdapter starter = new MouseInputAdapter()
    	{
    		@Override
        	public void mousePressed(MouseEvent e)
        	{
        		TransferHandler handler = (TransferHandler)
        			ReflectionUtils.invokeMethod("getTransferHandler", component, 
    					new Class[] {}, new Object[] {});
        		handler.exportAsDrag(component, e, transferType);
        		
        	}
    	};
    	
    	component.addMouseListener(starter);
    	component.addMouseMotionListener(starter);
    }
    
    public static void centralize(Component component)
    {
        component.setLocation(
    		Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 
    		component.getWidth() / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 
			component.getHeight() / 2);
    }
    
}