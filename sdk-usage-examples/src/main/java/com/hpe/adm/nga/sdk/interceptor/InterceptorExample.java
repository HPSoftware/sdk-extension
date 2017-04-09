package com.hpe.adm.nga.sdk.interceptor;

import com.hpe.adm.nga.sdk.Octane;
import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.customhttpclient.DummyOctaneHttpClient;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.FieldModel;
import com.hpe.adm.nga.sdk.model.StringFieldModel;
import com.hpe.adm.nga.sdk.network.AbstractRequestInterceptor;
import com.hpe.adm.nga.sdk.network.AbstractResponseInterceptor;
import com.hpe.adm.nga.sdk.network.RequestInterceptor;
import com.hpe.adm.nga.sdk.network.ResponseInterceptor;
import com.hpe.adm.nga.sdk.network.google.InterceptorGoogleHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

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
                Octane.Builder.OCTANE_HTTP_CLIENT_CLASS_NAME,
                "com.hpe.adm.nga.sdk.network.google.InterceptorGoogleHttpClient");

        InterceptorGoogleHttpClient.addRequestInterceptor(new AbstractRequestInterceptor() {
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

            //TODO: headers
        });

        //TODO: not working
        InterceptorGoogleHttpClient.addResponseInterceptor(new AbstractResponseInterceptor() {
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
