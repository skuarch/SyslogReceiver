package syslogreceiver;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author skuarch
 */
public class Main {

    ServerSocket serverSocket = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {

        try {

            serverSocket = new ServerSocket(514);

            while (true) {

                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        Socket socket = null;
                        ObjectInputStream inputStream = null;
                        String in = null;

                        try {
                            socket = serverSocket.accept();

                            inputStream = new ObjectInputStream(socket.getInputStream());

                            while ((in = inputStream.readUTF()) != null) {
                                System.out.println(in);
                            }

                            inputStream.close();
                            socket.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
