package com.logui.arghasen.socketapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public EditText editText_IP,editText_Port,editText_user;
    public String ip;
    public int port = 0;
    public String user;
    public String finall;
    public int option;
    public Socket client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_IP = (EditText) findViewById(R.id.edittext_IP);
        editText_Port = (EditText) findViewById(R.id.edittext_Port);
        editText_user = (EditText) findViewById(R.id.edittext_User);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);

    }
    private void sendSocket(){
        ip = editText_IP.getText().toString();
        port = Integer.valueOf(editText_Port.getText().toString());
        user = editText_user.getText().toString();
        finall = user +","+ String.valueOf(option);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(ip, port);
                    PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                    printWriter.write(finall);
                    printWriter.flush();
                    printWriter.close();
                    client.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                option = 1;
                sendSocket();
                break;
            case R.id.button2:
                option = 2;
                sendSocket();
                break;
            case R.id.button3:
                option = 3;
                sendSocket();
                break;
            case R.id.button4:
                option = 4;
                sendSocket();
                break;
            case R.id.button5:
                option = 5;
                sendSocket();
                break;


        }
    }
}
