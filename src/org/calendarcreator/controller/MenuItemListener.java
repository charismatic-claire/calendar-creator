/**
 * 
 */
package org.calendarcreator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.calendarcreator.model.CalendarModel;
import org.calendarcreator.view.CalendarView;

/**
 * Listener for menu items
 */
public class MenuItemListener extends CalendarController implements ActionListener {
	
	/**
	 * Constructor
	 */
	public MenuItemListener(CalendarModel model, CalendarView view) {
		super(model, view);
	}

	/**
	 * Action for buttons
	 */
	public void actionPerformed( ActionEvent e ) {
		// get command
		String command = e.getActionCommand();
		
		// close on exit
		if( command.equals( "exit" ) ) {
			view.terminate();
			System.exit( 0 );
		}
		
		// else tell model
		else {
			model.setLastActionCommand( command );
		}
	}
}
