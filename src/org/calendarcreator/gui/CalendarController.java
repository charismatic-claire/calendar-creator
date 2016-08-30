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

import org.calendarcreator.data.ModelConfiguration;
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
				model.createYear( view.getYearInteger() );
				break;
			case "open":
				model.importYearFromConfigXml( view.getOpenXmlFilePath() );
				break;
			case "save":
				model.exportYearToConfigXml( view.getSaveXmlFilePath() );
				break;
			case "export":
				model.exportYearToTex( view.getLanguage() , view.getStyle(), view.getSaveTexFilePath() );
				break;
			case "close":
				model.removeYear();
				break;
			case "exit":
				terminate();
				break;
			case "update.year":
				model.updateYear( view.getYearInteger() );
				break;
			case "add.holidays":
				model.addHolidays();
				break;
			case "remove.holidays":
				model.removeHolidays();
				break;
			case "edit.entries":
				model.editEntries();
				break;
			case "remove.entries":
				model.removeEntries();
				break;
			case "help":
				view.showHelp();
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