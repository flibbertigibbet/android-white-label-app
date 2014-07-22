package com.votinginfoproject.VotingInformationProject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.activities.VIPTabBarActivity;
import com.votinginfoproject.VotingInformationProject.models.VIPApp;
import com.votinginfoproject.VotingInformationProject.models.VoterInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BallotFragment.OnInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BallotFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BallotFragment extends Fragment {

    private static final String TAG = "BallotFragment";

    VoterInfo voterInfo;
    private OnInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BallotFragment.
     */
    public static BallotFragment newInstance() {
        BallotFragment fragment = new BallotFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public BallotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ballot, container, false);
        final VIPTabBarActivity myActivity = (VIPTabBarActivity)this.getActivity();
        SimpleDateFormat election_date_api_format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat election_date_display_format = new SimpleDateFormat("MMMM d, yyyy");

        // election label
        TextView election_name_label = (TextView)rootView.findViewById(R.id.ballot_election_name);
        TextView election_date_label = (TextView)rootView.findViewById(R.id.ballot_election_date);
        election_name_label.setText(voterInfo.election.name);

        try {
            Date election_date = election_date_api_format.parse(voterInfo.election.electionDay);
            election_date_label.setText(election_date_display_format.format(election_date));
        } catch (ParseException e) {
            Log.e("BallotFragment", "Failed to parse election date " + voterInfo.election.electionDay);
            election_date_label.setText(voterInfo.election.electionDay);
        }

        // populate contest list, using toString override on Contest class
        ArrayList contestInfo = (ArrayList) voterInfo.contests;
        ArrayAdapter adapter = new ArrayAdapter<String>(myActivity, android.R.layout.simple_selectable_list_item, contestInfo);
        ListView contestList = (ListView)rootView.findViewById(R.id.ballot_contests_list);
        contestList.setAdapter(adapter);
        contestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ContestsList", "clicked: " + voterInfo.contests.get(position).office);

                myActivity.showCandiateTab(position);
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnInteractionListener) activity;

            // get election info
            VIPApp app = (VIPApp) getActivity().getApplicationContext();
            voterInfo = app.getVoterInfo();
            Log.d("BallotFragment", "Got election: " + voterInfo.election.name);

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnInteractionListener {
        // TODO: Add methods here as necessary
    }

}
