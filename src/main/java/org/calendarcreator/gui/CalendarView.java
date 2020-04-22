/**
 * 
 */
package org.calendarcreator.gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.Language;
import org.calendarcreator.data.ModelConfiguration;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Style;
import org.calendarcreator.model.translator.CalendarTranslator;
import org.calendarcreator.model.translator.CalendarTranslatorEnglish;

/**
 * the main user interface
 */
public class CalendarView extends AbstractCalendarView {
	
	private static final long serialVersionUID = 1L;
	
	private JFrame editMonthFrame;
	
	private Month month;
	
	private EntryButtonController entryButtonController;
	
	public CalendarView( CalendarController controller ) {
		super( controller );
	}

	/**
	 * Update label text, according to model configuration  
	 * @param config ModelConfiguration
	 */
	protected void updateLabel( ModelConfiguration config ) {
		// calendar year
		if( config.isCreatedYear() && config.getYear() != null ) {
			yearLabel.setIcon( null );
			yearLabel.setText( "" + config.getYear().getYearInteger() + "" );
		}
		else {
			yearLabel.setIcon( errorIcon );
			yearLabel.setText( null );
		}
		// added holidays
		if( config.isAddedHolidays() ) {
			addedHolidaysLabel.setIcon( successIcon );
		}
		else {
			addedHolidaysLabel.setIcon( errorIcon );
		}
		// added entries
		if( config.isAddedEntries() ) {
			addedEntriesLabel.setIcon( successIcon );
		}
		else {
			addedEntriesLabel.setIcon( errorIcon );
		}
	}
	
	protected Integer getYearInteger() throws Exception {
			String yearString = JOptionPane.showInputDialog( "Select year:" );
			Integer yearInteger = Integer.parseInt( yearString );
			return yearInteger;
	}
	
	protected void showHelp() {
		String title = "Calendar Creator Help";
		String message = "* NEW CALENDAR: To initialize the calendar creation process\n" +
				"  you need to start with this.\n\n" + 
				"* OPEN CALENDAR: Open a saved calendar from a XML file. This is cool\n" +
				"  because this way you can reuse your entries\n\n" +
				"* SAVE CALENDAR: Save your calendar for later use to a XML file.\n\n" + 
				"* EXPORT AS TEX: Create a compilable TEX file from your calendar.\n" + 
				"  You can choose between different languages and styles.\n\n" + 
				"* CLOSE CALENDAR: Discard the current calendar and start over.\n\n" + 
				"* EXIT: Quit the program.\n\n" + 
				"* UPDATE YEAR: Change the year of your calendar, preserving every\n" +
				"  other property. This is i.e. cool if you want to reuse the saved\n"+
				"  calendar from last year.\n\n" + 
				"* ADD HOLIDAYS: Automatically determine the dates of all holidays this\n" +
				"  year and add them to the calendar.\n\n" + 
				"* REMOVE HOLIDAYS: Dismiss the added holidays.\n\n" + 
				"* EDIT ENTRIES: Shows a calendar dialog and let's you add individual\n" +
				"  entries to the calendar. This is i.e. cool for birthdays.\n\n" + 
				"* REMOVE ALL ENTRIES: Dismiss all added entries.\n\n" + 
				"* SHOW HELP: Show this help.\n\n";
		JOptionPane.showMessageDialog( this, message, title, JOptionPane.INFORMATION_MESSAGE );
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
	
	protected void editMonth( Month month, EntryButtonController entryButtonController ) {
		// save
		this.month = month;
		this.entryButtonController = entryButtonController;
		// do stuff
		try {
			// init
			CalendarTranslator translator = new CalendarTranslatorEnglish();
			ViewPrinter printer = new ViewPrinter( translator );
			boolean doRepaint = false;
			int rowCounter = 0;
			
			// configure frame
			if( editMonthFrame == null ) {
				editMonthFrame = new JFrame();
				editMonthFrame.setSize( 600, 750 );
				editMonthFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				editMonthFrame.setLayout( new GridLayout( 33, 2 ) );				
			}
			else {
				editMonthFrame.getContentPane().removeAll();
				doRepaint = true;
			}
			editMonthFrame.setTitle( "Edit month: " + printer.printMonth( month ) );
			
			// add days line by line
			List<Day> listOfDays = month.getListOfDays();
			for( Day day: listOfDays ) {
				// label
				JLabel label = new JLabel( printer.printDayBasic( day ) );
				label.setHorizontalAlignment( SwingConstants.RIGHT );
				editMonthFrame.add( label );
				// button
				JButton button = new JButton( printer.printDayText( day ) );
				button.setHorizontalAlignment( SwingConstants.LEFT );
				button.setActionCommand( printer.printButtonLabel( month, day ) );
				button.addActionListener( entryButtonController );
				editMonthFrame.add( button );
				// counter
				rowCounter++;
			}
			
			// spacer
			for( int totalRows = 33; totalRows - rowCounter > 1; rowCounter++ ) {
				for( int i=0; i<2; i++) {
					editMonthFrame.add( new JLabel( "" ) );
				}
			}
			
			// previous & next
			JButton previousButton = new JButton( "<< Previous" );
			previousButton.setActionCommand( "previous" );
			previousButton.addActionListener( controller );
			editMonthFrame.add( previousButton );
			JButton nextButton = new JButton( "Next >>" );
			nextButton.setActionCommand( "next" );
			nextButton.addActionListener( controller );
			editMonthFrame.add( nextButton );

			// repaint
			if( doRepaint ) {
				editMonthFrame.revalidate();
				editMonthFrame.repaint();
			}

			// show frame
			editMonthFrame.setLocationRelativeTo( null );
			editMonthFrame.setVisible( true );			
		}
		catch( Exception e) {
		// do nothing
		}
	}
	
	protected void updateMonth() {
		editMonth( month, entryButtonController );
	}
	
	protected String getEntry( String initEntry ) {
		String entry = null;
		try {
			entry = JOptionPane.showInputDialog( "Set entry:", initEntry );
		}
		catch( Exception e ) {
			System.err.println( "Entry setting failed." );
		}
		return entry;
	}
	
}
