package com.hpe.adm.nga.sdk;


import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.network.google.GoogleHttpClient;

import java.util.Collection;

public class GetExpandQueryExample {

    public static void main(String[] args) {

        Authentication authentication = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password
        );

        GoogleHttpClient httpClient = new GoogleHttpClient(OctaneConnectionConstants.urlDomain);
        httpClient.authenticate(authentication);

        ExtendedOctane octane = new ExtendedOctane(
                httpClient,
                OctaneConnectionConstants.urlDomain,
                OctaneConnectionConstants.sharedspaceId + "",
                OctaneConnectionConstants.workspaceId
                );

        Collection<EntityModel> enttities = octane.entityList("defects").get().expand("fields=\"author\"").execute();

        System.out.println(enttities.size());


    }

}
