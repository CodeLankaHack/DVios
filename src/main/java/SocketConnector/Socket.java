package SocketConnector;

import java.io.*;
import java.net.ServerSocket;

/**
 * Created by anuradhawick on 5/12/16.
 */
public class Socket {
    private static Parser p;
    private static Engine engine;

    public static void main(String[] args) {
        p = new Parser();
        engine = new Engine();
        int port = 20222;
        ServerSocket listenSock = null; //the listening server socket
        java.net.Socket sock = null;             //the socket that will actually be used for communication

        try {

            listenSock = new ServerSocket(port);

            while (true) {       //we want the server to run till the end of times

                sock = listenSock.accept();             //will block until connection recieved

                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                String line = "";
                while ((line = br.readLine()) != null) {
                    System.out.println("Received from PHP: " + line);
                    bw.write(getResponse(line) + "\n");
                    bw.flush();
                }

                //Closing streams and the current socket (not the listening socket!)
                bw.close();
                br.close();
                sock.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    static String getResponse(String s) {
//        Classifier classifier = p.getClassifier(s);
//        return engine.run(classifier.getHashMap());
//    }
}
