package com.votinginfoproject.VotingInformationProject;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.votinginfoproject.VotingInformationProject.Election;
import com.votinginfoproject.VotingInformationProject.R;


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

    public void testElectionQuery(View view) {
        testText = (TextView) findViewById(R.id.testTextBox);
        testText.setText("Fetching data...");

        Context myContext = view.getContext();
        String api_key = myContext.getString(R.string.google_api_browser_key);
        String apiUrl = "https://www.googleapis.com/civicinfo/v2/elections?key=" + api_key;

        System.out.println("Creating callback listener...");
        CivicInfoApiQuery.CallBackListener myListener = new electionsCallback();

        System.out.println("Going to query...");
        new CivicInfoApiQuery<ElectionQueryResponse>(myContext, ElectionQueryResponse.class, myListener).execute(apiUrl);

    }

    public void testVoterInfoQuery(View view) {
        testText = (TextView) findViewById(R.id.testTextBox);
        testText.setText("Fetching data...");

        Context myContext = view.getContext();
        String api_key = myContext.getString(R.string.google_api_browser_key);
        String apiUrl = "https://www.googleapis.com/civicinfo/v2/voterinfo?address=2121%20I%20St%20NWWashington,%20DC&electionId=4043&key=" + api_key;

        System.out.println("Creating callback listener...");
        CivicInfoApiQuery.CallBackListener voterInfoListener = new voterInfoCallback();

        System.out.println("Going to query...");
        new CivicInfoApiQuery<VoterInfo>(myContext, VoterInfo.class, voterInfoListener).execute(apiUrl);

    }

    public class electionsCallback implements CivicInfoApiQuery.CallBackListener {
        public void callback(Object obj) {
            System.out.println("Made it to callback!");
            ElectionQueryResponse qryResult = (ElectionQueryResponse) obj;
            String show = "Elections:\n\n";
            for (Election el : qryResult.elections) {
                show += el.id + ": " + el.name + "\n" + el.electionDay + "\n\n";
            }
            System.out.println("Going to show text.");
            testText.setText(show);
        }
    }

    public class voterInfoCallback implements CivicInfoApiQuery.CallBackListener {
        public void callback(Object obj) {
            System.out.println("Made it to callback!");
            VoterInfo qryResult = (VoterInfo) obj;

            System.out.println(qryResult.kind);
            System.out.println(qryResult);
            Election el = qryResult.election;
            String show = "Election:\n" + el.id + ": " + el.name + "\n" + el.electionDay + "\n\n";

            State state = qryResult.state.get(0);
            show += "State: " + state.name + "\n";
            show += "Election admin: " + state.electionAdministrationBody.name + "\n\n";
            show += "Sources:\n" + state.sources.get(0).name;

            System.out.println("Going to show text.");
            testText.setText(show);
        }
    }

}

