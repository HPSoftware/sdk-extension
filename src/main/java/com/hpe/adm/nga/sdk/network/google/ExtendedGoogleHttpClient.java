package com.hpe.adm.nga.sdk.network.google;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.net.Proxy;

/**
 * Created by savencu on 3/1/2017.
 */
public class ExtendedGoogleHttpClient extends GoogleHttpClient {
    private final String clientTypeHeader;
    /**
     * Creates an HTTP client instance using the url and authentication.
     *
     * @param urlDomain        The source URL of the Octane server
     * @param clientTypeHeader The special client type that can be sent to the API
     */
    public ExtendedGoogleHttpClient(String urlDomain, String clientTypeHeader) {
        super(urlDomain, clientTypeHeader);
        this.clientTypeHeader=clientTypeHeader;
    }

    public void setProxyForHttpClient(Proxy proxy){
        HttpTransport HTTP_TRANSPORT = new NetHttpTransport.Builder().setProxy(proxy).build();
        super.requestFactory = HTTP_TRANSPORT
                .createRequestFactory(request -> {
                    request.setResponseInterceptor(response -> {
                        // retrieve new LWSSO in response if any
                        HttpHeaders responseHeaders = response.getHeaders();
                        super.updateLWSSOCookieValue(responseHeaders);
                    });
                    request.setUnsuccessfulResponseHandler((httpRequest, httpResponse, b) -> false);

                    if (lwssoValue != null && !lwssoValue.isEmpty()) {
                        request.getHeaders().setCookie(LWSSO_COOKIE_KEY + "=" + lwssoValue);
                    }

                    if (clientTypeHeader != null && !clientTypeHeader.isEmpty()) {
                        request.getHeaders().set(HPE_CLIENT_TYPE, clientTypeHeader);
                    }

                    request.setReadTimeout(60000);
                });


    }
}
