package com.dodo.mblog;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan("com.dodo.mblog.mapper")
public class MblogApplication {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druid() {
        return new DruidDataSource();
    }


    // 用户显示druid后台界面
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<>();
        map.put("loginUserName", "admin");
        map.put("loginPassword", "123456");
        map.put("allow", "*");
        // map.put("deny","157.45.78.14") 不允许此ip访问
        bean.setInitParameters(map);
        return bean;

    }

    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new WebStatFilter());
        filter.addUrlPatterns("/*");
        Map<String, String> param = new HashMap<>();
        param.put("exclusions", "*.png,*.woff,*.js,*.css,/druid/*");
        filter.setInitParameters(param);
        return filter;
    }

    //解决配置文件占位符问题
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

    public static void main(String[] args) {
        SpringApplication.run(MblogApplication.class, args);
    }
}
