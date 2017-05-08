package com.hpe.adm.nga.sdk;

import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

public class ExtendedEntityListService extends EntityListService{

    public ExtendedEntityListService(OctaneHttpClient octaneHttpClient, String entityListDomain) {
        super(octaneHttpClient, entityListDomain);
    }

    @Override
    public ExtendedGet get() {
        return new ExtendedGet();
    }

    public class ExtendedGet extends Get {

        public ExtendedGet expand(String expand){
            octaneUrl.setParam("expand", expand);
            return this;
        }

    }

}
