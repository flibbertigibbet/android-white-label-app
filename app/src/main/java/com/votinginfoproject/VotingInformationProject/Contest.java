package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v2/voterinfo/voterInfoQuery
 */
public class Contest {

    public class District {
        String id;
        String name;
        String scope;
    }

    public class Candidate {

        String name;
        String party;
        String candidateUrl;
        String phone;
        String photoUrl;
        String email;
        Long orderOnBallot;
        List<SocialMediaChannel> channels;
    }

    String id;
    String type;
    String primaryParty;
    String electorateSpecifications;
    String special;
    String office;
    String level;
    District district;
    Long numberElected;
    Long numberVotingFor;
    Long ballotPlacement;
    List<Candidate> candidates;
    String referendumTitle;
    String referendumSubtitle;
    String referendumUrl;
    List <Source> sources;
}
