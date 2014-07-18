package com.votinginfoproject.VotingInformationProject.models;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v1/representatives/representativeInfoByAddress
 */
public class RepresentativeInfoByAddressResponse {
    public static String kind;
    public static Address normalizedInput;
    public static Dictionary<String, Division> divisions;
    public static List<Office> offices;
    public static List<Official> officials;
}
