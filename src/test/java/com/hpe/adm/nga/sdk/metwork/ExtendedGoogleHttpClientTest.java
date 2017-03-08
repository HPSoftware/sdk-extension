package com.hpe.adm.nga.sdk.metwork;

import com.hpe.adm.nga.sdk.*;
import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by savencu on 3/1/2017.
 */
public class ExtendedGoogleHttpClientTest {
    private Octane octane;
    private String PROXY_HOST="web-proxy.eu.hpecorp.net";
    private int PROXY_PORT=8080;

    @Before
    public void initOctane() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, PROXY_PORT));
        octane = TestUtil.createContextWithProxy(proxy);
    }
    @Test
    public void testConnectionWithProxy(){
        final Query.QueryBuilder idQueryBuilder = Query.statement("id", QueryMethod.EqualTo, 1020);
        octane.entityList("defects").get().query(idQueryBuilder.build()).execute();

    }
}
