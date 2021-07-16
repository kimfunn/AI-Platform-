package my.jes.web.util;

import java.io.*;
import java.net.*;

public class MyGetRequestor {

	public static String request(String urlString) {
		HttpURLConnection con=null;
		BufferedReader br=null;
		try {	
           
			URL url=new URL(urlString);
			con=(HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");	
			con.setRequestProperty("X-Naver-Client-Id", "J0DcPAj0yEY1zM3QcYzU");
			con.setRequestProperty("X-Naver-Client-Secret", "DNxQ7jyUEP");			
		
			br=new BufferedReader(new InputStreamReader(con.getInputStream()));
			String oneLine,sum="";
			while((oneLine=br.readLine()) != null) {
				sum +=oneLine; 
			}	
			return sum;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		} finally {
			if(br !=null )
				try {
					br.close();
				} catch (IOException e) {					
				}
		}

	}

}
