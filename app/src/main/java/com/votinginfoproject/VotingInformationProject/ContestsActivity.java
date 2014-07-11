package com.votinginfoproject.VotingInformationProject;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Timestamp;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ContestsActivity extends Activity {

    TextView testText;

    public ContestsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contests);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contests, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_contests, container, false);
            return rootView;
        }
    }

    public class ElevatorMeta {
        Integer elevators_out;
        String updated;
    }

    public class ElevatorOutageResult {
        String line;
        String station;
        String elevator;
        String message;
        String message_html;
        String alternate_url;
    }

    public class ElevatorOutages {
        public ElevatorMeta meta;
        public List<ElevatorOutageResult> results;
    }

    public void testDoStuff(View view) {
        testText = (TextView) findViewById(R.id.testTextBox);
        testText.setText("Hey, you clicked me!");

        String testUrl = "https://raw.githubusercontent.com/flibbertigibbet/unlockphilly/master/testdata/elevator_outage_json_examples/outage5.json";
        //String testGeoJson = "https://gist.githubusercontent.com/flibbertigibbet/9b2febda5f1aadf6add9/raw/989cfb5f62eec9001d433af3af24c77fa2b741c1/lancaster_ave.json";

        new GetStuff().execute(testUrl);

        /*
        try(Reader reader = InputStreamReader (ContestsActivity.class.getResourceAsStream("location.json", "UTF-8")) {
            Gson gson = new GsonBuilder().create();
            TestThing myThing = gson.fromJson(reader, TestThing.class);
            System.out.println(myThing);
        }
        */
    }

    private class GetStuff extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url = null;
            String line;
            StringBuilder gotStuff = new StringBuilder();
            ElevatorOutages myOutages = null;
            String show = "";

            try {
                url = new URL(urls[0]);
                HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
                InputStream in = myConnection.getInputStream();
                //BufferedReader ir = new BufferedReader(new InputStreamReader(in));
                InputStreamReader ir = new InputStreamReader(in);

                Gson gson = new GsonBuilder().create();
                myOutages = gson.fromJson(ir, ElevatorOutages.class);
                System.out.println(myOutages);

                System.out.println(myOutages.meta.elevators_out);
                System.out.println(myOutages.meta.updated);

                show = myOutages.meta.elevators_out.toString() + " elevators out:\n\n";
                for (ElevatorOutageResult res : myOutages.results) {
                    show += res.elevator + " - " + res.line + "\n" + res.message + "\n\n";
                }

                /*
                while ((line = ir.readLine()) != null) {
                    gotStuff.append(line);
                    System.out.println(line);
                }
                */
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "oops1";
            } catch (IOException e) {
                e.printStackTrace();
                return "oops2";
            }

            return show;
            //return gotStuff.toString();
        }

        protected void onPostExecute(String show) {
            testText.setText(show);
        }
    }

}

