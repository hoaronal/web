package com.app.vn.web.controller.admin;

import com.app.vn.common.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin_")
public class DashboardController extends BaseController{

    @GetMapping
    public String dashboard(Model model){
        return "admin/index";
    }
}
