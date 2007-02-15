package goitaca.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLHandler
{
    private Document document;
    
    public XMLHandler(File file)
        throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.document = builder.parse(file);
    }
    
    public XMLHandler(String fileName)
	    throws ParserConfigurationException, IOException, SAXException
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    document = builder.parse(getClass().getClassLoader().getResource(fileName).toString());
	}
    
    public String getTagContent(String tagName)
    {
    	return getTagContent(tagName, 0);
    }
    
    public String getTagContent(String tagName, int index)
    {
        return this.document.getElementsByTagName(tagName).item(index).getTextContent();
    }
    
    public int getTagCount(String tagName)
    {
    	return document.getElementsByTagName(tagName).getLength();
    }
    
    public List<String> getTagMultiContent(String tagName)
    {
    	List<String> list = new ArrayList<String>();
    	for (int i = 0; i < this.getTagCount(tagName); i++)
    		list.add(this.getTagContent(tagName, i));
    	return list;
    }
    
    public Document getDocument()
    {
        return document;
    }
}
