import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class sku {

	public static void main(String[] args) {
		
		
		try {
			Document doc = Jsoup.connect("https://www.desigual.com/en_GB/women/clothing/new-this-week/").userAgent("mozilla/17.0").get();
			Elements temp = doc.select("div.tile-body");
			// this loop prints Product Name and Product Price from PLP (all in order)
			int i = 0;
			for (Element movieList : temp) {
				int holder = i + 1;
				System.out.println(holder + " " + movieList.getElementsByTag("a").first().text() + "\n" +"Price: " + movieList.getElementsByTag("span").first().text() +"\n");
				i++;
			}
			
			// this loop stores each Product Name in an array that will then be used to put the values in the csv. The name comes from the PLP.			
			String array[] = new String [i];
			int j = 0;
			for (Element movieList : temp) {
				array [j] = movieList.getElementsByTag("a").first().text();
				System.out.println(array[j] + "\n");
				i++;
			}
			
			// this is the arrays for each respective element that will be used to store in the csv
			String sku[] = new String [i];
			String price[] = new String [i];
			String inner[] = new String [i];
			String outer[] = new String [i];
			String shoe[] = new String [i];
			String padding[] = new String [i];
			String longDescription[] = new String [i];
		
			// these variables are the ones that will be used for the for loops that will allows the code to store the elements in their array
			int a = 0;
			int b = 0;
			int c = 0;
			int d = 0;
			int f = 0;
			int g = 0;
			int h = 0;
			
			Elements links = doc.select("a.link");
	        for (Element link : links) {
	        	
	            try {
	            	//this gives sku values and stores it in the array. the values are extracted from the PDP
	    			Document doc1 = Jsoup.connect(link.attr("abs:href")).userAgent("mozilla/17.0").get();
	    			Elements temp1 = doc1.select("span.product-id");
	    			for (Element movieList : temp1) {
	    				sku[a] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(sku[a]);
	    				a++;
	    			}
	    			
	    			//this gives price values and stores it in the array. the values are extracted from the PDP
	    			Document doc3 = Jsoup.connect(link.attr("abs:href")).userAgent("mozilla/17.0").get();
	    			Elements temp7 = doc3.select("span.sales");
	    			for (Element movieList : temp7) {
	    				price[b] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(price[b]);
	    				b++;
	    			}

	    			}
	    			
	    		
	    			
	    			catch (IOException e) {
	    				e.printStackTrace();
	    			}
	    			
	    			
	    		
	    // this gives us the description bullet points
	    		
	    		try {
	    			
	    			Document doc2 = Jsoup.connect(link.attr("abs:href")).userAgent("mozilla/17.0").get();
	    			
	            	//this gives inner description values and stores it in the array. the values are extracted from the PDP
	    			Elements temp5 = doc2.select("p.inside-composition");
	    			for (Element movieList : temp5) {
	    				inner[c] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(movieList.getElementsByTag("strong").first().text() + " " + inner[c]);
	    				c++;
	    			}
	    			
	            	//this gives outer description values and stores it in the array. the values are extracted from the PDP
	    			Elements temp6 = doc2.select("p.outside-composition");
	    			for (Element movieList : temp6) {
	    				outer[d] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(movieList.getElementsByTag("strong").first().text() + " " + outer[d]);
	    				d++;
	    			}
	    			
	            	//this gives shoe sole values and stores it in the array. the values are extracted from the PDP
	    			Elements temp2 = doc2.select("p.shoe.sole.material");
	    			for (Element movieList : temp2) {
	    				shoe[f] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(movieList.getElementsByTag("strong").first().text() + " " + shoe[f]);
	    				f++;
	    			}
	    			
	            	//this gives the padding material values and stores it in the array. the values are extracted from the PDP
	    			Elements temp3 = doc2.select("p.padding-material");
	    			for (Element movieList : temp3) {
	    				padding[g] = movieList.getElementsByTag("span").first().text();
	    				System.out.println(movieList.getElementsByTag("strong").first().text() + " " + padding[g]);
	    				g++;
	    			}
	    			
	            	//this gives long description values and stores it in the array or stores null if theres no value. the values are extracted from the PDP
	    			Elements temp4 = doc2.select("div.mb-3.long-description");
	    			for (Element movieList : temp4) {
	    				
	    				if (movieList.getElementsByTag("p").text().equals("")) {
	    					 longDescription[h] = "";
	    					 continue;				
	    					 }
	    				else
	    				longDescription[h] = movieList.getElementsByTag("p").first().text();
	    				System.out.println(longDescription[h]);
	    				//System.out.println(movieList.ownText() + movieList.getElementsByTag("p").first().text() + "\n");
	    				h++;
	    			}
	    			
	            	// this prints the image links that you then copy and paste into each respective line of the csv, 
	    			// since they will be printed in the console
	    			Elements media = doc2.select("[src]");		        
	  		        for (Element src : media) {
	  		            if (src.tagName().equals("img")) {
	  		                print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
	  		                        trim(src.attr("alt"), 20));
	  		        }else {
	  		        	
	  		        }
	  		            }
	    			
	    			}
	    			
	    		
	    			
	    			catch (IOException e) {
	    				e.printStackTrace();
	    			}
	        }
			
			}
			
		
			
			catch (IOException e) {
				e.printStackTrace();
			}
					
		}
	
	 private static void print(String msg, Object... args) {
	        System.out.println(String.format(msg, args));
	    }

	    private static String trim(String s, int width) {
	        if (s.length() > width) {
	            return s.substring(0, width-1) + ".";
	        } else {
	            return s;
	        }
	    }
	
	
		
		
}
