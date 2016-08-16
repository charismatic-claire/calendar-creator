/**
 * 
 */
package org.calendarcreator.tests;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author claire
 *
 */
public class DayTest {
	
	private static Day day;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		day = new Day( 12, DayOfWeek.WEDNESDAY);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		day = null;
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#getDayOfMonth()}.
	 */
	@Test
	public void testGetDayOfMonth() {
		System.out.println( day.getDayOfMonth() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#setDayOfMonth(int)}.
	 */
	@Test
	public void testSetDayOfMonth() {
		day.setDayOfMonth( 13 );
		System.out.println( day.getDayOfMonth() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#getDayOfWeek()}.
	 */
	@Test
	public void testGetDayOfWeek() {
		System.out.println( day.getDayOfWeek() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#setDayOfWeek(org.calendarcreator.data.DayOfWeek)}.
	 */
	@Test
	public void testSetDayOfWeek() {
		day.setDayOfWeek( DayOfWeek.SUNDAY );
		System.out.println( day.getDayOfWeek() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#getEntry()}.
	 */
	@Test
	public void testGetEntry() {
		System.out.println( day.getEntry() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#setEntry(java.lang.String)}.
	 */
	@Test
	public void testSetEntry() {
		day.setEntry( "Hello world." );
		System.out.println( day.getEntry() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#getWeekOfYear()}.
	 */
	@Test
	public void testGetWeekOfYear() {
		System.out.println( day.getWeekOfYear() );
	}

	/**
	 * Test method for {@link org.calendarcreator.data.Day#setWeekOfYear(int)}.
	 */
	@Test
	public void testSetWeekOfYear() {
		day.setWeekOfYear( 14 );
		System.out.println( day.getWeekOfYear() );
	}

}
