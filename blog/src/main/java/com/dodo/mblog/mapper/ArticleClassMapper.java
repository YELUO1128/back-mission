package com.dodo.mblog.mapper;

import com.dodo.mblog.entity.ArticleClass;

import java.util.List;
import java.util.Map;




public interface ArticleClassMapper {

    public ArticleClass findById(Integer classid);
    public ArticleClass findByName(String classname);

    public List<Map> findArticleClasses();
}
