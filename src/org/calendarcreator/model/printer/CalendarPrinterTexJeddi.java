/**
 * 
 */
package org.calendarcreator.model.printer;

import java.util.List;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Week;
import org.calendarcreator.model.WeekFactory;
import org.calendarcreator.model.translator.CalendarTranslator;

/**
 *
 */
public class CalendarPrinterTexJeddi extends CalendarPrinterTex {

	public CalendarPrinterTexJeddi(CalendarTranslator translator) {
		super(translator);
	}
	
	@Override
	public String printMonth(Month month) {
		// init string
		String printedMonth = "";
		// open month
		printedMonth += "\\begin{calmonth}{" +
				printMonthOfYear( month ) + "}\n" +
				printDayOfWeekHeading() + "\n";
		// loop weeks
		WeekFactory weekFactory = new WeekFactory();
		List<Week> listOfWeeks= weekFactory.generateListOfWeeks( month );
		for( Week week : listOfWeeks ) {
			// print week
			printedMonth += printWeek( week );
		}
		
		// close month
		printedMonth += "\\end{calmonth}\n\n";
		// return result
		return printedMonth;
	}
	
	protected String printWeek( Week week ) {
		// init string
		String printedWeek = "";
		// print day of month
		printedWeek += "	\\daterow{" + printWeekOfYear( week ) + "}";
		for( int i=0; i<7; i++ ) {
			Day day = week.getWeek()[i];
			printedWeek += "{";
			// only if there is a day
			if( day != null ) {
				printedWeek += printDayOfMonth( day );
			}
			printedWeek += "}";
		}
		printedWeek += "\n";
		// print holiday and entry
		printedWeek += "	\\textrow";
		for( int i=0; i<7; i++ ) {
			Day day = week.getWeek()[i];
			printedWeek += "{";
			// only if there is a day
			if( day != null ) {
				// blank if no holiday and no entry
				if( printHoliday( day ).isEmpty() && printEntry( day ).isEmpty() ) {
					printedWeek += "\\bl";
				}
				// put it otherwise
				else {
					printedWeek += printHoliday( day );
					// seperate by comma if holiday and entry
					if( !( printHoliday( day ).isEmpty() || printEntry( day ).isEmpty() ) ) {
						printedWeek += ", ";
					}
					printedWeek += printEntry( day );
				}
			}
			// blank otherwise
			else {
				printedWeek += "\\bl";
			}
			printedWeek += "}";
		}
		printedWeek += "\n";
		// return result
		return printedWeek;
	}

	@Override
	protected String printPreamble() {
		// create
		String printedPreamble = printDocumentClass() +
				printPackages() +
				printGeometry() +
				printSpacing() +
				printCalMonth() +
				printTitleRow() +
				printDateRow() +
				printTextRow() +
				printFooter();
		// return
		return printedPreamble;
	}

	@Override
	protected String printDocumentClass() {
		// create
		String printedDocumentClass = "%% document class\n" +
				"\\documentclass[12pt]{article}\n\n";
		// return
		return printedDocumentClass;
	}
	
	@Override
	protected String printGeometry() {
		// create
		String printedGeometry = "%% geometry\n" +
				"\\geometry{a4paper, top=1cm, left=0.20cm, right=0.6cm, bottom=2cm, onecolumn, landscape}\n\n";
		// return
		return printedGeometry;
	}

	@Override
	protected String printSpacing() {
		// create
		String printedSpacing = "%% spacing\n" +
				"\\renewcommand{\\arraystretch}{1.98}\n" +
				"\\newcommand{\\ww}{0.5cm}\n" +
				"\\newcommand{\\cw}{3.55cm}\n" +
				"\\newcommand{\\bl}{~\\newline}\n\n";
		// return
		return printedSpacing;
	}
	
	@Override
	protected String printCalMonth() {
		// create
		String printedCalMonth = "%% month environment\n" +
				"\\newcolumntype{L}[1]{>{\\raggedright\\let\\newline\\\\\\arraybackslash\\hspace{0pt}}m{#1}}\n" +
				"\\newenvironment{calmonth}[1]{%\n" +
				"	\\section*{\\centering #1}%\n" +
				"	\\begin{tabular}{ p{\\ww}|L{\\cw}|L{\\cw}|L{\\cw}|L{\\cw}|L{\\cw}|L{\\cw}|L{\\cw}| }%\n" +
				"	\\hhline{~|-|-|-|-|-|-|-|}%\n" +
				"	}{%\n" +
				"	\\end{tabular}%\n" +
				"	\\vfill%\n" +
				"}\n\n";
		// return
		return printedCalMonth;
	}

	protected String printTitleRow() {
		// create
		String printedTitleRow = "%% title row\n" +
				"\\newcommand{\\titlerow}[7]{%\n" +
				"	&%\n" +
				"	\\cellcolor{black!20} \\textbf{#1} &%\n" +
				"	\\cellcolor{black!20} \\textbf{#2} &%\n" +
				"	\\cellcolor{black!20} \\textbf{#3} &%\n" + 
				"	\\cellcolor{black!20} \\textbf{#4} &%\n" +
				"	\\cellcolor{black!20} \\textbf{#5} &%\n" +
				"	\\cellcolor{black!20} \\textbf{#6} &%\n" +
				"	\\cellcolor{black!20} \\textbf{#7} \\\\ \\hhline{~|-|-|-|-|-|-|-|}%\n" +
				"}\n\n";
		// return
		return printedTitleRow;
	}
	
	protected String printDateRow() {
		// create
		String printedDateRow = "%% date row\n" +
				"\\newcommand{\\daterow}[8]{%\n" +
				"	{\\footnotesize #1} &%\n" +
				"	#2 &%\n" +
				"	#3 &%\n" +
				"	#4 &%\n" +
				"	#5 &%\n" +
				"	#6 &%\n" +
				"	#7 &%\n" +
				"	#8 \\\\%\n" +
				"}\n\n";
		// return
		return printedDateRow;
	}
	
	protected String printTextRow() {
		// create
		String printedTextRow = "%% text row\n" +
				"\\newcommand{\\textrow}[7]{%\n" +
				"	&%\n" +
				"	{\\footnotesize #1} &%\n" +
				"	{\\footnotesize #2} &%\n" +
				"	{\\footnotesize #3} &%\n" +
				"	{\\footnotesize #4} &%\n" +
				"	{\\footnotesize #5} &%\n" +
				"	{\\footnotesize #6} &%\n" +
				"	{\\footnotesize #7} \\\\ \\hhline{~|-|-|-|-|-|-|-|}%\n" +		
				"}\n\n";
		// return
		return printedTextRow;
	}
	
	protected String printDayOfWeekHeading() {
		// init string
		String printedDayOfWeekHeading = "	\\titlerow";
		// loop days of week
		DayOfWeek[] daysOfWeek = { DayOfWeek.MONDAY, 
				DayOfWeek.TUESDAY,
				DayOfWeek.WEDNESDAY,
				DayOfWeek.THURSDAY,
				DayOfWeek.FRIDAY,
				DayOfWeek.SATURDAY,
				DayOfWeek.SUNDAY };
		for( int i=0; i<7; i++ ) {
			// print day of week
			printedDayOfWeekHeading += "{" + printDayOfWeek( daysOfWeek[i] ) + "}";
		}
		// return result
		return printedDayOfWeekHeading;
	}
	
}
