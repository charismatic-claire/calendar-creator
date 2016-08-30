package org.calendarcreator.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

import org.calendarcreator.data.ModelConfiguration;

public abstract class AbstractCalendarView extends JFrame {
	
	protected static final long serialVersionUID = 1L;
	
	protected CalendarController controller;
	
	protected JLabel yearIntegerLabelText;

	protected JLabel createdYearLabelText;
	
	protected JLabel addedHolidaysLabelText;
	
	protected JLabel addedEntriesLabelText;
	
	protected JMenuItem newMenuItem;
	
	protected JMenuItem openMenuItem;
	
	protected JMenuItem saveMenuItem;
		
	protected JMenuItem exportMenuItem;
		
	protected JMenuItem closeMenuItem;
		
	protected JMenuItem exitMenuItem;
		
	protected JMenuItem updateYearMenuItem;
		
	protected JMenuItem addHolidaysMenuItem;
		
	protected JMenuItem removeHolidaysMenuItem;
		
	protected JMenuItem editEntriesMenuItem;
		
	protected JMenuItem removeEntriesMenuItem;
		
	protected JMenuItem helpMenuItem;
	
	public AbstractCalendarView( CalendarController controller ) {
		this.controller = controller;
		prepareGUI();
	}

	/**
	 * Update menu item availability, according to model configuration
	 * @param config ModelConfiguration
	 */
	protected void updateAvailability( ModelConfiguration config ) {
		setAvailability( config.isCreatedYear(), config.isAddedHolidays(), config.isAddedEntries() );
	}

	/**
	 * Prepare the GUI
	 */
	private void prepareGUI() {
		// init window
		this.setTitle( "CalendarCreater v2.0" );
		this.setSize( 400, 200 );
		this.setLayout( new GridLayout( 4, 2 ) );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
	    // init message area
		yearIntegerLabelText = new JLabel( "" );
	    createdYearLabelText = new JLabel( "FALSE" );
	    addedHolidaysLabelText = new JLabel( "FALSE" );
	    addedEntriesLabelText = new JLabel( "FALSE" );
	
	    // init menu bar
	    JMenuBar menuBar = new JMenuBar();
	    
	    // add elements to main window
	    this.setJMenuBar( menuBar );
	    this.add( new JLabel( "Year (integer):    ", SwingConstants.RIGHT ) );
	    this.add( yearIntegerLabelText );
	    this.add( new JLabel( "Created year:    ", SwingConstants.RIGHT ) );
	    this.add( createdYearLabelText );
	    this.add( new JLabel( "Added holidays:    ", SwingConstants.RIGHT ) );
	    this.add( addedHolidaysLabelText );
	    this.add( new JLabel( "Added entries:    ", SwingConstants.RIGHT ) );
	    this.add( addedEntriesLabelText );
	    
	    // create menus
		JMenu fileMenu = new JMenu( "File" );
		JMenu editMenu = new JMenu( "Edit" );
		JMenu helpMenu = new JMenu( "Help" );
		
		// create menu items
		newMenuItem = new JMenuItem( "New calendar" );
		newMenuItem.setActionCommand( "new" );
		openMenuItem = new JMenuItem( "Open calendar" );
		openMenuItem.setActionCommand( "open" );
		saveMenuItem = new JMenuItem( "Save calendar" );
		saveMenuItem.setActionCommand( "save" );
		exportMenuItem = new JMenuItem( "Export as TEX" );
		exportMenuItem.setActionCommand( "export" );
		closeMenuItem = new JMenuItem( "Close calendar" );
		closeMenuItem.setActionCommand( "close" );
		exitMenuItem = new JMenuItem( "Exit" );
		exitMenuItem.setActionCommand( "exit" );
		
		updateYearMenuItem = new JMenuItem( "Update year" );
		updateYearMenuItem.setActionCommand( "update.year" );
		addHolidaysMenuItem = new JMenuItem( "Add holidays" );
		addHolidaysMenuItem.setActionCommand( "add.holidays" );
		removeHolidaysMenuItem = new JMenuItem( "Remove holidays" );
		removeHolidaysMenuItem.setActionCommand( "remove.holidays" );
		editEntriesMenuItem = new JMenuItem( "Edit entries" );
		editEntriesMenuItem.setActionCommand( "edit.entries" );
		removeEntriesMenuItem = new JMenuItem( "Remove all entries" );
		removeEntriesMenuItem.setActionCommand( "remove.entries" );
		
		helpMenuItem = new JMenuItem( "Show help" );
		helpMenuItem.setActionCommand( "help" );
		
		// set initial availabilities
		setAvailability( false, false, false );
		
		// add action listener to menu items
		newMenuItem.addActionListener( controller );
		openMenuItem.addActionListener( controller );
		saveMenuItem.addActionListener( controller );
		exportMenuItem.addActionListener( controller );
		closeMenuItem.addActionListener( controller );
		exitMenuItem.addActionListener( controller );
		
		updateYearMenuItem.addActionListener( controller );
		addHolidaysMenuItem.addActionListener( controller );
		removeHolidaysMenuItem.addActionListener( controller );
		editEntriesMenuItem.addActionListener( controller );
		removeEntriesMenuItem.addActionListener( controller );
		
		helpMenuItem.addActionListener( controller );
		
		// add items to menu
		fileMenu.add( newMenuItem );
		fileMenu.add( openMenuItem );
		fileMenu.add( saveMenuItem );
		fileMenu.add( exportMenuItem );
		fileMenu.add( closeMenuItem );
		fileMenu.add( exitMenuItem );
		
		editMenu.add( updateYearMenuItem );
		editMenu.add( addHolidaysMenuItem );
		editMenu.add( removeHolidaysMenuItem );
		editMenu.add( editEntriesMenuItem );
		editMenu.add( removeEntriesMenuItem );
		
		helpMenu.add( helpMenuItem );
		
		// add menus to menu bar
		menuBar.add( fileMenu );
		menuBar.add( editMenu );
		menuBar.add( helpMenu );
		
		// center it
		this.setLocationRelativeTo( null );
		
		// make main window visible
		this.setVisible( true );
	}

	/**
	 * Convert boolean variable to String
	 * @param bool boolean
	 * @return String
	 */
	protected String bool2String( boolean bool ) {
		if( bool ) {
			return "TRUE";
		}
		else {
			return "FALSE";
		}
	}

	/**
	 * Set the availability
	 * @param createdYear created year?
	 * @param addedHolidays added holidays?
	 * @param addedEntries added entries?
	 */
	private void setAvailability( boolean createdYear, boolean addedHolidays, boolean addedEntries ) {
		newMenuItem.setEnabled( true );
		openMenuItem.setEnabled( true );
		exitMenuItem.setEnabled( true );
		helpMenuItem.setEnabled( true );
		// created year?
		if( createdYear ) {
			saveMenuItem.setEnabled( true );
			exportMenuItem.setEnabled( true );
			closeMenuItem.setEnabled( true );
			updateYearMenuItem.setEnabled( true );
			editEntriesMenuItem.setEnabled( true );
			// added holidays?
			if( addedHolidays ) {
				addHolidaysMenuItem.setEnabled( false );
				removeHolidaysMenuItem.setEnabled( true );
				// added entries?
				if( addedEntries ) {
					removeEntriesMenuItem.setEnabled( true );
				}
				else {
					removeEntriesMenuItem.setEnabled( false );					
				}
			}
			else {
				addHolidaysMenuItem.setEnabled( true );
				removeHolidaysMenuItem.setEnabled( false );
				// added entries?
				if( addedEntries ) {
					removeEntriesMenuItem.setEnabled( true );
				}
				else {
					removeEntriesMenuItem.setEnabled( false );					
				}
			}
		}
		else {
			saveMenuItem.setEnabled( false );
			exportMenuItem.setEnabled( false );
			closeMenuItem.setEnabled( false );
			updateYearMenuItem.setEnabled( false );
			addHolidaysMenuItem.setEnabled( false );
			removeHolidaysMenuItem.setEnabled( false );
			editEntriesMenuItem.setEnabled( false );
			removeEntriesMenuItem.setEnabled( false );
		}		
	}

	/**
	 * Close the view
	 */
	protected void terminate() {
		this.setVisible( false );
		this.dispose();
	}

}
