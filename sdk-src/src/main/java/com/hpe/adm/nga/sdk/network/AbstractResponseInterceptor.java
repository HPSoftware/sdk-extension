package com.hpe.adm.nga.sdk.network;

import java.util.Map;

/**
 * Default implementation, useful if you want to override just one of the methods
 */
public class AbstractResponseInterceptor implements ResponseInterceptor{
    public String content(String content){
        return content;
    }
    public Map<String, Object> headers(Map<String, Object> headers){
        return headers;
    }
}