package com.linyun.article.service.impl;

import com.aliyuncs.utils.StringUtils;
import com.linyun.article.mapper.ArticleMapper;
import com.linyun.article.service.ArticleService;
import com.linyun.common.constants.ArticleConstants;
import com.linyun.model.article.dtos.ArticleHomeDto;
import com.linyun.model.article.pojo.ApArticle;
import com.linyun.model.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author linyun
 * @since 2023/7/14 10:39
 */


@Slf4j
@Service

public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    public static final short MAX_PAGE_SIZE = 66;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short type) {
        //校验参数
        Integer size = dto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        //设置分页值最大不超过50
        Math.min(size, MAX_PAGE_SIZE);
        //判断type是在合理范围
        if (!type.equals(ArticleConstants.LOADTYPE_LOAD_MORE) && !type.equals(ArticleConstants.LOADTYPE_LOAD_NEW)) {
            type = 1;
        }
        //频道判断
        if (StringUtils.isEmpty(dto.getTag())) {
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        //时间校验
        if (dto.getMaxBehatTime() == null) {
            dto.setMaxBehatTime(new Date());
        }
        if (dto.getMinBehatTime() == null) {
            dto.setMinBehatTime(new Date());
        }
        //查询数据
        List<ApArticle> apArticles = articleMapper.listLoadArticle(dto, type);
        return ResponseResult.okResult(apArticles);
    }
}
