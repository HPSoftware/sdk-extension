package com.hpe.adm.nga.sdk.customhttpclient;

import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.Util;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.StringFieldModel;
import com.hpe.adm.nga.sdk.network.ExtendedOctane;
import com.hpe.adm.nga.sdk.network.OctaneHttpClient;
import com.hpe.adm.nga.sdk.network.OctaneHttpRequest;
import com.hpe.adm.nga.sdk.network.OctaneHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Example to show how to use your own custom http client with the extended sdk
 */
public class DummyOctaneHttpClientExample {

    public static void main(String[] args){

        // Create a custom version or a completely new OctaneHttpClient
        // In this example we just create a http client inline to serve some dummy data
        OctaneHttpClient customGoogleHttpClient = new OctaneHttpClient() {
            @Override
            public boolean authenticate(Authentication authentication) {return true;}
            @Override
            public void signOut() {}
            @Override
            public OctaneHttpResponse execute(OctaneHttpRequest octaneHttpRequest) {

                //Create some dummy entity models
                Collection<EntityModel> entities = new ArrayList<>();
                for(int i = 1; i<=10; i++) {
                    EntityModel entityModel = new EntityModel();
                    entityModel.setValue(new StringFieldModel("id", i+""));
                    entityModel.setValue(new StringFieldModel("name", "defect no:" + i));
                    entityModel.setValue(new StringFieldModel("type", "work_item"));
                    entityModel.setValue(new StringFieldModel("subtype", "defect"));
                    entities.add(entityModel);
                }

                //Convert them to JSON that matches server return type
                String returnJson = Util.getEntitiesJSONObject(entities).toString();

                //Return a dummy OctaneHttpResponse
                InputStream stream = new ByteArrayInputStream(returnJson.getBytes(StandardCharsets.UTF_8));
                return new OctaneHttpResponse(202, returnJson, stream);
            }
        };


        // the extended version of the Octane object doesn't require a builder,
        // however it is expected that you understand how to create the http client manually
        ExtendedOctane octane = new ExtendedOctane(
                customGoogleHttpClient,
                OctaneConnectionConstants.urlDomain,
                OctaneConnectionConstants.sharedspaceId.toString(),
                OctaneConnectionConstants.workspaceId
        );


        //Fetch defects as an example a print them to the console
        Collection<EntityModel> defects = octane.entityList("defects").get().execute();
        Util.printEntities(defects);
    }



}
