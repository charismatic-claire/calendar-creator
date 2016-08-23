package org.calendarcreator.tests;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.CalendarModel;
import org.junit.Test;

public class CalendarModelTest {
	
//	@Test
//	public void testPrintYear01() {
//		// create new model
//		CalendarModel model = new CalendarModel();
//		// create new year
//		model.createYear( 2016 );
//		// print year
//		model.printYear( Language.EN , Style.LANDSCAPE );		
//	}
//
//	@Test
//	public void testPrintYear02() {
//		// create new model
//		CalendarModel model = new CalendarModel();
//		// create new year
//		model.createYear( 2016 );
//		// add holidays
//		model.addHolidays();
//		// print year
//		model.printYear( Language.DE , Style.PLAIN );		
//	}

//	@Test
//	public void testPrintYear03() {
//		// create new model
//		CalendarModel model = new CalendarModel();
//		// create new year
//		model.createYear( 2016 );
//		// add holidays
//		model.addHolidays();
//		// print year
//		model.printYear( Language.EN , Style.CLASSIC );		
//	}
//
//	@Test
//	public void testPrintYear04() {
//		// create new model
//		CalendarModel model = new CalendarModel();
//		// create new year
//		model.createYear( 2016 );
//		// add holidays
//		model.addHolidays();
//		// print year
//		model.printYear( Language.DE , Style.KITCHEN );		
//	}
	
//	@Test
//	public void testPrintYear05() {
//		// create new model
//		CalendarModel model = new CalendarModel();
//		// create new year
//		model.createYear( 2016 );
//		// add holidays
//		model.addHolidays();
//		// print year
//		model.printYear( Language.DE , Style.JEDDI );		
//	}
	
	@Test
	public void testExportYearToTex() {
		// create new model
		CalendarModel model = new CalendarModel();
		// create new year
		model.createYear( 2016 );
		// add holidays
		model.addHolidays();
		// export year to *.tex
		if( model.exportYearToTex( Language.DE , Style.CLASSIC, "/tmp/2016.tex" ) ) {
			System.out.println( "Success!" );
		}
		else {
			System.out.println( "Failure" );
		}	
	}

	@Test
	public void testExportYearToXml1() {
		// create new model
		CalendarModel model = new CalendarModel();
		// create new year
		model.createYear( 2016 );
		// add holidays
		model.addHolidays();
		// set some entries
		model.addEntry( new Date( 1, 1, "First" ) );
		model.addEntry( new Date( 2, 2, "Second" ) );
		model.addEntry( new Date( 3, 3, "Third" ) );
		model.addEntry( new Date( 4, 4, "Fourth" ) );
		// export year to *.xml
		if( model.exportYearToXml( "/tmp/2016-1.xml" ) ) {
			System.out.println( "Success!" );
		}
		else {
			System.out.println( "Failure" );
		}	
	}
	
	@Test
	public void testExportYearToXml2() {
		// create new model
		CalendarModel model = new CalendarModel();
		// create new year
		model.createYear( 2016 );
		// add holidays
		model.addHolidays();
		// set some entries
		Dates dates = new Dates();
		dates.addDate( new Date( 5, 5, "First" ) );
		dates.addDate( new Date( 6, 6, "Second" ) );
		dates.addDate( new Date( 7, 7, "Third" ) );
		dates.addDate( new Date( 8, 8, "Fourth" ) );
		model.addEntries( dates );
		// export year to *.xml
		if( model.exportYearToXml( "/tmp/2016-2.xml" ) ) {
			System.out.println( "Success!" );
		}
		else {
			System.out.println( "Failure" );
		}	
	}
	

}
