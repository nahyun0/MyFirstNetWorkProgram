
import java.io.*;
import java.net.*;
import java.util.*;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Properties;
public class CalcClient_HW1 {
public static void main(String[] args) {
	//데이터 송수신을 위한 input/output 스트림 생성 
	BufferedReader in = null;
	BufferedWriter out = null;
	Socket socket = null;
	Scanner scanner = new Scanner(System.in);
	Properties properties = new Properties();
	FileInputStream input = null;
	try {
		input = new FileInputStream("server_info.dat"); //use .dat file
		properties.load(input);
		String serverAddress = properties.getProperty("ServerAddress");
		String serverPortStr = properties.getProperty("ServerPort");
		Integer serverPort = Integer.parseInt(serverPortStr);
		socket = new Socket(serverAddress,serverPort);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		while (true) {
			System.out.print("Enter operation + two numbers (ex, ADD 10 20):"); // 프롬프트
			 //outputstream 통해서 보낼 데이터 송신 (클라이언트 ->서버 ) 
			String outputMessage = scanner.nextLine(); // read
			if (outputMessage.equalsIgnoreCase("bye")) {
				out.write(outputMessage + "\n"); 
				out.flush();
				break; // bye -> end 
			}
			out.write(outputMessage + "\n"); //out is BufferedWriter object
// Send the expression string read from the keyboard
			out.flush();
			 //inputStream 통해서 데이터 수신(서버->클라이언트) 
			String inputMessage = in.readLine(); // Receive the calculation result from the server
			System.out.println(inputMessage);
		}
	} catch (IOException e) {
		System.out.println(e.getMessage());
	} finally {
		try {
			scanner.close();
			if (socket != null)
				socket.close(); // close client socket
		} catch (IOException e) {
			System.out.println("Error.");
		}
	}
	}
}

    
