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
            @SuppressWarnings("resource")
			ServerSocket socket = new ServerSocket(7789);
            String pathfile = "C:/Users/HVV/Downloads/UnwdmiGenerator21-full/db/";
            Thread t1 = new Thread();
            t1.start();
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
		        			int i = -2;
		        	        while ((inputLine = in.readLine()) != null) {        	
		        	        	inputLine = inputLine.trim();
		        	        	String output = "";
		        	        	switch (i%16) {
		        	            case 1:  
		        	            	inputLine = inputLine.replace("<STN>", "");
		        	            	inputLine = inputLine.replace("</STN>", "");
		        	            	station = inputLine;
		        	            break;
		        	            case 2:
		        	            	output = "Date: " + inputLine.substring(6,16) + " ";
		        	            	fileWriter(output, pathfile +station+ ".txt");
		        	            	
		        	        	break;
		        	            case 3:
		        	            	output = "Time: " + inputLine.substring(6,14) + " ";
		        	            	fileWriter(output, pathfile +station+ ".txt");
		        	            break;
		        	            case 6:
		        	            	inputLine = inputLine.replace("<STP>", "");
		        	            	inputLine = inputLine.replace("</STP>", "");
		        	            	output = "Air: " + inputLine + " ";
		        	            	fileWriter(output, pathfile +station+ ".txt");
		        	            break;
		        	            case 10:
		        	            	inputLine = inputLine.replace("<PRCP>", "");
		        	            	inputLine = inputLine.replace("</PRCP>", "");
		        	    			output = "Rain: " + inputLine + "\r\n";
		        	    			fileWriter(output, pathfile +station+ ".txt");
		        	            break;
		        	        	}
		        	        
		        	        	i++;
		        	        	if(i == 161) {
		        	        		i = -2;
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
}