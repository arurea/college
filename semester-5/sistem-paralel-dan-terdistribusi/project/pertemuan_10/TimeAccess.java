import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class TimeAccess{
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("time.nist.gov", 13);

        StringBuilder time = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(socket.getInputStream(), "ASCII");
        int c = reader.read();
        while (c!=-1){
            time.append((char) c);
            c = reader.read();
        }
        System.out.println(time);

        socket.close();
    }
}