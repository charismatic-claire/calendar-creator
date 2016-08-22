package org.calendarcreator.tests;

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
		if( model.exportYearToTex( Language.DE , Style.JEDDI, "/tmp/2016.tex" ) ) {
			System.out.println( "Success!" );
		}
		else {
			System.out.println( "Failure" );
		}	
	}

	@Test
	public void testExportYearToXml() {
		// create new model
		CalendarModel model = new CalendarModel();
		// create new year
		model.createYear( 2016 );
		// add holidays
		model.addHolidays();
		// export year to *.tex
		if( model.exportYearToXml( "/tmp/2016.xml" ) ) {
			System.out.println( "Success!" );
		}
		else {
			System.out.println( "Failure" );
		}	
	}

}
