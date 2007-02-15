package goitaca.utils;

import java.io.File;

public class XMLFileFinder 
{
    public static File getConfigurationFile(String fileName)
    {
        File file = new File(".");
        String s = file.getAbsolutePath();
        
        int flag = 0, i;
        for (i = s.length() - 1; i >= 0; i--)
        {
            if (s.charAt(i) == File.separatorChar)
                flag++;
            if (flag == 2)
                break;
        }
        s = s.substring(0, i+1);
        s += UrurauConfig.getConfigFolder() + File.separator + fileName;
        return new File(s);
    }
}