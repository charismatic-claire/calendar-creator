/**
 * 
 */
package org.calendarcreator;

import org.calendarcreator.controller.MenuItemListener;
import org.calendarcreator.model.CalendarModel;
import org.calendarcreator.view.CalendarView;

/**
 *
 */
public class CalendarMain {

	/**
	 * 
	 */
	public static void main(String[] args) {
		// create model
		CalendarModel model = new CalendarModel();
		// create view
		CalendarView view = new CalendarView();
		// create controller
		MenuItemListener controller = new MenuItemListener( model, view );
		// add observer
		model.addObserver( controller );
		// run view
		view.run( controller );
		

	}

}
