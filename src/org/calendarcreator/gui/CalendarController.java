/**
 * 
 */
package org.calendarcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import org.calendarcreator.data.Date;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.CalendarModel;

/**
 *
 */
public class CalendarController extends WindowAdapter implements ActionListener, Observer {
	
	private CalendarModel model;
	
	private CalendarView view;
	
	public void addModel( CalendarModel model ) {
		this.model = model;
	}
	
	public void addView( CalendarView view ) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed( ActionEvent ae ) {
		// init
		String command = ae.getActionCommand();
		// action
		switch( command ) {
			case "new":
				model.createYear( 2016 );
				break;
			case "open":
				model.importYearFromConfigXml( "/tmp/2016.xml" );
				break;
			case "save":
				model.exportYearToConfigXml( "/tmp/2016.xml" );
				break;
			case "export":
				model.exportYearToTex( Language.EN , Style.CLASSIC, "/tmp/2016.tex");
				break;
			case "close":
				model.removeYear();
				break;
			case "exit":
				terminate();
				break;
			case "update.year":
				model.updateYear( 2017 );
				break;
			case "add.holidays":
				model.addHolidays();
				break;
			case "remove.holidays":
				model.removeHolidays();
				break;
			case "add.entry":
				model.addEntry( new Date( 5, 8, "Liberation Day" ) );
				break;
			case "remove.entry":
				model.removeEntry( new Date( 5, 8 ) );
				break;
			case "remove.entries":
				model.removeEntries();
				break;
			default:
				// do nothing
		}
	}

	@Override
	public void update(Observable observable, Object argument ) {
		if( observable == model ) {
			ModelConfiguration config = (ModelConfiguration) argument;
			view.updateLabelText( config );
			view.updateAvailability( config );
		}
	}
	
	@Override
	public void windowClosed( WindowEvent we ) {
		terminate();
	}
	
	private void terminate() {
		view.terminate();
		System.exit( 0 );

	}
}
