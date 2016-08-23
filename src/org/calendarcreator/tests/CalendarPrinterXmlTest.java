/**
 * 
 */
package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import org.calendarcreator.data.Year;
import org.calendarcreator.model.YearFactory;
import org.calendarcreator.model.printer.CalendarPrinter;
import org.calendarcreator.model.printer.CalendarPrinterXml;
import org.junit.Test;

/**
 *
 */
public class CalendarPrinterXmlTest {

	@Test
	public void testPrintYear() {
		// create year
		YearFactory yearFactory = new YearFactory();
		Year year = yearFactory.createYear( 2016 );
		yearFactory.addHolidays( year );
		// print it
		CalendarPrinter printer = new CalendarPrinterXml();
		System.out.println( printer.printYear( year ) );
	}

}
