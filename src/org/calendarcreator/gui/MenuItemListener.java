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
		mainView.setStatusLabel( e.getActionCommand() + " JMenuItem clicked." );
	}
}
