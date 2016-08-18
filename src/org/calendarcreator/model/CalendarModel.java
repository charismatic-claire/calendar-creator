/**
 * 
 */
package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;

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
	 * Constructor
	 */
	public CalendarModel() {
		this.year = null;
		this.yearFactory = null;
	}

	/**
	 * Create a new year and hold it as private attribute
	 * @param year Year as integer
	 */
	public void createYear( int year ) {
		this.yearFactory = new YearFactory();
		this.year = yearFactory.createYear( year );
	}

	/**
	 * Add holidays to the year hold as attribute
	 */
	public void addHolidays() {
		if( yearFactory != null && year != null ) {
			yearFactory.addHolidays( year );
		}
	}

	/**
	 * Print an already created year
	 * @param language Language of translation
	 * @param style Layout style of the printer class
	 */
	public void printYear( Language language, Style style ) {
		if( year != null ) {
			// create translator
			CalendarTranslator translator;
			if( language == Language.DE ) {
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
					printer = new CalendarPrinterPortraitTexKitchen( translator );
					break;
				default:
					printer = new CalendarPrinterPortraitTexClassic( translator );
					break;
			}
			// print year
			System.out.println( printer.printYear( year ) );
		}
	}

//	private String lastActionCommand;
//
//	public String getLastActionCommand() {
//		return lastActionCommand;
//	}
//	
//	public void setLastActionCommand( String lastActionCommand ) {
//		// update variable
//		this.lastActionCommand = lastActionCommand;
//		// tell observer
//		setChanged();
//		notifyObservers( lastActionCommand );
//	}
}
