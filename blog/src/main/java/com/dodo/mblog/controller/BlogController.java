package com.dodo.mblog.controller;

import com.dodo.mblog.common.WebSecurityConfig;
import com.dodo.mblog.entity.ArticleClass;
import com.dodo.mblog.entity.Blog;
import com.dodo.mblog.entity.Message;
import com.dodo.mblog.service.ArticleClassService;
import com.dodo.mblog.service.BlogService;
import com.dodo.mblog.service.MessageService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/5/24 11:37
 * @Description:
 */


@Controller
// @RequestMapping("/myblog")
public class BlogController {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Resource
    private BlogService blogService;
    @Resource
    private ArticleClassService articleClassService;
    @Resource
    private MessageService messageService;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        // 从数据库获取blog列表
        String author = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        List<Blog> blogList = blogService.findAllBlog();

        // 将blog列表返回view
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("bloglist", blogList);
        return mav;
    }


    @GetMapping("/blog/{id}")
    public ModelAndView viewBlog(@PathVariable("id") String id) {
        Blog blog = blogService.findBlogById(Integer.parseInt(id));

        ArticleClass articleClass =articleClassService.findByName(blog.getClassname());
        ModelAndView mav = new ModelAndView("post");
        mav.addObject("articleClass",articleClass);
        mav.addObject("blog", blog);
        return mav;
    }


    @GetMapping("/ac")
    @ResponseBody
    public List<Map> getArticleClass(){
        return articleClassService.findArticleClasses();
    }


    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/send_msg")
    @ResponseBody
    public Message sendMsg(@Validated Message message){
        message.setSendtime(new Date());
        messageService.insertMsg(message);

        return message;

    }


    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/test")
    public String test(){
        return "test/index";
    }

}
