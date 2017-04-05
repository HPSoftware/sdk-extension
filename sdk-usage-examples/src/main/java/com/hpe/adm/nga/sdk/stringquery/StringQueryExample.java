package com.hpe.adm.nga.sdk.stringquery;

import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.StringQuery;
import com.hpe.adm.nga.sdk.Util;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.network.ExtendedOctane;
import com.hpe.adm.nga.sdk.network.google.CustomGoogleHttpClient;

import java.util.Collection;

public class StringQueryExample {

    public static void main(String[] args) {

        Authentication simpleAuth = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password);

        // Create a custom version or a completely new OctaneHttpClient
        // In this example we're using a slightly modified GoogleHttpClient which allows you to
        // change the HTTP Proxy the client is using for each request
        CustomGoogleHttpClient customGoogleHttpClient
                = new CustomGoogleHttpClient(OctaneConnectionConstants.urlDomain);

        // Make sure you call the authentication method before using the octane object
        customGoogleHttpClient.authenticate(simpleAuth);

        // the extended version of the Octane object doesn't require a builder,
        // however it is expected that you understand how to create the http client manually
        ExtendedOctane octane = new ExtendedOctane(
                customGoogleHttpClient,
                OctaneConnectionConstants.urlDomain,
                OctaneConnectionConstants.sharedspaceId.toString(),
                OctaneConnectionConstants.workspaceId
        );

        //Example: query to return stories or defects
        StringQuery stringQuery = StringQuery.fromString("subtype EQ 'story' || subtype EQ 'defect'");

        //Fetch defects as an example a print them to the console
        Collection<EntityModel> entities = octane
                .entityList("work_items")
                .get()
                .query(stringQuery)
                .execute();

        Util.printEntities(entities);
    }

}
