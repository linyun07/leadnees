package com.linyun.wemedia.controller.v1;

import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmNewsDto;
import com.linyun.model.wemedia.dtos.WmNewsPageReqDto;
import com.linyun.model.wemedia.pojos.WmNews;
import com.linyun.wemedia.service.WmNewsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linyun
 * @since 2023/7/19 15:44
 */

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {
    @Resource
    private WmNewsService wmNewsService;

    /**
     * 分页查询
     * @param dto
     * @return
     */
    @PostMapping("/list")
    public ResponseResult page(@RequestBody WmNewsPageReqDto dto) {
        return wmNewsService.page(dto);
    }

    /**
     * 发布 文章
     * @param dto
     * @return
     */
    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return wmNewsService.submitNews(dto);
    }
}
