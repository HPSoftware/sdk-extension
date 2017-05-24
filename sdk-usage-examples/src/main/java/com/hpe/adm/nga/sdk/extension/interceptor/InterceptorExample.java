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

package com.hpe.adm.nga.sdk.extension.interceptor;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.OctaneClassFactory;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.extension.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.extension.network.RequestInterceptor;
import com.hpe.adm.nga.sdk.extension.network.ResponseInterceptor;
import com.hpe.adm.nga.sdk.extension.network.google.InterceptorGoogleHttpClient;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.StringFieldModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class InterceptorExample {

    private static final Logger logger = LogManager.getLogger(InterceptorGoogleHttpClient.class.getName());
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public static void main(String[] args){
        Authentication authentication
                = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password);

        //Set custom implementation via system propery

        System.getProperties().setProperty(
                OctaneClassFactory.OCTANE_CLASS_FACTORY_CLASS_NAME,
                "ExtendedOctaneClassFactory");

        InterceptorGoogleHttpClient.addRequestInterceptor(new RequestInterceptor() {
            @Override
            public String url(String url) {
                String newUrl = url.replace("work_items","defects");
                logger.info("Intercepting url: {} ->  {}", url, newUrl);
                return newUrl;
            }
            @Override
            public String content(String content) {
                logger.info("Intercepting content: {} ->  {}", content, content);
                return content;
            }

            @Override
            public Map<String, Object> headers( Map<String, Object> headers) {
                logger.info("Intercepting headers: (printing)");
                headers.forEach((key, value) -> logger.info(key +": " + value));
                return headers;
            }

            //TODO: headers
        });

        //TODO: not working
        InterceptorGoogleHttpClient.addResponseInterceptor(new ResponseInterceptor() {
        });

        Octane octane =
                new Octane.Builder(authentication)
                        .Server(OctaneConnectionConstants.urlDomain)
                        .sharedSpace(OctaneConnectionConstants.sharedspaceId)
                        .workSpace(OctaneConnectionConstants.workspaceId)
                        .build();

        //Fetch defects as an example a print them to the console
        Collection<EntityModel> entities = octane.entityList("work_items").get().addFields("name").execute();

        if(entities.size()<1) return;

        EntityModel entityModel = entities.iterator().next();

        StringFieldModel stringFieldModel = (StringFieldModel) entityModel.getValue("name");
        stringFieldModel.setValue("name", "entity " + dateFormat.format(new Date()));
        entityModel.setValue(stringFieldModel);

        octane.entityList("work_items").update().entities(Collections.singletonList(entityModel)).execute();
    }

}
