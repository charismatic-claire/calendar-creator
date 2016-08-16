/**
 * 
 */
package org.calendarcreator.data;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Month {
	/**
	 * month of year
	 */
	private MonthOfYear monthOfYear;
	
	/**
	 * length of month
	 */
	private int lengthOfMonth;

	/**
	 * all days of the month
	 */
	private Map<Integer,Day> collectionOfDays;
	
	/**
	 * An array of all the month in a year
	 */
	private MonthOfYear[] monthsOfYear = {
			MonthOfYear.JANUARY,
			MonthOfYear.FEBRUARY,
			MonthOfYear.MARCH,
			MonthOfYear.APRIL,
			MonthOfYear.MAY,
			MonthOfYear.JUNE,
			MonthOfYear.JULY,
			MonthOfYear.AUGUST,
			MonthOfYear.SEPTEMBER,
			MonthOfYear.OCTOBER,
			MonthOfYear.NOVEMBER,
			MonthOfYear.DECEMBER
	};
	
	/**
	 * constructor II
	 */
	public Month( int monthOfYear, int offsetDayOfWeek, boolean isLeapYear ) {
		// save month
		this.monthOfYear = monthsOfYear[ monthOfYear - 1 ];
		// evaluate length
		this.lengthOfMonth = evaluateLengthOfMonth( isLeapYear );
		// generate collection of days
		this.collectionOfDays = generateCollectionOfDays( offsetDayOfWeek );
	}

	/**
	 * evaluate length of month
	 */
	private int evaluateLengthOfMonth( boolean isLeapYear ) {
		// month of length 30
		EnumSet<MonthOfYear> monthOfLength30 = EnumSet.of(
				MonthOfYear.APRIL,
				MonthOfYear.JUNE,
				MonthOfYear.SEPTEMBER,
				MonthOfYear.NOVEMBER
		);
		if( monthOfLength30.contains( monthOfYear ) ) {
			return 30;
		}
		
		// month of length 28 or 29
		else if( monthOfYear == MonthOfYear.FEBRUARY ) {
			if( isLeapYear ) {
				return 29;
			}
			else {
				return 28;
			}
		}
		
		// month of length 31
		else {
			return 31;
		}
	}
	
	/**
	 * generate collection of days
	 */
	private Map<Integer,Day> generateCollectionOfDays( int offsetDayOfWeek ) {
		// the days of the week
		DayOfWeek[] daysOfWeek = {
				DayOfWeek.MONDAY,
				DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY,
				DayOfWeek.SATURDAY,
				DayOfWeek.SUNDAY
		};
		
		// generate HashMap
		Map<Integer,Day> collectionOfDays = new HashMap<Integer,Day>();
		for( int i = 0; i < lengthOfMonth; i++ ) {
			collectionOfDays.put( i+1 , new Day( i+1, daysOfWeek[ ( i + offsetDayOfWeek ) % 7 ] ) );
		}
		
		// return result
		return collectionOfDays;
	}

	public MonthOfYear getMonthOfYear() {
		return monthOfYear;
	}

	public Map<Integer,Day> getCollectionOfDays() {
		return collectionOfDays;
	}

	public int getLengthOfMonth() {
		return lengthOfMonth;
	}
}
