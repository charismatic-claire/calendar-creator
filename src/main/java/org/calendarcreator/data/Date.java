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
	
	public Date() {
		this.monthOfYear = 0;
		this.dayOfMonth = 0;
		this.entry = null;
	}
	
	public Date( int monthOfYear, int dayOfMonth ) {
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
	}
	
	public Date( int monthOfYear, int dayOfMonth, String entry ) {
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
		this.entry = entry;
	}

	@XmlElement
	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}
	
	@XmlElement
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@XmlElement
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}
	
}
