/**
 * 
 */
package org.calendarcreator.model;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearConfig;

/**
 *
 */
public class CalendarImportExport {
	
	private YearFactory yearFactory;
	
	public CalendarImportExport() {
		yearFactory = new YearFactory();
	}

	public Year importYearFromString( String data ) {
		YearConfig yearConfig = importYearConfigFromString( data );
		Year year = yearConfig2Year( yearConfig );
		return year;
	}
	
	public YearConfig importYearConfigFromString( String data ) {
		YearConfig yearConfig = null;
		StringReader reader = new StringReader( data );
		yearConfig = (YearConfig) JAXB.unmarshal( reader, YearConfig.class );
		return yearConfig;
	}
	
	public Year yearConfig2Year( YearConfig yearConfig ) {
		// create year
		Year year = yearFactory.createYear( yearConfig.getYearInteger() );
		// add holidays
		if( yearConfig.isAddedHolidays() ) {
			yearFactory.addHolidays( year );
		}
		// add entries
		if( yearConfig.isAddedEntries() ) {
			yearFactory.addEntries( year, yearConfig.getDates() );
		}
		// return
		return year;
	}
	
	public YearConfig year2YearConfig( Year year ) {
		return yearFactory.createYearConfig( year );
	}
}
