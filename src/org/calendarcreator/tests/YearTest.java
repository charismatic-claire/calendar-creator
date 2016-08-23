/**
 * 
 */
package org.calendarcreator.tests;

//import static org.junit.Assert.*;

import java.util.Map;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Year;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author claire
 *
 */
public class YearTest {
	
	private static Year year;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		year = new Year( 2016 );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Year#Year(int)}.
	 */
	@Test
	public void testYear() {
		Year year = new Year( 2016 );
		Map<Integer,Month> collectionOfMonths = year.getCollectionOfMonths();
		for( int i = 1; i <= 12; i++ ) {
			Month month = collectionOfMonths.get( i );
			System.out.println( month.getMonthOfYear() + "\t" + month.getLengthOfMonth() );
		}
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Year#getYear()}.
	 */
	@Test
	public void testGetYear() {
		System.out.println( year.getYearInteger() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Year#getCollectionOfMonths()}.
	 */
	@Test
	public void testGetCollectionOfMonths() {
		Map<Integer,Month> collectionOfMonths = year.getCollectionOfMonths();
		for( int i = 1; i <= 12; i++ ) {
			Month month = collectionOfMonths.get( i );
			Map<Integer,Day> collectionOfDays = month.getCollectionOfDays();
			System.out.println( month.getMonthOfYear() );
			for( int j = 1; j <= month.getLengthOfMonth(); j++ ) {
				System.out.println(
						" " + collectionOfDays.get( j ).getDayOfMonth() + 
						" " + collectionOfDays.get( j ).getDayOfWeek() + 
						" " + collectionOfDays.get( j ).getWeekOfYear()
				);
			}
		}
	}

}
