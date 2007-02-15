package goitaca.utils;

import static java.util.Calendar.DATE;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarUtils
{
    public static String toString(Calendar calendar)
    {
        try
        {
            return CalendarUtils.leftPad(String.valueOf(calendar
                    .get(DAY_OF_MONTH)), 2, "0")
                    + "/"
                    + CalendarUtils.leftPad(String.valueOf(calendar
                            .get(MONTH) + 1), 2, "0")
                    + "/"
                    + CalendarUtils.leftPad(String.valueOf(calendar
                            .get(YEAR)), 4, "0");
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static String[] portugueseMonths = { "Janeiro", "Fevereiro",
            "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro" };

    public static String toMonthString(Calendar calendar)
    {
        try
        {
            return portugueseMonths[calendar.get(MONTH)];
        }
        catch (Exception e)
        {
            return null;
        }
    }

    private static String leftPad(String s, int size, String fill)
    {
        return CalendarUtils.replicate(fill, size - s.length()) + s;
    }

    private static String replicate(String s, int times)
    {
        String string = "";
        for (int i = 1; i <= times; i++)
            string += s; 
        return string;
    }

    public static Calendar toCalendar(String s)
    {
        Calendar calendar = new GregorianCalendar();

        int day, month, year;
        try
        {
            day = Integer.parseInt(s.substring(0, 2));
            month = Integer.parseInt(s.substring(3, 5));
            year = Integer.parseInt(s.substring(6, 10));
        }
        catch (NumberFormatException e)
        {
            return null;
        }

        calendar.set(DAY_OF_MONTH, day);
        calendar.set(MONTH, month - 1);
        calendar.set(YEAR, year);
        return calendar;
    }
    
    // Verifica se uma data é válida. Para isso, utiliza o formato
    // corrente como padrão
    public static boolean isValidDate(String date) 
    {
        date = date.replaceAll("/", "");
        
        //Carrega as partes da data em variáveis texto
        String sDay = getStrDay(date);
        String sMonth = getStrMonth(date);
        String sYear = getStrYear(date);
        
        //Testa se a data esta no tamanho correto
        if (date.length() != 8) 
            return false;
        
        //Transforma as partes da data em inteiro
        int day;
        int month;
        int year;
        
        try 
        {
            day = Integer.parseInt(sDay);
            month = Integer.parseInt(sMonth);
            year = Integer.parseInt(sYear);
        }
        catch (NumberFormatException e) 
        {
            return false;
        }
        
        //O Gregorian Calendar trabalha com mês de 0 a 11
        //Logo, deve haver a correçao
        month--;
        
        //Declarando a classe do tipo data 
        Calendar calendar = new GregorianCalendar();
        
        //Informando o ano da data
        calendar.set(YEAR, year);
        
        //verifica se o mês é um mês valido (entre 0 e 11)
        if (month > 11) 
            return false;
        
        //Informa o mês da data
        calendar.set(MONTH, month);
        
        //Testa se os dias correspondem ao número de dias do mês
        
        if (day > calendar.getActualMaximum(DATE)) 
            return false;
    
        return true;
    }   

    private static String format = "ddmmaaaa";

    private static String getStrDay (String date) 
    {
        int indice;
        indice = format.lastIndexOf("dd");
        return date.substring(indice, indice + 2);
    }

    private static String getStrMonth (String date) 
    {
        int indice;
        indice = format.lastIndexOf("mm");
        return date.substring(indice, indice + 2);
    }

    private static String getStrYear (String date) 
    {
        int indice;
        indice = format.lastIndexOf("aaaa");
        return date.substring(indice, indice + 4);
    }

    
    public static Calendar SQLDateToCalendar(java.sql.Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }
    
    public static Date putDate(Date source, Date target)
    {
        if (target == null)
        	target = new Date();
    	Calendar dataHoraCalendar = new GregorianCalendar();
        Calendar dataCalendar = new GregorianCalendar();
        
        dataHoraCalendar.setTime(target);
        dataCalendar.setTime(source);
        
        dataHoraCalendar.set(DAY_OF_MONTH, dataCalendar.get(DAY_OF_MONTH));
        dataHoraCalendar.set(MONTH, dataCalendar.get(MONTH));
        dataHoraCalendar.set(YEAR, dataCalendar.get(YEAR));
        
        return dataHoraCalendar.getTime();
    }
    
    public static Date putTime(String source, Date target)
    {
    	if (target == null)
    		target = new Date();
    	Calendar dataHoraCalendar = new GregorianCalendar();
        Calendar horaCalendar = new GregorianCalendar();
        
        Date horaNova = null;
        
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            horaNova = sdf.parse(source);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException(e);
        }
        
        dataHoraCalendar.setTime(target);
        horaCalendar.setTime(horaNova);
        
        dataHoraCalendar.set(HOUR_OF_DAY, horaCalendar.get(HOUR_OF_DAY));
        dataHoraCalendar.set(MINUTE, horaCalendar.get(MINUTE));
        
        return dataHoraCalendar.getTime();
    }
    
    public static Date add(Date date, int numDays)
    {
    	Calendar calendar = new GregorianCalendar();
    	calendar.setTimeInMillis(date.getTime());
    	calendar.add(DATE, numDays);
    	return calendar.getTime();
    }
}
