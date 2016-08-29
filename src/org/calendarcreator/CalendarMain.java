package org.calendarcreator;

import org.calendarcreator.gui.CalendarController;
import org.calendarcreator.gui.CalendarView;
import org.calendarcreator.model.CalendarModel;
import org.calendarcreator.model.CalendarModelMini;

public class CalendarMain {

	public static void main(String[] args) {
		// create model
		CalendarModel model = new CalendarModelMini();
		// create controller
		CalendarController controller = new CalendarController();
		// create view
		CalendarView view = new CalendarView( controller );
		// add view and model controller
		controller.addModel( model );
		controller.addView( view );
		// add controller to model
		model.addController( controller );				
	}

}
