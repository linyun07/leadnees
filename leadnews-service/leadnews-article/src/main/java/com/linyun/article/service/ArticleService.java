package com.linyun.article.service;

import com.linyun.model.article.dtos.ArticleHomeDto;
import com.linyun.model.dtos.ResponseResult;

/**
 * @author linyun
 * @since 2023/7/14 10:38
 */


public interface ArticleService {


    /** 加载文章列表
     * @param articleHomeDto
     * @param type           1-->下滑：加载更多  2-->刷新：加载最新
     * @return
     */
    public ResponseResult load(ArticleHomeDto articleHomeDto,Short type) ;
}
