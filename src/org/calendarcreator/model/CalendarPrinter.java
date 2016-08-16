/**
 * 
 */
package org.calendarcreator.model;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Year;

/**
 *
 */
public interface CalendarPrinter {

	public String printYear( Year year );
	
	public String printMonth( Month month );
	
	public String printDay( Day day );
	
}
