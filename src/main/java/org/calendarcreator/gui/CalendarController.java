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
	
	private EntryButtonController entryButtonController;
	
	private int monthToEdit;
	
	public CalendarController() {
		this.monthToEdit = 1;
	}
	
	public void init( CalendarModel model, CalendarView view ) {
		this.model = model;
		this.view = view;
		this.entryButtonController = new EntryButtonController( model, view );
	}
	
	@Override
	public void actionPerformed( ActionEvent ae ) {
		// init
		String command = ae.getActionCommand();
		// action
		switch( command ) {
			case "new":
				createYear();
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
				updateYear();
				break;
			case "add.holidays":
				model.addHolidays();
				break;
			case "remove.holidays":
				model.removeHolidays();
				break;
			case "edit.entries":
				view.editMonth( model.getYear().getCollectionOfMonths().get( monthToEdit ), entryButtonController );
				break;
			case "remove.entries":
				model.removeEntries();
				break;
			case "help":
				view.showHelp();
				break;
			case "previous":
				view.editMonth( model.getYear().getCollectionOfMonths().get( decrementMonth() ), entryButtonController );
				break;
			case "next":
				view.editMonth( model.getYear().getCollectionOfMonths().get( incrementMonth() ), entryButtonController );
				break;
			default:
				// do nothing
		}
	}

	@Override
	public void update(Observable observable, Object argument ) {
		if( observable == model ) {
			ModelConfiguration config = (ModelConfiguration) argument;
			view.updateLabel( config );
			view.updateAvailability( config );
		}
	}
	
	@Override
	public void windowClosed( WindowEvent we ) {
		terminate();
	}
	
	private void createYear() {
		try {
			model.createYear( view.getYearInteger() );
		}
		catch( Exception e ) {
			System.err.println( "Error creating new year." );
		}
	}
	
	private void updateYear() {
		try {
			model.updateYear( view.getYearInteger() );
		}
		catch( Exception e ) {
			System.err.println( "Error updating existing year." );
		}
	}
	private int incrementMonth() {
		if( monthToEdit < 12 ) {
			return ++monthToEdit;
		}
		else {
			return monthToEdit;
		}
	}
	
	private int decrementMonth() {
		if( monthToEdit > 1 ) {
			return --monthToEdit;
		}
		else {
			return monthToEdit;
		}
	}
	
	private void terminate() {
		view.terminate();
		System.exit( 0 );

	}
}
