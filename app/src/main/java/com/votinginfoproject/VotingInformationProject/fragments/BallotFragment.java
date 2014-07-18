package com.votinginfoproject.VotingInformationProject.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.models.VIPApp;
import com.votinginfoproject.VotingInformationProject.models.VoterInfo;

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
        TextView label = (TextView)rootView.findViewById(R.id.section_label);

        // should always have an election to show if we got here, but check anyways
        if (voterInfo != null && voterInfo.election != null) {
            label.setText(voterInfo.election.name);
        } else {
            Log.e("BallotFragment", "No election found to show!");
        }
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnInteractionListener) activity;

            // get voter info
            VIPApp app = (VIPApp) getActivity().getApplicationContext();
            voterInfo = app.getVoterInfo();
            Log.d("BallotFragment", "Got voter info: " + voterInfo.kind);
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
