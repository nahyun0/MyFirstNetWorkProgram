/*One client ! 
import java.io.*;
 
import java.net.*;
import java.util.*;

public class CalcServer_HW1 {

    public static String calc(String exp) {
        StringTokenizer st = new StringTokenizer(exp, " ");//split " "  
        if (st.countTokens() != 3)
            return "Error message : Too many arguments";
        String res = "";
        //example form : ADD 10 20 
        String opcode = st.nextToken(); //+ , - , * , /
        int op1 = Integer.parseInt(st.nextToken());
        int op2 = Integer.parseInt(st.nextToken());
        switch (opcode) {
            case "ADD":
                res = Integer.toString(op1 + op2);
                break;
            case "MIN":
                res = Integer.toString(op1 - op2);
                break;
            case "MUL":
                res = Integer.toString(op1 * op2);
                break;
            case "DIV":
            	if(op2 != 0) {
            	res = Integer.toString(op1 / op2);}
            	else {
            		res = "Error message : divided by zero";
            	}
            	break;
            default:
                res = "Error message : please use within (ADD, MIN, MUL, DIV) operation ";
                break;
        }
        return res;
    }

    public static void main(String[] args) {
    	 BufferedReader in = null;
         BufferedWriter out = null;
         ServerSocket listener = null;
         Socket socket = null;
         
         Properties properties = new Properties();//Properties(String,String)
         FileInputStream input = null;

         try {
        	 input = new FileInputStream("server_info.dat");//use .dat file, get infromation serverport number
             /*
             ex_wirte server_info.dat ---> 
             ServerAddress=localhost
        	 ServerPort=8999*/
        	/* properties.load(input);

             String serverPortStr = properties.getProperty("ServerPort");

             Integer serverPort = Integer.parseInt(serverPortStr);
             
             listener = new ServerSocket(serverPort); // create server socket
             System.out.println("Waiting.....");
             //step) bind-> listen-> accept
             socket = listener.accept(); 
             System.out.println("Connect");
             in = new BufferedReader(
            		 new InputStreamReader(socket.getInputStream()));
             out = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                String inputMessage = in.readLine();
                if (inputMessage.equalsIgnoreCase("bye")) {
                    System.out.println("End of client");
                    break; // "bye" then end
                }
                System.out.println(inputMessage); 
                String res = calc(inputMessage); // calculate
                out.write(res + "\n"); 
                out.flush();
               
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (socket != null)
                    socket.close(); // socket end 
                if (listener != null)
                    listener.close(); // server socket end 
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }
}*/

//multiple client at a time 
//Treadpool 사용 -> 여러 작업으로 인한 tread의 폭증을 막기 위해 사용 , 들어오는 작업들을 하나씩 맡아 처리 
//작동 원리 ) 작업 처리에 사용 되는 스레드를 개수 지정 -> 사용자로부터의 요청을 작업 큐에 넣은 후 처리
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CalcServer_HW1 {

	public static String calc(String exp) {
		StringTokenizer st = new StringTokenizer(exp, " ");//split " "  
        if (st.countTokens() != 3)
            return "Error message : Too many arguments";
        String res = "";
        //example form : ADD 10 20 
        String opcode = st.nextToken(); //+ , - , * , /
        int op1 = Integer.parseInt(st.nextToken());
        int op2 = Integer.parseInt(st.nextToken());
        switch (opcode) {
            case "ADD":
                res = Integer.toString(op1 + op2);
                break;
            case "MIN":
                res = Integer.toString(op1 - op2);
                break;
            case "MUL":
                res = Integer.toString(op1 * op2);
                break;
            case "DIV":
            	if(op2 != 0) {
            	res = Integer.toString(op1 / op2);}
            	else {
            		res = "Error message : divided by zero";
            	}
            	break;
            default:
                res = "Error message : please use within (ADD, MIN, MUL, DIV) operation ";
                break;
        }
        return res;
    
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // create max 10 threadpool

        Properties properties = new Properties();
        FileInputStream input = null;

        try {
            input = new FileInputStream("server_info.dat");
            /*
            ex_wirte server_info.dat ---> 
            ServerAddress=localhost
       	 ServerPort=8999*/
       	
            properties.load(input);
            String serverPortStr = properties.getProperty("ServerPort");
            Integer serverPort = Integer.parseInt(serverPortStr);
          
            ServerSocket listener = new ServerSocket(serverPort);
            System.out.println("Waiting...");
            
          //step) bind-> listen-> accept
            while (true) {
                Socket socket = listener.accept();
                System.out.println("New client connect.");

                // Define tasks to process client requests as Runnable
                Runnable clientTask = new ClientTask(socket);

                // Run Runnable in thread pool
                executor.execute(clientTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // End treadpool
        }
    }
}

class ClientTask implements Runnable {
    private Socket clientSocket;

    public ClientTask(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
    	 //데이터 송수신을 위한 input, output 스트림 생성 
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            while (true) {
            	//input 스트리 사용 데이터 수신 (클라이언트-> 서버)
                String inputMessage = in.readLine();
                if (inputMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected.");
                    break; // "bye" then end
                }
                System.out.println(inputMessage);
              //output 스트림 사용해서 데이터 송신 (서버->클라이언트)
                String res = CalcServer_HW1.calc(inputMessage); // calculate
                out.write(res + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
