package com.hpe.adm.nga.sdk.network.google;


import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.hpe.adm.nga.sdk.network.OctaneHttpRequest;
import com.hpe.adm.nga.sdk.network.OctaneHttpResponse;
import com.hpe.adm.nga.sdk.network.RequestInterceptor;
import com.hpe.adm.nga.sdk.network.ResponseInterceptor;

import java.io.IOException;
import java.net.Proxy;

public class InterceptorGoogleHttpClient extends GoogleHttpClient {

    private RequestInterceptor requestInterceptor;
    private ResponseInterceptor responseInterceptor;

    public InterceptorGoogleHttpClient(String urlDomain) {
        super(urlDomain);
    }

    public void setHttpProxy(Proxy httpProxy) {
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(httpProxy).build();
        requestFactory = HTTP_TRANSPORT.createRequestFactory(requestInitializer);
    }

    @Override
    protected HttpRequest convertOctaneRequestToGoogleHttpRequest(OctaneHttpRequest octaneHttpRequest) {
        return super.convertOctaneRequestToGoogleHttpRequest(octaneHttpRequest);
    }

    @Override
    protected OctaneHttpResponse convertHttpResponseToOctaneHttpResponse(HttpResponse httpResponse) throws IOException {
        return super.convertHttpResponseToOctaneHttpResponse(httpResponse);
    }

    public RequestInterceptor getRequestInterceptor() {
        return requestInterceptor;
    }

    public void setRequestInterceptor(RequestInterceptor requestInterceptor) {
        this.requestInterceptor = requestInterceptor;
    }

    public ResponseInterceptor getResponseInterceptor() {
        return responseInterceptor;
    }

    public void setResponseInterceptor(ResponseInterceptor responseInterceptor) {
        this.responseInterceptor = responseInterceptor;
    }

}