package com.app.vn.common.utils;

import java.net.URI;

import com.app.vn.common.entity.User;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class RestClientUtil {
    private HttpHeaders getHeaders() {
        String credential="mukesh:m123";
        //String credential="tarun:t123";
        String encodedCredential = new String(Base64.encodeBase64(credential.getBytes()));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + encodedCredential);
        return headers;
    }
    public void getUserByIdDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/User/{id}";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User.class, 1);
        User User = responseEntity.getBody();
    }
    public void getAllUsersDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/Users";
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<User[]> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, User[].class);
        User[] Users = responseEntity.getBody();
        for(User User : Users) {
           /* System.out.println("Id:"+User.getUserId()+", Title:"+User.getTitle()
                    +", Category: "+User.getCategory());*/
        }
    }
    public void addUserDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/User";
        User objUser = new User();
        /*objUser.setTitle("Spring REST Security using Hibernate");
        objUser.setCategory("Spring");*/
        HttpEntity<User> requestEntity = new HttpEntity<User>(objUser, headers);
        URI uri = restTemplate.postForLocation(url, requestEntity);
        System.out.println(uri.getPath());
    }
    public void updateUserDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/User";
        User objUser = new User();
       /* objUser.setUserId(1);
        objUser.setTitle("Update:Java Concurrency");
        objUser.setCategory("Java");*/
        HttpEntity<User> requestEntity = new HttpEntity<User>(objUser, headers);
        restTemplate.put(url, requestEntity);
    }
    public void deleteUserDemo() {
        HttpHeaders headers = getHeaders();
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/User/{id}";
        HttpEntity<User> requestEntity = new HttpEntity<User>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, 4);
    }
    public static void main(String args[]) {
        RestClientUtil util = new RestClientUtil();
        //util.getUserByIdDemo();
        util.getAllUsersDemo();
        //util.addUserDemo();
        util.updateUserDemo();
        //util.deleteUserDemo();
    }
}