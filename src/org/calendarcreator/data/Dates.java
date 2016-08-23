/**
 * 
 */
package org.calendarcreator.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class Dates {
	
	private List<Date> listOfDates;
	
	public Dates() {
		this.listOfDates = new ArrayList<Date>();
	}
	
	@XmlElement( name = "date" )
	public List<Date> getListOfDates() {
		return listOfDates;
	}

	public void setListOfDates( List<Date> listOfDates ) {
		this.listOfDates = listOfDates;
	}

	public void addDate( Date date ) {
		listOfDates.add( date );
	}
}
