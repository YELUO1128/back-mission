package com.dodo.mblog.controller;

import com.dodo.mblog.common.ErrorMessage;
import com.dodo.mblog.common.ShaEncrypt;
import com.dodo.mblog.common.WebSecurityConfig;
import com.dodo.mblog.entity.Blog;
import com.dodo.mblog.entity.Message;
import com.dodo.mblog.entity.User;
import com.dodo.mblog.entity.UserDetails;
import com.dodo.mblog.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/5/24 21:43
 * @Description:
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserDetails userDetails = null;

    @Value("${app.config.uploadPath}")
    private String uploadPath = "testdir";

    @Resource
    private BlogService blogService;
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private ArticleClassService articleClassService;
    @Resource
    private MessageService messageService;
    @Resource
    private UserService userService;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("vali/index");
        List<User> users = userService.findUserList();
        List<Blog> blogs = blogService.findAllBlog();

        mav.addObject("usercount",users.size());
        mav.addObject("blogcount",blogs.size());
        return mav;
    }


    /**
     * blog页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/blog")
    public ModelAndView blogPage(HttpSession session) {

        String author = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        List<Blog> blogList = blogService.findBlogList(author);
        ModelAndView mav = new ModelAndView("vali/blog");
        mav.addObject("blogs", blogList);
        return mav;
    }


    /**
     * 创建文章
     *
     * @return
     */
    @RequestMapping("/blog/create")
    public ModelAndView createBlog() {
        ModelAndView mav = new ModelAndView("vali/create-blog");
        mav.addObject("teststring", "test");
        return mav;
    }

    /**
     * 修改文章
     *
     * @param id
     * @return
     */
    @GetMapping("/blog/edit/{id}")
    public ModelAndView editorBlog(@PathVariable("id") Integer id) {
        Blog blog = blogService.findBlogById(id);
        ModelAndView mav = new ModelAndView("vali/edit-blog");
        mav.addObject("blog", blog);

        return mav;
    }

    @GetMapping("/blog/edit/test")
    public ModelAndView editor() {
        return new ModelAndView("vali/article-edit");
    }

    /**
     * 添加blog
     *
     * @param blog
     * @param session
     * @return
     */
    @PostMapping("/blog/add")
    public String addBlog(Blog blog, HttpSession session) {
        String username = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        logger.warn("创建者：" + username);
        blog.setAuthor(username);
        blog.setCreatetime(new Date());
        blog.setUpdatetime(new Date());
        if (blog.getIstop() == null) {
            blog.setIstop(0);
        }
        blogService.insertBlog(blog);
        return "redirect:/admin/blog";
    }

    /**
     * 更新博客
     *
     * @param blog
     * @return
     */
    @PostMapping("/blog/update")
    public String updateBlog(Blog blog) {
        Blog tmp = blogService.findBlogById(blog.getId());
        blog.setCreatetime(tmp.getCreatetime());
        blog.setUpdatetime(new Date());
        blog.setAuthor(tmp.getAuthor());
        if (blog.getIstop() == null) {
            blog.setIstop(0);
        } else {
            blog.setIstop(1);
        }
        blogService.updateBlog(blog);
        return "redirect:/admin/blog";
    }

    /**
     * 删除blog
     *
     * @param id
     * @return
     */
    @GetMapping("/blog/del/{id}")
    public String deleteBlog(@PathVariable("id") Integer id) {
        blogService.deleteBlog(id);
        return "redirect:/admin/blog";
    }

    /**
     * 登出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

    /**
     * 消息页面
     *
     * @return
     */
    @GetMapping("/msg_page")
    public ModelAndView msgView() {
        List<Message> messages = messageService.findAllMsg();
        ModelAndView mav = new ModelAndView("vali/msg-page");
        mav.addObject("messages", messages);
        return mav;
    }

    /**
     * 删除消息
     *
     * @param id
     * @return
     */
    @GetMapping("/msg/del/{id}")
    public String delMsg(@PathVariable("id") Integer id) {
        messageService.deleteById(id);
        return "redirect:/admin/msg_page";
    }

    @GetMapping("/user")
    public ModelAndView userPage(HttpSession session) {
        String username = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        UserDetails userDetails = userDetailsService.findByUserName(username);
        ModelAndView mav = new ModelAndView("vali/page-user");
        mav.addObject("user", userDetails);
        return mav;
    }

    @PostMapping("/user/update")
    public String userUpdate(UserDetails user, @RequestParam("image") MultipartFile file, HttpSession session) throws IOException {
        String username = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        //上传文件逻辑
        String imageurl = userDetailsService.saveFile(file);
        UserDetails userDetails = userDetailsService.findByUserName(username);
        user.setEmail(userDetails.getEmail());  //将不变的数据写入新的对象
        user.setImageurl(imageurl);
        userDetailsService.updateUser(user);
        // 重新设置session内的用户名值，避免页面用户名显示为旧的用户名
        session.setAttribute(WebSecurityConfig.SESSION_KEY, user.getUsername());

        return "redirect:/admin/user";
    }

    @PostMapping("/user/pwdreset")
    public ModelAndView pwdReset(String pwd, String newpwd, HttpSession session) throws Exception {
        ModelAndView mav;
        String username = (String) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        UserDetails userDetails = userDetailsService.findByUserName(username);
        User user = userService.findUserByEmail(userDetails.getEmail());
        Map<Integer, String> mapMsg;


        if (ShaEncrypt.encryptSHA(pwd).equals(user.getPwd())) {
            // 更新用户密码
            user.setPwd(ShaEncrypt.encryptSHA(newpwd));

            userService.updateUser(user);
            mapMsg = ErrorMessage.getErrorMap(ErrorMessage.RESET_PWD_SUCCUSS);
            logger.debug("--------------密码修改成功");

        } else {
            logger.error("--------------修改密码失败：原密码错误！");
            mapMsg = ErrorMessage.getErrorMap(ErrorMessage.RESET_PWD_ERROR);
        }
        mav = new ModelAndView("vali/login");
        mav.addObject("msg", mapMsg);
        return mav;

    }

    @GetMapping("user/del/{email}")
    public String delUser(@PathVariable("email") String email) {
        userDetailsService.deleteUser(email);
        userService.deleteUser(email);

        return "redirect:/admin/users";


    }

    @GetMapping("/users")
    public ModelAndView userList() {
        List<UserDetails> users = userDetailsService.getUsers();
        ModelAndView mav = new ModelAndView("vali/users-admin");
        mav.addObject("users", users);
        return mav;
    }


}
