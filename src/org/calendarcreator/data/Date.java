/**
 * 
 */
package org.calendarcreator.data;

/**
 *
 */
public class Date {
	
	private int monthOfYear;
	
	private int dayOfMonth;
	
	public Date( int monthOfYear, int dayOfMonth ) {
		this.monthOfYear = monthOfYear;
		this.dayOfMonth = dayOfMonth;
	}

	public int getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(int monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
}
