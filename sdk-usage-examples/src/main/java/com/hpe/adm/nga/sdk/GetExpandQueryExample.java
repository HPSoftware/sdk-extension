package com.hpe.adm.nga.sdk;


import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.entities.ExtendedGetEntities;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.network.google.GoogleHttpClient;

import java.util.Collection;

public class GetExpandQueryExample {

    public static void main(String[] args) {

        Authentication authentication = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password
        );

        System.getProperties().setProperty(
                OctaneClassFactory.OCTANE_CLASS_FACTORY_CLASS_NAME,
                "com.hpe.adm.nga.sdk.ExtendedOctaneClassFactory");

        GoogleHttpClient httpClient = new GoogleHttpClient(OctaneConnectionConstants.urlDomain);
        httpClient.authenticate(authentication);

        Octane octane = new Octane.Builder(authentication).Server(OctaneConnectionConstants.urlDomain).sharedSpace(OctaneConnectionConstants.sharedspaceId)
                .workSpace(OctaneConnectionConstants.workspaceId).build();
        final Collection<EntityModel> defects = ((ExtendedGetEntities) octane.entityList("defects").get()).expand("fields=\"author\"").execute();

        System.out.println(defects.size());


    }

}
