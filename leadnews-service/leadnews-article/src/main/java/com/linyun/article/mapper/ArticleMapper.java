package com.linyun.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linyun.model.article.dtos.ArticleHomeDto;
import com.linyun.model.article.pojo.ApArticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author linyun
 * @since 2023/7/14 10:39
 */


public interface ArticleMapper extends BaseMapper<ApArticle> {

    /**
     * 加载文章列表
     *
     * @param articleHomeDto
     * @param type           1-->下滑：加载更多  2-->刷新：加载最新
     * @return
     */
    List<ApArticle> listLoadArticle(@Param("dto") ArticleHomeDto articleHomeDto, @Param("type") short type);

    /**
     * 根据Id修改staticUrl
     * @param id
     * @param staticUrl
     * @return
     */
    int updateStaticUrlById(@Param("id") Long id, @Param("staticUrl") String staticUrl);
}
