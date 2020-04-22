package org.calendarcreator;

import org.calendarcreator.gui.CalendarController;
import org.calendarcreator.gui.CalendarView;
import org.calendarcreator.model.CalendarModel;
import org.calendarcreator.model.CalendarModelFull;

public class CalendarMain {

	public static void main(String[] args) {
		// create model
		CalendarModel model = new CalendarModelFull();
		// create controller
		CalendarController controller = new CalendarController();
		// create view
		CalendarView view = new CalendarView( controller );
		// initialize controller
		controller.init( model, view ); 
		// initialize model
		model.init( controller );				
	}

}
