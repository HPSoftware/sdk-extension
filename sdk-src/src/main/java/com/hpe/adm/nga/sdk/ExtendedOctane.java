package com.hpe.adm.nga.sdk;


import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

public class ExtendedOctane extends Octane {

    public ExtendedOctane(OctaneHttpClient octaneHttpClient, String domain, String sharedSpaceId, long workId) {
        super(octaneHttpClient, domain, sharedSpaceId, workId);
    }

    public ExtendedEntityList entityList(String entityName) {
        String entityListDomain = getBaseDomainFormat() + entityName;
        return new ExtendedEntityList(octaneHttpClient, entityListDomain);
    }

}