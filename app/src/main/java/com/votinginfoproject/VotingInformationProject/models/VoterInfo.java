package com.votinginfoproject.VotingInformationProject.models;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * Response object for voterInfoQuery:
 * https://developers.google.com/civic-information/docs/v1/voterinfo/voterInfoQuery
 */
public class VoterInfo {
    public static String kind;
    public static Election election;
    public static List<Election> otherElections;
    public static Address normalizedInput;
    public static List<PollingLocation> pollingLocations;
    public static List<PollingLocation> earlyVoteSites;
    public static List<Contest> contests;
    public static List<State> state;
}
