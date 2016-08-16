/**
 * 
 */
package org.calendarcreator.data;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Year {

	/**
	 * the year
	 */
	private int year;
	
	/**
	 * the list of months
	 */
	private Map<Integer,Month> collectionOfMonths;
	
	/**
	 * constructor
	 */
	public Year( int year ) {
		// save year
		this.year = year;
		// evaluate leap year
		boolean isLeapYear = evaluateIsLeapYear();
		// evaluate offset
		int offsetDayOfWeek = evaluateOffsetDayOfWeek();
		// generate collection of months
		this.collectionOfMonths = generateCollectionOfMonths( offsetDayOfWeek, isLeapYear );
	}
	
	/**
	 * Is it a leap year?
	 */
	private boolean evaluateIsLeapYear() {
		// divisible by 400
		boolean result = ( year % 400 == 0 );
		// or divisible by 4, but not by 100
		result = result || ( ( year % 4 == 0 ) && !( year % 100 == 0 ) );
		// return result
		return result; 
	}
	
	/**
	 * Evaluate initial offset 
	 */
	private int evaluateOffsetDayOfWeek() {
		// evaluate
		int os1 = ( year - 1 ) / 4;
		int os2 = ( year - 1 ) / 100;
		int os3 = ( year - 1 ) / 400;
		
		// return result
		return ( year + os1 - os2 + os3 - 1 ) % 7;
	}
	
	/**
	 * generate collection of months
	 */
	private Map<Integer,Month> generateCollectionOfMonths( int offsetDayOfWeek, boolean isLeapYear ) {
		// generate HashMap
		Map<Integer, Month> collectionOfMonths= new HashMap<Integer,Month>();
		int weekOfYear = 0;
		for( int i = 1; i <= 12; i++ ) {
			// create month
			Month newMonth = new Month( i , offsetDayOfWeek, isLeapYear );
			
			// add week of year
			Map<Integer,Day> collectionOfDays = newMonth.getCollectionOfDays();
			for( int j = 1; j <= newMonth.getLengthOfMonth(); j++ ) {
				Day day = collectionOfDays.get( j );
				if( day.getDayOfWeek() == DayOfWeek.SUNDAY ) {
					day.setWeekOfYear( ++weekOfYear );
				}
			}
			
			// add month to collection
			collectionOfMonths.put( i , newMonth );
			
			// update offset
			offsetDayOfWeek = ( offsetDayOfWeek + newMonth.getLengthOfMonth() ) % 7;
		}
		
		// return result
		return collectionOfMonths;
	}
		
	public int getYear() {
		return year;
	}

	public Map<Integer, Month> getCollectionOfMonths() {
		return collectionOfMonths;
	}
}
