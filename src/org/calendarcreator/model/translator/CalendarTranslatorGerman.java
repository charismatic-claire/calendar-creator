/**
 * 
 */
package org.calendarcreator.model.translator;

import java.util.HashMap;
import java.util.Map;

import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Holiday;
import org.calendarcreator.data.MonthOfYear;

/**
 *
 */
public class CalendarTranslatorGerman implements CalendarTranslator {
	private Map<DayOfWeek,String> mapDayOfWeek;
	
	private Map<MonthOfYear,String> mapMonthOfYear;
	
	private Map<Holiday,String> mapHoliday;
	
	public CalendarTranslatorGerman() {
		// day of week
		this.mapDayOfWeek = new HashMap<DayOfWeek,String>();
		mapDayOfWeek.put( DayOfWeek.MONDAY, "Mo" );
		mapDayOfWeek.put( DayOfWeek.TUESDAY, "Di" );
		mapDayOfWeek.put( DayOfWeek.WEDNESDAY, "Mi" );
		mapDayOfWeek.put( DayOfWeek.THURSDAY, "Do" );
		mapDayOfWeek.put( DayOfWeek.FRIDAY, "Fr" );
		mapDayOfWeek.put( DayOfWeek.SATURDAY, "Sa" );
		mapDayOfWeek.put( DayOfWeek.SUNDAY, "So" );
		
		// month of year
		this.mapMonthOfYear = new HashMap<MonthOfYear,String>();
		mapMonthOfYear.put( MonthOfYear.JANUARY, "Januar" );
		mapMonthOfYear.put( MonthOfYear.FEBRUARY, "Februar" );
		mapMonthOfYear.put( MonthOfYear.MARCH, "März" );
		mapMonthOfYear.put( MonthOfYear.APRIL, "April" );
		mapMonthOfYear.put( MonthOfYear.MAY, "Mai" );
		mapMonthOfYear.put( MonthOfYear.JUNE, "Juni" );
		mapMonthOfYear.put( MonthOfYear.JULY, "Juli" );
		mapMonthOfYear.put( MonthOfYear.AUGUST, "August" );
		mapMonthOfYear.put( MonthOfYear.SEPTEMBER, "September" );
		mapMonthOfYear.put( MonthOfYear.OCTOBER, "Oktober" );
		mapMonthOfYear.put( MonthOfYear.NOVEMBER, "November" );
		mapMonthOfYear.put( MonthOfYear.DECEMBER, "Dezember" );
		
		// holiday
		this.mapHoliday = new HashMap<Holiday,String>();
		mapHoliday.put( Holiday.ASCENSION_DAY, "Christi Himmelfahrt" );
		mapHoliday.put( Holiday.BOXING_DAY, "2. Weihnachtsfeiertag" );
		mapHoliday.put( Holiday.CHRISTMAS_DAY, "1. Weihnachtsfeiertag" );
		mapHoliday.put( Holiday.DAY_OF_GERMAN_UNITY, "Tag der Deutschen Einheit" );
		mapHoliday.put( Holiday.EASTER_DAY, "Ostersonntag" );
		mapHoliday.put( Holiday.EASTER_MONDAY, "Ostermontag" );
		mapHoliday.put( Holiday.MAY_DAY, "Tag der Arbeit" );
		mapHoliday.put( Holiday.NEW_YEARS_DAY, "Neujahr" );
		mapHoliday.put( Holiday.REFORMATION_DAY, "Reformationstag" );
		mapHoliday.put( Holiday.REPENTANCE_DAY, "Buß- und Bettag" );
		mapHoliday.put( Holiday.WHIT_MONDAY, "Pfingstmontag" );
		mapHoliday.put( Holiday.WHIT_SUNDAY, "Pfingstsonntag" );
	}
	
	@Override
	public String translateDayOfWeek(DayOfWeek dayOfWeek) {
		return mapDayOfWeek.get( dayOfWeek );
	}
	
	@Override
	public String translateMonthOfYear(MonthOfYear monthOfYear) {
		return mapMonthOfYear.get( monthOfYear );
	}

	@Override
	public String translateHoliday(Holiday holiday) {
		return mapHoliday.get( holiday );
	}
}
