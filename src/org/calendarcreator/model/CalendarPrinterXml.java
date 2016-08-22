/**
 * 
 */
package org.calendarcreator.model;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Year;

/**
 * @author claire
 *
 */
public class CalendarPrinterXml implements CalendarPrinter {

	@Override
	public String printYear( Year year ) {
		// init
		String printedYear;
		// create writer
		StringWriter writer = new StringWriter();
		// print year
		JAXB.marshal( year, writer );
		printedYear = writer.toString();
		// return result
		return printedYear;
	}

}
