package com.dodo.mblog.common;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @Auther: Administrator

 * @Description: session验证拦截器
 */

@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter {
    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    /**
     * 注册拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        List<String> patterns = new ArrayList<String>();
        patterns.add("/");
        patterns.add("/login");
        patterns.add("/regist");
        patterns.add("/about");
        patterns.add("/contact");
        patterns.add("/blog**");
        addInterceptor.excludePathPatterns(patterns);

        // 拦截配置
        addInterceptor.addPathPatterns("/admin/**");
    }

    /**
     * 拦截器
     */
    private class SecurityInterceptor extends HandlerInterceptorAdapter {



        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute(SESSION_KEY) != null)  // 当session不为空时，进入下一步
                return true;

            // 跳转登录
            String url = "/login";
            response.sendRedirect(url);
            return false;
        }
    }
}


