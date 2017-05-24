package com.hpe.adm.nga.sdk;

import com.hpe.adm.nga.sdk.entities.EntityList;
import com.hpe.adm.nga.sdk.entities.ExtendedEntityList;
import com.hpe.adm.nga.sdk.network.OctaneHttpClient;
import com.hpe.adm.nga.sdk.network.google.InterceptorGoogleHttpClient;

/**
 * Class factory for the extension
 */
public class ExtendedOctaneClassFactory implements OctaneClassFactory {

    private static ExtendedOctaneClassFactory instance = new ExtendedOctaneClassFactory();
    private ExtendedOctaneClassFactory(){};
    public static ExtendedOctaneClassFactory getInstance(){ return instance; }

    @Override
    public OctaneHttpClient getOctaneHttpClient(String urlDomain) {
        return new InterceptorGoogleHttpClient(urlDomain);
    }

    @Override
    public EntityList getEntityList(OctaneHttpClient octaneHttpClient, String baseDomain, String entityName) {
        return new ExtendedEntityList(octaneHttpClient, baseDomain + entityName);
    }
}