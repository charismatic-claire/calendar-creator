/**
 * 
 */
package org.calendarcreator.gui;

import java.awt.GridLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Style;

/**
 * the main user interface
 */
public class CalendarView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CalendarController controller;
	
	private JLabel yearIntegerLabelText;

	private JLabel createdYearLabelText;
	
	private JLabel addedHolidaysLabelText;
	
	private JLabel addedEntriesLabelText;
	
	private JMenuItem newMenuItem;
	
	private JMenuItem openMenuItem;
	
	private JMenuItem saveMenuItem;
		
	private JMenuItem exportMenuItem;
		
	private JMenuItem closeMenuItem;
		
	private JMenuItem exitMenuItem;
		
	private JMenuItem updateYearMenuItem;
		
	private JMenuItem addHolidaysMenuItem;
		
	private JMenuItem removeHolidaysMenuItem;
		
	private JMenuItem addEntryMenuItem;
		
	private JMenuItem removeEntryMenuItem;
		
	private JMenuItem removeEntriesMenuItem;
		
	private JMenuItem helpMenuItem;
	
	public CalendarView( CalendarController controller ) {
		this.controller = controller;
		prepareGUI();
	}

	/**
	 * Update label text, according to model configuration  
	 * @param config ModelConfiguration
	 */
	protected void updateLabelText( ModelConfiguration config ) {
		if( config.getYear() != null ) {
			yearIntegerLabelText.setText( "" + config.getYear().getYearInteger() + "" );
		}
		else {
			yearIntegerLabelText.setText( "" );
		}
		createdYearLabelText.setText( bool2String( config.isCreatedYear() ) );
		addedHolidaysLabelText.setText( bool2String( config.isAddedHolidays() ) );
		addedEntriesLabelText.setText( bool2String( config.isAddedEntries() ) );
	}
	
	/**
	 * Update menu item availability, according to model configuration
	 * @param config ModelConfiguration
	 */
	protected void updateAvailability( ModelConfiguration config ) {
		setAvailability( config.isCreatedYear(), config.isAddedHolidays(), config.isAddedEntries() );
	}
	
	protected Integer getYearInteger() {
		try {
			String yearString = JOptionPane.showInputDialog( "Select year:" );
			Integer yearInteger = Integer.parseInt( yearString );
			return yearInteger;
		}
		catch( Exception e ) {
			System.err.println( "Input not accepted." );
		}
		return null;
	}
	
	protected void showHelp() {
		JOptionPane.showMessageDialog( this, "CalendarCreator v2.0 Help\n\nHelp text here..." );
	}
	
	protected String getSaveTexFilePath() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
			fc.setFileFilter( new FileNameExtensionFilter( "TEX files", "tex" ) );
			if( fc.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION ) {
				return fc.getSelectedFile().toString();
			}
		}
		catch( Exception e ) {
			System.err.println( "File selection failed." );
		}
		return null;
	}	
	
	protected String getSaveXmlFilePath() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
			fc.setFileFilter( new FileNameExtensionFilter( "XML files", "XML" ) );
			if( fc.showSaveDialog( this ) == JFileChooser.APPROVE_OPTION ) {
				return fc.getSelectedFile().toString();
			}
		}
		catch( Exception e ) {
			System.err.println( "File selection failed." );
		}
		return null;
	}
	
	protected String getOpenXmlFilePath() {
		try {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode( JFileChooser.FILES_ONLY );
			fc.setFileFilter( new FileNameExtensionFilter( "XML files", "XML" ) );
			if( fc.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION ) {
				return fc.getSelectedFile().toString();
			}
		}
		catch( Exception e ) {
			System.err.println( "File selection failed." );
		}
		return null;
	}
	
	protected Language getLanguage() {
		try {
			Language[] langs = { Language.EN, Language.DE };
			Language lang = (Language) JOptionPane.showInputDialog(
					this,
					"Select languague:",
					"Language selection",
					JOptionPane.QUESTION_MESSAGE,
					null,
					langs,
					Language.EN );
			return lang;
		}
		catch( Exception e ) {
			System.err.println( "Language selection failed." );
		}
		return null;
	}
	
	protected Style getStyle() {
		try {
			Style[] styles = { Style.CLASSIC, Style.KITCHEN, Style.JEDDI };
			Style style = (Style) JOptionPane.showInputDialog(
					this, 
					"Select style:", 
					"Style selection", 
					JOptionPane.QUESTION_MESSAGE, 
					null, 
					styles, 
					Style.CLASSIC );
			return style;
		}
		catch( Exception e ) {
			System.err.println( "Style selection failed." );
		}
		return null;
	}
	
	/**
	 * Close the view
	 */
	protected void terminate() {
		this.setVisible( false );
		this.dispose();
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
		addEntryMenuItem = new JMenuItem( "Add entry" );
		addEntryMenuItem.setActionCommand( "add.entry" );
		removeEntryMenuItem = new JMenuItem( "Remove entry" );
		removeEntryMenuItem.setActionCommand( "remove.entry" );
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
		addEntryMenuItem.addActionListener( controller );
		removeEntryMenuItem.addActionListener( controller );
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
		editMenu.add( addEntryMenuItem );
		editMenu.add( removeEntryMenuItem );
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
	private String bool2String( boolean bool ) {
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
			addEntryMenuItem.setEnabled( true );
			// added holidays?
			if( addedHolidays ) {
				addHolidaysMenuItem.setEnabled( false );
				removeHolidaysMenuItem.setEnabled( true );
				// added entries?
				if( addedEntries ) {
					removeEntryMenuItem.setEnabled( true );
					removeEntriesMenuItem.setEnabled( true );
				}
				else {
					removeEntryMenuItem.setEnabled( false );
					removeEntriesMenuItem.setEnabled( false );					
				}
			}
			else {
				addHolidaysMenuItem.setEnabled( true );
				removeHolidaysMenuItem.setEnabled( false );
				// added entries?
				if( addedEntries ) {
					removeEntryMenuItem.setEnabled( true );
					removeEntriesMenuItem.setEnabled( true );
				}
				else {
					removeEntryMenuItem.setEnabled( false );
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
			addEntryMenuItem.setEnabled( false );
			removeEntryMenuItem.setEnabled( false );
			removeEntriesMenuItem.setEnabled( false );
		}		
	}
	
}
