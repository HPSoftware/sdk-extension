package com.hpe.adm.nga.sdk.network;

public interface RequestInterceptor {
    String url(String host);
    Integer headers(Integer port);
    String content(String path);
}