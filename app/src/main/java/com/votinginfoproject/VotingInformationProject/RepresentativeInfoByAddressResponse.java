package com.votinginfoproject.VotingInformationProject;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by kathrynkillebrew on 7/14/14.
 * https://developers.google.com/civic-information/docs/v2/representatives/representativeInfoByAddress
 */
public class RepresentativeInfoByAddressResponse {
    String kind;
    Address normalizedInput;
    Dictionary<String, Division> divisions;
    List<Office> offices;
    List<Official> officials;
}
