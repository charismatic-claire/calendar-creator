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
public class CalendarPrinterPlain implements CalendarPrinter {

	private CalendarTranslator translator;
	
	public CalendarPrinterPlain( CalendarTranslator translator ) {
		this.translator = translator;
	}
	
	public String printYear(Year year) {
		// init result
		String printedYear = "";
		
		// loop month
		Set<Entry<Integer,Month>> months = year.getCollectionOfMonths().entrySet();
		for( Entry<Integer,Month> month : months ) {
			// print month
			printedYear +=
					translator.translateMonthOfYear( month.getValue().getMonthOfYear() ) + ":\n";
			// loop days
			Set<Entry<Integer,Day>> days = month.getValue().getCollectionOfDays().entrySet();
			for( Entry<Integer,Day> day : days ) {
				// print day
				printedYear += "  ";
					// add whitespace
					if( day.getValue().getDayOfMonth() < 10 ) {
						printedYear += " ";
					}
				printedYear += day.getValue().getDayOfMonth() + 
						" "  + translator.translateDayOfWeek( day.getValue().getDayOfWeek() ) +
						" "  + day.getValue().getEntry();
				// add week of year
				if( day.getValue().getWeekOfYear() != 0 ) {
					printedYear += " ";
					// add whitespace
					if( day.getValue().getWeekOfYear() < 10 ) {
						printedYear += " ";
					}
					printedYear += day.getValue().getWeekOfYear();
				}
				// add end of line
				printedYear += "\n";
			}
			// add end of line
			printedYear += "\n";
		}
		
		// return result
		return printedYear;
	}
}
