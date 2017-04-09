package com.hpe.adm.nga.sdk.network;

import java.util.Map;

public interface RequestInterceptor {
    default String url(String url){
        return url;
    }
    default String content(String content){ return content; }
    default Map<String, Object> headers(Map<String, Object> headers){
        return headers;
    }
}