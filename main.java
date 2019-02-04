import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class client {
    public static void main(String args[]){
        try {
            ServerSocket socket = new ServerSocket(7789);
            
            Thread t1 = new Thread();
            t1.start();
            Thread thread1;
            //Socket sock;
            System.out.println("Werkt");           
            while(true){
                final Socket sock = socket.accept();
                new Thread() {
                	public void run() {
		                try {
							System.out.println(sock.getInputStream());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
                		String station = "";
		                try {
		        			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		        			String inputLine;
		        			
		        	        while ((inputLine = in.readLine()) != null) {
		        				inputLine = inputLine.trim();       				      				
		        				if (findStation(inputLine) != "") 
		        					station = findStation(inputLine);
		        				inputLine = parser(inputLine);
		        				if (inputLine != "") {
		        					fileWriter(inputLine, "C:/Users/Sebas/eclipse-workspace/Project 2.2/src/db/" +station+ ".txt");
		        					//System.out.println(inputLine);
		        				}
		        				
		        	        }
		                } catch (IOException e) {
		        			e.printStackTrace();
		        		}
                }}.start();
           }
        } catch (Exception e) {
            System.out.println("Werkt niet");
        }
    }
    
    public static void fileWriter(String var1, String var2) {
    	BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(var2,true));
			writer.write(var1);
	        writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public static String findStation(String var1) {
    	String output = "";
    	String temp = "";
    	if(var1.startsWith("<STN>")) {
			temp = var1.replace("<STN>", "");
			output = temp.replace("</STN>", "");
    	}
    	return output;
    }
    
    public static String parser(String var1) {
    	String output = "";
    	String temp = "";
    	if(var1.startsWith("<DATE>")) {
    		temp = var1.replace("<DATE>", "");
			output = temp.replace("</DATE>", "");
			output = "Date: " + output + " ";
    	}
    	else if(var1.startsWith("<TIME>")){
    		temp = var1.replace("<TIME>", "");
			output = temp.replace("</TIME>", "");
			output = "Time: " + output + " ";
    	}
    	else if(var1.startsWith("<STP>")){
    		temp = var1.replace("<STP>", "");
			output = temp.replace("</STP>", "");
			output = "Air: " + output + " ";
    	}
    	else if(var1.startsWith("<PRCP>")){
    		temp = var1.replace("<PRCP>", "");
			output = temp.replace("</PRCP>", "");
			output = "Rain: " + output + " ";
    	}
    	return output;
    }
}