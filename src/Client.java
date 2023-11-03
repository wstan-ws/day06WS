import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        
        Socket socket = new Socket("localhost", 3000);
        System.out.println("Successfully connected to server");

        Console cons = System.console();

        String line = cons.readLine("Type 'cookie' to get a fortune cookie: ");

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        bw.write(line + "\n");
        bw.flush();

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        line = br.readLine();
        System.out.printf("> Output: %s\n", line);
        
        os.close();
        is.close();

        socket.close();
    }
}