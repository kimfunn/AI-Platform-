package my.jes.web.controller;

import java.io.*;
import java.net.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {
	
	@PostMapping("upload.jes")
	public String upload(@RequestParam("myFile") MultipartFile file) {	
		StringBuffer reqStr = new StringBuffer();
		String clientId ="z4gmclajyp"; //애플리케이션 클ㄹ라이언트 아이디
		String clientSecret = "apBN8BjAv6lTru9V5kEr1C7SlSLRa4KbNMmZmT5g";//애플리케이션 클라이언트 시크릿
		String res= "";
		try {
			String filelo="c:\\temp\\smile"+file.getOriginalFilename();
			
			file.transferTo(new File(filelo));
			
			String paramName="image";
			
			String imgFile=filelo;
			File uploadFile=new File(imgFile);
			
			String apiURL = "https://naveropenapi.apigw.ntruss.com/vision/v1/face"; //얼굴인식
			
			URL url = new URL(apiURL);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			
			//multipart request
			
			String boundary="---"+System.currentTimeMillis()+"---";
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+boundary);
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			OutputStream outputStream = con.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"),true);
			String LINE_FEED ="\r\n";
			
			//file 추가
			String fileName=uploadFile.getName();
			writer.append("--"+boundary).append(LINE_FEED);
			writer.append("Content-Disposition: form-data; name=\""+paramName+"\";filename=\""+fileName+"\"").append(LINE_FEED);
			writer.append("Content-Type: "+URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
			writer.append(LINE_FEED);
			writer.flush();
			FileInputStream inputStream = new FileInputStream(uploadFile);
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.flush();
	            inputStream.close();
	            writer.append(LINE_FEED).flush();
	            writer.append("--" + boundary + "--").append(LINE_FEED);
	            writer.close();
	            BufferedReader br = null;
	            int responseCode = con.getResponseCode();
	            if(responseCode==200) { // 정상 호출
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            } else {  // 오류 발생
	                System.out.println("error!!!!!!! responseCode= " + responseCode);
	                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            }
	            String inputLine;
	            if(br != null) {
	                StringBuffer response = new StringBuffer();
	                while ((inputLine = br.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                br.close();
	                String str = response.toString();
	                //System.out.println("스트링:"+str);
	                String[] arr = str.split("\"");
	                String b=  arr[75];
	                String c=  arr[78].substring(1,6);
	                String e= "smile";
	                /*
					 *  String b = arr[2].substring(1,2); double c =
					 * Double.parseDouble(b)*100;
					 */
	               double d =Double.parseDouble(c)*100;
	               if(b != e) {
	            	   res = "당신이 웃어서 더 행복해지길 바랍니다!! \n 당신의 표정은 "  +b +"("+d+"%) 입니다";
	               }else {

	            	   res = "당신의 미소에 행복해 지네요 !!! \n 당신의 표정은 " +b +"("+d+"%) 입니다";  
	               }
	                
	                
	            } else {
	                System.out.println("error !!!");
	            }
	        } catch (Exception e) {
	            System.out.println(e);
	            return "upload fail";
	        }
		
	    return res;
	
	}
}
			
			
			
		