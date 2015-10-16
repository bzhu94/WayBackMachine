package View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import Controller.ConcreteCrawler;
import Controller.HTMLFileManager;
import Model.HTMLFile;

public class ConsoleMain {
	
	public static void main(String[] args) {
		
		//this is technically not necessary, but I am showing the crawler at work.
		//we could've assumed that prior to running this program, 
		//the crawler had already done all its work and the DB was already initialized and filled.
		
		ConcreteCrawler crawler = ConcreteCrawler.getInstance();
		crawler.crawl();
		
		Scanner scanner = new Scanner(System.in);
		
		String URL = null;
		String targetDateString = null;
		
		//this is the actual console program. when user enters 'exit', the entire program exits.
		while(true)
		{	
			System.out.println("Enter your URL in the form 'www.exampleURL.com'. To exit at any time, enter 'exit'.");
			System.out.println("IMPORTANT!!: For this program, the crawler has only crawled through www.google.com and www.tripadvisor.com.");
			System.out.println("For more info, check out the concreteCrawler crawl() implementation.");
			System.out.print(">> ");
			URL = scanner.nextLine();
			if(URL.equals("exit")) break;			
			if(!HTMLFileManager.hasHTML(URL))
			{
				System.out.println("Invalid address. Please try again.");
				continue;
			}

			System.out.println("Enter your target date in the form YYYY-MM-DD. To exit at any time, enter 'exit'.");
			System.out.print(">> ");
			targetDateString = scanner.nextLine();
			if(targetDateString.equals("exit")) break;
			
			LocalDate targetDate = parseStringDate(targetDateString);
			
			if(targetDate == null)
			{
				System.out.println("Invalid date. Please try again.");
				continue;
			}
			
			HTMLFile targetHTML = HTMLFileManager.getClosestVersion(URL, targetDate);
			printHTMLContent(targetHTML);
		}
		
	}
	
	//parses a string of form YYYY/MM/DD to a local date.
	public static LocalDate parseStringDate(String s)
	{
		String pattern = "yyyy-MM-dd";
		LocalDate result = null;
		try{
			result = LocalDate.parse(s, DateTimeFormat.forPattern(pattern));
		}
		catch(Exception e)
		{
			System.out.println("Your date was formatted incorrectly.");
		}
		
		//if(result != null) System.out.println("Input parsed as: " + result.toString());
		
		return result;
	}
	
	//prints file contents to console.
	public static void printHTMLContent(HTMLFile hf)
	{
		System.out.println("Displaying the contents of " + hf.URL + " with the version date " + hf.dateToString() + ": ");
		System.out.println();
		
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(hf.HTMLCode));

			String line;
			while(true)
			{
				line = in.readLine();
				if(line == null) break;
				System.out.println(line);
			} 
			
			in.close();
			
		}	
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Something went wrong! Sorry!");
			return;
		}	
		
		System.out.println();
		
	}
}
