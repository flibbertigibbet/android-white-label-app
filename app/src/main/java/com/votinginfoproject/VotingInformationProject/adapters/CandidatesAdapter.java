package com.votinginfoproject.VotingInformationProject.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.votinginfoproject.VotingInformationProject.R;
import com.votinginfoproject.VotingInformationProject.activities.VIPTabBarActivity;
import com.votinginfoproject.VotingInformationProject.asynctasks.FetchImageQuery;
import com.votinginfoproject.VotingInformationProject.models.Candidate;

import java.util.Comparator;
import java.util.List;

/**
 * Created by kathrynkillebrew on 8/4/14.
 */
public class CandidatesAdapter extends ArrayAdapter<Candidate> {

    VIPTabBarActivity myActivity;
    Comparator<Candidate> candidateComparator;

    // View lookup cache.  Pattern from here:
    // https://github.com/thecodepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
    private static class ViewHolder {
        TextView name;
        TextView party;
        ImageView photo;
        boolean alreadyQueryingForPhoto;
    }

    public void sortList() {
        sort(candidateComparator);
    }

    /**
     *
     * @param context VIPTabBarActivity that owns the ballot view
     * @param candidates list of contests to display
     */
    public CandidatesAdapter(VIPTabBarActivity context, List<Candidate> candidates) {
        super(context, R.layout.candidate_list_item, candidates);
        this.myActivity = context;

        // sort contests by ballot placement
        candidateComparator = new Comparator<Candidate>() {
            @Override
            public int compare(Candidate candidate1, Candidate candidate2) {
                return Double.compare(candidate1.orderOnBallot, candidate2.orderOnBallot);
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Candidate candidate = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.candidate_list_item, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.candidate_list_item_name);
            viewHolder.party = (TextView) convertView.findViewById(R.id.candidate_list_item_party);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.candidate_list_item_picture);
            viewHolder.alreadyQueryingForPhoto = false;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setTextView(viewHolder.name, candidate.name);
        setTextView(viewHolder.party, candidate.party);

        if (candidate.photo != null) {
            viewHolder.photo.setImageBitmap(candidate.photo);
        } else if (candidate.photoUrl != null && !candidate.photoUrl.isEmpty() && !viewHolder.alreadyQueryingForPhoto) {
            viewHolder.alreadyQueryingForPhoto = true;
            new FetchImageQuery(candidate, viewHolder.photo).execute(candidate.photoUrl);
        } else if (!viewHolder.alreadyQueryingForPhoto) {
            // TODO: fetching test image
            Log.d("CandidatesAdapter", "Fetching test image in list adapter");
            viewHolder.alreadyQueryingForPhoto = true;
            new FetchImageQuery(candidate, viewHolder.photo).execute("http://dailytwocents.com/wp-content/uploads/2014/06/Grumpy_Cat.jpg");
        }

        // Return the completed view to render on screen
        return convertView;
    }

    /**
     * Helper function to set text in view, or hide view if text empty or missing
     * @param view TextView to put text into
     * @param text String to put in the TextView
     */
    private void setTextView(TextView view, String text) {
        if (text != null && !text.isEmpty()) {
            view.setText(text);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}
