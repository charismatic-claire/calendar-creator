/**
 * 
 */
package org.calendarcreator.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 *
 */
public class CalendarReader {

	public String readFromDisk( String filename ) throws IOException {
		return FileUtils.readFileToString( new File( filename ) );
	}
}
