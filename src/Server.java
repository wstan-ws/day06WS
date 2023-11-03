import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Server {

    public static int PORT = 3000;
    public static void main(String[] args) throws Exception {
        
        System.out.printf("Starting up server with port %d\n", PORT);
        ServerSocket server = new ServerSocket(PORT);

        boolean isLooping = true;

        while (isLooping) {
            
            System.out.println("Waiting for a connection...");
            Socket client = server.accept();

            InputStream is = client.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // if (br.readLine() == null) {
            //     continue;
            // } 

            String command = br.readLine().trim();
            System.out.printf("INPUT RECEIVED: %s\n", command);
            // int number = Integer.parseInt(br.readLine().trim().toLowerCase().split(" ")[1]);
            // CHECK HERE

            FileReader fr = new FileReader("fortunecookie.txt");
            BufferedReader brtxt = new BufferedReader(fr);
            List<String> fortunes = brtxt.lines()
                .collect(Collectors.toList());
            brtxt.close();

            OutputStream os = client.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            if (command.equalsIgnoreCase("cookie")) {
                int index = (int)(Math.random() * fortunes.size());
                bw.write(fortunes.get(index) + "\n");
                bw.flush();
                // if (number > 0 && number <= fortunes.size()) {
                //     List<String> reply = new ArrayList<>();
                //     for (int i = 0; i < number; i++) {
                //         int index = (int)(Math.random() * fortunes.size());
                //         reply.add(fortunes.get(index));
                //     }
                //     for (String fortune : reply) {
                //         bw.write(fortune + "\n");
                //     }
                //     bw.flush();
                // } else if (number <= 0) { 
                //     bw.write("Invalid number. Please enter number greater than 0\n");
                //     bw.flush();
                // } else {
                //     String reply = "";
                //     reply = reply.formatted("Invalid number. Maximum number accepted = %d\n", fortunes.size());
                //     bw.write(reply + "\n");
                //     bw.flush();
                // }
            } else {
                bw.write("Invalid Command. Please enter 'cookie' to get your fortune cookie\n");
                bw.flush();
            }          
        }
    }   
}