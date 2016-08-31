/**
 * 
 */
package org.calendarcreator.io;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.calendarcreator.data.Format;

/**
 * Write a calendar serialization to a file
 */
public class CalendarWriter {

	public void writeToDisk( String data, String filename, Format format ) throws Exception {
		// add file extension
		File file = new File( addFileExtension( filename, format ) ); 
		// operate
		FileUtils.writeStringToFile( file, data );
	}
	
	private String addFileExtension( String file, Format format ) {
		// no empty string
		if( file.isEmpty() || file == null ) {
			return null;
		}
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
