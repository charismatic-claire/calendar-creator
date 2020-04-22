/**
 * 
 */
package org.calendarcreator;

// import static org.junit.Assert.*;

import java.util.Map;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Month;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author claire
 *
 */
public class MonthTest {
	
	private static Month month;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		month = new Month( 4 , 2, true );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Month#Month(org.calendarcreator.model.MonthOfYear, int, boolean)}.
	 */
	@Test
	public void testMonth() {
		System.out.println( new Month( 2 , 2, true ) );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Month#getMonthOfYear()}.
	 */
	@Test
	public void testGetMonthOfYear() {
		System.out.println( month.getMonthOfYear() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Month#getCollectionOfDays()}.
	 */
	@Test
	public void testGetCollectionOfDays() {
		Map<Integer,Day> collectionOfDays= month.getCollectionOfDays();
		for( int i = 1; i <= month.getLengthOfMonth(); i++  ) {
			Day day = collectionOfDays.get( i );
			System.out.println( day.getDayOfMonth() + 
					"\t" + day.getDayOfWeek() + 
					"\t" + day.getEntry() + 
					"\t" + day.getWeekOfYear()
			);
		}
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Month#getLengthOfMonth()}.
	 */
	@Test
	public void testGetLengthOfMonth() {
		System.out.println( month.getLengthOfMonth() );
	}

}
