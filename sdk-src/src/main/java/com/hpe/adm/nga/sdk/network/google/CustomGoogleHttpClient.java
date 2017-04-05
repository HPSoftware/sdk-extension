package com.hpe.adm.nga.sdk.network.google;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.net.Proxy;


/**
 * Google HTTP client with support for setting the HTTP proxy
 */
public class CustomGoogleHttpClient extends GoogleHttpClient {

    /**
     * Creates an HTTP client instance using the url and authentication.
     *
     * @param urlDomain The source URL of the Octane server
     */
    public CustomGoogleHttpClient(String urlDomain, Proxy httpProxy) {
        super(urlDomain);
        setHttpProxy(httpProxy);
    }

    public CustomGoogleHttpClient(String urlDomain) {
        super(urlDomain);
    }

    public void setHttpProxy(Proxy httpProxy) {
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(httpProxy).build();
        requestFactory = HTTP_TRANSPORT.createRequestFactory(requestInitializer);
    }

}