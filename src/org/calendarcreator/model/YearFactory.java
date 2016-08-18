/**
 * 
 */
package org.calendarcreator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Holiday;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Year;

import java.util.Set;

/**
 *
 */
public class YearFactory {
	
	/**
	 * A year
	 */
	private Year year;
	
	/**
	 * Create a year
	 */
	public Year createYear( int year ) {
		return new Year( year );
	}
	
	/**
	 * Add holidays to a given year
	 */
	public void addHolidays( Year year ) {
		// save the year
		this.year = year;
		// get the dates
		Map<Holiday,Date> holidays = evaluateDatesOfHolidays();
		Set<Entry<Holiday,Date>> entries = holidays.entrySet();
		// add them to year
		for( Entry<Holiday,Date> entry : entries ) {
			setHolidayOfDate( entry.getValue(), entry.getKey() );
		}
	}

	/**
	 * Set holiday of a specific date
	 */
	private void setHolidayOfDate( Date date, Holiday holiday ) {
		Month month = year.getCollectionOfMonths().get( date.getMonthOfYear() );
		Day day = month.getCollectionOfDays().get( date.getDayOfMonth() );
		day.setHoliday( holiday );
	}
	
	/**
	 * Get day of week of a specific date
	 */
	private DayOfWeek getDayOfWeekOfDate( Date date ) {
		Month month = year.getCollectionOfMonths().get( date.getMonthOfYear() );
		Day day = month.getCollectionOfDays().get( date.getDayOfMonth() );
		return day.getDayOfWeek();
	}
	
	private Map<Holiday,Date> evaluateDatesOfHolidays() {
		// initialize
		Map<Holiday,Date> holidays = new HashMap<Holiday,Date>();
		Date dateOfEasterDay = evaluateDateOfEasterDay();
		
		// NEW_YEARS_DAY
		holidays.put( Holiday.NEW_YEARS_DAY,  new Date( 1, 1 ) );
		// EASTER_DAY
		holidays.put( Holiday.EASTER_DAY, dateOfEasterDay );
		// EASTER_MONDAY
		holidays.put( Holiday.EASTER_MONDAY , dayOfYear2date( date2dayOfYear( dateOfEasterDay ) + 1 ) );
		// MAY_DAY
		holidays.put( Holiday.MAY_DAY, new Date( 5, 1 ) );
		// ASCENSION_DAY
		holidays.put( Holiday.ASCENSION_DAY, dayOfYear2date( date2dayOfYear( dateOfEasterDay ) + 39 ) );
		// WHIT_SUNDAY
		holidays.put( Holiday.WHIT_SUNDAY, dayOfYear2date( date2dayOfYear( dateOfEasterDay ) + 49 ) );
		// WHIT_MONDAY
		holidays.put( Holiday.WHIT_MONDAY, dayOfYear2date( date2dayOfYear( dateOfEasterDay ) + 50 ) );
		// DAY_OF_GERMAN_UNITY
		holidays.put( Holiday.DAY_OF_GERMAN_UNITY, new Date( 10, 3 ) );
		// REFORMATION_DAY
		holidays.put( Holiday.REFORMATION_DAY, new Date( 10, 31 ) );
		// REPENTANCE_DAY
		holidays.put( Holiday.REPENTANCE_DAY, evaluateDateOfRepentanceDay() );
		// CHRISTMAS_DAY
		holidays.put( Holiday.CHRISTMAS_DAY, new Date( 12, 25 ) );
		// BOXING_DAY
		holidays.put( Holiday.BOXING_DAY, new Date( 12, 26 ) );
		
		// return result
		return holidays;
	}

	/**
	 * Evaluate the date of Repentance Day
	 * @return
	 */
	private Date evaluateDateOfRepentanceDay() {
		// day of week 11/23
		DayOfWeek dayOfWeek1123 = getDayOfWeekOfDate( new Date( 11, 23 ) );
		// return result
		switch( dayOfWeek1123 ) {
		case MONDAY:
			return new Date( 11, 18 );
		case TUESDAY:
			return new Date( 11, 17 );
		case WEDNESDAY:
			return new Date( 11, 16 );
		case THURSDAY:
			return new Date( 11, 22 );
		case FRIDAY:
			return new Date( 11, 21 );
		case SATURDAY:
			return new Date( 11, 20 );
		default:
			return new Date( 11, 19 );
		}
	}
	
	/**
	 * Evaluate the date of Easter Day
	 * @return
	 */
	private Date evaluateDateOfEasterDay() {
		// the year
		int x = year.getYear();
		// secondary number
		int k = x / 100;
		// secondary moon phase
		int m = ( 15 + ( (3*k + 3) / 4 ) ) - ( (8*k + 13) / 25 );
		// secondary sun phase
		int s = 2 - ( (3*k + 3) / 4 );
		// moon parameter
		int a = x % 19;
		// first full moon in spring
		int d = ( 19*a + m ) % 30;
		// correction of calendar
		int r = ( d + (a / 11) ) / 29;
		// easter limit
		int og = 21 + d - r;
		// first sunday of march
		int sz = 7 - ( ( x + (x / 4) + s ) % 7 );
		// distance of easter day and easter limit
		int oe = 7 - ( (og - sz) % 7 );
		// date of easter day as march offset
		int os = og + oe;
		
		// return result
		if( os <= 31 ) {
			return new Date( 3, os );
		}
		else {
			return new Date( 4, os % 31 );
		}
	}
	
	/**
	 * transform date into day of year
	 */
	private int date2dayOfYear( Date date ) {
		int dayOfYear = date.getDayOfMonth();
		for( int i = 1; i < date.getMonthOfYear(); i++ ) {
			Month month = year.getCollectionOfMonths().get( i );
			dayOfYear += month.getLengthOfMonth();
		}
		return dayOfYear;
	}
	
	/**
	 * transform day of year into date
	 */
	private Date dayOfYear2date( int dayOfYear ) {
		int monthOfYear = 1;
		int dayOfMonth;
		
		// number of months
		int i = 1;
		while( dayOfYear > year.getCollectionOfMonths().get( i ).getLengthOfMonth() ) {
			monthOfYear++;
			dayOfYear -= year.getCollectionOfMonths().get( i ).getLengthOfMonth();
			i++;
		}
		
		// number of days leftover
		dayOfMonth = dayOfYear;
		
		// return result
		return new Date( monthOfYear, dayOfMonth );
	}
}
