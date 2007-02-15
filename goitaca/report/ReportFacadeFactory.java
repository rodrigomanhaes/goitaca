package goitaca.report;

import goitaca.utils.UrurauConfig;
import goitaca.utils.XMLHandler;

import javax.swing.JOptionPane;

public class ReportFacadeFactory
{
	private static ReportFacade facade = null;
	
	// TODO ler info de um XML
	public static ReportFacade getReportFacade()
	{
		if (facade != null)
			return facade;
		
		String file = UrurauConfig.getConfigFolder() + "/factories-config.xml";
		try
		{
			XMLHandler handler = new XMLHandler(file);
			String report = handler.getTagContent("report");
			facade = (ReportFacade) Class.forName(report).newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
			JOptionPane.showMessageDialog(null, e.getMessage());
			return null;
		}
		return facade;
	}
}
