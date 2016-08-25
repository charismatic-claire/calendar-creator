package org.calendarcreator.model;

import java.util.Observable;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;

public class CalendarModelMini extends Observable implements CalendarModel {
	
	private boolean createdYear;
	
	private boolean addedHolidays;
	
	private boolean addedEntries;
	
	private int numberOfEntries;
	
	public CalendarModelMini() {
		createdYear = false;
		addedHolidays = false;
		addedEntries = false;
		numberOfEntries = 0;
	}

	@Override
	public void createYear(int yearInteger) {
		if( !( createdYear ) ) {
			createdYear = true;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void updateYear(int yearInteger) {
		if( createdYear ) {
			// do nothing...
		}
	}

	@Override
	public void removeYear() {
		if( createdYear ) {
			createdYear = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void addHolidays() {
		if( createdYear && !( addedHolidays ) ) {
			addedHolidays = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void removeHolidays() {
		if( createdYear && addedHolidays ) {
			addedHolidays = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}
	}

	@Override
	public void addEntry(Date date) {
		if( createdYear ) {
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
				addedEntries = false;
				setChanged();
				notifyObservers( getModelConfiguration() );
			}
		}

	}

	@Override
	public void addEntries(Dates dates) {
		if( createdYear ) {
			addedEntries = true;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}

	}

	@Override
	public void removeEntries() {
		if( createdYear && addedEntries ) {
			addedEntries = false;
			setChanged();
			notifyObservers( getModelConfiguration() );
		}

	}

	@Override
	public boolean exportYearToTex(Language lang, Style style, String filename) {
		if( createdYear ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean exportYearToConfigXml(String filename) {
		if( createdYear ) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean importYearFromConfigXml(String filename) {
		createdYear = true;
		addedHolidays = false;
		addedEntries = false;
		setChanged();
		notifyObservers( getModelConfiguration() );
		return true;
	}

	@Override
	public ModelConfiguration getModelConfiguration() {
		ModelConfiguration mc = new ModelConfiguration();
		mc.setCreatedYear( createdYear );
		mc.setAddedHolidays( addedHolidays );
		mc.setAddedEntries( addedEntries );
		return mc;
	}

}
