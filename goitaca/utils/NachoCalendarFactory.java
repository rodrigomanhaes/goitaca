package goitaca.utils;

import java.awt.Insets;
import java.awt.SystemColor;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.nachocalendar.components.CalendarPanel;
import net.sf.nachocalendar.components.DayRenderer;
import net.sf.nachocalendar.components.DefaultDayRenderer;
import net.sf.nachocalendar.holidays.DefaultHoliDay;
import net.sf.nachocalendar.holidays.HoliDay;
import net.sf.nachocalendar.holidays.HoliDayModel;

public class NachoCalendarFactory 
{
	public static HoliDayModel brazilianHolidayModel(int year)
	{
		HoliDayModel model = new HoliDayModel();
		
		Set<Holiday> set = ReflectionUtils.getEnumInstances(Holiday.class);
		for (Holiday holiday: set)
		{
			System.out.print(holiday + "  ");
			HoliDay h = new DefaultHoliDay();
			h.setName(holiday.displayName());
			System.out.println(holiday.get(year));
			h.setDate(holiday.get(year));
			h.setRecurrent(true);
			model.addHoliDay(h);
		}
		
		return model;
	}
	
	public static DayRenderer holidayRenderer()
	{
		DefaultDayRenderer renderer = new DefaultDayRenderer();
		renderer.setBorder(new MatteBorder(new Insets(1, 1, 1, 1), SystemColor.control));
		return renderer; 
	}
	
	public static ChangeListener yearChangeHolidayModel()
	{
		return new ChangeListener()
			{
				public void stateChanged(ChangeEvent e) 
				{
					CalendarPanel panel = (CalendarPanel) e.getSource();
					Calendar calendar = new GregorianCalendar();
					calendar.setTimeInMillis(((Date) panel.getValue()).getTime());
					int year = calendar.get(Calendar.YEAR);
					panel.setModel(brazilianHolidayModel(year));
					panel.setEternalScroll(false);
					
				}
			};
	}
}
