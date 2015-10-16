package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.joda.time.LocalDate;

import Model.HTMLFile;
import Model.HTMLFileDatabase;

//is a singleton.
public class ConcreteCrawler implements IWebCrawler {
	
	private static ConcreteCrawler instance = null;
	
	public static ConcreteCrawler getInstance()
	{
		if(instance == null) instance = new ConcreteCrawler();
		
		return instance;
	}
	
	private ConcreteCrawler()
	{
		
	}

	//this is going to be a dummy "crawl" implementation. Here the crawler only crawls to 2 different sites.
	@Override
	public void crawl() {
		
		System.out.println("Crawler is crawling and setting up the database...");
		
		//"crawl" to google and get all versions.

		LocalDate one = new LocalDate(1992, 5, 12); 
		LocalDate two = new LocalDate(2000, 1, 20); 
		LocalDate three = new LocalDate(2014, 10, 27); 
		
		String google = "www.google.com";
		
		File HTMLCodeGoogleOne = createFileWithHTML("google.com(1).html", "*HTML Content for google.com version 1*");
		File HTMLCodeGoogleTwo = createFileWithHTML("google.com(2).html", "*HTML Content for google.com version 2*");
		File HTMLCodeGoogleThree = createFileWithHTML("google.com(3).html", "*HTML Content for google.com version 3*");


		HTMLFile fileOne = new HTMLFile(google, one, HTMLCodeGoogleOne);
		HTMLFile fileTwo = new HTMLFile(google, two, HTMLCodeGoogleTwo);
		HTMLFile fileThree = new HTMLFile(google, three, HTMLCodeGoogleThree);
		
		HTMLFileDatabase.put(google, fileOne);
		HTMLFileDatabase.put(google, fileTwo);
		HTMLFileDatabase.put(google, fileThree);
		
		
		//"crawl" to tripadvisor and get all versions.
		String tripadvisor = "www.tripadvisor.com";
		

		LocalDate four = new LocalDate(1980, 1, 12); 
		LocalDate five = new LocalDate(1999, 9, 20); 
		LocalDate six = new LocalDate(2009, 11, 17); 
		LocalDate seven = new LocalDate(2015, 3, 23); 

		File HTMLCodeTAOne = createFileWithHTML("tripadvisor.com(1).html", "*HTML Content for tripadvisor.com version 1*");
		File HTMLCodeTATwo = createFileWithHTML("tripadvisor.com(2).html", "*HTML Content for tripadvisor.com version 2*");
		File HTMLCodeTAThree = createFileWithHTML("tripadvisor.com(3).html", "*HTML Content for tripadvisor.com version 3*");
		File HTMLCodeTAFour = createFileWithHTML("tripadvisor.com(3).html", "*HTML Content for tripadvisor.com version 4*");

		
		HTMLFile tripAdvisorFileOne = new HTMLFile(tripadvisor, four, HTMLCodeTAOne);
		HTMLFile tripAdvisorFileTwo = new HTMLFile(tripadvisor, five, HTMLCodeTATwo);
		HTMLFile tripAdvisorFileThree = new HTMLFile(tripadvisor, six, HTMLCodeTAThree);
		HTMLFile tripAdvisorFileFour = new HTMLFile(tripadvisor, seven, HTMLCodeTAFour);

		
		HTMLFileDatabase.put(tripadvisor, tripAdvisorFileOne);
		HTMLFileDatabase.put(tripadvisor, tripAdvisorFileTwo);
		HTMLFileDatabase.put(tripadvisor, tripAdvisorFileThree);
		HTMLFileDatabase.put(tripadvisor, tripAdvisorFileFour);

	}

	@Override
	public void crawlURL(String URL) {
		// TODO Auto-generated method stub
		
	}
	
	private static File createFileWithHTML(String fileName, String content)
	{
		File resultFile = new File(fileName);
		
		try {
			PrintWriter pw = new PrintWriter(resultFile);
			pw.write(content);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultFile;

	}

}
