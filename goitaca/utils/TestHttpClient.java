package goitaca.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class TestHttpClient
{
	public void testeGet()
	{
		HttpClient client = new HttpClient();
		String url = "http://localhost:8080/ururauweb/imagens/httpclient_logo.png";
		HttpMethod method = null;
		
		// identifica-se como um navegador web para não levar forbidden de alguns sites
		try
		{
			method = new GetMethod(url);
			client.executeMethod(method);
			
			if (method.getStatusCode() == HttpStatus.SC_OK)
			{
				 byte[] image = method.getResponseBody();
				
				JLabel label = new JLabel(new ImageIcon(image));
				JDialog frame = new JDialog();
				frame.getContentPane().add(label);
				frame.pack();
				frame.setModal(true);
				frame.setVisible(true);
				
				InputStreamReader inputReader = new InputStreamReader(method.getResponseBodyAsStream());
				BufferedReader reader = new BufferedReader(inputReader);
				String content = null;
				
				while ((content = reader.readLine()) != null)
					System.out.println(content);
				
				inputReader.close();
				reader.close();
			}
			method.releaseConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}
	
	private void testeUpload()
	{
		String url = "http://localhost:8080/UrurauWeb/imagens";
		HttpClient client = new HttpClient();
		File file = new File("C:\\Arquivos de programas\\ACDSee32\\splash.jpg");
		int status = 0;
		PostMethod method = null;
		String resposta = null;
		try
		{
			Part part = new FilePart("matricula_aluno.jpg", "splash.jpg", file);
			method = new PostMethod(url);
			MultipartRequestEntity entity = 
				new MultipartRequestEntity(new Part[]{part}, method.getParams());
			method.setRequestEntity(entity);
			status = client.executeMethod(method);
			if (status == HttpStatus.SC_OK)
			{
				System.out.println("Enviado com sucesso!");
				resposta = method.getResponseBodyAsString();
				System.out.println(resposta);
			}
			else
				System.out.println("Erro: " + status + " " + HttpStatus.getStatusText(status));
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
		finally
		{
			method.releaseConnection();
		}
	}
	
	public static void main(String[] args)
	{
		new TestHttpClient().testeUpload();
	}
}
