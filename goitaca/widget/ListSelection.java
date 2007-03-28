package goitaca.widget;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.NONE;
import goitaca.resources.GoitacaResourceBox;
import goitaca.utils.SwingUtils;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ListSelection extends JPanel
{
	private static final long serialVersionUID = 4235110614870053570L;
	
	private JList source;
	private JList target;
	private JButton oneToSource;
	private JButton allToSource;
	private JButton oneToTarget;
	private JButton allToTarget;
	
	private JScrollPane sourceScroll;
	
	private String attribute;
	private boolean sorted;
	
	public ListSelection(String attribute, boolean sorted)
	{
		this(null, attribute, sorted);
	}
	
	public ListSelection(String title, String attribute, boolean sorted)
	{
		super();
		if (title != null)
			this.setBorder(BorderFactory.createTitledBorder(title));
		this.attribute = attribute;
		this.sorted = sorted;
	}
	
	public ListSelection initialize()
	{
		this.init();
		this.build();
		return this;
	}
	
	public void addObject(Object object, int index)
	{
		((IndividualListSelectionModel) source.getModel()).add(object, index);
	}
	
	public void addObject(Object object)
	{
		((IndividualListSelectionModel) source.getModel()).add(object);
	}
	
	public void clear()
	{
		((IndividualListSelectionModel) source.getModel()).removeAll();
		((IndividualListSelectionModel) target.getModel()).removeAll();
	}
	
	protected void init()
	{
		source = new JList();
		source.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		target = new JList();
		target.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		source.setModel(new IndividualListSelectionModel(null, attribute, sorted));
		target.setModel(new IndividualListSelectionModel(null, attribute, sorted));
		
		final IndividualListSelectionModel sourceModel = 
			(IndividualListSelectionModel) source.getModel();
		final IndividualListSelectionModel targetModel = 
			(IndividualListSelectionModel) target.getModel();
		
		sourceModel.addListDataListener(
			new ListDataListener()
			{
				public void contentsChanged(ListDataEvent arg0) 
				{
				}

				public void intervalAdded(ListDataEvent e) 
				{
					List<Object> sourceObjects = sourceModel.getAll();
					List<Object> targetObjects = targetModel.getAll();
					
					for (Object sobj: sourceObjects)
						if (targetObjects.contains(sobj))
							targetModel.remove(sobj);
				}

				public void intervalRemoved(ListDataEvent arg0) 
				{
				}
				
			}
		);
		
		targetModel.addListDataListener(
			new ListDataListener()
			{
				public void contentsChanged(ListDataEvent arg0) 
				{
				}

				public void intervalAdded(ListDataEvent e) 
				{
					List<Object> sourceObjects = sourceModel.getAll();
					List<Object> targetObjects = targetModel.getAll();
					
					for (Object tobj: targetObjects)
						if (sourceObjects.contains(tobj))
							sourceModel.remove(tobj);
				}

				public void intervalRemoved(ListDataEvent arg0) 
				{
				}
			}
		);
		
		oneToSource = new JButton(new ImageIcon(
			this.getClass().getClassLoader().getResource(GoitacaResourceBox.getPrevIcon())));
		oneToSource.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					oneToSource();
				}
			}
		);
		
		allToSource = new JButton(new ImageIcon(
			this.getClass().getClassLoader().getResource(GoitacaResourceBox.getFirstIcon())));
		
		allToSource.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					allToSource();
				}
			}
		);
		
		oneToTarget = new JButton(new ImageIcon(
			this.getClass().getClassLoader().getResource(GoitacaResourceBox.getNextIcon())));
		oneToTarget.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					oneToTarget();
				}
			}
		);
		
		allToTarget = new JButton(new ImageIcon(
			this.getClass().getClassLoader().getResource(GoitacaResourceBox.getLastIcon())));
		allToTarget.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					allToTarget();
				}
			}
		);		
	}
	
	protected void build()
	{
		this.setLayout(new GridBagLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		SwingUtils.addGridBagComponent(panel, oneToTarget, 0, 0, 1, 1, CENTER, NONE, new Insets(2, 3, 18, 3));
		SwingUtils.addGridBagComponent(panel, allToTarget, 0, 1, 1, 1, CENTER, NONE);
		SwingUtils.addGridBagComponent(panel, oneToSource, 0, 2, 1, 1, CENTER, NONE);
		SwingUtils.addGridBagComponent(panel, allToSource, 0, 3, 1, 1, CENTER, NONE, new Insets(2, 3, 2, 3));
		
		sourceScroll = SwingUtils.scrollComponent(source, 120, 150);
		SwingUtils.addGridBagComponent(this, sourceScroll,
			0, 0, 1, 1, CENTER, NONE, new Insets(2, 5, 5, 5));

		SwingUtils.addGridBagComponent(this, panel, 1, 0, 1, 1, CENTER, NONE);
		
		SwingUtils.addGridBagComponent(this, SwingUtils.scrollComponent(target, 120, 150), 
			2, 0, 1, 1, CENTER, NONE);
		
	}
	
	private void moveOne(IndividualListSelectionModel sourceModel, IndividualListSelectionModel targetModel, JList source)
	{
		int[] indices = source.getSelectedIndices();
		if (indices.length == 0)
			return;
		for (int i = indices.length - 1; i >= 0; i--)
		{
			Object object = sourceModel.getObjectAt(indices[i]);
			sourceModel.remove(indices[i]);
			targetModel.add(object, targetModel.getSize());
		}
	}
	
	private void moveAll(IndividualListSelectionModel sourceModel, IndividualListSelectionModel targetModel)
	{
		if (sourceModel.getSize() == 0)
			return;
		int size = sourceModel.getSize();
		for (int i =  size - 1; i >= 0; i--)
		{
			Object object = sourceModel.getObjectAt(i);
			sourceModel.remove(i);
			targetModel.add(object, targetModel.getSize());
		}
	}
	
	private void oneToSource()
	{
		IndividualListSelectionModel targetModel = (IndividualListSelectionModel) target.getModel(); 
		IndividualListSelectionModel sourceModel = (IndividualListSelectionModel) source.getModel();
		moveOne(targetModel, sourceModel, target);
	}
	
	private void allToSource()
	{
		IndividualListSelectionModel targetModel = (IndividualListSelectionModel) target.getModel(); 
		IndividualListSelectionModel sourceModel = (IndividualListSelectionModel) source.getModel();
		moveAll(targetModel, sourceModel);
	}
	
	private void oneToTarget()
	{
		IndividualListSelectionModel targetModel = (IndividualListSelectionModel) target.getModel(); 
		IndividualListSelectionModel sourceModel = (IndividualListSelectionModel) source.getModel();
		moveOne(sourceModel, targetModel, source);
	}
	
	private void allToTarget()
	{
		IndividualListSelectionModel targetModel = (IndividualListSelectionModel) target.getModel(); 
		IndividualListSelectionModel sourceModel = (IndividualListSelectionModel) source.getModel();
		moveAll(sourceModel, targetModel);
	}
	
	public void select(Object item)
	{
		IndividualListSelectionModel targetModel = (IndividualListSelectionModel) target.getModel(); 
		IndividualListSelectionModel sourceModel = (IndividualListSelectionModel) source.getModel();
		
		if (!sourceModel.contains(item))
			return;
		
		sourceModel.remove(item);
		targetModel.add(item);
	}
	
	public IndividualListSelectionModel getSourceModel()
	{
		return (IndividualListSelectionModel) source.getModel();
	}
	
	public IndividualListSelectionModel getTargetModel()
	{
		return (IndividualListSelectionModel) target.getModel();
	}
	
	/* Component */
	public void setBackgroundColor(Color color)
	{
		source.setBackground(color);
		target.setBackground(color);
	}
	
	public void setReadOnly(boolean readOnly)
	{
		oneToSource.setEnabled(!readOnly);
		allToSource.setEnabled(!readOnly);
		oneToTarget.setEnabled(!readOnly);
		allToTarget.setEnabled(!readOnly);
	}
	
	public void showOnlyTarget(boolean show)
	{
		oneToSource.setVisible(!show);
		allToSource.setVisible(!show);
		oneToTarget.setVisible(!show);
		allToTarget.setVisible(!show);
		sourceScroll.setVisible(!show);
	}
}