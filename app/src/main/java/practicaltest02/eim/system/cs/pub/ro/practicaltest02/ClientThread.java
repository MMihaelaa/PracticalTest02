package practicaltest02.eim.system.cs.pub.ro.practicaltest02;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class ClientThread implements Runnable {

    Socket socket;
    Integer port;
    String word;
    String ipServer;

    ClientThread(Integer port, String word, String ipServer) {
        this.port = port;
        this.word = word;
        this.ipServer = ipServer;
//        Log.e("CLIENT", "Constructor, trimit " + url);
    }

    @Override
    public void run() {
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            InputStream is = socket.getInputStream();
            Log.e("CLIENT", "M-am conectat, trimit " + word);
            out.write(word);
            out.flush();

            String result = "";
            byte[] buffer = new byte[1024];
            int read;
            while((read = is.read(buffer)) != -1) {
//                Log.e("CLIENT", "Am primit " + buffer);
                result += new String(buffer, 0, read);
            }

            Log.e("CLIENT", "Am primit de la server " + result);
//            Toast.makeText(PracticalTest02MainActivity.getActivityContext(),result, Toast.LENGTH_SHORT).show();
//            PracticalTest02MainActivity.bodyView.setText(result);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
