package com.hpe.adm.nga.sdk.interceptor;

import com.hpe.adm.nga.sdk.ExtendedOctane;
import com.hpe.adm.nga.sdk.OctaneConnectionConstants;
import com.hpe.adm.nga.sdk.Util;
import com.hpe.adm.nga.sdk.authentication.Authentication;
import com.hpe.adm.nga.sdk.authentication.SimpleUserAuthentication;
import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.network.RequestInterceptor;
import com.hpe.adm.nga.sdk.network.ResponseInterceptor;
import com.hpe.adm.nga.sdk.network.google.InterceptorGoogleHttpClient;

import java.util.Collection;
import java.util.Map;

public class InterceptorExample {

    public static void main(String[] args){

        Authentication simpleAuth = new SimpleUserAuthentication(
                OctaneConnectionConstants.username,
                OctaneConnectionConstants.password);

        // Use the http client with interceptor support
        InterceptorGoogleHttpClient interceptorGoogleHttpClient = new InterceptorGoogleHttpClient(OctaneConnectionConstants.urlDomain);

        // Make sure you call the authentication method before using the octane object
        interceptorGoogleHttpClient.authenticate(simpleAuth);

        // the extended version of the Octane object doesn't require a builder,
        // however it is expected that you understand how to create the http client manually
        ExtendedOctane octane = new ExtendedOctane(
                interceptorGoogleHttpClient,
                OctaneConnectionConstants.urlDomain,
                OctaneConnectionConstants.sharedspaceId.toString(),
                OctaneConnectionConstants.workspaceId
        );

        //TODO: interceptors are not applied yet
        interceptorGoogleHttpClient.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public String baseUrl(String requestBaseUrl) {
                return super.baseUrl(requestBaseUrl);
            }

            @Override
            public Map<String, String> queryParams(Map<String, String> queryParams) {
                return super.queryParams(queryParams);
            }

            @Override
            public String requestContent(String requestContent) {
                return super.requestContent(requestContent);
            }
        });

        //TODO: interceptors are not applied yet
        interceptorGoogleHttpClient.setResponseInterceptor(new ResponseInterceptor(){
            @Override
            public String responseContent(String responseContent){
                return responseContent;
            }
        });

        Collection<EntityModel> defects = octane.entityList("work_items").get().execute();
        Util.printEntities(defects);
    }

}
