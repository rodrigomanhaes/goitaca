package goitaca.widget;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.NONE;
import goitaca.resources.GoitacaResourceBox;
import goitaca.utils.ReflectionUtils;
import goitaca.utils.SwingUtils;
import goitaca.utils.UrurauUtils;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class ListSelection extends JPanel
{
	private static final long serialVersionUID = 4235110614870053570L;
	
	private JList source;
	private JList target;
	private JButton oneToSource;
	private JButton allToSource;
	private JButton oneToTarget;
	private JButton allToTarget;
	
	public ListSelection(String attribute, boolean sorted)
	{
		this(null, attribute, sorted);
	}
	
	public ListSelection(String title, String attribute, boolean sorted)
	{
		super();
		if (title != null)
			this.setBorder(BorderFactory.createTitledBorder(title));
		source.setModel(new SelectionListModel(null, attribute, sorted));
		target.setModel(new SelectionListModel(null, attribute, sorted));
	}
	
	public Object initialize()
	{
		this.init();
		this.build();
		return this;
	}
	
	public void addObject(Object object, int index)
	{
		((SelectionListModel) source.getModel()).add(object, index);
	}
	
	public void clear()
	{
		((SelectionListModel) source.getModel()).removeAll();
		((SelectionListModel) target.getModel()).removeAll();
	}
	
	protected void init()
	{
		source = new JList();
		source.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		target = new JList();
		target.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
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
		
		SwingUtils.addGridBagComponent(this, 
			SwingUtils.scrollComponent(source, 120, 150), 
			0, 0, 1, 1, CENTER, NONE, new Insets(2, 5, 5, 5));

		SwingUtils.addGridBagComponent(this, panel, 1, 0, 1, 1, CENTER, NONE);
		
		SwingUtils.addGridBagComponent(this, 
			SwingUtils.scrollComponent(target, 120, 150), 
			2, 0, 1, 1, CENTER, NONE);
		
	}
	
	private void moveOne(SelectionListModel sourceModel, SelectionListModel targetModel, JList source)
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
	
	private void moveAll(SelectionListModel sourceModel, SelectionListModel targetModel)
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
		SelectionListModel targetModel = (SelectionListModel) target.getModel(); 
		SelectionListModel sourceModel = (SelectionListModel) source.getModel();
		moveOne(targetModel, sourceModel, target);
	}
	
	private void allToSource()
	{
		SelectionListModel targetModel = (SelectionListModel) target.getModel(); 
		SelectionListModel sourceModel = (SelectionListModel) source.getModel();
		moveAll(targetModel, sourceModel);
	}
	
	private void oneToTarget()
	{
		SelectionListModel targetModel = (SelectionListModel) target.getModel(); 
		SelectionListModel sourceModel = (SelectionListModel) source.getModel();
		moveOne(sourceModel, targetModel, source);
	}
	
	private void allToTarget()
	{
		SelectionListModel targetModel = (SelectionListModel) target.getModel(); 
		SelectionListModel sourceModel = (SelectionListModel) source.getModel();
		moveAll(sourceModel, targetModel);
	}

}

@SuppressWarnings("serial")
class SelectionListModel extends AbstractListModel
{
	private List<Object> objects;
	private String attribute;
	
	private boolean sorted;
	
	public SelectionListModel(List<Object> objects, String attribute, boolean sorted)
	{
		this.objects = objects == null ? new ArrayList<Object>() : objects;
		this.attribute = attribute;
		this.sorted = sorted;
	}

	public Object getElementAt(int index)
	{
		return this.getValue(objects.get(index), attribute);
	}
	
	public Object getObjectAt(int index)
	{
		return this.objects.get(index);
	}
	
	public List<Object> getAll()
	{
		return Collections.unmodifiableList(this.objects);
	}
	
	public void remove(Object object)
	{
		int index = this.objects.indexOf(object); 
		if (index != -1)
			this.remove(index);
	}
	

	public int getSize()
	{
		return objects.size();
	}
	
	private Object getValue(Object object, String attribute)
	{
		try
		{
			return ReflectionUtils.getProperty(object, attribute);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
			return null;
		}
	}
	
	public void remove(int index)
	{
		this.objects.remove(index);
		this.fireIntervalRemoved(this, index, index);
	}
	
	public void add(Object object, int index)
	{
		this.objects.add(index, object);
		this.fireIntervalAdded(this, index, index);
		if (sorted)
		{
			UrurauUtils.sortListByAttribute(objects, attribute);
			this.fireContentsChanged(this, 0, objects.size() - 1);
		}
	}
	
	public void add(Object object)
	{
		this.add(object, this.getSize());
	}
	
	public void add(Collection<Object> items)
	{
		for (Object o: items)
			this.add(o);
	}
	
	public void removeAll()
	{
		this.objects.clear();
		this.fireIntervalRemoved(this, 0, objects.size() -1);
	}

}
