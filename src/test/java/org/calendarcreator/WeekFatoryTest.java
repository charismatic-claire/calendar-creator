/**
 * 
 */
package org.calendarcreator;

//import static org.junit.Assert.*;

import java.util.List;

import org.calendarcreator.data.Month;
import org.calendarcreator.data.Week;
import org.calendarcreator.data.Year;
import org.calendarcreator.model.WeekFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 */
public class WeekFatoryTest {
	
	private static Year year;
	
	private static Month month;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		year = new Year( 2016 );
		month = year.getCollectionOfMonths().get( 9 );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		month = null;
	}

	/**
	 * Test method for {@link org.calendarcreator.model.WeekFactory#generateListOfWeeks(org.calendarcreator.data.Month)}.
	 */
	@Test
	public void testGenerateListOfWeeks() {
		WeekFactory weekFactory = new WeekFactory();
		List<Week> listOfWeeks = weekFactory.generateListOfWeeks( month );
		for( Week week : listOfWeeks ) {
			System.out.println( week.toString() );
		}
	}

}
