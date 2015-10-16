package Controller;

public interface IWebCrawler {

//this crawls through the web and puts all HTMLFiles it finds into the file database.
//the ID that differentiates two unique HTMLFiles with the same URL will be the date version!
public void crawl();

//finds all versions of a specific URL. Not sure if necessary.
public void crawlURL(String URL);

}
