/**
 * 
 */
package org.calendarcreator.controller;

import java.util.Observable;
import java.util.Observer;

import org.calendarcreator.model.CalendarModelFull;
import org.calendarcreator.view.CalendarView;

/**
 *
 */
public class CalendarController implements Observer {
	
	protected CalendarModelFull model;
	
	protected CalendarView view;
	
	public CalendarController( CalendarModelFull model, CalendarView view ) {
		this.model = model;
		this.view = view;
	}

	public void update(Observable o, Object arg) {
		// abort if not called by CalendarModelFull
		if( o != model ) {
			return;
		}
		
		// update the view
		String text = (String) arg;
		view.setStatusLabel( text + "menu item clicked." );
	}

}
