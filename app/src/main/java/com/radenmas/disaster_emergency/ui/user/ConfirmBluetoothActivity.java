package com.radenmas.disaster_emergency.ui.user;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.radenmas.disaster_emergency.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;


public class ConfirmBluetoothActivity extends AppCompatActivity {
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    private EditText etTextChat;
    private ImageView imgPicture, image;
    private RelativeLayout btnSendChat;

    private OutputStream outputStream;
    String encodedImg;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    Bitmap bitmap;
    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_confirm_bluetooth);

        initView();

        Intent newint = getIntent();
        address = newint.getStringExtra(PairBluetoothActivity.EXTRA_ADDRESS);

        new ConnectBT().execute();

        onClick();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);

            if (bitmap != null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                //Time taken varies inversely with the quality of the image

                byte[] byteFormat = stream.toByteArray();
                /*Deflater compresser = new Deflater();
                compresser.setInput(byteFormat);
                compresser.finish();
                int compressedDataLength = compresser.deflate(byteFormat);
                Toast.makeText(this, (compressedDataLength/byteFormat.length*100)+"Compressed length "+compressedDataLength+"Original "+ byteFormat.length, Toast.LENGTH_LONG).show();
                compresser.end();
                //This commented code compresses the image
                */

                //base64 encoding is used
                encodedImg = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

//                write();

            } else {
                Toast.makeText(this.getApplicationContext(), "Picture not taken!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void onClick() {
        imgPicture.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        btnSendChat.setOnClickListener(view -> {
            //text
            try {
                String msg = etTextChat.getText().toString();
                byte[] tosend = msg.getBytes();
                OutputStream out = btSocket.getOutputStream();
                out.write(tosend);


            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    //send image ending with stop to denote end of byte string
    private void write() {
        try {
            outputStream = btSocket.getOutputStream();
            outputStream.write((encodedImg + "stop").getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        etTextChat = findViewById(R.id.et_text_chat);
        imgPicture = findViewById(R.id.img_picture);
        btnSendChat = findViewById(R.id.btn_send_chat);

        image = findViewById(R.id.image);
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    @SuppressLint("StaticFieldLeak")
    private class ConnectBT extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ConfirmBluetoothActivity.this, "Connecting...", "Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
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