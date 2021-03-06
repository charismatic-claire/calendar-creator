package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;
import org.calendarcreator.data.Year;
import org.calendarcreator.gui.CalendarController;

public class CalendarModelMini extends Observable implements CalendarModel {
	
	private boolean createdYear;
	
	private boolean addedHolidays;
	
	private boolean addedEntries;
	
	private Year year;
	
	public CalendarModelMini() {
		createdYear = false;
		addedHolidays = false;
		addedEntries = false;
		year = null;
	}

	@Override
	public void createYear(int yearInteger) {
		System.out.println( "createYear()" );
		createdYear = true;
		addedHolidays = false;
		addedEntries = false;
		year = new Year( yearInteger );
		setChanged();
		notifyObservers( getModelConfiguration() );
	}

	@Override
	public void updateYear(int yearInteger) {
		if( createdYear ) {
			System.out.println( "updateYear()" );
			year = new Year( yearInteger );
			setChanged();
			notifyObservers( getModelConfiguration() );			
		}
	}

	@Override
	public void removeYear() {
		if( createdYear ) {
			System.out.println( "removeYear()" );
			createdYear = false;
			year = null;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void addHolidays() {
		if( createdYear && !( addedHolidays ) ) {
			System.out.println( "addHolidays()" );
			addedHolidays = true;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void removeHolidays() {
		if( createdYear && addedHolidays ) {
			System.out.println( "removeHolidays()" );
			addedHolidays = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void addEntry( Date date ) {
		if( createdYear ) {
			System.out.println( "addEntry()" );
			addedEntries = true;
			setChanged();
			notifyObservers( getModelConfiguration() );			
		}
	}
	
	@Override
	public void removeEntry( Date date ) {
		if( createdYear && addedEntries ) {
			System.out.println( "removeEntry()" );
			setChanged();
			notifyObservers( getModelConfiguration() );			
		}
	}
	
	@Override
	public void addEntries( Dates dates ) {
		if( createdYear ) {
			System.out.println( "addEntries()" );
			addedEntries = true;
			setChanged();
			notifyObservers( getModelConfiguration() );						
		}
	}

	@Override
	public void removeEntries() {
		if( createdYear && addedEntries ) {
			System.out.println( "removeEntries()" );
			addedEntries = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}

	}
	
	@Override
	public String getEntry( Date date ) {
		return null;
	}

	@Override
	public void exportYearToTex(Language lang, Style style, String filename) {
		if( createdYear ) {
			System.out.println( "exportYearToTex(): " + lang + ", " + style + ", " + filename );
		}
	}

	@Override
	public void exportYearToConfigXml(String filename) {
		if( createdYear ) {
			System.out.println( "exportYearToConfigXml(): " + filename );
		}
	}

	@Override
	public void importYearFromConfigXml(String filename) {
		System.out.println( "importYearFromConfigXml() :" + filename );
		createdYear = true;
		addedHolidays = false;
		addedEntries = false;
		year = new Year( 2015 );
		setChanged();
		notifyObservers( getModelConfiguration() );
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

	private ModelConfiguration getModelConfiguration() {
		ModelConfiguration mc = new ModelConfiguration();
		mc.setYear( year );
		mc.setCreatedYear( createdYear );
		mc.setAddedHolidays( addedHolidays );
		mc.setAddedEntries( addedEntries );
		return mc;
	}

}
