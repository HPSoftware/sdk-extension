package com.hpe.adm.nga.sdk.network;

import java.util.Map;

public abstract class RequestInterceptor {

    public String baseUrl(String requestBaseUrl){
       return requestBaseUrl;
    }

    public Map<String, String> queryParams(Map<String, String> queryParams){
        return queryParams;
    }

    public String requestContent(String requestContent){
        return requestContent;
    }

    OctaneHttpRequest interceptOctaneHttpRequest(OctaneHttpRequest octaneHttpRequest){
        //TODO: Change octane http request based on how the above methods were implemented
        return octaneHttpRequest;
    }

}
