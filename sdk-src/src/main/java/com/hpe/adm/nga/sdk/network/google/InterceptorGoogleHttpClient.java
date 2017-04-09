package com.hpe.adm.nga.sdk.network.google;


import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.common.io.CharStreams;
import com.hpe.adm.nga.sdk.network.OctaneHttpRequest;
import com.hpe.adm.nga.sdk.network.OctaneHttpResponse;
import com.hpe.adm.nga.sdk.network.RequestInterceptor;
import com.hpe.adm.nga.sdk.network.ResponseInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.charset.Charset;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class InterceptorGoogleHttpClient extends GoogleHttpClient {

    private static final Logger logger = LogManager.getLogger(InterceptorGoogleHttpClient.class.getName());

    private static List<RequestInterceptor> requestInterceptors = new ArrayList<>(1);
    private static List<ResponseInterceptor> responseInterceptors = new ArrayList<>(1);

    public InterceptorGoogleHttpClient(String urlDomain) {
        super(urlDomain);
    }

    public void setHttpProxy(Proxy httpProxy) {
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(httpProxy).build();
        requestFactory = HTTP_TRANSPORT.createRequestFactory(requestInitializer);
    }

    @Override
    protected HttpRequest convertOctaneRequestToGoogleHttpRequest(OctaneHttpRequest octaneHttpRequest) {
        HttpRequest request = super.convertOctaneRequestToGoogleHttpRequest(octaneHttpRequest);
        requestInterceptors.forEach(requestInterceptor -> applyRequestInterceptor(requestInterceptor, request));
        return request;
    }

    @Override
    protected OctaneHttpResponse convertHttpResponseToOctaneHttpResponse(HttpResponse httpResponse) throws IOException {
        responseInterceptors.forEach(responseInterceptor -> applyResponseInterceptor(responseInterceptor, httpResponse));
        return super.convertHttpResponseToOctaneHttpResponse(httpResponse);
    }

    private static void applyRequestInterceptor(RequestInterceptor requestInterceptor, HttpRequest httpRequest){

        String newUrl = requestInterceptor.url(httpRequest.getUrl().toString());
        httpRequest.setUrl(new GenericUrl(newUrl));

        if(httpRequest.getContent() != null && !(httpRequest.getContent() instanceof EmptyContent)){
            if(httpRequest.getContent() instanceof JsonHttpContent){
                JsonHttpContent jsonContent = (JsonHttpContent) httpRequest.getContent();
                requestInterceptor.content(jsonContent.getData().toString());
            }
            if(httpRequest.getContent() instanceof ByteArrayContent){
                ByteArrayContent byteArrayContent = (ByteArrayContent) httpRequest.getContent();
                try {
                    String byteArrayContentString = CharStreams.toString(new InputStreamReader(byteArrayContent.getInputStream(), StandardCharsets.UTF_8));
                    requestInterceptor.content(byteArrayContentString);
                } catch (IOException e) {
                    logger.error("Failed to call request interceptor content method, error while reading ByteArrayContent", e);
                }
            }
            //TODO actually change the content
        }

        //TODO: headers

        logger.debug("Applying requestInterceptor " + requestInterceptors.indexOf(requestInterceptor) + " of " + requestInterceptors.size());
    }

    private static void applyResponseInterceptor(ResponseInterceptor responseInterceptor, HttpResponse httpResponse){
        //TODO, implement this thing
        logger.debug("Applying responseInterceptor " + responseInterceptors.indexOf(responseInterceptor) + " of " + responseInterceptors.size());
    }

    public static boolean addRequestInterceptor(RequestInterceptor requestInterceptor) {
        return requestInterceptors.add(requestInterceptor);
    }
    public static boolean removeRequestInterceptor(RequestInterceptor requestInterceptor) {
        return requestInterceptors.remove(requestInterceptor);
    }
    public static boolean addResponseInterceptor(ResponseInterceptor responseInterceptor) {
        return responseInterceptors.add(responseInterceptor);
    }
    public static boolean removeResponseInterceptor(ResponseInterceptor responseInterceptor) {
        return responseInterceptors.add(responseInterceptor);
    }

}