/**
 * 
 */
package org.calendarcreator.controller;

import java.util.Observable;
import java.util.Observer;

import org.calendarcreator.model.CalendarModel;
import org.calendarcreator.view.CalendarView;

/**
 *
 */
public class CalendarController implements Observer {
	
	protected CalendarModel model;
	
	protected CalendarView view;
	
	public CalendarController( CalendarModel model, CalendarView view ) {
		this.model = model;
		this.view = view;
	}

	public void update(Observable o, Object arg) {
		// abort if not called by CalendarModel
		if( o != model ) {
			return;
		}
		
		// update the view
		String text = (String) arg;
		view.setStatusLabel( text + "menu item clicked." );
	}

}
