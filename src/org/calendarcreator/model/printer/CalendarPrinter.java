/**
 * 
 */
package org.calendarcreator.model.printer;

import org.calendarcreator.data.Month;
import org.calendarcreator.data.Year;

/**
 * Serialize a calendar
 */
public interface CalendarPrinter {

	/**
	 * Convert a created year into a string
	 * @param year Abstract representation of the year
	 * @return Serialization of the year
	 */
	public String printYear( Year year );
	
	/**
	 * Convert month into string
	 * @param month Month
	 * @return String
	 */
	public String printMonth( Month month );
	
}
