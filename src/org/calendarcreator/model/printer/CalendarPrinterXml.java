/**
 * 
 */
package org.calendarcreator.model.printer;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearXml;
import org.calendarcreator.model.YearFactory;

/**
 *
 */
public class CalendarPrinterXml implements CalendarPrinter {
	
	@Override
	public String printYear( Year year ) {
		// init
		String printedYear;
		// create writer
		StringWriter writer = new StringWriter();
		// create YearXml
		YearFactory yearFactory = new YearFactory();
		YearXml yearXml = yearFactory.createYearXml( year );
		// print YearXml
		JAXB.marshal( yearXml, writer );
		printedYear = writer.toString();
		// return result
		return printedYear;
	}

}
