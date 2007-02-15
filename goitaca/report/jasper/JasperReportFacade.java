package goitaca.report.jasper;

import goitaca.report.ReportFacade;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

public class JasperReportFacade implements ReportFacade
{
	public Object generateReport(String report, List<Object> pojos)
	{
		try
		{
			JasperReport jasper = (JasperReport) JRLoader.loadObject(getClass().getClassLoader().getResource(report));
			JasperPrint print = JasperFillManager.fillReport(jasper, 
				new HashMap<String, Serializable>(), 
				new JRBeanCollectionDataSource(pojos));
			JasperExportManager.exportReportToPdf(print);
//			JasperViewer.viewReport(print, false);
            
            return (Object) new JRViewer(print);
		}
		catch (JRException e)
		{
			e.printStackTrace(System.err);
		}
        return null;
	}
}
