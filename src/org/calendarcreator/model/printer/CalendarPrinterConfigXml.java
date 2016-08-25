/**
 * 
 */
package org.calendarcreator.model.printer;

import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearConfig;
import org.calendarcreator.model.YearFactory;

/**
 *
 */
public class CalendarPrinterConfigXml implements CalendarPrinter {
	
	@Override
	public String printYear( Year year ) {
		// init
		String printedYear;
		// create writer
		StringWriter writer = new StringWriter();
		// create YearConfig
		YearFactory yearFactory = new YearFactory();
		YearConfig yearConfig = yearFactory.createYearConfig( year );
		// print YearConfig
		JAXB.marshal( yearConfig, writer );
		printedYear = writer.toString();
		// return result
		return printedYear;
	}

}
