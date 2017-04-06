package com.hpe.adm.nga.sdk.network;

public abstract class AbstractOctaneHttpClient implements OctaneHttpClient {

    RequestInterceptor requestInterceptor;
    ResponseInterceptor responseInterceptor;

    @Override
    public OctaneHttpResponse execute(OctaneHttpRequest octaneHttpRequest){

        if(requestInterceptor!=null){
            octaneHttpRequest = requestInterceptor.interceptOctaneHttpRequest(octaneHttpRequest);
        }

        OctaneHttpResponse octaneHttpResponse = executeOctaneHttpRequest(octaneHttpRequest);

        if(responseInterceptor!=null) {
            octaneHttpResponse = responseInterceptor.interceptOctaneHttpResponse(octaneHttpResponse);
        }

        return octaneHttpResponse;
    }

    public abstract OctaneHttpResponse executeOctaneHttpRequest(OctaneHttpRequest octaneHttpRequest);

    public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    public void setResponseInterceptor(ResponseInterceptor responseInterceptor) {
        this.responseInterceptor = responseInterceptor;
    }

    public RequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    public ResponseInterceptor getResponseInterceptor() {
        return responseInterceptor;
    }
}
