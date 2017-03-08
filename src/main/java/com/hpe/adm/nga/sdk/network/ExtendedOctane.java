package com.hpe.adm.nga.sdk.network;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.network.google.ExtendedGoogleHttpClient;

import java.net.Proxy;

/**
 * Created by savencu on 3/1/2017.
 */
public class ExtendedOctane extends Octane {

    public ExtendedOctane(OctaneHttpClient octaneHttpClient, String domain, String sharedSpaceId, long workId) {
        super(octaneHttpClient, domain, sharedSpaceId, workId);
    }

    public static class CustomOctaneBuilder extends Octane.Builder {
        private final Authentication authentication;

        public CustomOctaneBuilder(Authentication authentication) {
            super(authentication);
            this.authentication = authentication;
        }

        public Octane customBuild(String urlDomain, String idsharedSpaceId, long workSpaceId, Proxy proxy) {
            Octane objOctane = null;

            ExtendedGoogleHttpClient octaneHttpClient = new ExtendedGoogleHttpClient(urlDomain, authentication.getClientHeader());
            octaneHttpClient.setProxyForHttpClient(proxy);
            if (octaneHttpClient.authenticate(authentication)) {
                objOctane = new ExtendedOctane(octaneHttpClient, urlDomain, idsharedSpaceId, workSpaceId);
            }

            return objOctane;
        }
    }
}
