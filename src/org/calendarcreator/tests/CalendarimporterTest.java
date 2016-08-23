package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearXml;
import org.calendarcreator.model.CalendarImporter;
import org.calendarcreator.model.YearFactory;
import org.calendarcreator.model.printer.CalendarPrinter;
import org.calendarcreator.model.printer.CalendarPrinterXml;
import org.junit.Test;

public class CalendarimporterTest {

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
		CalendarPrinter printer = new CalendarPrinterXml();
		String data = printer.printYear( year );
		// print it
		System.out.println( data );
		// convert to back year xml
		CalendarImporter importer = new CalendarImporter();
		YearXml yearXml = importer.importYearXmlFromString( data );
		// try to read it
		System.out.println( yearXml.getYearInteger() );
		System.out.println( yearXml.isAddedHolidays() );
		System.out.println( yearXml.isAddedEntries() );
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
		CalendarPrinter printer = new CalendarPrinterXml();
		String data = printer.printYear( year );
		// print it
		System.out.println( data );
		// convert to back year xml
		CalendarImporter importer = new CalendarImporter();
		YearXml yearXml = importer.importYearXmlFromString( data );
		// convert back to year
		year = importer.yearXml2Year( yearXml );
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
		CalendarPrinter printer = new CalendarPrinterXml();
		String data = printer.printYear( year );
		// convert back
		CalendarImporter importer = new CalendarImporter();
		year = importer.importYearFromString( data );
		// print it
		System.out.println( year.getYearInteger() );
	}

}
