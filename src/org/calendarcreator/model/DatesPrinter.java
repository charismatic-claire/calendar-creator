/**
 * 
 */
package org.calendarcreator.model;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Dates;

/**
 * Serialize a list of dates to an XML string
 *
 */
public class DatesPrinter {

	public String printDates( Dates dates ) {
		// init
		String printedDates;
		// create writer
		StringWriter writer = new StringWriter();
		// print dates
		JAXB.marshal( dates, writer );
		printedDates = writer.toString();
		// return result
		return printedDates;
	}
	
}
