/**
 * 
 */
package org.calendarcreator.model;

import java.util.Observable;

/**
 *
 */
public class CalendarModel extends Observable {

	private String lastActionCommand;
	
	public String getLastActionCommand() {
		return lastActionCommand;
	}
	
	public void setLastActionCommand( String lastActionCommand ) {
		// update variable
		this.lastActionCommand = lastActionCommand;
		// tell observer
		setChanged();
		notifyObservers( lastActionCommand );
	}
}
