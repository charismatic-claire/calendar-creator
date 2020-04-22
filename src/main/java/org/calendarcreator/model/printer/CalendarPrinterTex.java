/**
 * 
 */
package org.calendarcreator.model.printer;

import java.util.Arrays;

import org.calendarcreator.data.Day;
import org.calendarcreator.data.DayOfWeek;
import org.calendarcreator.data.Month;
import org.calendarcreator.data.Week;
import org.calendarcreator.data.Year;
import org.calendarcreator.model.translator.CalendarTranslator;

/**
 *
 */
public abstract class CalendarPrinterTex implements CalendarPrinter {
	
	protected CalendarTranslator translator;
	
	protected Year year;
	
	public CalendarPrinterTex( CalendarTranslator translator ) {
		this.translator = translator;
	}

	@Override
	public String printYear(Year year) {
		// init 
		this.year = year;
		String printedYear = "";
		// print preamble
		printedYear += printPreamble();
		// open document
		printedYear += "%% content\n" +
				"\\begin{document}\n\n";
		// print months
		for( Month month : year.getListOfMonths() ) {
			printedYear += printMonth( month );
		}
		// close document
		printedYear += "\\end{document}\n";
		// return result
		return printedYear;
	}

	@Override
	public String printMonth(Month month) {
		// init string
		String printedMonth = "";
		// open month
		printedMonth += "\\begin{calmonth}{" +
				printMonthOfYear( month ) + "}\n";
		// print days
		for( Day day : month.getListOfDays() ) {
			printedMonth += "	" + printDay( day );
		}
		// close month
		printedMonth += "\\end{calmonth}\n\n";
		// return result
		return printedMonth;
	}
	
	protected String printDay( Day day ) {
		// define weekend
		DayOfWeek[] weekend = {
				DayOfWeek.SATURDAY,
				DayOfWeek.SUNDAY
		};
		// init string
		String printedDay = "";
		// open day
		if( Arrays.asList( weekend ).contains( day.getDayOfWeek() ) ) {
			printedDay += "\\weekend";
		}
		else {
			printedDay += "\\workweek";
		}
		// print day of month
		printedDay += "{" + printDayOfMonth( day ) + "}";
		// print day of week
		printedDay += "{" + printDayOfWeek( day ) + "}"; 
		// print holiday
		printedDay += "{" + printHoliday( day );
		// print entry
		if( !(printHoliday( day ).isEmpty() || printEntry( day ).isEmpty() ) ) {
			printedDay += ", ";
		}
		printedDay += printEntry( day ) + "}";
		// print week of year
		if( ! Arrays.asList( weekend ).contains( day.getDayOfWeek() ) ) {
			printedDay += "{" + printWeekOfYear( day ) + "}";
		}
		// close day
		printedDay += "\n";
		// return result
		return printedDay;
	}
	
	protected String printPreamble() {
		// create
		String printedPreamble = printDocumentClass() +
				printPackages() +
				printGeometry() +
				printSpacing() +
				printCalMonth() +
				printWorkWeek() +
				printWeekend() +
				printFooter();
		// return
		return printedPreamble;
	}
	
	protected String printDocumentClass() {
		return "";
	}
	
	protected String printPackages() {
		// create 
		String printedPackages = "%% packages\n" +
				"\\usepackage[utf8]{inputenc}\n" +
				"\\usepackage[T1]{fontenc}\n" +
				"\\usepackage[T1]{fontenc}\n" +
				"\\usepackage{parskip}\n" +
				"\\usepackage{geometry}\n" +
				"\\usepackage[table]{xcolor}\n" +
				"\\usepackage{fancyhdr}\n" +
				"\\usepackage{hhline}\n\n";
		// return
		return printedPackages;
	}
	
	protected String printGeometry() {
		return "";
	}
	
	protected String printSpacing() {
		return "";
	}
	
	protected String printCalMonth() {
		// create
		String printedCalMonth = "%% month environment\n" +
				"\\newenvironment{calmonth}[1]{%\n" +
				"	\\section*{#1}%\n" +
				"	\\begin{tabular}{ |p{\\colone} p{\\coltwo} | p{\\colthree} }%\n" +
				"	\\hhline{|-|-|}%\n" +
				"	}{%\n" +
				"	\\end{tabular}%\n" +
				"	\\vfill%\n" +
				"}\n\n";
		// return
		return printedCalMonth;
	}
	
	protected String printWorkWeek() {
		// create
		String printedWorkWeek = "%% table rows, monday to friday\n" +
				"\\newcommand{\\workweek}[4]{%\n" +
				"	\\textbf{#1} \\hspace{1.0mm} #2 &%\n" +
				"	{\\footnotesize #3} &%\n" +
				"	{\\scriptsize #4} \\\\ \\hhline{|-|-|}%\n" +
				"}\n\n";
		// return
		return printedWorkWeek;
	}
	
	protected String printWeekend() {
		// create
		String printedWeekend = "%% table rows, staturday and sunday\n" +
				"\\newcommand{\\weekend}[3]{%\n" +
				"	\\rowcolor{black!20} \\textbf{#1} \\hspace{1.0mm} #2 &%\n" +
				"	{\\footnotesize #3} &%\n" +
				"	\\cellcolor{white} \\\\ \\hhline{|-|-|}%\n" +
				"}\n\n";
		// return
		return printedWeekend;
	}
	
	protected String printFooter() {
		// create
		String printedFooter = "%% footer\n" +
				"\\pagestyle{fancy}\n" +
				"\\fancyhf{}\n" +
				"\\renewcommand{\\headrulewidth}{0pt}\n" +
				"\\fancyhf[FC]{{\\scriptsize -- " + year.getYearInteger() + " --}}\n\n";
		// return
		return printedFooter;
	}

	protected String printMonthOfYear( Month month ) {
		// init string
		String printedMonth = "";
		// print month
		printedMonth = translator.translateMonthOfYear( month.getMonthOfYear() );
		// return result
		return printedMonth;
	}
	
	protected String printDayOfMonth( Day day ) {
		// init string
		String printedDayOfMonth = "";
		// print day of month
		if( day.getDayOfMonth() < 10 ) {
			printedDayOfMonth += "0";
		}
		printedDayOfMonth += day.getDayOfMonth();
		// return result
		return printedDayOfMonth;
	}
	
	protected String printDayOfWeek( Day day ) {
		// init string
		String printedDayOfWeek = "";
		// print day of week
		printedDayOfWeek += translator.translateDayOfWeek( day.getDayOfWeek() );
		// return result
		return printedDayOfWeek;
	}
	
	protected String printDayOfWeek( DayOfWeek dayOfWeek ) {
		// init string
		String printedDayOfWeek = "";
		// print day of week
		printedDayOfWeek += translator.translateDayOfWeek( dayOfWeek );
		// return result
		return printedDayOfWeek;
	}

	
	protected String printHoliday( Day day ) {
		// init string
		String printedHoliday = "";
		// print holiday
		if( day.getHoliday() != null ) {
			printedHoliday += translator.translateHoliday( day.getHoliday() ); 
		}
		// return result
		return printedHoliday;
	}
	
	protected String printEntry( Day day ) {
		// init string
		String printedEntry = "";
		// print entry
		if( day.getEntry() != null ) {
			printedEntry += day.getEntry();
		}
		// return result
		return printedEntry;
	}
	
	protected String printWeekOfYear( Day day ) {
		// init string
		String printedWeekOfYear = "";
		// print week of year
		if( day.getWeekOfYear() != null ) {
			if( day.getWeekOfYear() < 10 ) {
				printedWeekOfYear += "0";
			}
			printedWeekOfYear += day.getWeekOfYear();
		}
		// return result
		return printedWeekOfYear;
	}
	
	protected String printWeekOfYear( Week week ) {
		// init string
		String printedWeekOfYear = "";
		// print week of year
		if( week.getWeekOfYear() != null ) {
			if( week.getWeekOfYear() < 10 ) {
				printedWeekOfYear += "0";
			}
			printedWeekOfYear += week.getWeekOfYear();
		}
		// return result
		return printedWeekOfYear;
	}
	
}
