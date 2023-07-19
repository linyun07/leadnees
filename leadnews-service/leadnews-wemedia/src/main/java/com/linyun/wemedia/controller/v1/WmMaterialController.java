package com.linyun.wemedia.controller.v1;

import com.linyun.model.dtos.PageRequestDto;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmMaterialDto;
import com.linyun.wemedia.service.WmMaterialService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author linyun
 * @since 2023/7/19 11:06
 */

@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {
    @Resource
    private WmMaterialService materialService;
    /**
     * 分页查询
     *
     * @param dto 存储页码:page，页面条数:size 和是否收藏 1：表示收藏 0：表示未收藏
     * @return
     */
    @PostMapping("list")
    public ResponseResult page(@RequestBody WmMaterialDto dto) {
        return materialService.page(dto);
    }

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {

        return materialService.uploadPicture(multipartFile);
    }
    /**
     * 按图片Id取消收藏
     * @param id
     * @return
     */
    @GetMapping("/cancel_collect/{id}")
    public ResponseResult cancelCollect(@PathVariable Integer id){
        return materialService.cancelCollect(id);
    }
    /**
     * 按Id收藏图片
     * @param id
     * @return
     */
    @GetMapping("/collect/{id}")
    public ResponseResult collect(@PathVariable Integer id){
        return materialService.collect(id);
    }
}
