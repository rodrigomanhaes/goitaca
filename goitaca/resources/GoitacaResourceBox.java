package goitaca.resources;

public class GoitacaResourceBox
{
	private static final String RESOURCE_LOCATION = "goitaca/resources/images/";
	
    /*
     * Navigation
     */
    public static String getFirstIcon()
    {
        return GoitacaResourceBox.RESOURCE_LOCATION + "first.gif";
    }
    
    public static String getPrevIcon()
    {
        return GoitacaResourceBox.RESOURCE_LOCATION + "prev.gif";
    }
    
    public static String getNextIcon()
    {
        return GoitacaResourceBox.RESOURCE_LOCATION + "next.gif";
    }
    
    public static String getLastIcon()
    {
        return GoitacaResourceBox.RESOURCE_LOCATION + "last.gif";
    }
    
}