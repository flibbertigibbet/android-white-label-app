package com.votinginfoproject.VotingInformationProject.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.activities.VIPTabBarActivity;
import com.votinginfoproject.VotingInformationProject.models.Election;
import com.votinginfoproject.VotingInformationProject.models.ElectionAdministrationBody;
import com.votinginfoproject.VotingInformationProject.models.State;
import com.votinginfoproject.VotingInformationProject.models.VoterInfo;

import org.w3c.dom.Text;

import java.net.URI;


public class ElectionDetailsFragment extends Fragment {

    private Activity mActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ElectionDetailsFragment.
     */
    public static ElectionDetailsFragment newInstance() {
        ElectionDetailsFragment fragment = new ElectionDetailsFragment();
        return fragment;
    }
    public ElectionDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_election_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ElectionDetailsFragment", "In onActivityCreated");

        mActivity = getActivity();

        // TODO: we might still want to open links this way, if we want to stay in the app,
        // instead of using android:autoLink
        //setUrlClickHandler(R.id.details_state_election_info_url);
        //setUrlClickHandler(R.id.details_local_election_info_url);

        setContents();
    }

    /**
     * Helper function to set click handler for TextViews containing URLs.
     * Will launch a browser with the URL in the text view.
     *
     * @param textViewId R id of the TextView containing a URL as its text
     */
    private void setUrlClickHandler(int textViewId) {
        View textView = mActivity.findViewById(textViewId);
        textView.setOnClickListener(view -> {
            try {
                // could use webview instead to open link within app
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView)view).getText().toString()));
                browseIntent.addCategory(Intent.CATEGORY_BROWSABLE);

                startActivity(browseIntent);
            } catch (Exception ex) {
                Log.e("ElectionDetailsFragment", "Failed to open clicked URL");
                ex.printStackTrace();
            }
        });
    }

    /** Helper function to populate the view labels.
     *
     */
    private void setContents() {
        TextView title = (TextView) mActivity.findViewById(R.id.details_election_title);
        TextView subTitle = (TextView) mActivity.findViewById(R.id.details_election_subtitle);

        try {
            VoterInfo voterInfo = ((VIPTabBarActivity) mActivity).getVoterInfo();
            Election election = voterInfo.election;

            title.setText(election.name);
            subTitle.setText(election.getFormattedDate());

            // TODO: what if election is in multiple states?
            State thisState = voterInfo.state.get(0);

            Log.d("ElectionDetailsFragment", "Got state " + thisState.name);

            ElectionAdministrationBody stateAdmin = thisState.electionAdministrationBody;

            if (stateAdmin != null) {
                Log.d("ElectionDetailsFragment", "Got state election admin body " + stateAdmin.name);

                TextView state_name = (TextView) mActivity.findViewById(R.id.details_state_admin_body_name);
                state_name.setText(stateAdmin.name);

                // set state admin body table values
                setTextView(R.id.details_state_election_info_url, R.id.details_state_election_info_url_row, stateAdmin.electionInfoUrl);
                setTextView(R.id.details_state_registration_url, R.id.details_state_registration_url_row, stateAdmin.electionRegistrationUrl);
                setTextView(R.id.details_state_registration_confirmation_url, R.id.details_state_registration_confirmation_url_row, stateAdmin.electionRegistrationConfirmationUrl);
                setTextView(R.id.details_state_absentee_url, R.id.details_state_absentee_url_row, stateAdmin.absenteeVotingInfoUrl);
                ////////////////////////////////////
            } else {
                TableLayout stateTable = (TableLayout) mActivity.findViewById(R.id.details_state_admin_body_table);
                stateTable.setVisibility(View.GONE);
                TextView stateTableHeader = (TextView) mActivity.findViewById(R.id.details_state_admin_body_section_label);
                stateTableHeader.setVisibility(View.GONE);
            }

            if (thisState.local_jurisdiction != null && thisState.local_jurisdiction.electionAdministrationBody != null ) {
                ElectionAdministrationBody localAdmin = thisState.local_jurisdiction.electionAdministrationBody;
                Log.d("ElectionDetailsFragment", "Got local election admin body " + localAdmin.name);

                TextView local_name = (TextView) mActivity.findViewById(R.id.details_local_admin_body_name);
                local_name.setText(localAdmin.name);

                // set local admin body table values
                setTextView(R.id.details_local_election_info_url, R.id.details_local_election_info_url_row, localAdmin.electionInfoUrl);
                setTextView(R.id.details_local_registration_url, R.id.details_state_registration_url_row, localAdmin.electionRegistrationUrl);
                setTextView(R.id.details_local_registration_confirmation_url, R.id.details_local_registration_confirmation_url_row, localAdmin.electionRegistrationConfirmationUrl);
                setTextView(R.id.details_local_absentee_url, R.id.details_state_absentee_url_row, localAdmin.absenteeVotingInfoUrl);

                ////////////////////////////////////
            } else {
                TableLayout localTable = (TableLayout) mActivity.findViewById(R.id.details_local_admin_body_table);
                localTable.setVisibility(View.GONE);
                TextView localTableHeader = (TextView) mActivity.findViewById(R.id.details_local_admin_body_section_label);
                localTableHeader.setVisibility(View.GONE);
            }

        } catch (Exception ex) {
            Log.e("ElectionDetailsFragment", "Failed to set election details info!");
            ex.printStackTrace();
        }

    }

    /**
     * Helper function to set a TextView to a string, or hide the TextView's TableRow,
     * if the string is null or empty.
     *
     * @param textViewId R id of the TextView to set
     * @param rowViewId R id of the TextView's TableRow, to hide if value is missing
     * @param val String to put in the TextView
     */
    private void setTextView(int textViewId, int rowViewId, String val) {
        TextView textView = (TextView) mActivity.findViewById(textViewId);
        if (val != null && !val.isEmpty()) {
            textView.setText(val);
        } else {
            TableRow tableRow = (TableRow) mActivity.findViewById(rowViewId);
            tableRow.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
