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
import org.calendarcreator.data.YearConfig;
import org.calendarcreator.gui.CalendarController;
import org.calendarcreator.io.CalendarReader;
import org.calendarcreator.io.CalendarWriter;
import org.calendarcreator.model.printer.CalendarPrinter;
import org.calendarcreator.model.printer.CalendarPrinterLandscape;
import org.calendarcreator.model.printer.CalendarPrinterPortrait;
import org.calendarcreator.model.printer.CalendarPrinterTexClassic;
import org.calendarcreator.model.printer.CalendarPrinterTexJeddi;
import org.calendarcreator.model.printer.CalendarPrinterTexKitchen;
import org.calendarcreator.model.printer.CalendarPrinterConfigXml;
import org.calendarcreator.model.translator.CalendarTranslator;
import org.calendarcreator.model.translator.CalendarTranslatorEnglish;
import org.calendarcreator.model.translator.CalendarTranslatorGerman;

/**
 * The 'Model' class in the MVC pattern. 
 * Perform actions on years.
 */
public class CalendarModelFull extends Observable implements CalendarModel {

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
	 * Constructor with no arguments
	 */
	public CalendarModelFull() {
		this.year = null;
		this.yearFactory = new YearFactory();
		this.createdYear = false;
		this.addedHolidays = false;
		this.addedEntries = false;
	}

	@Override
	public void createYear( int yearInteger ) {
			this.year = yearFactory.createYear( yearInteger );
			updateModelConfiguration();
	}
	
	@Override
	public void updateYear( int yearInteger ) {
		if( createdYear ) {
			// init 
			CalendarImportExport cie = new CalendarImportExport();
			// create year config
			YearConfig yearConfig = cie.year2YearConfig( year );
			// update configuration
			yearConfig.setYearInteger( yearInteger );;
			// import it
			year = cie.yearConfig2Year( yearConfig );
			// update
			updateModelConfiguration();
		}
	}
	
	@Override
	public void removeYear() {
		if( createdYear ) {
			year = null;
			updateModelConfiguration();
		}
	}

	@Override
	public void addHolidays() {
		if( createdYear && !( addedHolidays ) ) {
			yearFactory.addHolidays( year );
			updateModelConfiguration();
		}
	}
	
	@Override
	public void removeHolidays() {
		if( createdYear && addedHolidays ) {
			// init 
			CalendarImportExport cie = new CalendarImportExport();
			// create year config
			YearConfig yearConfig = cie.year2YearConfig( year );
			// update configuration
			yearConfig.setAddedHolidays( false );
			// import it
			year = cie.yearConfig2Year( yearConfig );
			// update
			updateModelConfiguration();
		}
	}

	@Override
	public void addEntry( Date date ) {
		if( createdYear ) {
			if( date.getEntry() != null ) {
				yearFactory.addEntry( year, date );
				updateModelConfiguration();
			}
		}
	}

	@Override
	public void removeEntry( Date date ) {
		if( createdYear && addedEntries ) {
			yearFactory.removeEntry( year, date );
			updateModelConfiguration();
		}
	}

	@Override
	public void addEntries( Dates dates ) {
		for( Date date : dates.getListOfDates() ) {
			addEntry( date );
		}
	}

	@Override
	public void removeEntries() {
		if( createdYear && addedEntries ) {
			// init 
			CalendarImportExport cie = new CalendarImportExport();
			// create year config
			YearConfig yearConfig = cie.year2YearConfig( year );
			// update configuration
			yearConfig.setDates( new Dates() );
			// import it
			year = cie.yearConfig2Year( yearConfig );
			// update
			updateModelConfiguration();
		}		
	}
	
	@Override
	public String getEntry( Date date ) {
		if( createdYear ) {
			return yearFactory.getEntry( year, date );
		}
		return null;
	}
	
	@Override
	public void exportYearToTex( Language lang, Style style, String filename ) {
		if( createdYear ) {
			try {
				// create string
				String printedYear = printYearTex( lang, style );
				// create writer
				CalendarWriter writer = new CalendarWriter();
				// write file
				writer.writeToDisk( printedYear, filename, Format.TEX );
			}
			catch( Exception e ) {
				System.err.println( "Error exporting calendar to TEX." );
			}
		}
	}

	@Override
	public void exportYearToConfigXml( String filename ) {
		if( createdYear ) {
			// create string
			String printedYear = printYearConfig();
			// create writer
			CalendarWriter writer = new CalendarWriter();
			// write file
			try {
				writer.writeToDisk( printedYear, filename, Format.XML );
			}
			catch( Exception e) {
				System.err.println( "Error exporting to XML." );
			}
		}
	}
	
	@Override
	public void importYearFromConfigXml( String filename ) {
		CalendarReader reader = new CalendarReader();
		CalendarImportExport importer = new CalendarImportExport();
		try {
			String data = reader.readFromDisk( filename );
			year = importer.importYearFromString( data );
			updateModelConfiguration();
		}
		catch( Exception e ) {
			System.err.println( "Error opening calendar." );
		}
	}
	
	@Override
	public Year getYear() {
		if( createdYear ) {
			return year;
		}
		return null;
	}
	
	@Override
	public void init( CalendarController controller ) {
		this.addObserver( controller );
	}

	/**
	 * Get the model configuration
	 * @return ModelConfiguration
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
		// check
		if( year != null ) {
			// set createdYear
			createdYear = true;
			// set addedHolidays
			addedHolidays = yearFactory.isAddedHolidays( year );
			// set addedEntries
			addedEntries = yearFactory.isAddedEntries( year );
		}
		// notify
		setChanged();
		notifyObservers( getModelConfiguration() );
	}
	
	/**
	 * Print an already created year as *.tex string
	 * @param lang Language of translation
	 * @param style Layout style of the printer class
	 * @return *.tex string
	 */
	private String printYearTex( Language lang, Style style ) throws Exception  {
		// init
		String printedYear = null;
		
		// process
		if( year != null ) {
			// create translator
			CalendarTranslator translator;
			switch( lang ) {
				case DE:
					translator = new CalendarTranslatorGerman();
					break;
				case EN:
					translator = new CalendarTranslatorEnglish();
					break;
				default:
					throw new Exception() ;
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
				case CLASSIC:
					printer = new CalendarPrinterTexClassic( translator );
					break;
				default:
					throw new Exception();
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
	private String printYearConfig() {
		// init
		String printedYear = null;
		// process
		if( year != null ) {
			// create printer
			CalendarPrinter printer = new CalendarPrinterConfigXml();
			// create string
			printedYear = printer.printYear( year );
		}
		// return
		return printedYear;
	}

}
