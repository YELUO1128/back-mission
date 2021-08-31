package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.Blog;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/5/24 13:31
 * @Description:
 */

public interface BlogMapper {

    public Blog findById(Integer id);
    public List<Blog> findBlogList(String author);
    public List<Blog> findAllBlog();
    public void insertBlog(Blog blog);
    public void update(Blog blog);
    public void delete(Integer id);
}
