package com.votinginfoproject.VotingInformationProject.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.votinginfoproject.VotingInformationProject.R;

/**
 * Created by kathrynkillebrew on 7/21/14.
 */
public class BallotWrapperFragment extends Fragment {
    private static final String TAG = "BallotWrapper";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ballot_wrapper, container, false);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ballot_wrapper, BallotFragment.newInstance());
        transaction.commit();
        return view;
    }
}
