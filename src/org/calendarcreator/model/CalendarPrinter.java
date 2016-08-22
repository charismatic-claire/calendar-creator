/**
 * 
 */
package org.calendarcreator.model;

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
}
