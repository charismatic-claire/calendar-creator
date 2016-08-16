/**
 * 
 */
package org.calendarcreator.model;

import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Holiday;
import org.calendarcreator.data.MonthOfYear;

/**
 *
 */
public interface CalendarTranslator {

	public String translateDayOfWeek( DayOfWeek dayOfWeek );
	
	public String translateMonthOfYear( MonthOfYear monthOfYear );
	
	public String translateHoliday( Holiday holiday );
}
