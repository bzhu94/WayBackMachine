package Controller;

import java.util.ArrayList;

import org.joda.time.LocalDate;

import Model.HTMLFile;
import Model.HTMLFileDatabase;

public class HTMLFileManager {

public static ConcreteCrawler crawler = ConcreteCrawler.getInstance();

public static boolean hasHTML(String URL)
{
	return HTMLFileDatabase.containsKey(URL);
}

//gets the closest LAST version of the specified URL.
public static HTMLFile getClosestVersion(String URL, LocalDate targetDate)
{
	//assume that the URL has already been put.
	
	if(HTMLFileDatabase.containsKey(URL))
	{
		LocalDate todayDate = new LocalDate();


		//get all files 
		ArrayList<HTMLFile> HTMLFiles = HTMLFileDatabase.get(URL);
		
		//if the target date is in the future, then we throw an error message and return the newest version of the site.
		if(targetDate.isAfter(todayDate))
		{
			System.out.println("Error! Your date is in the future. The newest version of " +  URL + " is " + HTMLFiles.get(HTMLFiles.size()-1).dateToString() + ". We'll display the newest version.");
		}
		
		return closestVersionSearch(targetDate, HTMLFiles);
	}
	else
	{
		System.out.println("Error! the crawler has not yet put this URL in the DB. It will do it right away.");
		crawler.crawlURL(URL);
		
		if(HTMLFileDatabase.containsKey(URL))
		{
			//get all files 
			ArrayList<HTMLFile> HTMLFiles = HTMLFileDatabase.get(URL);
			return closestVersionSearch(targetDate, HTMLFiles);
		}
		else
		{
			System.out.println("ERROR: Your requested URL is not a valid URL");
			return null;
		}
	}
}

//here we implement a binary search. Assumes HTMLFiles is sorted by date. Does in O(log n) time where n = number of versions of the URL.
private static HTMLFile closestVersionSearch(LocalDate targetDate, ArrayList<HTMLFile> HTMLFiles)
{
	return closestVersionHelper(targetDate, HTMLFiles, 0, HTMLFiles.size()-1);
}

private static HTMLFile closestVersionHelper(LocalDate targetDate, ArrayList<HTMLFile> HTMLFiles, int startIndex, int endIndex)
{
	//base case.
	if(startIndex >= endIndex) 
	{
		HTMLFile candidateFile = HTMLFiles.get(startIndex);
		
		//if the candidate file's date is less than or equal to the target date's, the we return it.
		if(candidateFile.versionDate.compareTo(targetDate) <= 0) return candidateFile;
		
		//else look left to see if closest file exists.
		else if(startIndex > 0)
		{
			return HTMLFiles.get(startIndex-1);
		}
		else //if the target date is older than the first version of the site, just return oldest version of site.
		{
			System.out.println("Error! The oldest version of " +  candidateFile.URL + " is "  + HTMLFiles.get(startIndex).dateToString() + " which was created after the specified date (" +targetDate.toString() + ").");
			return HTMLFiles.get(startIndex);
		}
	}
	
	//recursive case.
	
	int mid = (startIndex + endIndex)/2;
	HTMLFile midFile = HTMLFiles.get(mid); 
	
	//if the middle file has a date equal to the target, just return middle file.
	if(midFile.versionDate.compareTo(targetDate) == 0) return HTMLFiles.get(mid);
	
	//if the middle file has a date less than the target, we look right.

	if(midFile.versionDate.compareTo(targetDate) < 0) return closestVersionHelper(targetDate, HTMLFiles, mid+1, endIndex);
	
	//mid file has a date greater than target therefore look left.
	return closestVersionHelper(targetDate, HTMLFiles, startIndex, mid-1);
	
}



}
