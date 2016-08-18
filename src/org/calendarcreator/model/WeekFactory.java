/**
 * 
 */
package org.calendarcreator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.MonthOfYear;
import org.calendarcreator.data.Week;

/**
 * Generate a set of weeks from a given month
 */
public class WeekFactory {
	
	private Week week;
	
	public List<Week> generateListOfWeeks( Month month ) {
		// init
		List<Week> listOfWeeks = new ArrayList<Week>();
		MonthOfYear monthOfYear = month.getMonthOfYear();
		Integer weekOfYear = null;
		
		// loop days
		Set<Entry<Integer,Day>> days = month.getCollectionOfDays().entrySet();
		for( Entry<Integer,Day> day : days ) {
			// create first week
			if( day.getValue().getDayOfMonth() == 1 ) { 
				week = new Week( monthOfYear );
			}
			// create new week if necessary
			else if( day.getValue().getDayOfWeek().equals( DayOfWeek.MONDAY ) ) {
				// put week of year and update
				if( weekOfYear != null ) {
					week.setWeekOfYear( weekOfYear++ );
				}
				// put old week, create new
				listOfWeeks.add( week );
				week = new Week( monthOfYear );
			}
			// set week of year
			else if( day.getValue().getDayOfWeek().equals( DayOfWeek.SUNDAY ) ) {
				weekOfYear = day.getValue().getWeekOfYear();
			}
			// put day
			week.putDay( day.getValue() );
		}
		
		// add last week
		week.setWeekOfYear( weekOfYear );
		listOfWeeks.add( week );
		
		// return result
		return listOfWeeks;
	}
}
