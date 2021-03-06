package com.zcc.nativecode.demo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String SOURCE_FILE = "/storage/emulated/0/RecForge/11.wav";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String DESTINATION = Environment.getExternalStorageDirectory() + "/result.wav";
        // Example of a call to a native method
        Log.i("zcc", DESTINATION);
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        File f = new File(SOURCE_FILE);
        Log.i("zcc", f.exists() + "");

        final TextView tvv = (TextView) findViewById(R.id.change);
        tvv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvv.setText(processFile(SOURCE_FILE, DESTINATION) + "");
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native int processFile(String input, String output);
}
