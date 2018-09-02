package com.app.vn.web.controller.admin.user;

import com.app.vn.common.entity.PaggingResult;
import com.app.vn.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
@RequestMapping("admin_")
public class UserController {

    @Autowired
    private UserService userService;

    @Inject
    private PaggingResult paggingResult;

    @GetMapping("user/list")
    public String getList(@RequestParam(required = false,defaultValue = "1") int pageNumber,
                          @RequestParam(required = false,defaultValue = "50") int pageSize, Model model){
        paggingResult = userService.getList(pageNumber, pageSize);
        return "";
    }
}
