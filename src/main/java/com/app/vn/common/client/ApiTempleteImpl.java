package com.app.vn.common.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service("apiTemplete")
public class ApiTempleteImpl implements ApiTemplate {

    private static Logger log = LoggerFactory.getLogger(ApiTempleteImpl.class);

    @Autowired
    protected HttpServletRequest request;

    @Value("${api.url.local}")
    private String URL_LOCAL;

    @Value("${api.url.remote}")
    private String URL_REMOTE;

    @Override
    public String get(String path) {
        HttpGet httpGet = null;
        String response = "";
        try {
            CloseableHttpClient httpClient= HttpClientBuilder.create().build();
            httpGet = new HttpGet(buildFullURI(path));
            httpGet.setHeader("Authorization", "Basic " + setHeaders());
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpGet, responseHandler);
        }  catch(Exception e) {
            log.error("Co loi xay ra tai Client.getObject: " + e.getMessage()+":" + buildFullURI(path));
        } finally {
            httpGet.reset();
        }
        return response;
    }

    @Override
    public String post(String path, String jsonObject) throws IOException {
        String response = null;
        HttpPost httpPost = null;
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        try {
            httpPost = new HttpPost(buildFullURI(path));
            httpPost.addHeader(HttpHeaders.CONTENT_TYPE,"application/json;charset=UTF-8");
            httpPost.addHeader("Authorization", "Basic " + setHeaders());
            StringEntity stringEntity = new StringEntity(jsonObject, "UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(stringEntity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            response = httpClient.execute(httpPost, responseHandler);
        } catch(Exception e) {
            e.printStackTrace();
            log.error("Co loi xay ra tai post: " + e.getMessage() + ":" + buildFullURI(path) +":"+jsonObject);
        } finally {
            httpPost.reset();
            httpClient.close();
        }
        return response;
    }


    @Override
    public String put(String path) {
        return "PUT";
    }

    @Override
    public String delete(String path) {
        return "DELETE";
    }

    private String setHeaders() {
        request.getSession().setAttribute("username","hoaronal");
        request.getSession().setAttribute("password","123456");
        String username = (String) request.getSession().getAttribute("username");
        String password = (String) request.getSession().getAttribute("password");
        String credential=username+":"+password;
        String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        return encodedCredential;
    }

    public String buildFullURI(String path){
        return URL_REMOTE + path;
    }


}
