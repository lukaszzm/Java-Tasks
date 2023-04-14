import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector implements NetConnection {
    public static final String GREETING = "Human or Program";
    public static final String WHAT_NUMBER = "Here we go! Count how many times a number has appeared: ";
    public static final String EOD = "EOD";
    public static final String QUESTION = "Your answer ??????";
    public static final String GOODBYE = "We are closing the connection. Goodbye!";
    @Override
    public void connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine;
            boolean isCountingInProgress = false;
            String selectedNumber = null;
            int count = 0;

            while ((inputLine = in.readLine()) != null) {

                if (inputLine.contains(GREETING))
                    out.println("program");

                if (inputLine.contains(WHAT_NUMBER)) {
                    selectedNumber = inputLine.replace(WHAT_NUMBER, "");
                    isCountingInProgress = true;
                }

                if (inputLine.contains(EOD))
                    isCountingInProgress = false;

                if (inputLine.contains(QUESTION))
                    out.println(count);

                if (inputLine.contains(GOODBYE)) {
                    out.close();
                    in.close();
                    socket.close();
                    return;
                }

                if (isCountingInProgress && inputLine.equals(selectedNumber)) count++;
            }
            }
        catch(UnknownHostException exc ){
                System.out.println("Host connection error");
            }
        catch(ConnectException exc ){
                System.out.println("Server connection error");
            }
        catch(Exception e ){
                System.out.println("Unknown error");
            }
        }
}
