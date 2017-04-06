package com.hpe.adm.nga.sdk.network.google;


import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.network.AbstractOctaneHttpClient;
import com.hpe.adm.nga.sdk.network.OctaneHttpRequest;
import com.hpe.adm.nga.sdk.network.OctaneHttpResponse;

/**
 * Implementation of {@link AbstractOctaneHttpClient}
 * that uses an inner {@link GoogleHttpClient}
 * Enables use of {@link com.hpe.adm.nga.sdk.network.RequestInterceptor} and {@link com.hpe.adm.nga.sdk.network.ResponseInterceptor}
 */
public class InterceptorGoogleHttpClient extends AbstractOctaneHttpClient{

    private GoogleHttpClient googleHttpClient;

    public InterceptorGoogleHttpClient(String urlDomain) {
        googleHttpClient = new GoogleHttpClient(urlDomain);
    }

    public boolean authenticate(Authentication authentication) {
        return googleHttpClient.authenticate(authentication);
    }

    public void signOut() {
        googleHttpClient.signOut();
    }

    @Override
    public OctaneHttpResponse executeOctaneHttpRequest(OctaneHttpRequest octaneHttpRequest) {
        return googleHttpClient.execute(octaneHttpRequest);
    }

}
