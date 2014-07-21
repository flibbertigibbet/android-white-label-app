package com.votinginfoproject.VotingInformationProject.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.models.Contest;

/**
 * Created by kathrynkillebrew on 7/21/14.
 */
public class ContestFragment extends Fragment {
    private static final String CONTEST_NUM = "contest_number";
    private int contestNum;
    Contest contest;
    private OnInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contest_number Index of this contest within the list of contests on VoterInfo object
     * @return A new instance of fragment ContestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContestFragment newInstance(int contest_number) {
        ContestFragment fragment = new ContestFragment();
        Bundle args = new Bundle();
        args.putInt(CONTEST_NUM, contest_number);
        fragment.setArguments(args);
        return fragment;
    }
    public ContestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            contestNum = getArguments().getInt(CONTEST_NUM);
            Log.d("ContestFragment", "Got contest #" + contestNum);

        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ContestFragment", "In onActivityCreated");
        TextView contest_label = (TextView)this.getActivity().findViewById(R.id.contest_title);
        if (contest_label == null) {
            Log.d("ContestFragment", "contest_label is null in onActivityCreated");
        } else {
            Log.d("ContestFragment", "GOT CONTEST LABEL");
            contest_label.setText("CONTEST IS #" + contestNum);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //int gotViews = container.getChildCount();
        //Log.d("ContestFragment", "View container has " + gotViews + " views");

        // TODO: fix back stack
        container.removeAllViewsInLayout();
        // container ID: 2131230723
        View rootView = inflater.inflate(R.layout.fragment_contest, container, false);
        Activity myActivity = this.getActivity();
        //TextView contest_label = (TextView)rootView.findViewById(R.id.contest_title);
        //contest_label.setText("This is contest #" + contestNum);

        return rootView;
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
