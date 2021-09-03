package com.dodo.mblog.controller;

import com.dodo.mblog.common.ShaEncrypt;
import com.dodo.mblog.common.WebSecurityConfig;
import com.dodo.mblog.entity.User;
import com.dodo.mblog.entity.UserDetails;

import com.dodo.mblog.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * @Auther: Administrator

 * @Description:
 */

@Controller
public class UserController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;


    @GetMapping("/login")
    public ModelAndView login() {
        // logger.error(UUID.randomUUID().toString().replaceAll("-", ""));
        ModelAndView mav = new ModelAndView("vali/login");
        return mav;
    }

    @PostMapping("/user_login")
    public ModelAndView loginIn(@Validated User user, BindingResult result, HttpSession session) throws Exception {
        ModelAndView modelAndView;
        Map<String, String> msgMap = new HashMap<>();

        if (result.hasErrors()) {
            logger.error("error : " + result.getFieldError().getDefaultMessage());
            modelAndView = new ModelAndView("redirect:/login");
            msgMap.put("10003", result.getFieldError().getDefaultMessage());
            modelAndView.addObject("msg", msgMap);
            return modelAndView;
        }

        User u = userService.findUserByEmail(user.getEmail());
        if (ShaEncrypt.encryptSHA(user.getPwd()).equals(u.getPwd())) {
            modelAndView = new ModelAndView("vali/index");
            // 设置session
            UserDetails userDetails = userService.findByEmail(user.getEmail());
            session.setAttribute(WebSecurityConfig.SESSION_KEY, userDetails.getUsername());
        } else {
            modelAndView = new ModelAndView("vali/login");
            msgMap.put("10001", "用户名或者密码错误！");
            logger.error("10001,用户名或者密码错误！");
            //msgMap.put("10002","密码长度不能低于6位！");
            modelAndView.addObject("msg", msgMap);
        }

        return modelAndView;
    }

    @GetMapping("/regist")
    public String regist() {
        return "/vali/register";
    }

    @PostMapping("/user_regist")
    public String addUser(@Validated User user, BindingResult result, HttpSession session) throws Exception {

        // 对密码进行加密
        String encryPwd = ShaEncrypt.encryptSHA(user.getPwd());
        user.setPwd(encryPwd);
        userService.addUser(user);

        UserDetails userDetails = new UserDetails();
        userDetails.setEmail(user.getEmail());
        userDetails.setUsername("user" + (int) (Math.random() * 1000));
        userDetails.setImageurl(null);
        userDetails.setAge(null);
        userDetails.setPhone(null);
        userDetails.setRegtime(new Date());
        userService.addUserDetails(userDetails);
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, userDetails.getUsername());
        return "redirect:/admin/";
    }
}
