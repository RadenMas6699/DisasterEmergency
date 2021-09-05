package com.radenmas.disaster_emergency.ui.user;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.radenmas.disaster_emergency.R;

import java.util.ArrayList;
import java.util.Set;

public class PairBluetoothActivity extends AppCompatActivity {
    ListView PairBluetoothActivity;

    private MaterialButton btnPaired;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";
    private String submain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pair_bluetooth);

        submain = getIntent().getExtras().getString("submain");

        PairBluetoothActivity = findViewById(R.id.listView);
        btnPaired = findViewById(R.id.btn_paired_devices);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();

        if (myBluetooth == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            finish();
        } else if (!myBluetooth.isEnabled()) {
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnBTon, 1);
        }

        pairedDevicesList();

        btnPaired.setOnClickListener(view -> {
            pairedDevicesList();
        });

    }

    private void pairedDevicesList() {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        PairBluetoothActivity.setAdapter(adapter);
        PairBluetoothActivity.setOnItemClickListener(myListClickListener);

    }

    private AdapterView.OnItemClickListener myListClickListener = (av, v, arg2, arg3) -> {
        String info = ((TextView) v).getText().toString();
        String address = info.substring(info.length() - 17);

        switch (submain) {
            case "confirm":
                Intent i = new Intent(PairBluetoothActivity.this, ConfirmBluetoothActivity.class);
                i.putExtra(EXTRA_ADDRESS, address);
                startActivity(i);
                break;
            case "panic":
                Intent subMain = new Intent(PairBluetoothActivity.this, PanicActivity.class);
                subMain.putExtra(EXTRA_ADDRESS, address);
                startActivity(subMain);
                break;
        }

    };
}