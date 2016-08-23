package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.model.DatesPrinter;
import org.junit.Test;

public class DatesPrinterTest {

	@Test
	public void testPrintDates() {
		// create dates
		Dates dates = new Dates();
		Date date1 = new Date( 1, 1 );
		date1.setEntry( "Hello Earth." );
		dates.addDate( date1 );
		Date date2 = new Date( 2, 2 );
		date2.setEntry( "Hello Moon." );
		dates.addDate( date2 );
		Date date3 = new Date( 3, 3 );
		date3.setEntry( "Hello Mars." );
		dates.addDate( date3 );
		Date date4 = new Date( 4, 4 );
		date4.setEntry( "Hello Venus." );
		dates.addDate( date4 );
		// print it
		DatesPrinter printer = new DatesPrinter();
		System.out.println( printer.printDates( dates ) );
	}
}
