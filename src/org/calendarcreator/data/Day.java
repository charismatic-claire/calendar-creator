/**
 * 
 */
package org.calendarcreator.data;

/**
 *
 */
public class Day {
	/**
	 * day of month
	 */
	private int dayOfMonth;
	
	/**
	 * day of week
	 */
	private DayOfWeek dayOfWeek;
	
	/**
	 * entry
	 */
	private String entry;
	
	/**
	 * week of year
	 */
	private int weekOfYear;
	
	/**
	 * constructor	
	 * @param dom day of month
	 * @param dow day of week
	 */
	public Day( int dayOfMonth, DayOfWeek dayOfWeek ) {
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		entry = "";
		weekOfYear = 0;
	}

	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public int getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}
}
