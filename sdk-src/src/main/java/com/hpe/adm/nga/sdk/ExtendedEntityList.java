package com.hpe.adm.nga.sdk;

import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

public class ExtendedEntityList extends EntityList {

    /**
     * Creates a new {@link EntityList} instance with the entity collection name and the client to be used
     *
     * @param octaneHttpClient    - The client that is used for REST communication
     * @param strEntityListDomain - The entity collection name
     */
    public ExtendedEntityList(OctaneHttpClient octaneHttpClient, String strEntityListDomain) {
        entityListService = new ExtendedEntityListService(octaneHttpClient, strEntityListDomain);
    }

    public ExtendedEntityListService.ExtendedGet get() {
        return ((ExtendedEntityListService) entityListService).get();
    }

}
