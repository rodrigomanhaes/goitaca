package goitaca.utils;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.SUNDAY;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public enum Holiday 
{
	CONFRATERNIZACAOUNIVERSAL("Confraternização universal"),
	CARNAVAL("Carnaval"),
	CINZAS("Cinzas"),
	SEXTAFEIRASANTA("Paixão de Cristo"),
	PASCOA("Páscoa"),
	CORPUSCHRISTI("Corpus Christi"),
	DIADASMAES("Dia das mães"),
	DIADOSPAIS("Dia dos pais"),
	TIRADENTES("Tiradentes"),
	TRABALHO("Dia do trabalho"),
	INDEPENDENCIA("Independência"),
	APARECIDA("N. Sra. Aparecida"),
	FINADOS("Finados"), 
	PROCLAMACAOREPUBLICA("Proclamação da República"),
	NATAL("Natal");
	
	private String displayName;
	
	private Holiday(String displayName)
	{
		this.displayName = displayName;
	}
	
	public Date get(int year)
	{
		Method[] methods = Holiday.class.getMethods();
		for (Method method: methods)
			if (method.getName().toLowerCase().equals(this.name().toLowerCase()))
			{
				try
				{
					return (Date) method.invoke(null, new Object[] { year });
				}
				catch (Exception e)
				{
					return null;
				}
			}
		return null;
	}
	
	public String displayName()
	{
		return displayName;
	}
	
	public static Set<Date> holidays(int ano)
	{
		Set<Date> holidays = new HashSet<Date>();
		holidays.add(confraternizacaoUniversal(ano));
		holidays.add(carnaval(ano));
		holidays.add(cinzas(ano));
		holidays.add(sextaFeiraSanta(ano));
		holidays.add(pascoa(ano));
		holidays.add(corpusChristi(ano));
		holidays.add(diaDasMaes(ano));
		holidays.add(diaDosPais(ano));
		holidays.add(tiradentes(ano));
		holidays.add(trabalho(ano));
		holidays.add(independencia(ano));
		holidays.add(aparecida(ano));
		holidays.add(finados(ano));
		holidays.add(proclamacaoRepublica(ano));
		holidays.add(natal(ano));
		return holidays;
	}
	
	public static Date pascoa(int ano)
    {
    	int r1 = ano % 19;
    	int r2 = ano % 4;
    	int r3 = ano % 7;
    	int r4 = (19 * r1 + 24) % 30;
    	int r5 = (6 * r4 + 4 * r3 + 2 * r2 + 5) % 7;
    	
    	Calendar calendar = new GregorianCalendar();
    	calendar.set(ano, 2, 22);
    	calendar.add(DATE, r4 + r5);
    	
    	int day = calendar.get(DATE);
    	if (day == 26)
    		calendar.set(ano, 3, 19);
    	else if (day == 25 && r1 > 10)
    		calendar.set(ano, 3, 18);
    	
    	return calendar.getTime();
    }
    
    public static Date carnaval(int ano)
    {
    	return CalendarUtils.add(pascoa(ano), -47); 
    }
    
    public static Date cinzas(int ano)
    {
    	return CalendarUtils.add(pascoa(ano), -46); 
    }
    
    public static Date sextaFeiraSanta(int ano)
    {
    	return CalendarUtils.add(pascoa(ano), -2); 
    }
    
    public static Date corpusChristi(int ano)
    {
    	return CalendarUtils.add(pascoa(ano), 60); 
    }
    
    public static Date diaDasMaes(int ano)
    {
    	Calendar calendar = new GregorianCalendar();
    	calendar.set(ano, 4, 1);
    	while (true)
    	{
    		if (calendar.get(DAY_OF_WEEK) == SUNDAY)
    		{
    			calendar.add(DATE, 7);
    			return calendar.getTime();
    		}
    		calendar.add(DATE, 1);
    	}
    }
    
    public static Date confraternizacaoUniversal(int ano)
    {
    	return new GregorianCalendar(ano, 0, 1).getTime();  
    }
    
    public static Date tiradentes(int ano)
    {
    	return new GregorianCalendar(ano, 3, 21).getTime();
    }
    
    public static Date trabalho(int ano)
    {
    	return new GregorianCalendar(ano, 4, 1).getTime();
    }
    
    public static Date independencia(int ano)
    {
    	return new GregorianCalendar(ano, 8, 7).getTime();
    }
    
    public static Date aparecida(int ano)
    {
    	return new GregorianCalendar(ano, 9, 12).getTime();
    }
    
    public static Date finados(int ano)
    {
    	return new GregorianCalendar(ano, 10, 2).getTime();
    }
    
    public static Date proclamacaoRepublica(int ano)
    {
    	return new GregorianCalendar(ano, 10, 15).getTime();
    }
    
    public static Date natal(int ano)
    {
    	return new GregorianCalendar(ano, 11, 25).getTime();
    }
    
    public static Date diaDosPais(int ano)
    {
    	Calendar calendar = new GregorianCalendar();
    	calendar.set(ano, 7, 1);
    	while (true)
    	{
    		if (calendar.get(DAY_OF_WEEK) == SUNDAY)
    		{
    			calendar.add(DATE, 7);
    			return calendar.getTime();
    		}
    		calendar.add(DATE, 1);
    	}
    }
}
