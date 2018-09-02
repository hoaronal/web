package com.app.vn.service.post;

import com.app.vn.common.entity.PaggingResult;
import com.app.vn.common.entity.Post;

public interface PostService {
    public String add(Post post);

    public PaggingResult getList(int pageNumber, int pageSize);

    public Post getDetail(long id);

    public Post getDetailByCode(String code);

    public Post getDetailNextOrPrev(String input,long id);
}
