package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 */
public class AdministrationRegion {
    public class Sources {
        String name;
        Boolean official;
    }

    String id;
    String name;
    ElectionAdministrationBody electionAdministrationBody;
    List<Sources> sources;
}
