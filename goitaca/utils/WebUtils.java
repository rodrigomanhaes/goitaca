package goitaca.utils;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class WebUtils
{
	public static void uploadFile(String fileName, File file)
	{
		String url = "http://localhost:8080/ururauweb/uploadimage";
		HttpClient client = new HttpClient();
		PostMethod method = null;
		try
		{
			Part part = new FilePart(fileName, file.getName(), file);
			method = new PostMethod(url);
			MultipartRequestEntity entity = new MultipartRequestEntity(new Part[] { part }, 
				method.getParams());
			method.setRequestEntity(entity);
			int status = client.executeMethod(method);
			if (status != HttpStatus.SC_OK)
				System.out.println("Erro " + status + " - " + HttpStatus.getStatusText(status));
			method.releaseConnection();
			
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	public static byte[] downloadFile(String fileName)
	{
		String url = "http://localhost:8080/ururauweb/downloadimage";
		HttpClient client = new HttpClient();
		HttpMethod method = new PostMethod(url);
		method.setQueryString(new NameValuePair[]{ new NameValuePair("fileName", fileName) });
		try
		{
			int status = client.executeMethod(method);
			if (status == HttpStatus.SC_OK)
				return method.getResponseBody();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
		return null;
		
	}
	
	public static void main(String[] args)
	{
		File file = new File("C:\\Arquivos de programas\\ACDSee32\\splash.jpg");
		uploadFile("pesfigurasplash.jpg", file);
		byte[] imageData = downloadFile("pesfigurasplash.jpg");
		JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.getContentPane().add(new JLabel(new ImageIcon(imageData)));
		dialog.pack();
		dialog.setVisible(true);
	}
}
