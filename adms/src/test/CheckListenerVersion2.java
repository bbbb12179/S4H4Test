package test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;


public class CheckListenerVersion2 {

	public CheckListenerVersion2(){
		checkListenerVersion();
	}
	
	public void checkListenerVersion(){
		String listenerVersion = null;
		String httpRequest = null;
		try{
			URL url = new URL("http://192.168.212.101:12347/listenerVersion");//改成自己的IP
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);				
			//httpURLConnection.setReadTimeout(30000);												
			httpURLConnection.setRequestProperty("Content-type", "text/xml;charset=UTF-8");
			
			//get response
			int responseCode = httpURLConnection.getResponseCode();//確實連線過去，並且取得回應碼
			Thread.sleep(1000);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));			
			StringBuffer stringBuffer = new StringBuffer();
			
			while(bufferedReader.ready()){
				stringBuffer.append(bufferedReader.readLine());
			}
			bufferedReader.close();
			httpRequest = stringBuffer.toString();
			listenerVersion = httpRequest.substring(httpRequest.indexOf("Version") + 8 , httpRequest.indexOf("Version") + 18);
			System.out.println("ListenerVersion: " + listenerVersion);
			
			Writer wt = new FileWriter("C:\\ListenerVersion.txt");
			PrintWriter pw = new PrintWriter(wt); 
        	pw.println("ListenerVersion: " + listenerVersion);
        	pw.flush();
        	pw.close();
	
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
