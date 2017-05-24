/*
 * Copyright 2017 Hewlett-Packard Enterprise Development Company, L.P.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hpe.adm.nga.sdk.extension;

import com.hpe.adm.nga.sdk.OctaneClassFactory;
import com.hpe.adm.nga.sdk.entities.EntityList;
import com.hpe.adm.nga.sdk.extension.entities.ExtendedEntityList;
import com.hpe.adm.nga.sdk.extension.network.google.InterceptorGoogleHttpClient;
import com.hpe.adm.nga.sdk.network.OctaneHttpClient;

/**
 * Class factory for the extension
 */
public class ExtendedOctaneClassFactory implements OctaneClassFactory {

    private static ExtendedOctaneClassFactory instance = new ExtendedOctaneClassFactory();
    private ExtendedOctaneClassFactory(){}
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