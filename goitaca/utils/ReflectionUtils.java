package goitaca.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtils
{
    public static Object getProperty(Object object, String property)
        throws NoSuchFieldException, IllegalAccessException 
    {
    	if (object == null)
    		return null;
        try
        {
            return invokeMethod(UrurauUtils.getGetMethodName(property), object, 
                new Class[] {  }, new Object[]{});
        }
        catch (Exception e)
        {
            Class clazz = object.getClass();
        
            Field field = getField(clazz, property);
            field.setAccessible(true);
            return field.get(object);
        }
    }
    
    public static void setProperty(Object object, String property, Object value)
        throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException 
    {
        try
        {
            invokeMethodWithExceptions(UrurauUtils.getSetMethodName(property), object, 
                new Class[] {getPropertyType(object, property)},new Object[]{ value });
        }
        catch (NoSuchMethodException e)
        {
            Class clazz = object.getClass();
            
            Field field = getField(clazz, property);
            field.setAccessible(true);
            
            field.set(object, value);
        }
    }
    
    public static Object getNestedProperty(Object object, String property)
        throws NoSuchFieldException, IllegalAccessException
    {
    	while (property.indexOf('.') != -1) 
        {
            int i = property.indexOf('.');
            String prop = property.substring(0, i); 
            property = property.substring(i + 1);
            
            if (object == null)
                return null;
            object = ReflectionUtils.getProperty(object, prop);
        }
        return ReflectionUtils.getProperty(object, property);
    }
    
    public static void setNestedProperty(Object object, String property, Object value, boolean autoCreate)
        throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException,
        	InvocationTargetException
    { 
        while (property.indexOf('.') != -1) 
        {
            int i = property.indexOf('.');
            String prop = property.substring(0, i); 
            property = property.substring(i + 1);
            
            Object newObject = ReflectionUtils.getProperty(object, prop);
            if (newObject == null && autoCreate)
            {
                newObject = getPropertyType(object, prop).newInstance();
                setProperty(object, prop, newObject);
            }
            object = newObject;
                
        }
        ReflectionUtils.setProperty(object, property, value);
    }
    
    public static void setNestedProperty(Object object, String property, Object value)
        throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
         	InvocationTargetException
    { 
        try
        {
            setNestedProperty(object, property, value, false);
        }
        catch (InstantiationException e)
        {
            // com um parâmetro false, nunca se cairá num InstantiationException
        }
    }
    
    public static Class getPropertyType(Object object, String property)
        throws NoSuchFieldException, NoSuchMethodException
    {
        Class clazz = object.getClass();
        Field field = null;
        try
        {
            field = getNestedField(clazz, property);
            return field.getType();
        }
        catch (NoSuchFieldException e)
        {
            Method method = getMethod(clazz, UrurauUtils.getGetMethodName(property), (Class[]) null);
            return method.getReturnType();
        }
    }
    
    public static Field getNestedField(Class clazz, String fieldName)
        throws NoSuchFieldException
    {
        while (fieldName.indexOf('.') != -1) 
        {
            int i = fieldName.indexOf('.');
            String prop = fieldName.substring(0, i); 
            fieldName = fieldName.substring(i + 1);
            
            clazz = ReflectionUtils.getField(clazz, prop).getType();
        }
        return ReflectionUtils.getField(clazz, fieldName);
    }
    
    public static Object invokeMethod(String methodName, Object target, Class[] classes, Object... parameters)
    {
        try
        {
        	Method method = getMethod(target.getClass(), methodName, classes);
            method.setAccessible(true);
            return method.invoke(target, parameters);
        }
        catch (Exception e)
        {
            //e.printStackTrace(System.err);
            throw new IllegalArgumentException(e);
        }
    }
    
    public static Object invokeMethodWithExceptions(String methodName, Object target, Class[] classes, Object... parameters)
    	throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method method = getMethod(target.getClass(), methodName, classes);
        method.setAccessible(true);
        return method.invoke(target, parameters);
   }
    
    private static Method getMethod(Class<?> clazz, String methodName, Class... params)
        throws NoSuchMethodException
    {
        Method method = null;
        try
        {
            method = clazz.getMethod(methodName, params);
        }
        catch (NoSuchMethodException e)
        {
            method = clazz.getDeclaredMethod(methodName, params);
        }
        return method;
    }
    
    private static Field getField(Class clazz, String fieldName)
        throws NoSuchFieldException
    {
        Field field = null;
        try
        {
            field = clazz.getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException nsfe1)
        {
            try
            {
                field = clazz.getField(fieldName);
            }
            catch (NoSuchFieldException nsfe2)
            {
                if (clazz.getSuperclass() != null)
                    field = ReflectionUtils.getField(clazz.getSuperclass(), fieldName);
                else
                    throw nsfe2;
            }
        }
        
        return field;
    }
    
    public static Type[] getGenericTypes(Object object, String fieldName)
    	throws NoSuchFieldException
    {
    	Field field = ReflectionUtils.getField(object.getClass(), fieldName);
    	Type gtype = field.getGenericType();
    	Type[] genericTypes = null;
    	if (gtype instanceof ParameterizedType)
    	{
    		ParameterizedType ptype = (ParameterizedType) gtype;
    		genericTypes = new Type[1];
    		genericTypes[0] = ptype.getOwnerType();
    	}
    	return genericTypes;
    }
    
    public static Set<Field> getAllFields(Class<?> clazz)
    {
        Set<Field> set = new HashSet<Field>();
        set.addAll(Arrays.asList(clazz.getDeclaredFields()));
        while (clazz.getSuperclass() != null)
        {
        	clazz = clazz.getSuperclass();
        	set.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        
        return set;
    }
    
    @SuppressWarnings("unchecked")
	public static <T> Set<T> getEnumInstances(Class<T> clazz)
    {
    	Set<T> set = new HashSet<T>(); 
    	try
    	{
	    	for (Field field: clazz.getFields())
	    	{
	    		field.setAccessible(true);
	    		set.add((T) field.get(null));
	    	}
    	}
    	catch (IllegalAccessException e)
    	{
    	}
    	return set;
    }

}