package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * Response object for voterInfoQuery:
 * https://developers.google.com/civic-information/docs/v2/voterinfo/voterInfoQuery
 */
public class VoterInfo {
    String kind;
    Election election;
    List<Election> otherElections;
    Address normalizedInput;
    List<PollingLocation> pollingLocations;
    List<PollingLocation> earlyVoteSites;
    List<Contest> contests;
    List<State> state;
}
