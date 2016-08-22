/**
 * 
 */
package org.calendarcreator.model;

import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.calendarcreator.data.Date;

/**
 * Serialize a list of dates to an XML string
 *
 */
public class DatesPrinter {

	public String printDates( List<Date> listOfDates ) {
		// init
		String printedDates;
		Dates dates = new Dates();
		dates.setListOfDates( listOfDates );
		// create writer
		StringWriter writer = new StringWriter();
		// print dates
		JAXB.marshal(dates, writer);
		printedDates = writer.toString();
		// return result
		return printedDates;
	}
	
	@XmlRootElement
	private static class Dates {
		private List<Date> listOfDates;
		@XmlElement( name = "date" )
		public List<Date> getListOfDates() {
			return listOfDates;
		}
		public void setListOfDates( List<Date> listOfDates ) {
			this.listOfDates = listOfDates;
		}
	}

}
