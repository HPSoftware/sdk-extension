package com.hpe.adm.nga.sdk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by savencu on 2/27/2017.
 */
public class StringQuery extends Query {

    public StringQuery(String s) {
        try {
            super.queryString = URLEncoder.encode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
