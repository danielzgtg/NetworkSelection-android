package io.github.danielzgtg.networkselection;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NetworkSelectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_selection);

        final Button mAutoButton = (Button) findViewById(R.id.set_automatic_button);
        final Button mManualButton = (Button) findViewById(R.id.set_manual_button);
        final EditText mEdit = (EditText) findViewById(R.id.operatorid);

        if (this.checkPermission(Manifest.permission.MODIFY_PHONE_STATE,
                Process.myPid(), Process.myUid()) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(NetworkSelectionActivity.this,
                    "Please install as system app", Toast.LENGTH_LONG).show();
            this.finish();
            return;
        }

        mAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    tm.setNetworkSelectionModeAutomatic();
                    Toast.makeText(NetworkSelectionActivity.this,
                            "Set cell provider to auto", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(NetworkSelectionActivity.this,
                            "Error while setting cell provider to auto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mManualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String selection = mEdit.getText().toString();
                    boolean result = tm.setNetworkSelectionModeManual(selection, true);
                    Toast.makeText(NetworkSelectionActivity.this,
                            result ?
                            "Set cell provider to manual: " + selection :
                                    "System rejected setting cell provider to manual: " + selection
                            , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

