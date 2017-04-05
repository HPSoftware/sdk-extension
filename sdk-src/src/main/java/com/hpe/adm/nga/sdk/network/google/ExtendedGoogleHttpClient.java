package com.hpe.adm.nga.sdk.network.google;

/**
 * Google HTTP client with support for setting the HTTP proxy
 */
public class ExtendedGoogleHttpClient extends GoogleHttpClient {

    /**
     * Creates an HTTP client instance using the url and authentication.
     *
     * @param urlDomain        The source URL of the Octane server
     */
    public ExtendedGoogleHttpClient(String urlDomain) {
        super(urlDomain);
    }

}
