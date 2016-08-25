package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearConfig;
import org.calendarcreator.model.CalendarImportExport;
import org.calendarcreator.model.YearFactory;
import org.calendarcreator.model.printer.CalendarPrinter;
import org.calendarcreator.model.printer.CalendarPrinterConfigXml;
import org.junit.Test;

public class CalendarImportExportTest {

	@Test
	public void testImportYearXmlFromString() {
		// create year
		YearFactory yearFactory = new YearFactory();
		Year year = yearFactory.createYear( 2016 );
		// add holidays
		yearFactory.addHolidays( year );
		// add entries
		yearFactory.addEntry( year, new Date( 1, 7, "*Jette" ) );
		yearFactory.addEntry( year, new Date( 11, 12, "*Kai" ) );
		// convert to xml string
		CalendarPrinter printer = new CalendarPrinterConfigXml();
		String data = printer.printYear( year );
		// print it
		System.out.println( data );
		// convert to back year xml
		CalendarImportExport importer = new CalendarImportExport();
		YearConfig yearConfig = importer.importYearConfigFromString( data );
		// try to read it
		System.out.println( yearConfig.getYearInteger() );
		System.out.println( yearConfig.isAddedHolidays() );
		System.out.println( yearConfig.isAddedEntries() );
	}
	
	@Test
	public void testYearXml2Year() {
		// create year
		YearFactory yearFactory = new YearFactory();
		Year year = yearFactory.createYear( 2016 );
		// add holidays
		yearFactory.addHolidays( year );
		// add entries
		yearFactory.addEntry( year, new Date( 1, 7, "*Jette" ) );
		yearFactory.addEntry( year, new Date( 11, 12, "*Kai" ) );
		// convert to xml string
		CalendarPrinter printer = new CalendarPrinterConfigXml();
		String data = printer.printYear( year );
		// print it
		System.out.println( data );
		// convert to back year xml
		CalendarImportExport importer = new CalendarImportExport();
		YearConfig yearConfig = importer.importYearConfigFromString( data );
		// convert back to year
		year = importer.yearConfig2Year( yearConfig );
		// print it
		System.out.println( year.getYearInteger() );
	}
	
	@Test
	public void testImportYearFromString() {
		// create year
		YearFactory yearFactory = new YearFactory();
		Year year = yearFactory.createYear( 2016 );
		// add holidays
		yearFactory.addHolidays( year );
		// add entries
		yearFactory.addEntry( year, new Date( 1, 7, "*Jette" ) );
		yearFactory.addEntry( year, new Date( 11, 12, "*Kai" ) );
		// convert to xml string
		CalendarPrinter printer = new CalendarPrinterConfigXml();
		String data = printer.printYear( year );
		// convert back
		CalendarImportExport importer = new CalendarImportExport();
		year = importer.importYearFromString( data );
		// print it
		System.out.println( year.getYearInteger() );
	}

}
