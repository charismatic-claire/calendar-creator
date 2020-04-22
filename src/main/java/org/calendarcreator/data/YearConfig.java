/**
 * 
 */
package org.calendarcreator.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class YearConfig {

	private int yearInteger;
	
	private boolean addedHolidays;
	
	private boolean addedEntries;
	
	private Dates dates;
	
	public YearConfig() {
		this.yearInteger = 0;
		this.addedHolidays = false;
		this.addedEntries = false;
		this.dates = new Dates();
	}

	@XmlElement
	public int getYearInteger() {
		return yearInteger;
	}

	public void setYearInteger( int yearInteger ) {
		this.yearInteger = yearInteger;
	}

	@XmlElement
	public boolean isAddedHolidays() {
		return addedHolidays;
	}

	public void setAddedHolidays( boolean addedHolidays ) {
		this.addedHolidays = addedHolidays;
	}

	@XmlElement
	public Dates getDates() {
		return dates;
	}

	public void setDates( Dates dates ) {
		this.dates = dates;
	}

	@XmlElement
	public boolean isAddedEntries() {
		return addedEntries;
	}

	public void setAddedEntries(boolean addedEntries) {
		this.addedEntries = addedEntries;
	}

}
