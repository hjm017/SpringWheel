package com.springwheel.common.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * @author hjm
 * @Time 2016/5/1 20:45.
 */
public class HttpClientUtil {

    public static String post(String url, String json) throws Exception {
        HttpClient httpsClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Accept", "application/json");
        post.setEntity(new StringEntity(json, Charset.forName("UTF-8")));

        HttpResponse response = httpsClient.execute(post);
        return EntityUtils.toString(response.getEntity());

    }

}
