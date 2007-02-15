package goitaca.utils;

import java.util.Comparator;

public class StringComparator implements Comparator
{
	@SuppressWarnings("unchecked")
	public int compare(Object o1, Object o2) 
	{
		if (o1 instanceof String)
		{
	        String a = o1.toString();
	        String b = o2.toString();
	 
	        if (a.equalsIgnoreCase(b))
	            return a.compareTo(b);
	        
	        return String.CASE_INSENSITIVE_ORDER.compare(a, b);
		}
		else if (o1 instanceof Comparable)
			return ((Comparable) o1).compareTo(o2);
		else
			throw new IllegalArgumentException("Parameter must be a Comparable instance!");
    }
}
