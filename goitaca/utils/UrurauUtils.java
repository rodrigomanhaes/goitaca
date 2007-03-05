package goitaca.utils;

import java.lang.reflect.Method;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UrurauUtils
{
    public static <T> void toList(List<T> list, T[] vector)
    {
        if (vector == null)
            return;
        if (list == null)
            list = new ArrayList<T>();
        list.clear();
        for (T t: vector)
            list.add(t);
    }
    
    @Deprecated
    public static <T> List<T> toList(T[] vector)
    {
        if (vector == null)
            return null;
        List<T> list = new ArrayList<T>();
        for (T t: vector)
            list.add(t);
        return list;
    }
    
    public static String toUpperFirstCharacter(String string)
    {
        String ret = string.substring(0, 1).toUpperCase();
        ret += string.substring(1, string.length());
        return ret;
    }
    
    public static String toLowerFirstCharacter(String string)
    {
        String ret = string.substring(0, 1).toLowerCase();
        ret += string.substring(1, string.length());
        return ret;
    }
    
    public static String getGetMethodName(String entity)
    {
        return "get" + toUpperFirstCharacter(entity);
    }
    
    public static String getSetMethodName(String entity)
    {
        return "set" + toUpperFirstCharacter(entity);
    }
    
    public static String getAddMethodName(String entity)
    {
        return "add" + toUpperFirstCharacter(entity);
    }
    
    public static Method getMethodByName(Class clazz, String methodName)
    {
        for (Method m: clazz.getMethods())
            if (m.getName().equals(methodName))
                return m;
        return null;
    }
    
    @Deprecated
    public static String getEntityFromVO(Class clazz)
    {
        return clazz.getSimpleName().substring(0, clazz.getSimpleName().length() - 2);
    }
    
    public static <K, V> K getKeyFromValue(Map<K, V> map, V value)
    {
        for (K key: map.keySet())
            if (value.equals(map.get(key)))
                return key;
        return null;
    }
    
    @SuppressWarnings("unchecked")
	public static <T extends Number> T convert(String number, T t)
    {
    	try
    	{
    		char thousand = new DecimalFormatSymbols().getGroupingSeparator();
    		String s = number.replace(new String(new char[] {thousand}), "");
    		String className = t.getClass().getSimpleName().equals("Integer") ? "Int" : t.getClass().getSimpleName(); 
    		return (T) ReflectionUtils.invokeMethod("parse" + className, t, new Class[] { String.class }, s);
    	}
    	catch (Exception e)
    	{
    		return t;
    	}
    }
    
    public static String toHex(byte[] digest)
	{
		StringBuffer buf = new StringBuffer();
		for (byte b: digest)
			buf.append(Integer.toHexString((int) b & 0x00FF));
		return buf.toString();
	}
    
    public static <T> void sortListByAttribute(List<T> list, String attribute)
    {
        sortListByAttribute(list, attribute, true);
    }
    
	@SuppressWarnings("unchecked")
	public static <BO> void sortListByAttribute(List<BO> list, String attribute, boolean revert)
    {
    	Map<Object, List<BO>> map = new TreeMap<Object, List<BO>>(new StringComparator());
    	List<BO> nullList = new ArrayList<BO>();
    	
    	try
    	{
    		for (BO bo: list)
    		{
    			Object value = ReflectionUtils.getProperty(bo, attribute);
    			if (value == null)
    			{
    				nullList.add(bo);
    				continue;
    			}
    			if (!map.containsKey(value))
    			{
	    			List<BO> lista = new ArrayList<BO>();
	    			lista.add(bo);
    				map.put(value, lista);
    			}
    			else
    				map.get(value).add(bo);
    		}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace(System.err);
    	}
    	
    	list.clear();
    	for (Object obj: map.keySet())
    		for (BO bo: map.get(obj))
    			list.add(bo);
    	list.addAll(nullList);
    	
    	if (revert)
    		Collections.reverse(list);
    }
    
    public static <T> T notNull(T t, T _default)
    {
    	return t != null ? t : _default;
    }
}
