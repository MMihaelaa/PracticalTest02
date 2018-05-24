package practicaltest02.eim.system.cs.pub.ro.practicaltest02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02MainActivity extends AppCompatActivity {

    // Server widgets
    private EditText serverPortEditText = null;
    private Button connectButton = null;

    // Client widgets
    private EditText clientAddressEditText = null;
    private EditText clientPortEditText = null;
    private EditText clientWordEditText = null;
    private Button getInfoButton = null;
    public static TextView bodyView = null;

    private ServerThread serverThread = null;
    private ClientThread clientThread = null;

    private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
    private class ConnectButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String serverPort = serverPortEditText.getText().toString();
            if (serverPort == null || serverPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Server port should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            serverThread = new ServerThread(Integer.parseInt(serverPort));
//            if (serverThread.getServerSocket() == null) {
//                Log.e("[PracticalTest02]", "[MAIN ACTIVITY] Could not create server thread!");
//                return;
//            }
            new Thread(serverThread).start();



        }

    }

    private getInfoButtonClickListener getInfoButtonClickListener = new getInfoButtonClickListener();
    private class getInfoButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            String clientAddress = clientAddressEditText.getText().toString();
            String clientPort = clientPortEditText.getText().toString();
            String clientWord = clientWordEditText.getText().toString();

            if (clientAddress == null || clientAddress.isEmpty()
                    || clientPort == null || clientPort.isEmpty()) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] Client connection parameters should be filled!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (serverThread == null) {
                Toast.makeText(getApplicationContext(), "[MAIN ACTIVITY] There is no server to connect to!", Toast.LENGTH_SHORT).show();
                return;
            }


            bodyView.setText("");

            clientThread = new ClientThread(Integer.parseInt(clientPort), clientWord,clientAddress );
            new Thread(clientThread).start();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("[Prdactical 02]", "[MAIN ACTIVITY] onCreate() callback method has been invoked");
        setContentView(R.layout.activity_practical_test02_main);
//
        serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(connectButtonClickListener);
//
        clientAddressEditText = (EditText)findViewById(R.id.client_adress_infos);
        clientPortEditText = (EditText)findViewById(R.id.client_port_edit_text);
        clientWordEditText = (EditText)findViewById(R.id.word);
        getInfoButton = (Button)findViewById(R.id.get_infos);
        getInfoButton.setOnClickListener(getInfoButtonClickListener);
        bodyView = (TextView)findViewById(R.id.body_view);
    }
//
//    @Override
//    protected void onDestroy() {
//        Log.i(Constants.TAG, "[MAIN ACTIVITY] onDestroy() callback method has been invoked");
//        if (serverThread != null) {
//            serverThread.stopThread();
//        }
//        super.onDestroy();
//    }

    public static Context getActivityContext(){
        return getActivityContext();
    }
}
