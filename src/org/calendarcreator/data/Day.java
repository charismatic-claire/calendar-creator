/**
 * 
 */
package org.calendarcreator.data;

import javax.xml.bind.annotation.XmlElement;

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
	 * Holiday identifier
	 */
	private Holiday holiday;
	
	/**
	 * entry
	 */
	private String entry;
	
	/**
	 * week of year
	 */
	private Integer weekOfYear;
	
	/**
	 * constructor	
	 * @param dom day of month
	 * @param dow day of week
	 */
	public Day( int dayOfMonth, DayOfWeek dayOfWeek ) {
		this.dayOfMonth = dayOfMonth;
		this.dayOfWeek = dayOfWeek;
		this.entry = null;
		this.weekOfYear = null;
	}

	@XmlElement
	public int getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	@XmlElement
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	@XmlElement
	public Holiday getHoliday() {
		return holiday;
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}

	@XmlElement
	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	@XmlElement
	public Integer getWeekOfYear() {
		return weekOfYear;
	}

	public void setWeekOfYear(int weekOfYear) {
		this.weekOfYear = weekOfYear;
	}
}
