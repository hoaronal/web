package com.app.vn.service.category;

import com.app.vn.common.client.ApiTemplate;
import com.app.vn.common.entity.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ApiTemplate apiTemplate;

    @Override
    public List<Category> getAll() throws IOException {
        String response = apiTemplate.get("/private/category/get");
        ObjectMapper mapper = new ObjectMapper();
        if(response != null){
            List<Category> categoryList = new Category().toListObject(response);
            return categoryList;
        }
        return null;
    }
}
