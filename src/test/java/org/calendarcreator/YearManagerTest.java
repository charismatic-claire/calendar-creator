/**
 * 
 */
package org.calendarcreator;

//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;

//import org.calendarcreator.data.Date;
//import org.calendarcreator.data.Day;
//import org.calendarcreator.data.Holiday;
//import org.calendarcreator.data.Month;
//import org.calendarcreator.data.Year;
//import org.calendarcreator.model.CalendarPrinter;
//import org.calendarcreator.model.CalendarPrinterLandscape;
//import org.calendarcreator.model.CalendarPrinterPlain;
//import org.calendarcreator.model.CalendarTranslator;
//import org.calendarcreator.model.CalendarTranslatorEnglish;
//import org.calendarcreator.model.CalendarTranslatorGerman;
//import org.calendarcreator.model.YearManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
//import org.junit.Test;

/**
 *
 */
public class YearManagerTest {
	
//	private static YearManager yearManager;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		yearManager = new YearManager( new Year( 2016 ) );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

//	/**
//	 * Test method for {@link org.calendarcreator.model.YearManager#getEntryOfDate(org.calendarcreator.model.Date)}.
//	 */
//	@Test
//	public void testGetEntryOfDate() {
//		System.out.println( yearManager.getEntryOfDate( new Date( 2, 2 ) ) );
//	}
//
//	/**
//	 * Test method for {@link org.calendarcreator.model.YearManager#setEntryOfDate(org.calendarcreator.model.Date, java.lang.String)}.
//	 */
//	@Test
//	public void testSetEntryOfDate() {
//		yearManager.setEntryOfDate( new Date( 2, 2 ) , "Hello world.");
//		System.out.println( yearManager.getEntryOfDate( new Date( 2, 2 ) ) );
//	}

//	@Test
//	public void testDate2dayOfYear() {
//		System.out.println( "03/27: " + yearManager.date2dayOfYear( new Date( 3, 27 ) ) );
//		System.out.println( "03/28: " + yearManager.date2dayOfYear( new Date( 3, 28 ) ) );
//		System.out.println( "05/05: " + yearManager.date2dayOfYear( new Date( 5, 5 ) ) );
//		System.out.println( "05/15: " + yearManager.date2dayOfYear( new Date( 5, 15 ) ) );
//		System.out.println( "05/15: " + yearManager.date2dayOfYear( new Date( 5, 16 ) ) );
//	}
//	
//	@Test
//	public void testDayOfYear2date() {
//		Date date = yearManager.dayOfYear2date( 87 );
//		System.out.println( date.getMonthOfYear() + "/" + date.getDayOfMonth() );
//	}

//	@Test
//	public void testEvaluateDatesOfHolidays() {
//		Map<Holiday,Date> holidays = yearManager.evaluateDatesOfHolidays();
//		Set<Entry<Holiday,Date>> entries = holidays.entrySet();
//		for( Entry<Holiday,Date> entry : entries ) {
//			System.out.println( entry.getKey() + " : " + 
//		entry.getValue().getMonthOfYear() + "/"  + entry.getValue().getDayOfMonth() );
//		}
//	}
	
//	@Test
//	public void testAutoAddHolidays() {
//		yearManager.autoAddHolidays( new CalendarTranslatorEnglish() );
//		Set<Entry<Integer,Month>> months = yearManager.getYear().getCollectionOfMonths().entrySet();
//		for( Entry<Integer,Month> month : months ) {
//			System.out.println( month.getValue().getMonthOfYear() + ":" );
//			Set<Entry<Integer,Day>> days = month.getValue().getCollectionOfDays().entrySet();
//			for( Entry<Integer,Day> day : days ) {
//				System.out.println( "  " + day.getValue().getDayOfMonth() + 
//						" " + day.getValue().getDayOfWeek() +
//						" " + day.getValue().getEntry() + 
//						" " + day.getValue().getWeekOfYear()
//				);
//			}
//		}
//	}
	
}
