/**
 * 
 */
package org.calendarcreator.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.calendarcreator.controller.MenuItemListener;

/**
 * the main user interface
 */
public class CalendarView {

	/**
	 * main window
	 */
	private JFrame mainFrame;
	
	/**
	 * status messages
	 */
	private JLabel statusLabel;
	
	/**
	 * the menu bar
	 */
	private JMenuBar menuBar;
	
	/**
	 * Action listener for menu items
	 */
	private MenuItemListener menuItemListener;

	/**
	 * Run the view
	 */
	public void run( MenuItemListener menuItemListener ) {
		// set menu item listener
		this.menuItemListener = menuItemListener;
		// prepare gui
		prepareGUI();
		// populate gui
		populateGUI();

	}
	
	/**
	 * Initialize the main window
	 */
	private void prepareGUI() {
		// init main window
		mainFrame = new JFrame( "Calendar Creator v2.0" );
	    mainFrame.setSize( 500, 150 );
	    mainFrame.setLayout( new GridLayout( 1, 1) );
	    
	    // init message area
	    statusLabel = new JLabel("",JLabel.CENTER);

	    // terminate when window closed
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    // init menu bar
	    menuBar = new JMenuBar();
	    
	    // add elements to main window
	    mainFrame.setJMenuBar( menuBar );
	    mainFrame.add( statusLabel );
	}

	/**
	 * Create the main window
	 */
	private void populateGUI() {		
		// create menus
		JMenu fileMenu = new JMenu( "File" );
		JMenu editMenu = new JMenu( "Edit" );
		JMenu helpMenu = new JMenu( "Help" );
		
		// create menu items
		JMenuItem newMenuItem = new JMenuItem( "New calendar" );
		newMenuItem.setActionCommand( "new" );
		JMenuItem openMenuItem = new JMenuItem( "Open calendar" );
		openMenuItem.setActionCommand( "open" );
		JMenuItem saveMenuItem = new JMenuItem( "Save calendar" );
		saveMenuItem.setActionCommand( "save" );
		JMenuItem exportCalendarMenuItem = new JMenuItem( "Export calendar as *.tex" );
		exportCalendarMenuItem.setActionCommand( "export.calendar" );
		JMenuItem closeMenuItem = new JMenuItem( "Close calendar" );
		closeMenuItem.setActionCommand( "close" );
		JMenuItem exitMenuItem = new JMenuItem( "Exit" );
		exitMenuItem.setActionCommand( "exit" );
		JMenuItem importDatesMenuItem = new JMenuItem( "Import dates" );
		importDatesMenuItem.setActionCommand( "import.dates" );
		JMenuItem exportDatesMenuItem = new JMenuItem( "Export dates" );
		exportDatesMenuItem.setActionCommand( "export.dates" );
		JMenuItem removeDatesMenuItem = new JMenuItem( "Remove dates" );
		removeDatesMenuItem.setActionCommand( "remove.dates" );
		JMenuItem autoAddDatesMenuItem = new JMenuItem( "Automatically add holidays" );
		autoAddDatesMenuItem.setActionCommand( "add.dates" );
		JMenuItem editDatesMenuItem = new JMenuItem( "Edit dates" );
		editDatesMenuItem.setActionCommand( "edit.dates" );
		JMenuItem helpMenuItem = new JMenuItem( "Show help" );
		helpMenuItem.setActionCommand( "help" );
		
		// add action listener to menu items
		newMenuItem.addActionListener( menuItemListener );
		openMenuItem.addActionListener( menuItemListener );
		saveMenuItem.addActionListener( menuItemListener );
		exportCalendarMenuItem.addActionListener( menuItemListener );
		closeMenuItem.addActionListener( menuItemListener );
		exitMenuItem.addActionListener( menuItemListener );
		importDatesMenuItem.addActionListener( menuItemListener );
		exportDatesMenuItem.addActionListener( menuItemListener );
		removeDatesMenuItem.addActionListener( menuItemListener );
		autoAddDatesMenuItem.addActionListener( menuItemListener );
		editDatesMenuItem.addActionListener( menuItemListener );
		helpMenuItem.addActionListener( menuItemListener );
		
		// add items to 'File' menu
		fileMenu.add( newMenuItem );
		fileMenu.add( openMenuItem );
		fileMenu.add( saveMenuItem );
		fileMenu.add( exportCalendarMenuItem );
		fileMenu.add( closeMenuItem );
		fileMenu.add( exitMenuItem );
		
		// add items to 'Edit' menu
		editMenu.add( importDatesMenuItem );
		editMenu.add( exportDatesMenuItem );
		editMenu.add( removeDatesMenuItem );
		editMenu.add( autoAddDatesMenuItem );
		editMenu.add( editDatesMenuItem );
		
		// add items to 'Help' menu
		helpMenu.add( helpMenuItem );
		
		// add menus to menu bar
		menuBar.add( fileMenu );
		menuBar.add( editMenu );
		menuBar.add( helpMenu );
		
	    
		// make main window visible
		mainFrame.setVisible( true );
	}
	
	/**
	 * Get status label
	 */
	public String getStatusLabel() {
		return statusLabel.getText();
	}
	
	/**
	 * Set status label
	 */
	public void setStatusLabel( String text ) {
		statusLabel.setText( text );
	}
	
	/**
	 * Terminate the main window
	 */
	public void terminate() {
		mainFrame.setVisible( false );
		mainFrame.dispose();
	}
}
