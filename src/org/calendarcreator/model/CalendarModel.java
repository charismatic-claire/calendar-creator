/**
 * 
 */
package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;

/**
 *
 */
public class CalendarModel extends Observable {

	private String lastActionCommand;
	
	private YearManager yearManager;
	
	public String getLastActionCommand() {
		return lastActionCommand;
	}
	
	public void setLastActionCommand( String lastActionCommand ) {
		// update variable
		this.lastActionCommand = lastActionCommand;
		// tell observer
		setChanged();
		notifyObservers( lastActionCommand );
	}
	
	public void createYear( int year ) {
		this.yearManager = new YearManager( new Year( year ) );
	}
	
	public void autoAddHolidays() {
		yearManager.autoAddHolidays();
	}
	
	public void printYear( Language language, Style style ) {
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
		if( style == Style.PLAIN ) {
			printer = new CalendarPrinterPlain( translator );
		}
		else {
			printer = new CalendarPrinterLandscape( translator );
		}
		// print year
		System.out.println( printer.printYear( yearManager.getYear() ) );
	}
}
