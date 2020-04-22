package org.calendarcreator;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.calendarcreator.data.Date;
import org.calendarcreator.data.Dates;
import org.calendarcreator.data.Day;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.CalendarModelFull;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalendarModelFullTest {
	
	private static CalendarModelFull model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		model = new CalendarModelFull();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		model = null;
	}

	@Before
	public void setUp() throws Exception {
		model.createYear( 2016 );
	}

	@After
	public void tearDown() throws Exception {
		model.removeYear();
		FileUtils.deleteQuietly( new File( "tmp/2016.tex" ) );
		FileUtils.deleteQuietly( new File( "tmp/2016.xml" ) );
	}

	@Test
	public void testUpdateYear() {
		// do
		model.addHolidays();
		model.addEntry( new Date( 5, 8, "Liberation Day") );
		model.updateYear( 2017 );
		// test
		assertEquals( model.getModelConfiguration().getYear().getYearInteger(), 2017 );
		assertTrue( model.getModelConfiguration().isAddedHolidays() );
		assertTrue( model.getModelConfiguration().isAddedEntries() );
		Month month = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 5 );
		Day day = month.getCollectionOfDays().get( 8 );
		assertEquals( day.getEntry(), "Liberation Day" );
		// print
		System.out.println( "update year:\t\t" + model.getModelConfiguration().getYear().getYearInteger() );
	}

	@Test
	public void testAddHolidays() {
		// do
		model.addHolidays();
		// print
		System.out.println( "add holidays:\t\t" + model.getModelConfiguration().isAddedHolidays() );
		// test
		assertTrue( model.getModelConfiguration().isAddedHolidays() );
	}

	@Test
	public void testRemoveHolidays() {
		// do
		model.addHolidays();
		// test
		assertTrue( model.getModelConfiguration().isAddedHolidays() );
		// do
		model.removeHolidays();
		// test
		assertFalse( model.getModelConfiguration().isAddedHolidays() );
		// print
		System.out.println( "remove holidays:\t" + model.getModelConfiguration().isAddedHolidays() );
	}

	@Test
	public void testAddEntry() {
		// do
		model.addEntry( new Date( 5, 8, "Liberation Day" ) );
		// print
		Month month = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 5 );
		Day day = month.getCollectionOfDays().get( 8 );
		System.out.println( "add entry:\t\t" + day.getEntry() );
		// test
		assertTrue( model.getModelConfiguration().isAddedEntries() );
		assertEquals( day.getEntry(), "Liberation Day" );
	}

	@Test
	public void testRemoveEntry() {
		// do
		model.addEntry( new Date( 5, 8, "Liberation Day" ) );
		// test
		assertTrue( model.getModelConfiguration().isAddedEntries() );
		// do
		model.removeEntry( new Date( 5, 8 ) );
		// test
		assertFalse( model.getModelConfiguration().isAddedEntries() );
		// print
		System.out.println( "remove entry:\t\t" + model.getModelConfiguration().isAddedEntries() );
	}

	@Test
	public void testAddEntries() {
		// do
		Dates dates = new Dates();
		dates.addDate( new Date( 1, 1, "One") );
		dates.addDate( new Date( 2, 2, "Two") );
		dates.addDate( new Date( 3, 3, "Three") );
		dates.addDate( new Date( 4, 4, "Four") );
		model.addEntries( dates );
		// test
		assertTrue( model.getModelConfiguration().isAddedEntries() );
		Month month1 = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 1 );
		Day day1 = month1.getCollectionOfDays().get( 1 );
		Month month2 = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 2 );
		Day day2 = month2.getCollectionOfDays().get( 2 );
		Month month3 = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 3 );
		Day day3 = month3.getCollectionOfDays().get( 3 );
		Month month4 = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 4 );
		Day day4 = month4.getCollectionOfDays().get( 4 );
		assertEquals( day1.getEntry(), "One" );
		assertEquals( day2.getEntry(), "Two" );
		assertEquals( day3.getEntry(), "Three" );
		assertEquals( day4.getEntry(), "Four" );
		// print
		System.out.println( "add entries:\t\t" + day1.getEntry() + ", "
				+ day2.getEntry() + ", "
				+ day3.getEntry() + ", "
				+ day4.getEntry());
	}

	@Test
	public void testRemoveEntries() {
		// do
		Dates dates = new Dates();
		dates.addDate( new Date( 1, 1, "One") );
		dates.addDate( new Date( 2, 2, "Two") );
		dates.addDate( new Date( 3, 3, "Three") );
		dates.addDate( new Date( 4, 4, "Four") );
		model.addEntries( dates );
		// test
		assertTrue( model.getModelConfiguration().isAddedEntries() );
		// do
		model.removeEntries();
		// test
		assertFalse( model.getModelConfiguration().isAddedEntries() );
		// print
		System.out.println( "remove entries:\t\t" + model.getModelConfiguration().isAddedEntries() );
	}

	@Test
	public void testExportYearToTex() throws IOException {
		// do
		model.addHolidays();
		model.addEntry( new Date( 5, 8, "Liberation Day") );
		model.exportYearToTex( Language.EN , Style.CLASSIC, "tmp/2016.tex" );
		// print
		List<String> lines = FileUtils.readLines( new File( "tmp/2016.tex" ) );
		System.out.println( "export year to tex:\t" + 
				lines.get( 0 ) + "\n\t\t\t" + lines.get( 1 ));
		// test
		assertEquals( FileUtils.checksumCRC32( new File( "tmp/2016.tex" ) ), 4165645712.0, 0.01 );
	}

	@Test
	public void testExportYearToConfigXml() throws IOException {
		// do
		model.addHolidays();
		model.addEntry( new Date( 5, 8, "Liberation Day") );
		model.exportYearToConfigXml( "tmp/2016.xml" );
		// print
		List<String> lines = FileUtils.readLines( new File( "tmp/2016.xml" ) );
		System.out.println( "export year to xml:\t" + 
				lines.get( 0 ) + "\n\t\t\t" + lines.get( 1 ));
		// test
		assertEquals( FileUtils.checksumCRC32( new File( "tmp/2016.xml" ) ), 1141844215.0, 0.01 );
	}

	@Test
	public void testImportYearFromConfigXml() throws IOException {
		// do
		model.addHolidays();
		model.addEntry( new Date( 5, 8, "Liberation Day") );
		model.exportYearToConfigXml( "tmp/2016.xml" );
		// test
		assertEquals( FileUtils.checksumCRC32( new File( "tmp/2016.xml" ) ), 1141844215.0, 0.01 );
		// do
		model.removeYear();
		// test
		assertFalse( model.getModelConfiguration().isCreatedYear() );
		// do
		model.importYearFromConfigXml( "tmp/2016.xml" );
		// test
		assertTrue( model.getModelConfiguration().isCreatedYear() );
		assertTrue( model.getModelConfiguration().isAddedHolidays() );
		assertTrue( model.getModelConfiguration().isCreatedYear() );
		assertEquals( model.getModelConfiguration().getYear().getYearInteger(), 2016 );
		Month month = model.getModelConfiguration().getYear().getCollectionOfMonths().get( 5 );
		Day day = month.getCollectionOfDays().get( 8 );
		assertEquals( day.getEntry(), "Liberation Day" );
		// print
		System.out.println( "import year from xml:\t" + model.getModelConfiguration().getYear().getYearInteger() );
	}

}
