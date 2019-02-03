import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

class client {
    public static void main(String args[]){
        try {
            Semaphore sem = new Semaphore(1);
            ServerSocket socket = new ServerSocket(7789);
            ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
            String station = "";
            Thread t1 = new Thread();
            t1.start();
            Thread thread1;
            Socket sock;
            System.out.println("Werkt");           
            while(true){
                sock = socket.accept();
                thread1 = new Thread();
                thread1.start();
                System.out.println(sock.getInputStream());
                try {
        			BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        			String inputLine;
        			//Parser parser = new Parser();
        			
        	        while ((inputLine = in.readLine()) != null) {
        				inputLine = inputLine.trim();
        				//parser.parseLine(inputLine);
        				System.out.println(inputLine);       				
        				if (findStation(inputLine) != "test3") 
        					station = findStation(inputLine);
        				fileWriter(inputLine, "C:/Users/Sebas/eclipse-workspace/Project 2.2/src/db/" +station+ ".txt");
        	        }
                } catch (IOException e) {
        			e.printStackTrace();
        		} 
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
    	String output = "test3";
    	String temp = "";
    	if(var1.startsWith("<STN>")) {
			temp = var1.replace("<STN>", "");
			output = temp.replace("</STN>", "");
    	}
    	return output;
    }
}
