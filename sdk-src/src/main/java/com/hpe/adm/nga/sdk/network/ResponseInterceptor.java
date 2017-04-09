package com.hpe.adm.nga.sdk.network;

import java.util.Map;

/**
 * Default implementation, useful if you want to override just one of the methods
 */
public interface ResponseInterceptor {
    String content(String content);
    Map<String, Object> headers(Map<String, Object> headers);
}