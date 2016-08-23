package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.CalendarModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalendarModelTest {
	
	private static CalendarModel model;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new CalendarModel();
		model.createYear( 2016 );
		model.addHolidays();
		model.addEntry( new Date( 1, 7, "*Jette" ) );
		model.addEntry( new Date( 11, 12, "*Kai" ) );
		model.addEntry( new Date( 11, 23, "*Suse" ) );
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		model = null;
	}

	@Test
	public void testExportYearToTex() {
		if( model.exportYearToTex( Language.EN , Style.CLASSIC, "/tmp/2016-1.tex" ) ) {
			System.out.println( "Successfully exported to TEX." );
		}
	}

	@Test
	public void testExportYearToXml() {
		if( model.exportYearToXml( "/tmp/2016.xml" ) ) {
			System.out.println( "Successfully exported to XML." );
		}
	}

	@Test
	public void testImportYearFromXml() {
		if( model.exportYearToXml( "/tmp/2016.xml" ) ) {
			CalendarModel model2 = new CalendarModel();
			if( model2.importYearFromXml( "/tmp/2016.xml" ) ) {
				if( model2.exportYearToTex( Language.DE, Style.JEDDI, "/tmp/2016-2.tex" ) ) {
					System.out.println( "Successfully imported from XML." );
				}
			}
		}
	}

}
