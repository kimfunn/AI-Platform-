package test1.speech;

import java.io.*;
import java.net.*;

public class STT {

	public static void main(String[] args) {
		
		String clientId = "4ah7jwo95w";             // Application Client ID";
	    String clientSecret = "T1xJmAzPrFnuTCISsdWBSfB3vYW0Rclz7VF1zUlQ";     // Application Client Secret";

	     
			DataOutputStream dos=null;
			BufferedReader br=null;
			try {
				String language = "Kor";
				String apiURL="https://naveropenapi.apigw.ntruss.com/recog/v1/stt?lang=" + language;
				URL url=new URL(apiURL);
				HttpURLConnection con=(HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setUseCaches(false);
				con.setDoOutput(true);
				con.setDoInput(true);
				
				con.setRequestProperty("Content-Type", "application/octet-stream");
	            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
	            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
				
	            OutputStream outputStream = con.getOutputStream();
	            
	            File voiceFile = new File("c:\\temp\\sample.wav");
	            FileInputStream inputStream = new FileInputStream(voiceFile);
	            byte[] buffer=new byte[4096];
	            int bytesRead = -1;
	            while( (bytesRead=inputStream.read(buffer) ) != -1) {
	            	outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close(); //sample.wav 같은 음성파일 전송 완료
				
				 br=new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String oneLine="";
				StringBuilder sum=new StringBuilder();
				while(  (oneLine=br.readLine()) != null   ) {
					sum.append(oneLine);
				}
				
				System.out.println(sum.toString());
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if(br != null)br.close();
					if(dos !=null) dos.close();
				}catch(Exception e) {
					
				}
			}

		}

	}





