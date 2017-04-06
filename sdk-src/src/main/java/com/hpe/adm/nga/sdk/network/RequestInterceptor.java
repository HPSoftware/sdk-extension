package com.hpe.adm.nga.sdk.network;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;

public abstract class RequestInterceptor {

    public abstract String host(String host);

    public abstract Integer port(Integer port);

    public abstract String path(String path);

    public abstract List<NameValuePair> queryParams(List<NameValuePair> queryParams);

    public String requestContent(String requestContent){
        return requestContent;
    }

    OctaneHttpRequest interceptOctaneHttpRequest(OctaneHttpRequest octaneHttpRequest){

        String url = octaneHttpRequest.getRequestUrl();
        URIBuilder uriBuilder;

        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            return octaneHttpRequest;
        }

        uriBuilder.setHost(host(uriBuilder.getHost()));
        uriBuilder.setPort(port(uriBuilder.getPort()));
        uriBuilder.setPath(path(uriBuilder.getPath()));
        uriBuilder.setParameters(queryParams(uriBuilder.getQueryParams()));

        //OctaneHttpRequest request = new OctaneHttpRequest();

        //TODO: Change octane http request based on how the above methods were implemented
        return octaneHttpRequest;
    }


}
