package com.votinginfoproject.VotingInformationProject.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.models.Candidate;
import com.votinginfoproject.VotingInformationProject.models.Contest;
import com.votinginfoproject.VotingInformationProject.models.VIPApp;

import java.util.ArrayList;


public class ContestFragment extends Fragment {
    private static final String CONTEST_NUM = "contest_number";
    private int contestNum;
    Contest contest;
    private OnInteractionListener mListener;
    private ViewGroup mContainer;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param contest_number Index of this contest within the list of contests on VoterInfo object
     * @return A new instance of fragment ContestFragment.
     */
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
        setContents();
    }

    /** Helper function to populate the view labels.
    *
    */
    private void setContents() {
        Activity myActivity = getActivity();
        TextView title = (TextView)myActivity.findViewById(R.id.contest_title);
        TextView subtitle = (TextView)myActivity.findViewById(R.id.contest_subtitle);
        TextView contestType = (TextView)myActivity.findViewById(R.id.contest_type);
        TextView office = (TextView)myActivity.findViewById(R.id.contest_office);
        TextView numberElected = (TextView)myActivity.findViewById(R.id.contest_number_elected);
        TextView numberVotingFor = (TextView)myActivity.findViewById(R.id.contest_number_voting_for);
        TextView ballotPlacement = (TextView)myActivity.findViewById(R.id.contest_ballot_placement);


        try {
            VIPApp app = (VIPApp) getActivity().getApplicationContext();
            contest = app.getVoterInfo().contests.get(contestNum);
            Log.d("ContestFragment", "Got contest for office: " + contest.office);

            title.setText(contest.referendumTitle);
            subtitle.setText(contest.referendumSubtitle);
            contestType.setText(contest.type);
            office.setText(contest.office);

            if (contest.numberElected != null) {
                numberElected.setText(contest.numberElected.toString());
            }
            if (contest.numberVotingFor != null) {
                numberVotingFor.setText(contest.numberVotingFor.toString());
            }
            if (contest.ballotPlacement != null) {
                ballotPlacement.setText(contest.ballotPlacement.toString());
            }

            // populate candidate list, using toString override on Candidate class
            ArrayList candidateInfo = (ArrayList) contest.candidates;
            ArrayAdapter adapter = new ArrayAdapter<String>(myActivity, android.R.layout.simple_selectable_list_item, candidateInfo);
            ListView candidateList = (ListView)myActivity.findViewById(R.id.contest_candidate_list);
            candidateList.setAdapter(adapter);
            candidateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // TODO: launch candidate detail view here
                    Candidate candidate = contest.candidates.get(position);
                    Log.d("CandidateList", "clicked: " + contest.candidates.get(position) + " " + candidate.name);
                }
            });


        } catch (Exception ex) {
            Log.e("ContestFragment", "Failed to get contest info!");
            ex.printStackTrace();
        }
    }

    /**
     * Hide ballot fragment components here, then show them again when user goes back.
     * Doing this because replacing a fragment within a TabBar doesn't remove the old fragment.
     * Ballot layout has its contents wrapped in an inner RelativeLayout so there is only one
     * child view to hide/show here.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContainer = container;
        Log.d("ContestFragment:onCreateView", "Hiding ballot container's view");
        container.getChildAt(0).setVisibility(View.INVISIBLE);

        View rootView = inflater.inflate(R.layout.fragment_contest, container, false);
        return rootView;
    }

    @Override
    public void onDetach() {
        // Show ballot fragment components again when user goes back
        Log.d("ContestFragment:onDetach", "Showing ballot container's view again");
        mContainer.getChildAt(0).setVisibility(View.VISIBLE);

        super.onDetach();
        mListener = null;
    }

    public interface OnInteractionListener {
        // TODO: Add methods here as necessary
    }
}
