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
	
	private int numberOfEntries;
	
	private Year year;
	
	public CalendarModelMini() {
		createdYear = false;
		addedHolidays = false;
		addedEntries = false;
		numberOfEntries = 0;
		year = null;
	}

	@Override
	public void createYear(int yearInteger) {
		System.out.println( "createYear()" );
		createdYear = true;
		addedHolidays = false;
		addedEntries = false;
		numberOfEntries = 0;
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
	public void addEntry(Date date) {
		if( createdYear ) {
			System.out.println( "addEntry()" );
			addedEntries = true;
			numberOfEntries++;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void removeEntry(Date date) {
		if( createdYear && addedEntries ) {
			if( --numberOfEntries < 1 ) {
				System.out.println( "removeEntry()" );
				addedEntries = false;
				setChanged();
				notifyObservers( getModelConfiguration() );
			}
		}

	}

	@Override
	public void addEntries(Dates dates) {
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
	public boolean exportYearToTex(Language lang, Style style, String filename) {
		if( createdYear ) {
			System.out.println( "exportYearToTex()" );
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean exportYearToConfigXml(String filename) {
		if( createdYear ) {
			System.out.println( "exportYearToConfigXml()" );
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean importYearFromConfigXml(String filename) {
		System.out.println( "importYearFromConfigXml()" );
		createdYear = true;
		addedHolidays = false;
		addedEntries = false;
		numberOfEntries = 0;
		year = new Year( 2015 );
		setChanged();
		notifyObservers( getModelConfiguration() );
		return true;
	}

	@Override
	public ModelConfiguration getModelConfiguration() {
		ModelConfiguration mc = new ModelConfiguration();
		mc.setYear( year );
		mc.setCreatedYear( createdYear );
		mc.setAddedHolidays( addedHolidays );
		mc.setAddedEntries( addedEntries );
		return mc;
	}
	
	public void addController( CalendarController controller ) {
		this.addObserver( controller );
	}

}
