package com.app.vn.service.post;

import com.app.vn.common.client.ApiTemplate;
import com.app.vn.common.constant.Constant;
import com.app.vn.common.entity.PaggingResult;
import com.app.vn.common.entity.Post;
import com.app.vn.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service("postService")
public class PostServiceImpl implements PostService {
    @Autowired
    private ApiTemplate apiTemplate;

    @Override
    public String add(Post post) {
        try {
            String response = apiTemplate.post(Constant.URL_REMOTE.URL_ADD_POST,new Post().generateJsonObject(post));
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
        String response = apiTemplate.get("/private/post/get?pageNumber="+pageNumber+"&pageSize="+pageSize);
        if(response != null){
            return (PaggingResult) new Post().toObject(response);
        }
        return null;
    }

    @Override
    public Post getDetail(long id) {
        String response = apiTemplate.get("/private/post/get-detail?id=" + id);
        if(response != null){
            return (Post) new Post().convertJson(response);
        }
        return null;
    }

    @Override
    public Post getDetailByCode(String code) {
        String response = apiTemplate.get("/private/post/get-detail-by-code?code=" + code);
        if(response != null){
            return (Post) new Post().convertJson(response);
        }
        return null;
    }

    @Override
    public Post getDetailNextOrPrev(String input, long id) {
        String response = apiTemplate.get("/private/post/get-detail-by-next-or-prev?input=" + input + "&id=" + id);
        if(response != null){
            return (Post) new Post().convertJson(response);
        }
        return null;
    }
}
