package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v2/voterinfo/voterInfoQuery
 */
public class ElectionAdministrationBody {

    public class ElectionOfficials {
        String name;
        String title;
        String officePhoneNumber;
        String faxNumber;
        String emailAddress;
    }

    String name;
    String electionInfoUrl;
    String electionRegistrationUrl;
    String electionRegistrationConfirmationUrl;
    String absenteeVotingInfoUrl;
    String votingLocationFinderUrl;
    String ballotInfoUrl;
    String electionRulesUrl;
    List<String> voter_services;
    String hoursOfOperation;
    Address correspondenceAddress;
    Address physicalAddress;
    List<ElectionOfficials> electionOfficials;
}
