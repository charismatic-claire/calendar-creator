package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.calendarcreator.data.Date;
import org.calendarcreator.model.DatesPrinter;
import org.junit.Test;

public class DatesPrinterTest {

	@Test
	public void testPrintDates() {
		// create list of dates
		List<Date> dates = new ArrayList<Date>();
		Date date1 = new Date( 1, 1 );
		date1.setEntry( "Hello Earth." );
		dates.add( date1 );
		Date date2 = new Date( 2, 2 );
		date2.setEntry( "Hello Moon." );
		dates.add( date2 );
		Date date3 = new Date( 3, 3 );
		date3.setEntry( "Hello Mars." );
		dates.add( date3 );
		Date date4 = new Date( 4, 4 );
		date4.setEntry( "Hello Venus." );
		dates.add( date4 );
		// print it
		DatesPrinter printer = new DatesPrinter();
		System.out.println( printer.printDates( dates ) );
	}
}
