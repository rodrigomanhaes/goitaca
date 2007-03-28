package goitaca.widget;

import goitaca.utils.ReflectionUtils;
import goitaca.utils.UrurauUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

public class IndividualListSelectionModel extends AbstractListModel
{
	private static final long serialVersionUID = -4238494108487290987L;
	
	private List<Object> objects;
	private String attribute;
	
	private boolean sorted;
	
	public IndividualListSelectionModel(List<Object> objects, String attribute, boolean sorted)
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
	
	public void addAll(Collection<Object> items)
	{
		for (Object o: items)
			this.add(o);
	}
	
	public void removeAll()
	{
		int index = objects.size();
		this.objects.clear();
		this.fireIntervalRemoved(this, 0, index > 0 ? index - 1 : 0);
	}
	
	public void removeAll(Collection<?> items)
	{
		for (Object item: items)
		{
			int index = objects.indexOf(item);
			if (index != -1)
			{
				this.objects.remove(item);
				this.fireIntervalRemoved(this, index, index);
			}
		}
	}
	
	public boolean contains(Object item)
	{
		return objects.contains(item);
	}
	
}
