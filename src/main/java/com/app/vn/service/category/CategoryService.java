package com.app.vn.service.category;

import com.app.vn.common.entity.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    public List<Category> getAll() throws IOException;
}
