package testnewapp.everythingforward.com.registrationexample.service;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import testnewapp.everythingforward.com.registrationexample.HttpRequest;
import testnewapp.everythingforward.com.registrationexample.MainActivity;

/**
 * Created by santh on 1/20/2017.
 */

public class LoginIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    private static String POST_URL = "http:/192.168.1.67/PHPAndroid/registration.php";
    BufferedReader reader=null;
    HttpURLConnection connection = null;
    public LoginIntentService()
    {
        super("LoginIntentService");
    }

    public LoginIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        //Using HttpRequest to POST data.


        Bundle extraBundle = intent.getExtras();
        ArrayList<String> arraylist = extraBundle.getStringArrayList("key");
        String uName = arraylist.get(0);
        String uPassword = arraylist.get(1);

        //USING THE FOLLOWING STATEMENTS WILL ALSO GET BACK THE JSON RESPONSE GIVEN.

        HashMap<String,String> data = new HashMap<String,String>();
        data.put("username",uName);
        data.put("password",uPassword);
        String response =  HttpRequest.post(POST_URL).form(data).body();
        System.out.println(response);

        String broadcastString = null;

        try {
            JSONObject mainObject = new JSONObject(response);
            boolean output = mainObject.getBoolean("error");
            if(output==true)
            {
                broadcastString = "User not registered because user exists!";

                System.out.println("User not registered because user exists!");

            }
            else
            {
                broadcastString = "User registered successfully";
                System.out.println("User registered successfully");
            }


            Intent intent1 = new Intent(MainActivity.ACTION);
            intent1.putExtra(Intent.EXTRA_TEXT,broadcastString);
            sendBroadcast(intent1);



        } catch (JSONException e) {
            e.printStackTrace();
        }




        /*HashMap<String,String> data = new HashMap<String,String>();
        data.put("username",uName);
        data.put("password",uPassword);
        boolean value= HttpRequest.post(POST_URL).form(data).created();

        System.out.println(value);*/





    }
}
