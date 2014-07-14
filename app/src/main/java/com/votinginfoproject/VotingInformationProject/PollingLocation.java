package com.votinginfoproject.VotingInformationProject;

import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * Polling location or early voting site
 * https://developers.google.com/civic-information/docs/v2/voterinfo
 */
public class PollingLocation {
    Address address;
    String id;
    String name;
    String startDate;
    String endDate;
    String notes;
    String pollingHours;
    List<Source> sources;
    String voterServices; // This field is not populated for polling locations.
}
