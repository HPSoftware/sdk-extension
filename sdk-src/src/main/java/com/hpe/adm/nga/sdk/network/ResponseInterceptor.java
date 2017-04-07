package com.hpe.adm.nga.sdk.network;

public interface ResponseInterceptor {
    Integer headers(Integer port);
    String content(String path);
}