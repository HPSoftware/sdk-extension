package com.hpe.adm.nga.sdk.customhttpclient;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.Util;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.exception.OctaneException;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.network.ExtendedOctane;
import com.hpe.adm.nga.sdk.network.google.CustomGoogleHttpClient;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Collection;

/**
 * Example to show how to use your own custom http client with the extended sdk
 */
public class CustomGoogleOctaneHttpClientExample {

    public static void main(String[] args){

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

        printDefects(octane);

        //Set a valid, or invalid http proxy on your custom OctaneHttpClient and see what happens
        Proxy httpProxy = Proxy.NO_PROXY;
        customGoogleHttpClient.setHttpProxy(httpProxy);
        printDefects(octane);

        // Unless you are actually running a http proxy on localhost:8080,
        // the next time you use the octane object, it will fail to get data
        httpProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 8080));
        customGoogleHttpClient.setHttpProxy(httpProxy);

        try {
            printDefects(octane);
        } catch (OctaneException ex){
            System.out.println("Exception caught: " + ex.getMessage());
        }
    }

    private static void printDefects(Octane octane){
        //Fetch defects as an example a print them to the console
        Collection<EntityModel> defects = octane.entityList("defects").get().execute();
        Util.printEntities(defects);

    }
}
