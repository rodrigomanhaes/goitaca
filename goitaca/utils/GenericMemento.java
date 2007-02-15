package goitaca.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

public class GenericMemento<T>
{
	private T memento; 
	
	@SuppressWarnings("unchecked")
	public GenericMemento(T object)
	{
		try
		{
			memento = (T) object.getClass().newInstance();
			write(object, memento);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public void restore(T destination)
	{
		write(destination, memento);
	}
	
	private void write(T origin, T destination)
	{
		try
		{
			Set<Field> set = ReflectionUtils.getAllFields(origin.getClass());
			for(Field f: set)
			{
				if (!Modifier.isFinal(f.getModifiers()))
				{
					f.setAccessible(true);
					Object value = f.get(origin);
					f.set(destination, value);
				}
			}
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace(System.err);
		}
		
	}
}
