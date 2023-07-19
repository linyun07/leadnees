package com.linyun.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linyun.model.article.pojo.ApArticleContent;

/**
 * @author linyun
 * @since 2023/7/18 17:34
 */


public interface ApArticleContentMapper extends BaseMapper<ApArticleContent> {

    /**
     * 根据articleId查询文章内容
     *
     * @param articleId
     * @return
     */
    ApArticleContent getArticleContentByArticleId(Long articleId);
}
