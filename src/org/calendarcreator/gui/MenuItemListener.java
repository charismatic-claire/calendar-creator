/**
 * 
 */
package org.calendarcreator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listener for menu items
 */
public class MenuItemListener implements ActionListener {
	
	/**
	 * Points to the main view
	 */
	private MainView mainView;
	
	/**
	 * Constructor
	 */
	protected MenuItemListener( MainView mainView ) {
		this.mainView = mainView;
	}
	
	/**
	 * Action for buttons
	 */
	public void actionPerformed( ActionEvent e ) {
		// close on exit
		if( e.getActionCommand().equals( "exit" ) ) {
			mainView.terminate();
			System.exit( 0 );
		}
		
		// print action
		mainView.setStatusLabel( e.getActionCommand() + " JMenuItem clicked." );
	}
}
