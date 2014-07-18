package com.votinginfoproject.VotingInformationProject.models;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * Response object for voterInfoQuery:
 * https://developers.google.com/civic-information/docs/v1/voterinfo/voterInfoQuery
 */
public class VoterInfo {
    public String kind;
    public Election election;
    public List<Election> otherElections;
    public Address normalizedInput;
    public List<PollingLocation> pollingLocations;
    public List<PollingLocation> earlyVoteSites;
    public List<Contest> contests;
    public List<State> state;
}
