/**
 * 
 */
package org.calendarcreator.data;

/**
 *
 */
public class Week {

	private MonthOfYear monthOfYear;
	
	private Integer weekOfYear;
	
	private Day[] week = { null, null, null, null, null, null, null };
	
	public Week( MonthOfYear monthOfYear ) {
		this.monthOfYear = monthOfYear;
	}

	public MonthOfYear getMonthOfYear() {
		return monthOfYear;
	}

	public Integer getWeekOfYear() {
		return weekOfYear;
	}
	
	public void setWeekOfYear( Integer weekOfYear ) {
		this.weekOfYear = weekOfYear;
	}

	public Day[] getWeek() {
		return week;
	}
	
	public void putDay( Day day ) {
		switch( day.getDayOfWeek() ) {
			case MONDAY:
				week[0] = day;
				break;
			case TUESDAY:
				week[1] = day;
				break;
			case WEDNESDAY:
				week[2] = day;
				break;
			case THURSDAY:
				week[3] = day;
				break;
			case FRIDAY:
				week[4] = day;
				break;
			case SATURDAY:
				week[5] = day;
				break;
			case SUNDAY:
				week[6] = day;
				break;
		}
	}
	
	@Override
	public String toString() {
		String weekString = monthOfYear.toString() + ":\n";
		if( weekOfYear != null ) {
			weekString += "(" + weekOfYear.toString() + "): ";
		}
		for( int i=0; i<7; i++ ) {
			if( week[i] == null ) {
				weekString += "XX ";
			}
			else {
				weekString += week[i].getDayOfMonth() + " ";
			}
		}
		weekString += "\n";
		return weekString;
	}
}
