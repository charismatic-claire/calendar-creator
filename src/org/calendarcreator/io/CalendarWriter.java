/**
 * 
 */
package org.calendarcreator.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.calendarcreator.data.Format;

/**
 * Write a calendar serialization to a file
 */
public class CalendarWriter {

	public boolean writeToDisk( String data, String filename, Format format ) {
		// init
		Boolean success = true;
		// add file extension
		File file = new File( addFileExtension( filename, format ) ); 
		// operate
		try {
			FileUtils.writeStringToFile( file, data );
		}
		catch (IOException e) {
			success = false;
		}
		// return
		return success;
	}
	
	private String addFileExtension( String file, Format format ) {
		// init
		String fileWithExtension;
		// prepare
		if( ( format == Format.TEX && FilenameUtils.isExtension( file, "tex" ) ) ||
				( format == Format.XML && FilenameUtils.isExtension( file, "xml" ) )
		) {
			fileWithExtension = file;
		}
		else {
			fileWithExtension = FilenameUtils.getFullPath( file ); 
			fileWithExtension = FilenameUtils.concat( fileWithExtension, FilenameUtils.getBaseName( file ) );
			if( format == Format.TEX ) {
				fileWithExtension += ".tex";
			}
			else if( format == Format.XML ) {
				fileWithExtension += ".xml";
			}
		}
		// return
		return fileWithExtension;
	}

}
