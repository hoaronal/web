package com.app.vn.web.controller.admin.post;


import com.app.vn.common.entity.Post;
import com.app.vn.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin_")
public class PostCreateController {
    @Autowired
    private PostService postService;
    @PostMapping("post-create")
    public String newPost(@RequestParam String body, @RequestParam String title){

        Post post = new Post();
        post.setTitle(title);
        post.setStatus("");
        post.setBody(body);
        postService.add(post);
        return "admin/post/new";
    }

}
