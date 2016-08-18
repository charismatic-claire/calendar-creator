package org.calendarcreator.tests;

import org.calendarcreator.data.Language;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.CalendarModel;
import org.junit.Test;

public class CalendarModelTest {
	
	@Test
	public void testPrintYear() {
		// create new model
		CalendarModel model = new CalendarModel();
		// create new year
		model.createYear( 2016 );
		// print year
		model.printYear( Language.EN , Style.LANDSCAPE );		
	}

}
