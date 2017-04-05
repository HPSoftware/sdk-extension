package com.hpe.adm.nga.sdk.network;

import com.hpe.adm.nga.sdk.Octane;

/**
 * Extension of the Octane object. <br>
 * Currently allows directly instantiating the Octane object without using a builder. <br>
 * Also allows the user to pass in any implementation of an {@link OctaneHttpClient}.
 */
public class ExtendedOctane extends Octane {

    public ExtendedOctane(OctaneHttpClient octaneHttpClient, String domain, String sharedspaceId, long workspaceId) {
        super(octaneHttpClient, domain, sharedspaceId, workspaceId);
    }

}