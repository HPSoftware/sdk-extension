package com.hpe.adm.nga.sdk.customhttpclient;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.OctaneClassFactory;
import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.Util;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.model.EntityModel;

import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Example to show how to use your own custom http client with the extended sdk
 */
public class DummyOctaneHttpClientExample {

    public static void main(String[] args) {

        Authentication authentication
                = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password);

        //Set custom implementation via system propery

        System.getProperties().setProperty(
                OctaneClassFactory.OCTANE_HTTP_CLIENT_CLASS_NAME,
                "com.hpe.adm.nga.sdk.customhttpclient.DummyOctaneHttpClient");

        int dummyDefectCount = ThreadLocalRandom.current().nextInt(0, 101);
        //Set the number of dummy entities the custom http client should make
        DummyOctaneHttpClient.dummyDefectCount = dummyDefectCount;

        Octane octane =
                new Octane.Builder(authentication)
                        .Server(OctaneConnectionConstants.urlDomain)
                        .sharedSpace(OctaneConnectionConstants.sharedspaceId)
                        .workSpace(OctaneConnectionConstants.workspaceId)
                        .build();

        //Fetch defects as an example a print them to the console
        Collection<EntityModel> defects = octane.entityList("work_items").get().execute();
        Util.printEntities(defects);

        assert defects.size() == dummyDefectCount;

        //Remove system property
        System.getProperties().remove(OctaneClassFactory.OCTANE_HTTP_CLIENT_CLASS_NAME);
    }
}
