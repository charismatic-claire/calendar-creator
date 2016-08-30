package org.calendarcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.calendarcreator.data.Date;
import org.calendarcreator.model.CalendarModel;

public class EntryButtonController implements ActionListener {

	private CalendarModel model;
	
	private CalendarView view;
	
	public EntryButtonController( CalendarModel model, CalendarView view ) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed( ActionEvent ae ) {
		// get command
		String command = ae.getActionCommand();
		// seperate the parts
		String[] parts = command.split( "\\-" );
		// transform to integers
		Integer monthOfYear = Integer.parseInt( parts[0] );
		Integer dayOfMonth = Integer.parseInt( parts[1] );
		// create date
		Date date = new Date( monthOfYear, dayOfMonth );
		// get entry
		date.setEntry( view.getEntry( model.getEntry( date ) ) );
		// set entry
		if( date.getEntry() == null || date.getEntry().isEmpty() ) {
			model.removeEntry( date );
		}
		else {
			model.addEntry( date );
		}
		// refresh view
		view.updateMonth();
	}

}
