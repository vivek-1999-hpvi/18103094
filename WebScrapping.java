package webscrapper;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileWriter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.UnsupportedMimeTypeException;
public class WebScrapping {

    /**
     * @param args the command line arguments
     */
    
    private static Document connect(String URL, int NumTry){
        if(NumTry > 3){
            System.out.println("Failure! Tried 3 times to connect to " + URL + ". Moving forward...");
            return null;
        }
        try{
            System.out.print("Trying to connect(" +  NumTry + ") to " + URL + ": ");
            Connection conn = Jsoup.connect(URL);
            Document page = conn.get();
            if(conn.response().statusCode() == 200){
                System.out.println("Status OK");
                if(!conn.response().contentType().contains("text/html")){
                    System.out.println("Oops! not a HTML page. Moving forward...");
                    return null;
                }
                return page;
            }
            else{
                System.out.println("Status FAILED. Retrying...");
                return connect(URL, NumTry+1);
            }
        } catch(IOException e){
            System.out.println("Error: " + e.getMessage() + ". Moving forward...");
            return null;
        }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Queue<String> URLQueue = new LinkedList<>();
        HashSet<String> visitedURL = new HashSet<>();
        HashSet<String> uniqueURLs = new HashSet<>();
        
        FileWriter anchorFile = new FileWriter("anchor.csv");
        anchorFile.write("\"URL\",\"text\"\n");

        FileWriter tagsFile = new FileWriter("tags.csv");
        tagsFile.write("\"tag\",\"text\"\n");
        
        FileWriter nonHTMLFiles = new FileWriter("nonHTML.csv");
        nonHTMLFiles.write("\"URL\",\"text\"\n");
        
        System.out.print("Enter base URL: ");
        String baseURL = sc.nextLine();
        
        System.out.println("Do you want to run focused search?(y/n)");
        boolean focused = sc.nextLine().equalsIgnoreCase("y");
        
        String keyword = "";
        if(focused){
            System.out.println("Enter keyword for focused search");
            keyword = sc.nextLine();
        }
       
        if(baseURL.endsWith("/"))
            baseURL = baseURL.substring(0, baseURL.length()-1);
        
        URLQueue.add(baseURL);
        uniqueURLs.add(baseURL);
        while(!URLQueue.isEmpty()){
            String URL = URLQueue.poll();
            
            if(URL.endsWith("/"))
                URL = URL.substring(0, URL.length()-1);
            
            if(visitedURL.contains(URL))
                continue;
                
            Document page = connect(URL, 1);
            if(page == null)
                continue;

            for(Element element: page.getAllElements()){
                String tag = element.tagName();
                if(!tag.equalsIgnoreCase("div")) {
                    String text = element.text();
                    if(tag.equalsIgnoreCase("a")){
                        String nextURL = element.attr("href");
                        if(nextURL.startsWith("/"))
                            nextURL = baseURL + nextURL;
                        if(nextURL.endsWith("/"))
                            nextURL = nextURL.substring(0, nextURL.length()-1);
                        if (!nextURL.startsWith(baseURL) || uniqueURLs.contains(nextURL))
                            continue;
                        if(focused && (!nextURL.toLowerCase().contains(keyword) && !text.toLowerCase().contains(keyword)))
                            continue;
                        
                        try{
                            Jsoup.connect(nextURL).execute();
                        }catch(UnsupportedMimeTypeException ume){
                            System.out.println(ume.getMessage());
                            nonHTMLFiles.write("\"" + nextURL + "\"" + ",\"" + text + "\"\n");
                            continue;
                        }
                        
                        uniqueURLs.add(nextURL);
                        URLQueue.add(nextURL);
                        anchorFile.write("\"" + nextURL + "\"" + ",\"" + text + "\"\n");
                    } else {
                        tagsFile.write("\"" + tag + "\"" + ",\"" + text + "\"\n");
                    }
                }  
            }

            visitedURL.add(URL);
           
        }
        
        tagsFile.close();
        anchorFile.close();
        nonHTMLFiles.close();
    }
    
}
