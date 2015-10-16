package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class HTMLFileDatabase {
	
	private static HashMap<String, ArrayList<HTMLFile>> database = new HashMap<String, ArrayList<HTMLFile>>();
	
	public static void put(String URL, HTMLFile f)
	{
		//if there exists no value with key database, put that into the database and init the arraylist.
		if(!database.containsKey(URL))
		{
			ArrayList<HTMLFile> HTMLFiles = new ArrayList<HTMLFile>();
			HTMLFiles.add(f);
			database.put(URL, HTMLFiles);
		}
		else //URL already exists.
		{
			ArrayList<HTMLFile> HTMLFiles = database.get(URL);
			HTMLFiles.add(f);
		}
	}
	
	public static ArrayList<HTMLFile> get(String URL)
	{
		return database.get(URL);
	}
	
	public static boolean containsKey(String URL)
	{
		return database.containsKey(URL);
	}
}
