package com.linyun.article.controller.v1;

import com.linyun.article.service.ArticleService;
import com.linyun.common.constants.ArticleConstants;
import com.linyun.model.article.dtos.ArticleHomeDto;
import com.linyun.model.dtos.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.linyun.common.constants.ArticleConstants.LOADTYPE_LOAD_MORE;
import static com.linyun.common.constants.ArticleConstants.LOADTYPE_LOAD_NEW;

/**
 * @author linyun
 * @since 2023/7/14 10:38
 */

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**加载首页
     * @param articleHomeDto
     * @return
     */
    @PostMapping("load")
    public ResponseResult load(@RequestBody ArticleHomeDto articleHomeDto) {
        return articleService.load(articleHomeDto, LOADTYPE_LOAD_MORE);
    }

    /**往下滑 加载更多
     * @param articleHomeDto
     * @return
     */
    @PostMapping("loadMore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto articleHomeDto) {
        return articleService.load(articleHomeDto, LOADTYPE_LOAD_MORE);
    }

    /** 刷新 加载最新
     * @param articleHomeDto
     * @return
     */
    @PostMapping("loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto articleHomeDto) {
        return articleService.load(articleHomeDto, LOADTYPE_LOAD_NEW);
    }

}
