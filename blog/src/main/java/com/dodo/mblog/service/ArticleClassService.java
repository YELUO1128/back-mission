package com.dodo.mblog.service;

import com.dodo.mblog.entity.ArticleClass;
import com.dodo.mblog.mapper.ArticleClassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/5/24 18:27
 * @Description:
 */
@Service
public class ArticleClassService {

    @Resource
    private ArticleClassMapper articleClassMapper;

    public ArticleClass findById(Integer classid){
        return articleClassMapper.findById(classid);
    }

    public ArticleClass findByName(String classname){
        return articleClassMapper.findByName(classname);
    }

    public List<Map> findArticleClasses(){
        return articleClassMapper.findArticleClasses();
    }
}
