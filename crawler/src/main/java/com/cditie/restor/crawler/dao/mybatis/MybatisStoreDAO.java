package com.cditie.restor.crawler.dao.mybatis;

import com.cditie.restor.crawler.model.BlogContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author 汪浩淼 et.tw@163.com
 * @since 2016/7/27.
 */
public interface MybatisStoreDAO {

    @Insert("insert into blog (title,content,image,update_time) values (#{blog.title},#{blog.content},#{blog.image},now())")
    @Options(useGeneratedKeys = true, keyProperty = "blog.id")
    int save(@Param("blog") BlogContent blog);

    @Select("select * from blog order by update_time desc limit 10")
    List<Map<String,Object>> getBlogContent();
}
