package com.hpe.adm.nga.sdk.network;

import java.util.Map;

public interface RequestInterceptor {
    String url(String url);
    String content(String content);
    Map<String, Object> headers(Map<String, Object> headers);
}