package com.app.vn.web.controller.guest;

import com.app.vn.service.mail.EmailService;
import com.app.vn.service.user.UserService;
import com.app.vn.web.form.UserCreateForm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;



    /*@GetMapping(value = "/register", params = "view")
    public String register(Model model){
        UserCreateForm form = new UserCreateForm();
        model.addAttribute("isSuccess",false);
        return "guest/sign-up";
    }*/

    /*@PostMapping(value = "/register")
    @ResponseBody
    public String doPost(@Valid UserCreateForm user, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                List<ObjectError> allErrors = bindingResult.getAllErrors();
                List<FieldError> fieldErrors = bindingResult.getFieldErrors();
                for (FieldError fieldError : fieldErrors) {
                    mapResult.put(fieldError.getField(),fieldError.getDefaultMessage());
                    System.out.println(fieldError.getField());
                    System.out.println(fieldError.getDefaultMessage());
                }
                model.addAttribute("user", user);
                model.addAttribute("mapResult", mapResult);
                return "guest/sign-up";
            }
            userService.add(user.convertToUser());
            model.addAttribute("","");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "guest/index";
    }*/
    @GetMapping(value = "/register", params = "view")
    public String register(@Valid UserCreateForm post, Model model) {
        model.addAttribute("isSuccess",false);
        return "guest/sign-up";
    }

    @PostMapping(value = "/register")
    public String doPost(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, Model model,final Locale locale) {
        try {
            if (bindingResult.hasErrors()) {
                //bindingResult.rejectValue("reLoginPassword","password.equal");
                model.addAttribute("isSuccess",false);
                return "guest/sign-up";
            }
            String result = userService.add(userCreateForm.convertToUser());
            emailService.sendSimpleMail(userCreateForm.getEmail(), userCreateForm.getEmail(), locale);
            model.addAttribute("isSuccess",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "guest/sign-up";
    }

    @RequestMapping(value = "/sendMailWithInlineImage", method = RequestMethod.POST)
    public String sendMailWithInline(
            @RequestParam("recipientName") final String recipientName,
            @RequestParam(value = "recipientEmail") final String recipientEmail,
            @RequestParam("image") final MultipartFile image,
            final Locale locale)
            throws MessagingException, IOException {


        return "redirect:sent.html";

    }
}
