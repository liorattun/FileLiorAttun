package com.example.lioratton.fileliorattun;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.et);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void press1(View view) {
        String in = et.getText().toString();
        if (in.equals("")) {
            Toast.makeText(this, "אנא הכנס קלט", Toast.LENGTH_SHORT).show();
        } else {
            FileOutputStream fos = null;
            try {
                fos = openFileOutput("input.txt", Context.MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            try {
                bw.write(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, "כתיבה לקובץ בוצעה בהצלחה!", Toast.LENGTH_SHORT).show();
        }
    }

    public void press2(View view) {
        InputStream is=null;
        try {
            is=openFileInput("input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader tmp=new InputStreamReader(is);
        BufferedReader reader=new BufferedReader(tmp);
        String st="";
        StringBuffer buffer=new StringBuffer();
        try {
            while ((st=reader.readLine()) != null)
            {
                buffer.append(st+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText(buffer);
    }
}
