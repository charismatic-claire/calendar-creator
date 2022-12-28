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
public class CalendarTranslatorEnglish implements CalendarTranslator {

	private Map<DayOfWeek,String> mapDayOfWeek;
	
	private Map<MonthOfYear,String> mapMonthOfYear;
	
	private Map<Holiday,String> mapHoliday;
	
	public CalendarTranslatorEnglish() {
		// day of week
		this.mapDayOfWeek = new HashMap<DayOfWeek,String>();
		mapDayOfWeek.put( DayOfWeek.MONDAY, "M" );
		mapDayOfWeek.put( DayOfWeek.TUESDAY, "T" );
		mapDayOfWeek.put( DayOfWeek.WEDNESDAY, "W" );
		mapDayOfWeek.put( DayOfWeek.THURSDAY, "T" );
		mapDayOfWeek.put( DayOfWeek.FRIDAY, "F" );
		mapDayOfWeek.put( DayOfWeek.SATURDAY, "S" );
		mapDayOfWeek.put( DayOfWeek.SUNDAY, "S" );
		
		// month of year
		this.mapMonthOfYear = new HashMap<MonthOfYear,String>();
		mapMonthOfYear.put( MonthOfYear.JANUARY, "January" );
		mapMonthOfYear.put( MonthOfYear.FEBRUARY, "February" );
		mapMonthOfYear.put( MonthOfYear.MARCH, "March" );
		mapMonthOfYear.put( MonthOfYear.APRIL, "April" );
		mapMonthOfYear.put( MonthOfYear.MAY, "May" );
		mapMonthOfYear.put( MonthOfYear.JUNE, "June" );
		mapMonthOfYear.put( MonthOfYear.JULY, "July" );
		mapMonthOfYear.put( MonthOfYear.AUGUST, "August" );
		mapMonthOfYear.put( MonthOfYear.SEPTEMBER, "September" );
		mapMonthOfYear.put( MonthOfYear.OCTOBER, "October" );
		mapMonthOfYear.put( MonthOfYear.NOVEMBER, "November" );
		mapMonthOfYear.put( MonthOfYear.DECEMBER, "December" );
		
		// holiday
		this.mapHoliday = new HashMap<Holiday,String>();
//		mapHoliday.put( Holiday.ASCENSION_DAY, "Ascension Day" );
//		mapHoliday.put( Holiday.BOXING_DAY, "Boxing Day" );
//		mapHoliday.put( Holiday.CHRISTMAS_DAY, "Christmas Day" );
//		mapHoliday.put( Holiday.CHRISTMAS_EVE , "Christmas Eve" );
//		mapHoliday.put( Holiday.DAY_OF_GERMAN_UNITY, "Day of German Unity" );
//		mapHoliday.put( Holiday.EASTER_SUNDAY, "Easter Sunday" );
//		mapHoliday.put( Holiday.EASTER_MONDAY, "Easter Monday" );
//		mapHoliday.put( Holiday.GOOD_FRIDAY , "Good Friday" );
//		mapHoliday.put( Holiday.MAY_DAY, "May Day" );
//		mapHoliday.put( Holiday.NEW_YEARS_DAY, "New Years Day" );
//		mapHoliday.put( Holiday.REFORMATION_DAY, "Reformation Day" );
//		mapHoliday.put( Holiday.REPENTANCE_DAY, "Repentance Day" );
//		mapHoliday.put( Holiday.WHIT_MONDAY, "Whit Monday" );
//		mapHoliday.put( Holiday.WHIT_SUNDAY, "Whit Sunday" );
		mapHoliday.put( Holiday.ASCENSION_DAY, "Christi Himmelfahrt" );
		mapHoliday.put( Holiday.BOXING_DAY, "2. Weihnachtsfeiertag" );
		mapHoliday.put( Holiday.CHRISTMAS_DAY, "1. Weihnachtsfeiertag" );
		mapHoliday.put( Holiday.CHRISTMAS_EVE, "Heiligabend" );
		mapHoliday.put( Holiday.DAY_OF_GERMAN_UNITY, "Tag der Deutschen Einheit" );
		mapHoliday.put( Holiday.EASTER_SUNDAY, "Ostersonntag" );
		mapHoliday.put( Holiday.EASTER_MONDAY, "Ostermontag" );
		mapHoliday.put( Holiday.GOOD_FRIDAY , "Karfreitag" );
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
