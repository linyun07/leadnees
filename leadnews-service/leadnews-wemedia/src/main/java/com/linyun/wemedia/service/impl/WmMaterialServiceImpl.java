package com.linyun.wemedia.service.impl;

import com.linyun.file.service.FileStorageService;
import com.linyun.model.dtos.PageRequestDto;
import com.linyun.model.dtos.PageResponseResult;
import com.linyun.model.dtos.ResponseResult;
import com.linyun.model.enums.AppHttpCodeEnum;
import com.linyun.model.wemedia.dtos.WmMaterialDto;
import com.linyun.model.wemedia.pojos.WmMaterial;
import com.linyun.wemedia.mapper.WmMaterialMapper;
import com.linyun.wemedia.service.WmMaterialService;
import com.linyun.wemedia.thread.WmThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author linyun
 * @since 2023/7/19 11:10
 */

@Slf4j
@Service
public class WmMaterialServiceImpl implements WmMaterialService {
    @Resource
    private WmMaterialMapper wmMaterialMapper;

    @Resource
    private FileStorageService fileStorageService;


    @Override
    public ResponseResult page(WmMaterialDto dto) {
        //检查分页信息
        dto.checkParam();
        //分页查询
        List<WmMaterial> wmMaterials = wmMaterialMapper.listAllByUserIdAndIsCollection(dto);
        //获取总数
        int count = wmMaterialMapper.getCountByUserIdAndIsCollection(dto);
        //装填数据返回
        ResponseResult result = new PageResponseResult(dto.getPage(), dto.getSize(), count);
        result.setData(wmMaterials);
        return result;
    }

    @Transactional
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //1.检查参数
        if (multipartFile == null || multipartFile.getSize() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //将图片上传到MinIO
        String fileName = UUID.randomUUID().toString().replace("-", "");
        String originalFilename = multipartFile.getOriginalFilename();
        String postFix = originalFilename.substring(originalFilename.lastIndexOf("."));
        System.err.println(postFix);
        String path = null;
        try {
            path = fileStorageService.uploadHtmlFile("", fileName + postFix, multipartFile.getInputStream());
            log.info("上传图片到MinIo中成功" + path);
        } catch (IOException e) {
            log.error("上传图片到MinIo中失败");
            throw new RuntimeException(e);
        }
        //保存到数据库中
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setUserId(WmThreadLocalUtil.getUser().getId());
        wmMaterial.setUrl(path);
        wmMaterial.setIsCollection((short) 0);
        wmMaterial.setType((short) 0);
        wmMaterial.setCreatedTime(new Date());
        wmMaterialMapper.insertAll(wmMaterial);
        return ResponseResult.okResult(wmMaterial);
    }

    @Override
    public ResponseResult cancelCollect(Integer id) {
        return setOrCancelCollect(id, 0);
    }

    @Override
    public ResponseResult collect(Integer id) {
        return setOrCancelCollect(id, 1);
    }

    /**
     * 根据isCollection设置收藏还是取消收藏
     * @param id
     * @param isCollection
     * @return
     */
    private ResponseResult setOrCancelCollect(Integer id, int isCollection) {
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setId(id);
        wmMaterial.setIsCollection((short) isCollection);
        int i = wmMaterialMapper.updateAll(wmMaterial);
        if (!(i > 0)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
