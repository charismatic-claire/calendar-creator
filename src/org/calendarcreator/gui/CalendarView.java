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
import javax.swing.JPanel;
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
				editMonthFrame.setSize( 300, 700 );
				editMonthFrame.setLayout( new GridLayout( 0, 1 ) );
				editMonthFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			}
			else {
				editMonthFrame.getContentPane().removeAll();
				doRepaint = true;
			}
			editMonthFrame.setTitle( printer.printMonth( month ) );
			
			// add days line by line
			List<Day> listOfDays = month.getListOfDays();
			for( Day day: listOfDays ) {
				JButton button = new JButton( printer.printDay( day ) );
				button.setHorizontalAlignment( SwingConstants.LEFT );
				button.setActionCommand( printer.printButtonLabel( month, day ) );
				button.addActionListener( entryButtonController );
				editMonthFrame.add( button );
			}
			
			// add previous and next
			JPanel panel = new JPanel();
			panel.setLayout( new GridLayout( 1, 0 ) );
			JButton previousButton = new JButton( "<< Previous" );
			previousButton.setActionCommand( "previous" );
			previousButton.addActionListener( controller );
			JButton nextButton = new JButton( "Next >>" );
			nextButton.setActionCommand( "next" );
			nextButton.addActionListener( controller );
			panel.add( previousButton );
			panel.add( nextButton );
			editMonthFrame.add( new JLabel( "" ) );
			editMonthFrame.add( panel );

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
