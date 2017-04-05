package com.hpe.adm.nga.sdk.network;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.authentication.Authentication;

public class ExtendedOctane extends Octane {

    public ExtendedOctane(OctaneHttpClient octaneHttpClient, String domain, String sharedspaceId, long workspaceId) {
        super(octaneHttpClient, domain, sharedspaceId, workspaceId);
    }

    public static class Builder extends Octane.Builder {

        public Builder(Authentication authentication) {
            super(authentication);
        }
    }
}
