package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 *
 * Local election information for voter's state:
 * https://developers.google.com/civic-information/docs/v2/voterinfo/voterInfoQuery
 */
public class State {

    String id;
    String name;
    ElectionAdministrationBody electionAdministrationBody;
    AdministrationRegion local_jurisdicition;
    List<Source> sources;
}
