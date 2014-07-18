package com.votinginfoproject.VotingInformationProject.models;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v1/representatives#resource
 * Is response object for searching representativeInfoByDivision:
 * https://developers.google.com/civic-information/docs/v1/representatives/representativeInfoByDivision
 */
public class RepresentativeInfo {
    public static Dictionary<String, Division> divisions;
    public static List<Office> offices;
    public static List<Official> officials;
}
