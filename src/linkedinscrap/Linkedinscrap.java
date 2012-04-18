/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedinscrap;

/**
 *
 * @author ajith
 */





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.FileReader;
import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class Linkedinscrap {
   // private static final String ENCODE_FORMAT = "UTF-8";
    public static void main(String[] args) throws IOException {
        
      String content;
      
      // Load the input file
      
      BufferedReader in = new BufferedReader(new FileReader("input.txt"));
      
      //Create the output fiel
      
      BufferedWriter out = new BufferedWriter(new FileWriter("outfile.txt"));
      
      
      String str = "";
      out.write(str);
      
      //Read the contents from the input file
      
      while((content = in.readLine())!= null){
            
        
        String url = content;
        //URLDecoder.decode(url, ENCODE_FORMAT);
      
        //Print the url
        
        System.out.println("Fetching %s..." +  url);

        //Creating the connection using jsoup
        
        Document doc = Jsoup.connect(url).get();
       
        //Extrscting the required tags
        
        
        Elements moto = doc.getElementsByClass("text-logo");
        Element type_of_company = doc.getElementById("extra");
        Elements dltag = type_of_company.getElementsByTag("dt");
        Elements ddtag = type_of_company.getElementsByTag("dd");
        Elements adrs = type_of_company.getElementsByClass("adr");
        
        //Storing the description in the output string
        
        str += moto.text();
        
        //Adding the address details of the company fetched in the output string
        
        
        
        for (Element adr : adrs){
              str +=  ", " + adr.getElementsByTag("span").text();
            
        }
    
       
        //Adding the company details in the output string
        
        
        int i = 0;
        
        while (i < 4){
            
            str +=  ", " + ddtag.get(i).text() ;
            i++;
        }
        str += "\n";
       
       
      }
      
      //Printing the result on the screen
      
      System.out.println(str);
      
      //Printing the result into output file
      out.append(str);
      
      
      //Closing the BufferedWriter descriptor
      
      out.close();
     
    }
}
        