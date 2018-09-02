package com.app.vn.service.user;

import com.app.vn.common.entity.PaggingResult;
import com.app.vn.common.entity.User;

import java.util.List;

public interface UserService {

    public String test();

    public String add(User user);

    public PaggingResult getList(int pageNumber, int pageSize);
}
