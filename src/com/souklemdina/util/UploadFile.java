/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.souklemdina.util;

import java.io.File;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author rkader
 */
public class UploadFile {

    public static String uploadImage(String filePath, String oldName) throws Exception {

//        String filePath = System.getProperty("user.home") + "/Desktop/";
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
        HttpPost httppost;
        if (oldName != null) {
            httppost = new HttpPost("http://localhost/SoukLemdina/uploadImages.php?old=" + oldName);
        } else {
            httppost = new HttpPost("http://localhost/SoukLemdina/uploadImages.php");
        }
        File file = new File(filePath);
        MultipartEntity mpEntity = new MultipartEntity();
        ContentBody contentFile = new FileBody(file);
        mpEntity.addPart("userfile", contentFile);
        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine());
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity resEntity = response.getEntity();

        if ((response.getStatusLine().toString()).equals("HTTP/1.1 200 OK")) {
            System.out.println("Successful Upload!");
        } else {
            System.err.println("Didn't Upload!");
        }
        String newName = null;
        System.out.println(response.getStatusLine());
        if (resEntity != null) {
            newName = EntityUtils.toString(resEntity);
            //System.out.println(EntityUtils.toString(resEntity));
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }
        httpclient.getConnectionManager().shutdown();
        return newName;
    }

}
