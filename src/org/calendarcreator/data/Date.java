/**
 * 
 */
package org.calendarcreator.data;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 */
public class Date {
	
	private int monthOfYear;
	
	private int dayOfMonth;
	
	private String entry;
	
	public Date( int monthOfYear, int dayOfMonth ) {
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
	}

	@XmlElement( name = "monthOfYear" )
	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	
	@XmlElement( name = "dayOfWeek" )
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@XmlElement( name = "entry" )
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
}
