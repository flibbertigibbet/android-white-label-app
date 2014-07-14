package com.votinginfoproject.VotingInformationProject;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by kathrynkillebrew on 7/14/14.
 *
 * Generalized class for querying the API.  Instantiation paramters:
 * ctx -> application context
 * ret -> class type of object returned by query
 * cb -> callback function on view to handle receiving the return object
 *
 * Execution parameter:
 * url -> query url
 */
public class CivicInfoApiQuery<T> extends AsyncTask<String, Void, T> {

    public interface CallBackListener {
        public void callback(Object ob);
    }

    private Object returnObject;
    private CallBackListener callbackListener;
    private Context context;
    private Class<T> returnClass;

    public CivicInfoApiQuery (Context ctx, Class ret, CallBackListener cb) {
        System.out.println("Going to instantiate CivicInfoApiQuery...");
        try {
            this.context = ctx;
            this.returnClass = ret;
            this.callbackListener = cb;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected T doInBackground(String... urls) {
        URL url = null;
        returnObject = null;

        try {
            url = new URL(urls[0]);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            InputStream in = myConnection.getInputStream();
            BufferedReader ir = new BufferedReader(new InputStreamReader(in));
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(ir, returnClass);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onPostExecute(T thing) {
        callbackListener.callback(thing);
    }
}
