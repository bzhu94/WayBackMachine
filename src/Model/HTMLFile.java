package Model;

import java.io.File;
import org.joda.time.LocalDate;

//this class wraps a HTMLFile with a easy to access name as well as a Date ID.
public class HTMLFile {

	public String URL;
	public LocalDate versionDate; //this will be the unique ID that differentiates HTMLFiles with the same URL.
	public File HTMLCode;
	
	public HTMLFile(String URLParameter, LocalDate d, File f)
	{
		URL = URLParameter;
		versionDate = d;
		HTMLCode = f;
	}
	
	public String dateToString()
	{
		return versionDate.toString();
	}
}
