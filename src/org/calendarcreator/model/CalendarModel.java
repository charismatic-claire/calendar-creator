/**
 * 
 */
package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Format;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;
import org.calendarcreator.io.CalendarWriter;

/**
 * The 'Model' class in the MVC pattern. 
 * Perform actions on years.
 */
public class CalendarModel extends Observable {

	/**
	 * Year, collection of weeks
	 */
	private Year year;
	
	/**
	 * Can create and manipulate a created year 
	 */
	private YearFactory yearFactory;
	
	/**
	 * True if a year was created
	 */
	private boolean createdYear;
	
	/**
	 * True if holidays were added
	 */
	private boolean addedHolidays;
	
	/**
	 * Constructor
	 */
	public CalendarModel() {
		this.year = null;
		this.yearFactory = null;
		this.createdYear = false;
		this.addedHolidays = false;
	}

	/**
	 * Create a new year and hold it as private attribute
	 * @param year Year as integer
	 */
	public void createYear( int year ) {
		yearFactory = new YearFactory();
		this.year = yearFactory.createYear( year );
		createdYear = true;
	}

	/**
	 * Add holidays to the year hold as attribute
	 */
	public void addHolidays() {
		if( createdYear ) {
			yearFactory.addHolidays( year );
			addedHolidays = true;
		}
	}
	
	/**
	 * 
	 * @param lang Language of translation
	 * @param style Layout style of the printer class
	 * @param filename Destination file
	 * @return Success
	 */
	public boolean exportYearToTex( Language lang, Style style, String filename ) {
		if( createdYear ) {
			// create string
			String printedYear = printYearTex( lang, style );
			// create writer
			CalendarWriter writer = new CalendarWriter();
			// write file
			return writer.writeToDisk( printedYear, filename, Format.TEX );
		}
		else {
			return false;
		}
	}

	/**
	 * 
	 * @param lang Language of translation
	 * @param style Layout style of the printer class
	 * @param filename Destination file
	 * @return Success
	 */
	public boolean exportYearToXml( String filename ) {
		if( createdYear ) {
			// create string
			String printedYear = printYearXml();
			// create writer
			CalendarWriter writer = new CalendarWriter();
			// write file
			return writer.writeToDisk( printedYear, filename, Format.XML );
		}
		else {
			return false;
		}
	}
	
	/**
	 * Get year as integer
	 * @return Year as integer
	 */
	public int getYear() {
		return year.getYear();
	}
	
	/**
	 * True if year was created
	 * @return true/false
	 */
	public boolean getCreatedYear() {
		return createdYear;
	}
	
	/**
	 * True if holidays were added
	 * @return true/false
	 */
	public boolean getAddedHolidays() {
		return addedHolidays;
	}

	/**
	 * Print an already created year as *.tex string
	 * @param lang Language of translation
	 * @param style Layout style of the printer class
	 * @return *.tex string
	 */
	private String printYearTex( Language lang, Style style ) {
		// init
		String printedYear = null;
		
		// process
		if( year != null ) {
			// create translator
			CalendarTranslator translator;
			if( lang == Language.DE ) {
				translator = new CalendarTranslatorGerman();
			}
			else {
				translator = new CalendarTranslatorEnglish();
			}
			// create printer
			CalendarPrinter printer;
			switch( style ) {
				case PLAIN:
					printer = new CalendarPrinterPortrait( translator );
					break;
				case LANDSCAPE:
					printer = new CalendarPrinterLandscape( translator );
					break;
				case KITCHEN:
					printer = new CalendarPrinterTexKitchen( translator );
					break;
				case JEDDI:
					printer = new CalendarPrinterTexJeddi( translator );
					break;
				default:
					printer = new CalendarPrinterTexClassic( translator );
					break;
			}
			// create string
			printedYear = printer.printYear( year );
		}
		
		// return
		return printedYear;
	}
	
	/**
	 * Print an already created year as *.xml string
	 * @return *xml string
	 */
	private String printYearXml() {
		// init
		String printedYear = null;
		// process
		if( year != null ) {
			// create printer
			CalendarPrinter printer = new CalendarPrinterXml();
			// create string
			printedYear = printer.printYear( year );
		}
		// return
		return printedYear;
	}

}
