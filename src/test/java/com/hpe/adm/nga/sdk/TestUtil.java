package com.hpe.adm.nga.sdk;

import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.network.ExtendedOctane;

import java.net.Proxy;

/**
 * Created by savencu on 2/27/2017.
 */
public class TestUtil {
    public static Octane createContext() {

        Authentication userPassAuthentication = new SimpleUserAuthentication("sa@nga", "Welcome1");

        final Octane.Builder octaneBuilder = new Octane.Builder(userPassAuthentication);
        octaneBuilder.Server("http://myd-vm19852.hpeswlab.net:8080");
        octaneBuilder.sharedSpace(1001);
        octaneBuilder.workSpace(1002);

        return octaneBuilder.build();
    }
    public static Octane createContextWithProxy(Proxy proxy) {

        Authentication userPassAuthentication = new SimpleUserAuthentication("sa@nga", "Welcome1");

        final ExtendedOctane.CustomOctaneBuilder octaneBuilder = new ExtendedOctane.CustomOctaneBuilder(userPassAuthentication);

        return octaneBuilder.customBuild("http://myd-vm19852.hpeswlab.net:8080","1001",1002,proxy);
    }
}
