package Controller;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import Model.HTMLFile;


public class HTMLFileManagerTest {	
	
	//Unfortunately, not enough time to write test cases/suite.
	
	//this test method is clearly incomplete.
	@Test
	public void testgetClosestVersion()
	{
		ConcreteCrawler cc = ConcreteCrawler.getInstance();
		cc.crawl();
		
		//let's test google.
		
		//1. Target Date before first version date.
		LocalDate lowDate = new LocalDate(0001, 01, 01);
		
		LocalDate correctLowDate = new LocalDate(1992, 5, 12);
		HTMLFile closestVersion = HTMLFileManager.getClosestVersion("www.google.com", lowDate);
		assertEquals(correctLowDate, closestVersion.versionDate);
		
		//2. Target Date after today.
		LocalDate futureDate = new LocalDate(2111, 01, 01);
		LocalDate correctNewestDate = new LocalDate(2014, 10, 27);

		HTMLFile newestVersion = HTMLFileManager.getClosestVersion("www.google.com", futureDate);
		assertEquals(correctNewestDate, newestVersion.versionDate);
		
	}
	
	
}
