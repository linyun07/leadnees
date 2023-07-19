package com.linyun.wemedia.service;

import com.linyun.model.dtos.PageRequestDto;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.wemedia.dtos.WmMaterialDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author linyun
 * @since 2023/7/19 11:10
 */


public interface WmMaterialService {


    /**
     * 图片上传
     *
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     *分页查询
     * @param wmMaterialDto 存储页码:page，页面条数:size 和是否收藏 1：表示收藏 0：表示未收藏
     * @return
     */
    ResponseResult page(WmMaterialDto wmMaterialDto);

    /**
     * 按图片Id取消收藏
     * @param id
     * @return
     */
    ResponseResult cancelCollect(Integer id);

    /**
     * 按Id收藏图片
     * @param id
     * @return
     */
    ResponseResult collect(Integer id);
}
