package com.dodo.mblog.service;

import com.dodo.mblog.entity.Blog;
import com.dodo.mblog.mapper.BlogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.ExceptionListener;
import java.util.List;



@Service
public class BlogService {

    @Resource
    private BlogMapper blogMapper;

    public List<Blog> findAllBlog(){
        return blogMapper.findAllBlog();
    }

    public List<Blog> findBlogList(String author){
        return blogMapper.findBlogList(author);
    }

    public Blog findBlogById(Integer id){
        return blogMapper.findById(id);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void insertBlog(Blog blog){
        blogMapper.insertBlog(blog);
    }

    @Transactional(noRollbackFor = Exception.class)
    public void updateBlog(Blog blog) {
        blogMapper.update(blog);

    }

    @Transactional(noRollbackFor = Exception.class)
    public void deleteBlog(Integer id) {
        blogMapper.delete(id);
    }
}
