/**
 * 
 */
package org.calendarcreator.data;

/**
 *
 */
public class ModelConfiguration {

	private Year year;
	
	private boolean createdYear;
	
	private boolean addedHolidays;
	
	private boolean addedEntries;

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public boolean isCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(boolean createdYear) {
		this.createdYear = createdYear;
	}

	public boolean isAddedHolidays() {
		return addedHolidays;
	}

	public void setAddedHolidays(boolean addedHolidays) {
		this.addedHolidays = addedHolidays;
	}

	public boolean isAddedEntries() {
		return addedEntries;
	}

	public void setAddedEntries(boolean addedEntries) {
		this.addedEntries = addedEntries;
	}
		
}
