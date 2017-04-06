package com.hpe.adm.nga.sdk.network;

public abstract class ResponseInterceptor {

    public String responseContent(String responseContent){
        return responseContent;
    }

    OctaneHttpResponse interceptOctaneHttpResponse(OctaneHttpResponse octaneHttpResponse){
        //TODO: Change octane http response based on how the above methods were implemented
        return octaneHttpResponse;
    }

}