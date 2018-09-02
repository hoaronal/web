package com.app.vn.web.controller.guest;

import com.app.vn.common.entity.Category;
import com.app.vn.common.entity.PaggingResult;
import com.app.vn.common.entity.Post;
import com.app.vn.common.entity.User;
import com.app.vn.service.category.CategoryService;
import com.app.vn.service.post.PostService;
import com.app.vn.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @Inject
    private PaggingResult paggingResult;

    @GetMapping({"trang-chu","/"})
    public String dashboard(Model model, HttpServletRequest request){
        try {
            //List<Category> categoryList = categoryService.getAll();
            String page = request.getParameter("page");
            if(StringUtils.isBlank(page)){
                page = "1";
            }
            if(!StringUtils.isNumeric(page)){
                page = "1";
            }
            List<String> nameCategory = new ArrayList<>();
            nameCategory.add("C/C++");
            nameCategory.add("Java");
            nameCategory.add("C#");
            nameCategory.add("PHP");
            nameCategory.add("Python");
            nameCategory.add("Ngôn ngữ khác");
            paggingResult = postService.getList(Integer.parseInt(page), 5);
            model.addAttribute("listItem",paggingResult.getItemList());
            model.addAttribute("listCategory",nameCategory);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "guest/index";
    }

    @GetMapping("/lien-he")
    public String contact(Model model){
        return "guest/contact";
    }

    @GetMapping("/gioi-thieu")
    public String about(Model model){
        return "guest/about";
    }

    @GetMapping("sdsdsd")
    public String home(Model model){
        String a = userService.test();
        model.addAttribute("test",a);
        return "hello";
    }

    @GetMapping("test-data")
    public String hello(Model model){
        try {
            String listData = userService.test();
            model.addAttribute("test",listData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "hello";
    }

    @GetMapping("tro-choi")
    public String game(Model model){
        return "guest/game";
    }

    @GetMapping("bai-viet/{code}")
    public String newPost(@PathVariable("code") String code, Model model, HttpServletRequest request){
        try {
            Post post = null;
            if(!StringUtils.isBlank(request.getParameter("next"))){
                post = postService.getDetailNextOrPrev("next", Long.parseLong(request.getParameter("id")));
            }else if(!StringUtils.isBlank(request.getParameter("prev"))){
                post = postService.getDetailNextOrPrev("prev", Long.parseLong(request.getParameter("id")));
            }else{
                post = postService.getDetailByCode(code);
            }
            model.addAttribute("item",post);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "guest/post/detail";
    }

    @GetMapping("chat-box")
    public String chatbox(Model model){
        return "index";
    }

    @GetMapping("chat")
    public String chat(Model model){
        return "chat";
    }
}
