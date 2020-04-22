/**
 * 
 */
package org.calendarcreator.model.printer;

import java.util.Set;
import java.util.List;
import java.util.Map.Entry;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Week;
import org.calendarcreator.data.Year;
import org.calendarcreator.model.WeekFactory;
import org.calendarcreator.model.translator.CalendarTranslator;

/**
 *
 */
public class CalendarPrinterLandscape implements CalendarPrinter {

	private CalendarTranslator translator;
	
	public CalendarPrinterLandscape( CalendarTranslator translator ) {
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

	@Override
	public String printMonth(Month month) {
		// init string
		String printedMonth = "";
		// print month of year
		printedMonth += printMonthOfYear( month );
		// print day of week heading
		printedMonth += printDayOfWeekHeading();
		// generate set of weeks
		WeekFactory weekFactory = new WeekFactory();
		List<Week> listOfWeeks= weekFactory.generateListOfWeeks( month );
		// loop weeks
		for( Week week : listOfWeeks ) {
			// print week
			printedMonth += printWeek( week );
		}
		// add end of line
		printedMonth += "\n";
		// return result
		return printedMonth;
	}
	
	private String printWeek( Week week ) {
		// init string
		String printedWeek = "  ";
		// print day of month
		for( int i=0; i<7; i++ ) {
			if( week.getWeek()[i] != null ) {
				printedWeek += printDayOfMonth( week.getWeek()[i] );
			}
			else {
				printedWeek += "     ";
			}
		}
		printedWeek += "  (" + week.getWeekOfYear() + ")\n  ";
		// print holiday and entry
		for( int i=0; i<7; i++ ) {
			if( week.getWeek()[i] != null ) {
				printedWeek += printHolidayAndEntry( week.getWeek()[i] ); 
			}
			else {
				printedWeek += "    ";
			}
		}
		printedWeek += "\n";
		// return result
		return printedWeek;
	}
	
	private String printDayOfMonth( Day day ) {
		// init string
		String printedDayOfMonth = "";
		// print day of month
		if( day.getDayOfMonth() < 10 ) {
			printedDayOfMonth += " ";
		}
		printedDayOfMonth += day.getDayOfMonth() + "   ";
		// return result
		return printedDayOfMonth;
	}
	
	private String printHolidayAndEntry( Day day ) {
		// init string
		String printedHolidayAndEntry = "";
		// print holiday and entry
		if( day.getHoliday() != null ) {
			printedHolidayAndEntry += translator.translateHoliday( day.getHoliday() ) + " ";
		}
		if( day.getEntry() != null ) {
			printedHolidayAndEntry += day.getEntry() + " ";
		}
		// return result
		return printedHolidayAndEntry;
	}

	private String printDayOfWeekHeading() {
		// init string
		String printedDayOfWeekHeading = "  ";
		// loop days of week
		DayOfWeek[] daysOfWeek = { DayOfWeek.MONDAY, 
				DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY,
				DayOfWeek.SATURDAY,
				DayOfWeek.SUNDAY };
		for( int i=0; i<7; i++ ) {
			// print day of week
			printedDayOfWeekHeading += printDayOfWeek( daysOfWeek[i] );
		}
		printedDayOfWeekHeading += "\n";
		// return result
		return printedDayOfWeekHeading;
	}
	
	private String printDayOfWeek( DayOfWeek dayOfWeek ) {
		// init string
		String printedDayOfWeek = "";
		// print day of week
		printedDayOfWeek += translator.translateDayOfWeek( dayOfWeek ) + "  ";
		// return result
		return printedDayOfWeek;
	}

	private String printMonthOfYear( Month month ) {
		// init string
		String printedMonth = "";
		// print month
		printedMonth = translator.translateMonthOfYear( month.getMonthOfYear() ) + ":\n";
		// return result
		return printedMonth;
	}

}
