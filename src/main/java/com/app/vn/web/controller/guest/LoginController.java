package com.app.vn.web.controller.guest;


import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping(value = "/login-facebook", params = "accessToken")
    public String register(Model model, RedirectAttributes attributes, HttpServletRequest
            request, HttpServletResponse response) {
            HttpSession session = request.getSession();
            String accessToken = request.getParameter("accessToken");
            FacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_11);
            User user = facebookClient.fetchObject("me", User.class);
            com.app.vn.common.entity.User users = new com.app.vn.common.entity.User();
            users.setNickname(user.getName());
            users.setLoginId(user.getId());

            //users.setFacebooklink("https://facebook.com/"+user.getId());
            /*Users checkExits = userService.getByProperty("facebookId",user.getId());
            if(checkExits == null){
                userService.add(users);
            }*/
            //attributes.addFlashAttribute("imageLink","https://graph.facebook.com/762694757258295/picture?type=square");
            session.setAttribute("loginId", users.getNickname());
            return "redirect:/trang-chu";
        }

    @GetMapping(value = "/signout", params = "accessToken")
    public String signout(Model model, RedirectAttributes attributes, HttpServletRequest
            request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginId");
        return "redirect:/trang-chu";
    }
}
