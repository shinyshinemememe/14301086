import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
public class WebServer {  
    public static void main(String[] args) throws IOException {  
        ServerSocket server = new ServerSocket(3333);  
          
        while (true) {  
            Socket socket = server.accept();  
            GetServer(socket);  
        }  
    }  
      
    private static void GetServer(final Socket socket) throws IOException {  
        new Thread(new Runnable() {  
            public void run() {  
                BufferedReader in = null;  
                PrintWriter out = null;  
                try {  
                	InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                    in = new BufferedReader(reader);  
                    out = new PrintWriter(socket.getOutputStream());  
  
                    while (true) {  
                        String str = in.readLine();  
                        for (int i = str.length()-1; i >= 0; i--) {
                        	System.out.print(str.charAt(i));
                        }
                        out.flush();  
                        if (str.equals("end")) {  
                            break;  
                        }  
                    }  
                    socket.close();
                } catch(IOException ex) {  
                    ex.printStackTrace();  
                } finally {  
                    try {  
                        in.close();  
                    } catch (Exception e) {}  
                    try {  
                        out.close();  
                    } catch (Exception e) {}  
                }  
            }  
        }).start();  
    }  
}  