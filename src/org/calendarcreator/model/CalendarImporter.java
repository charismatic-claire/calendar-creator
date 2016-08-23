/**
 * 
 */
package org.calendarcreator.model;

import java.io.StringReader;

import javax.xml.bind.JAXB;

import org.calendarcreator.data.Year;
import org.calendarcreator.data.YearXml;

/**
 *
 */
public class CalendarImporter {

	public Year importYearFromString( String data ) {
		YearXml yearXml = importYearXmlFromString( data );
		Year year = yearXml2Year( yearXml );
		return year;
	}
	
	public YearXml importYearXmlFromString( String data ) {
		YearXml yearXml = null;
		StringReader reader = new StringReader( data );
		yearXml = (YearXml) JAXB.unmarshal( reader, YearXml.class );
		return yearXml;
	}
	
	public Year yearXml2Year( YearXml yearXml ) {
		// init
		YearFactory yearFactory = new YearFactory();
		// create year
		Year year = yearFactory.createYear( yearXml.getYearInteger() );
		// add holidays
		if( yearXml.isAddedHolidays() ) {
			yearFactory.addHolidays( year );
		}
		// add entries
		if( yearXml.isAddedEntries() ) {
			yearFactory.addEntries( year, yearXml.getDates() );
		}
		// return
		return year;
	}
}
