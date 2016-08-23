/**
 * 
 */
package org.calendarcreator.model;

import java.util.Set;
import java.util.Map.Entry;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Year;

/**
 *
 */
public class CalendarPrinterPortrait implements CalendarPrinter {

	private CalendarTranslator translator;
	
	public CalendarPrinterPortrait( CalendarTranslator translator ) {
		this.translator = translator;
	}
	
	@Override
	public String printYear(Year year) {
		// init string
		String printedYear = "";
		// add header
		printedYear += "-------- " + year.getYearInteger() + " --------\n\n";
		// loop month
		Set<Entry<Integer,Month>> months = year.getCollectionOfMonths().entrySet();
		for( Entry<Integer,Month> month : months ) {
			// print month
			printedYear += printMonth( month.getValue() );
		}
		// add footer
		printedYear += "----------------------\n";
		// return result
		return printedYear;
	}
	
	private String printMonth( Month month ) {
		// init string
		String printedMonth = "";
		// print month of year
		printedMonth += printMonthOfYear( month );
		// loop days
		Set<Entry<Integer,Day>> days = month.getCollectionOfDays().entrySet();
		for( Entry<Integer,Day> day : days ) {
			// print day
			printedMonth += printDay( day.getValue() );
		}
		// add end of line
		printedMonth += "\n";
		// return result
		return printedMonth;
	}
	
	public String printDay( Day day ) {
		// init string
		String printedDay = "";
		// print day of month
		printedDay += printDayOfMonth( day );
		// print day of week
		printedDay += printDayOfWeek( day );
		// print holiday
		printedDay += printHoliday( day );
		// print entry
		printedDay += printEntry( day );
		// print week of year
		printedDay += printWeekOfYear( day );
		// add end of line
		printedDay += "\n";
		// return result
		return printedDay;
	}
	
	private String printMonthOfYear( Month month ) {
		// init string
		String printedMonth = "";
		// print month
		printedMonth = translator.translateMonthOfYear( month.getMonthOfYear() ) + ":\n";
		// return result
		return printedMonth;
	}
	
	private String printDayOfMonth( Day day ) {
		// init string
		String printedDayOfMonth = "  ";
		// print day of month
		if( day.getDayOfMonth() < 10 ) {
			printedDayOfMonth += " ";
		}
		printedDayOfMonth += day.getDayOfMonth();
		// return result
		return printedDayOfMonth;
	}
	
	private String printDayOfWeek( Day day ) {
		// init string
		String printedDayOfWeek = " ";
		// print day of week
		printedDayOfWeek += translator.translateDayOfWeek( day.getDayOfWeek() );
		// return result
		return printedDayOfWeek;
	}
	
	private String printHoliday( Day day ) {
		// init string
		String printedHoliday = "";
		// print holiday
		if( day.getHoliday() != null ) {
			printedHoliday += " " + translator.translateHoliday( day.getHoliday() ); 
		}
		// return result
		return printedHoliday;
	}
	
	private String printEntry( Day day ) {
		// init string
		String printedEntry = "";
		// print entry
		if( day.getEntry() != null ) {
			printedEntry += " "  + day.getEntry();
		}
		// return result
		return printedEntry;
	}
	
	private String printWeekOfYear( Day day ) {
		// init string
		String printedWeekOfYear = "";
		// print week of year
		if( day.getWeekOfYear() != null ) {
			printedWeekOfYear += " ";
			if( day.getWeekOfYear() < 10 ) {
				printedWeekOfYear += " ";
			}
			printedWeekOfYear += day.getWeekOfYear();
		}
		// return result
		return printedWeekOfYear;
	}
}
