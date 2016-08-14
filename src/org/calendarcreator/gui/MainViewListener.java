/**
 * 
 */
package org.calendarcreator.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Listener for the main window
 */
public class MainViewListener extends WindowAdapter {
	public void WindowClosed( WindowEvent e ) {
		System.exit( 0 );
	}
}
