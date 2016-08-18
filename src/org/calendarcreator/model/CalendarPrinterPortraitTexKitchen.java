/**
 * 
 */
package org.calendarcreator.model;

/**
 *
 */
public class CalendarPrinterPortraitTexKitchen extends CalendarPrinterPortraitTex {

	public CalendarPrinterPortraitTexKitchen(CalendarTranslator translator) {
		super(translator);
	}
	
	@Override
	protected String printDocumentClass() {
		// create
		String printedDocumentClass = "%% document class\n" +
				"\\documentclass[10pt]{article}\n\n";
		// return
		return printedDocumentClass;
	}
	
	@Override
	protected String printGeometry() {
		// create
		String printedGeometry = "%% geometry\n" +
				"\\geometry{a4paper, top=1cm, left=1cm, right=1cm, bottom=2cm, twocolumn}\n\n";
		// return
		return printedGeometry;
	}

	@Override
	protected String printSpacing() {
		// create
		String printedSpacing = "%% spacing\n" +
				"\\setlength{\\columnsep}{1.0cm}\n" +
				"\\renewcommand{\\arraystretch}{1.94}\n" +
				"\\newcommand{\\colone}{1.4cm}\n" +
				"\\newcommand{\\coltwo}{4.9cm}\n" +
				"\\newcommand{\\colthree}{1.4cm}\n\n";
		// return
		return printedSpacing;
	}

}
