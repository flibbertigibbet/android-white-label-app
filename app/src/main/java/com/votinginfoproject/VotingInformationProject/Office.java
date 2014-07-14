package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v2/representatives#resource
 */
public class Office {
    String divisionId;
    List<String> level;
    String name;
    List<String> officialIndexes;
    List<String> roles;
    List<Source> sources;
}
