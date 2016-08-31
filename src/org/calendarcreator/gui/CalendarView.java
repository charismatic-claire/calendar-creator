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
			
			// configure frame
			if( editMonthFrame == null ) {
				editMonthFrame = new JFrame();
				editMonthFrame.setSize( 600, 750 );
				editMonthFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
				editMonthFrame.setLayout( new GridLayout( 0, 2 ) );				
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
			}
			
			// spacer
			editMonthFrame.add( new JLabel( "" ) );
			editMonthFrame.add( new JLabel( "" ) );
			
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
