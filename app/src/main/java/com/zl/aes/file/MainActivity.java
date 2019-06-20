package com.zl.aes.file;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("read: ", readShaderFromAssets(this, "test.json"));
    }

    public static String readShaderFromAssets(Context context, final String fileName) {

//        final InputStream inputStream = getResources().openRawResource(
//                resourceId);
        final InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(context.getResources().getAssets().open(fileName));;
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String nextLine;
            final StringBuilder body = new StringBuilder();

            try {
                while ((nextLine = bufferedReader.readLine()) != null) {
                    body.append(nextLine);
                    body.append('\n');
                }
            } catch (IOException e) {
                return null;
            }
            //AESUtils.decode是解密方法，BuildConfig.AES_KEY，为gradle中配置的key
            String decrypt = AESUtils.decode(body.toString(), BuildConfig.AES_KEY);
            return decrypt;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }
}
