/**
 * 
 */
package org.calendarcreator.model;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;
import org.calendarcreator.gui.CalendarController;

/**
 * Interface for communication between Controller and Model
 */
public interface CalendarModel {
	
	/**
	 * Create a new calendar year.
	 * Precondition: NONE
	 * @param yearInteger year as integer number
	 */
	public void createYear( int yearInteger );
	
	/**
	 * Update the calendar year, change the "yearInteger" attribute
	 * inherit all the other.
	 * Precondition: createdYear 
	 * @param yearInteger year as integer number
	 */
	public void updateYear( int yearInteger );
	
	/**
	 * Remove the created calendar year
	 * Precondition: createdYear
	 */
	public void removeYear();
	
	/**
	 * Automatically add holidays to the calendar year
	 * Precondition: createdYear, not addedHolidays
	 */
	public void addHolidays();
	
	/**
	 * Remove all the added holidays from the calendar year
	 * Precondition: createdYear, addedHolidays
	 */
	public void removeHolidays();
	
	/**
	 * Add an "Entry" to a specific "Day" of the calendar year.
	 * Precondition: createdYear
	 * @param date the date having the entry to be added
	 */
	public void addEntry( Date date );
	
	/**
	 * Remove an "Entry" from a specific "Day" of the calendar year.
	 * Precondition: createdYear, addedEntries
	 * @param date the date to be removed
	 */
	public void removeEntry( Date date );
	
	/**
	 * Do "addEntry" for every "Date" in the ArrayList<Date>
	 * Precondition: createdYear
	 * @param dates ArrayList<Date> of "Dates" to be added
	 */
	public void addEntries( Dates dates );
	
	/**
	 * Remove all entries from the calendar year.
	 * Precondition: createdYear, addedEntries
	 */
	public void removeEntries();
	
	/**
	 * Export the calendar year to a valid TEX file  
	 * Precondition: createdYear
	 * @param lang the language
	 * @param style the style
	 * @param filename path to output file
	 * @return true if successful
	 */
	public boolean exportYearToTex( Language lang, Style style, String filename );
	
	/**
	 * Export the configuration of a calendar year to a XML file.
	 * Precondition: createdYear
	 * @param filename path to output file
	 * @return true if successful
	 */
	public boolean exportYearToConfigXml( String filename );
	
	/**
	 * Import the configuration of a calendar year from a XML file
	 * and create a new year with exactly that properties.
	 * Precondition: NONE
	 * @param filename path to input file
	 * @return true if successful
	 */
	public boolean importYearFromConfigXml( String filename );
	
	/**
	 * Get the "ModelConfiguration" (wrapper class) of the model
	 * Precondition: NONE
	 * @return ModelConfiguration
	 */
	public ModelConfiguration getModelConfiguration();
	
	/**
	 * Return calendar year from the model
	 * Precondition: createdYear
	 * @return Year
	 */
	public Year getYear();
	
	/**
	 * Connect CalendarModel to a CalendarController
	 * @param controller CalendarController
	 */
	public void addController( CalendarController controller );

}
