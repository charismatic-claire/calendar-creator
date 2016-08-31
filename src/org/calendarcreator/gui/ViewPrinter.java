package org.calendarcreator.gui;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Month;
import org.calendarcreator.model.translator.CalendarTranslator;

public class ViewPrinter {

	private CalendarTranslator translator;
	
	public ViewPrinter( CalendarTranslator translator ) {
		this.translator = translator;
	}
	
	public String printMonth( Month month ) {
		return translator.translateMonthOfYear( month.getMonthOfYear() );
	}
	
	public String printDayBasic( Day day ) {
		String printedDayBasic = translator.translateDayOfWeek( day.getDayOfWeek() ) + "  |  " ;
		if( day.getDayOfMonth() < 10 ) {
			printedDayBasic += "0";
		}
		printedDayBasic += day.getDayOfMonth() + "    "; 
		return printedDayBasic;
	}
	
	public String printDayText( Day day ) {
		String printedDayText = " ";
		if( day.getHoliday() != null ) {
			printedDayText += translator.translateHoliday( day.getHoliday() );
		}
		if( day.getEntry() != null ) {
			if( day.getHoliday() != null ) {
				printedDayText += ", ";
			}
			printedDayText += day.getEntry();
		}
		return printedDayText;
	}
	
	public String printButtonLabel( Month month, Day day ) {
		switch( month.getMonthOfYear() ) {
			case JANUARY:
				return "1-" + day.getDayOfMonth();
			case FEBRUARY:
				return "2-" + day.getDayOfMonth();
			case MARCH:
				return "3-" + day.getDayOfMonth();
			case APRIL:
				return "4-" + day.getDayOfMonth();
			case MAY:
				return "5-" + day.getDayOfMonth();
			case JUNE:
				return "6-" + day.getDayOfMonth();
			case JULY:
				return "7-" + day.getDayOfMonth();
			case AUGUST:
				return "8-" + day.getDayOfMonth();
			case SEPTEMBER:
				return "9-" + day.getDayOfMonth();
			case OCTOBER:
				return "10-" + day.getDayOfMonth();
			case NOVEMBER:
				return "11-" + day.getDayOfMonth();
			default:
				return "12-" + day.getDayOfMonth();
		}
	}

}
