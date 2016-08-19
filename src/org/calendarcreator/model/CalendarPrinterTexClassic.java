/**
 * 
 */
package org.calendarcreator.model;

/**
 *
 */
public class CalendarPrinterTexClassic extends CalendarPrinterTex {

	public CalendarPrinterTexClassic(CalendarTranslator translator) {
		super(translator);
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
				"\\geometry{a4paper, top=1cm, left=1cm, right=0.1cm, bottom=2cm, twocolumn}\n\n";
		// return
		return printedGeometry;
	}

	@Override
	protected String printSpacing() {
		// create
		String printedSpacing = "%% spacing\n" +
				"\\setlength{\\columnsep}{0.2cm}\n" +
				"\\renewcommand{\\arraystretch}{1.59}\n" +
				"\\newcommand{\\colone}{1.8cm}\n" +
				"\\newcommand{\\coltwo}{6.2cm}\n" +
				"\\newcommand{\\colthree}{0.29cm}\n\n";
		// return
		return printedSpacing;
	}

}
