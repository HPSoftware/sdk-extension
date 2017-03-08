package com.hpe.adm.nga.sdk;


import org.junit.Before;
import org.junit.Test;

/**
 * Created by savencu on 2/27/2017.
 */
public class StringQueryTest {
    Octane octane;

    @Before
    public void initOctane() {
        octane = TestUtil.createContext();
    }

    @Test
    public void testAlterStringQuery() {
        octane.entityList("defects").get().query(new StringQuery("id=1018")).execute();

    }
}
