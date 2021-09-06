package com.radenmas.disaster_emergency.ui.user;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.radenmas.disaster_emergency.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class PanicActivity extends AppCompatActivity {
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private EditText etTextChat;
    private ImageView imgPicture, image;
    private RelativeLayout btnPanic;

    private OutputStream outputStream;
    String encodedImg;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Bitmap bitmap;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_user_panic);

        initView();

        Intent newint = getIntent();
        address = newint.getStringExtra(PairBluetoothActivity.EXTRA_ADDRESS);

        new ConnectBT().execute();

        onClick();

    }

    private void onClick() {
        btnPanic.setOnLongClickListener(view -> {
            if (btSocket != null) {
                try {
                    msg("SOS Terkirim");
                    btSocket.getOutputStream().write("#".getBytes());
                } catch (IOException e) {
                    msg("Error");
                }
            }
            return false;
        });
    }


    private void initView() {
        btnPanic = findViewById(R.id.btn_panic);
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(PanicActivity.this, "Connecting...", "Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
//            try {
//                if (btSocket == null || !isBtConnected) {
//                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
//                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
//                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
//                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
//                    btSocket.connect();
//                }
//            } catch (IOException e) {
//                ConnectSuccess = false;
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}