package com.hpe.adm.nga.sdk.entities;

import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

/**
 * Created by brucesp on 15-May-17.
 */
public class ExtendedGetEntities extends GetEntities {
    ExtendedGetEntities(OctaneHttpClient octaneHttpClient, String urlDomain) {
        super(octaneHttpClient, urlDomain);
    }

    public ExtendedGetEntities expand(String expand){
        getOctaneUrl().setParam("expand", expand);
        return this;
    }
}
