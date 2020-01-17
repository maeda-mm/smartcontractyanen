package com.cool.maedanoma.sampleether;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private static final String ETHER_URL = "http://127.0.0.1:8545";
    private static final String TAG = "EthApp";
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this::onClick);
    }

    private void onClick(View v) {
        mExecutor.execute(this::run);
    }

    private void run() {
        Web3ClientVersion response = send();
        if (response == null) {
            return;
        }
        Log.i("EthApp", "response = " + response.getResult());
    }

    private Web3ClientVersion send() {
        Web3j web3j = Web3j.build(new HttpService(ETHER_URL));
        try {
            return web3j.web3ClientVersion().send();
        } catch (IOException e) {
            Log.e(TAG, "exception occurred", e);
        }
        return null;
    }
}
