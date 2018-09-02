package com.app.vn.service.user;

import com.app.vn.common.client.ApiTemplate;
import com.app.vn.common.entity.PaggingResult;
import com.app.vn.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private ApiTemplate apiTemplate;

    public String test(){
        String a = apiTemplate.get("/private/get");
        return a ;
    }

    @Override
    public String add(User user) {
        try {
            String response = apiTemplate.post("/private/user/add",new User().generateJsonObject(user));
            if(response != null){
                return response;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PaggingResult getList(int pageNumber, int pageSize) {
        String response = apiTemplate.get("/private/get?pageNumber="+pageNumber+"&pageSize="+pageNumber);
        if(response != null){
            return (PaggingResult) new User().toObject(response);
        }
        return null;
    }
}
