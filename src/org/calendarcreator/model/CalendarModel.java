/**
 * 
 */
package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Format;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;
import org.calendarcreator.io.CalendarReader;
import org.calendarcreator.io.CalendarWriter;
import org.calendarcreator.model.printer.CalendarPrinter;
import org.calendarcreator.model.printer.CalendarPrinterLandscape;
import org.calendarcreator.model.printer.CalendarPrinterPortrait;
import org.calendarcreator.model.printer.CalendarPrinterTexClassic;
import org.calendarcreator.model.printer.CalendarPrinterTexJeddi;
import org.calendarcreator.model.printer.CalendarPrinterTexKitchen;
import org.calendarcreator.model.printer.CalendarPrinterXml;
import org.calendarcreator.model.translator.CalendarTranslator;
import org.calendarcreator.model.translator.CalendarTranslatorEnglish;
import org.calendarcreator.model.translator.CalendarTranslatorGerman;

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
	 * True if entries were added
	 */
	private boolean addedEntries;
	
	/**
	 * Constructor
	 */
	public CalendarModel() {
		this.year = null;
		this.yearFactory = new YearFactory();
		this.createdYear = false;
		this.addedHolidays = false;
		this.addedEntries = false;
	}

	/**
	 * Create a new year and hold it as private attribute
	 * @param year Year as integer
	 */
	public void createYear( int yearInteger ) {
		this.year = yearFactory.createYear( yearInteger );
		updateModelConfiguration();
	}

	/**
	 * Add holidays to the year hold as attribute
	 */
	public void addHolidays() {
		if( createdYear ) {
			yearFactory.addHolidays( year );
			updateModelConfiguration();
		}
	}
	
	/**
	 * Add entry to the year hold as an attribute
	 */
	public void addEntry( Date date ) {
		if( createdYear && date.getEntry() != null ) {
			yearFactory.addEntry( year, date );
			updateModelConfiguration();
		}
	}
	
	/**
	 * Add entries to the year hold as an attribute
	 */
	public void addEntries( Dates dates ) {
		for( Date date : dates.getListOfDates() ) {
			addEntry( date );
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
	 * Import year from *.xml file
	 */
	public boolean importYearFromXml( String filename ) {
		CalendarReader reader = new CalendarReader();
		CalendarImporter importer = new CalendarImporter();
		try {
			String data = reader.readFromDisk( filename );
			year = importer.importYearFromString( data );
			updateModelConfiguration();
		}
		catch( Exception e ) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the actual state of the model, will be used by the controller
	 * @return modelConfiguration
	 */
	public ModelConfiguration getModelConfiguration() {
		ModelConfiguration modelConfiguration = new ModelConfiguration();
		modelConfiguration.setYear( year );
		modelConfiguration.setCreatedYear( createdYear );
		modelConfiguration.setAddedHolidays( addedHolidays );
		modelConfiguration.setAddedEntries( addedEntries );
		return modelConfiguration;
	}

	/**
	 * Update state of the model
	 */
	private void updateModelConfiguration() {
		// init
		createdYear = false;
		addedHolidays = false;
		addedEntries = false;
		if( year != null ) {
			// set createdYear
			createdYear = true;
			// set addedHolidays
			addedHolidays = yearFactory.isAddedHolidays( year );
			// set addedEntries
			addedEntries = yearFactory.isAddedEntries( year );
		}
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
	 * @return *.xml string
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
