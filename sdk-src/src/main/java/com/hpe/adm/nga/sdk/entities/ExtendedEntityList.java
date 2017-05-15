package com.hpe.adm.nga.sdk.entities;

import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

public final class ExtendedEntityList extends EntityList {

    public ExtendedEntityList(OctaneHttpClient octaneHttpClient, String entityListDomain) {
        super(octaneHttpClient, entityListDomain);
    }

    public ExtendedGetEntities get() {
        return new ExtendedGetEntities(octaneHttpClient, urlDomain);
    }
}
