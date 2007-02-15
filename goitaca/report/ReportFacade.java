package goitaca.report;

import java.util.List;


public interface ReportFacade 
{
	public Object generateReport(String report, List<Object> pojos);
}
